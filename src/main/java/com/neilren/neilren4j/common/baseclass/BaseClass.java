package com.neilren.neilren4j.common.baseclass;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author NeilRen
 * @version 1.0
 * @ClassName BaseClass
 * @Description TODO
 * @Date 2018/9/18 20:48
 */
@Slf4j
@Component
public class BaseClass {
    protected static Long defaultCacheTime = 43200L;

    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 从缓存中获取对象，如果没有则运行对象获取接口放入缓存
     *
     * @param key            H
     * @param hashKey        HK
     * @param functionObject 对象获取接口
     * @param seconds        过期时间（秒）
     * @return 对象
     */
    protected Object cacheGet(String key, String hashKey, IFunctionObject functionObject, Long seconds) {
        Object object = cacheGet(key, hashKey, functionObject);
        cacheSetExpire(key, seconds);
        return object;
    }

    /**
     * 从缓存中获取对象，如果没有则运行对象获取接口放入缓存
     *
     * @param key            H
     * @param hashKey        HK
     * @param functionObject 对象获取接口
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    protected Object cacheGet(String key, String hashKey, IFunctionObject functionObject) {
        Object object = null;
        try {
            //从缓存中获取对象
            object = redisTemplate.opsForHash().get(key, hashKey);
            if (object == null) {
                //没有获取到对象，运行对象获取接口放入缓存
                object = functionObject.function();
                if (object != null && object instanceof Serializable)
                    cacheSetHash(key, hashKey, object);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return object;
    }

    @Async
    protected void cacheSetHash(String key, String hashKey, Object object) {
        if (object != null)
            redisTemplate.opsForHash().put(key, hashKey, object);
    }

    @Async
    protected void cacheSetExpire(String key, Long seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
}
