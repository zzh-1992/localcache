package com.grapefruit.localcache.cacheconvertor;

import com.grapefruit.localcache.cache.LocalCache;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
public interface CacheConvertor {
    /**
     * 设置缓存
     */
    void setCache(String key, Object obj, LocalCache localCache);
}
