package com.hoaven.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by hehuanchun on 2016/12/20 0020.
 */
public class RedisPool {
    protected JedisPool pool = null;

    /**
     * 传入ip和端口号构建redis 连接池
     *
     * @param ip   ip
     * @param prot 端口
     */
    public RedisPool(String ip, int port) {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//            config.setMaxActive(500);
            // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(1000 * 100);
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            // pool = new JedisPool(config, "192.168.0.121", 6379, 100000);
            pool = new JedisPool(config, ip, port, 100000);
        }
    }

//    /**
//     * 通过配置对象 ip 端口 构建连接池
//     *
//     * @param config 配置对象
//     * @param ip     ip
//     * @param port   端口
//     */
//    public void getRedisPool(JedisPoolConfig config, String ip, int port) {
//        if (pool == null) {
//            pool = new JedisPool(config, ip, port, 10000);
//        }
//    }
//
//    /**
//     * 通过配置对象 ip 端口 超时时间 构建连接池
//     *
//     * @param config  配置对象
//     * @param ip      ip
//     * @param port    端口
//     * @param timeout 超时时间
//     */
//    public RedisPool(JedisPoolConfig config, String ip, int port, int timeout) {
//        if (pool == null) {
//            pool = new JedisPool(config, ip, port, timeout);
//        }
//    }
//
//    /**
//     * 通过连接池对象 构建一个连接池
//     *
//     * @param pool 连接池对象
//     */
//    public RedisPool(JedisPool pool) {
//        if (this.pool == null) {
//            this.pool = pool;
//        }
//    }

    /**
     * 返还到连接池
     *
     * @param pool
     * @param
     */
    public static void returnResource(JedisPool pool, Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }
}
