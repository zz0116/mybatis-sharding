package com.zyz.demo.sharding.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Sharding {
    String shardingKey();

    ShardingStrategy strategy() default ShardingStrategy.PRECISE;

    enum ShardingStrategy {
        PRECISE // 精准分片
        // 下面暂未实现
//        RANGE, // 范围分片
//        HASH; // 哈希分片
    }
}
