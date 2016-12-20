package com.hoaven.redis;

/**
 * Created by hehuanchun on 2016/12/20 0020.
 */
public class TestRedis {
    public static void main(String[] args) {
        RedisKey redisKey = new RedisKey();
        redisKey.set("lh","lhhhhs");
        System.out.println(redisKey.get("lh"));
    }
}
