package com.hoaven.redis;

import com.hoaven.Exception.CommonException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hehuanchun on 2016/12/20 0020.
 */
public class RedisConfig {
    private static Properties configs = new Properties();

    static {
        load();
    }

    private static void load(){
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("redis.properties");
            configs.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String get(String key) {
        if (configs == null) {
            throw new CommonException("redis config no load");
        }
        String url = (String) configs.get(key);
        if (url == null) {
            load();
            url = (String) configs.get(key);
            if(url == null){
                throw new CommonException(key + " url not find");
            }
        }
        return url;
    }
}
