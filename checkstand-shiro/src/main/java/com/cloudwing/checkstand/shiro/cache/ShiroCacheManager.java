package com.cloudwing.checkstand.shiro.cache;

import org.apache.shiro.cache.Cache;

/**
 * shiro cache manager 接口
 * @author yangyuantao
 *
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache();

    void destroy();
    void clear();

}

