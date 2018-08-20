package com.zyz.demo.sharding.util;

import com.zyz.demo.sharding.annotation.Sharding;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ShardingUtil {
    private final static Logger logger = Logger.getLogger(ShardingUtil.class);
    private static Map<String, List<String>> shardingTableMap;
    private static Map<String, String> shardingKeyMap;

    static {
        shardingTableMap = new HashMap<>();
        shardingKeyMap = new HashMap<>();
        try {
            PathMatchingResourcePatternResolver resourcePatternResolver
                    = new PathMatchingResourcePatternResolver();
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + ClassUtils.convertClassNameToResourcePath("com.zyz.demo.sharding") + "/**/*.class";
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
                    resourcePatternResolver);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader reader = readerFactory.getMetadataReader(resource);
                    String className = reader.getClassMetadata().getClassName();
                    Class<?> clazz = Class.forName(className);
                    // 判断是否有指定注解
                    Sharding sharding = clazz.getAnnotation(Sharding.class);
                    if (sharding != null) {
                        String logicTable = clazz.getSimpleName().toLowerCase();
                        Connection connection = DBUtil.getConnection();
                        Statement statement = connection.createStatement();
                        statement.execute("show tables like '" + logicTable + "_%'");
                        ResultSet resultSet = statement.getResultSet();
                        List<String> shardingTables = new ArrayList<>();
                        while (resultSet.next()) {
                            shardingTables.add(resultSet.getString("Tables_in_ssmdemo (" + logicTable + "_%)"));
                        }
                        DBUtil.close(connection, resultSet);
                        shardingTableMap.put(logicTable, shardingTables);
                        String shardingKey = sharding.shardingKey();
                        shardingKeyMap.put(logicTable, shardingKey);
                    }
                }
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            logger.error("读取class失败", e);
        }
    }

    public static Map<String, List<String>> getShardingTableMap() {
        return shardingTableMap;
    }

    public static Map<String, String> getShardingKeyMap() {
        return shardingKeyMap;
    }
}
