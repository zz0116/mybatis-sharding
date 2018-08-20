package com.zyz.demo.sharding.plugin;

import com.zyz.demo.sharding.util.ShardingUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.*;

@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )
})
public class ShardingPlugin implements Interceptor {
    private final static Logger logger = Logger.getLogger(ShardingPlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();

        // 拦截涉及到sharding的表的sql
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();

        // 获取sharding注解的类对应逻辑表名和实际表名
        Map<String, List<String>> shardingTableMap = ShardingUtil.getShardingTableMap();

        // sql中是否含有分表
        List<String> actualTables = new ArrayList<>();
        String logicTable = null;
        for (String key : shardingTableMap.keySet()) {
            if (sql.contains(key)) {
                logicTable = key;
                actualTables = shardingTableMap.get(logicTable);
                break;
            }
        }

        // 不是涉及到分表的sql则直接返回不处理
        if (logicTable == null) {
            return invocation.proceed();
        }

        // 获取sharding key的值
        Map<String, String> shardingKeyMap = ShardingUtil.getShardingKeyMap();
        HashMap<String, Object> parameterMap = (HashMap<String, Object>) boundSql.getParameterObject();
        Object shardingKeyValue = parameterMap.get(shardingKeyMap.get(logicTable));

        // 拼写实际表名
        String actualTable = logicTable + "_" + shardingKeyValue.toString();
        String newSql = sql.replaceAll(logicTable, actualTable);

        // 判断是否存在该分表，若不存在则新建
        if (!actualTables.contains(actualTable)) {
            if (sql.contains("insert")) {
                newSql = "create table " + actualTable + " like " + actualTables.get(0) + ";" + newSql;
            } else {
                logger.error(actualTable + "表不存在！");
            }
        }

        // 替换sql
        Field sqlField = boundSql.getClass().getDeclaredField("sql");
        sqlField.setAccessible(true);
        sqlField.set(boundSql, newSql);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
