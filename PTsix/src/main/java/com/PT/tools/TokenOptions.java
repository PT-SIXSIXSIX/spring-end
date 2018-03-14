package com.PT.tools;


import com.google.common.cache.CacheLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.cache.LoadingCache;

/**
 * created by yxhuang
 * token操作类
 */
import java.util.concurrent.TimeUnit;
public class TokenOptions {
    private static Logger logger = LoggerFactory.getLogger(TokenOptions.class);

    private static int INITNUMBER = 1000;
    private static int MAXIMUMSIZE = 10000;
    private static int MINUTES = 1200;
    public static final String TOKEN_PREFIX = "token_is_";
    //LRU
    /**
     * 生成localCache，初始化大小INITNUMBER，最多支持MAXIMUMSIZE，MINUTES分钟没有读则失效
     */
    private static LoadingCache<String,String> localCache =
            com.google.common.cache.CacheBuilder.newBuilder().initialCapacity(INITNUMBER).
                    maximumSize(MAXIMUMSIZE).expireAfterAccess(MINUTES, TimeUnit.MINUTES).
                    build(new CacheLoader<String, String>() {
        @Override
        public String load(String k1) throws Exception {
            return "null";
        }
    });

    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(value)){
                return null;
            }
            return value;
        }catch (Exception e){
            logger.error("localCache get error",e);
        }
        return null;
    }
}
