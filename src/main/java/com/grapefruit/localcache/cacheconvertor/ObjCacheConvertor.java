package com.grapefruit.localcache.cacheconvertor;

import com.grapefruit.localcache.cache.LocalCache;
import org.springframework.stereotype.Component;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
@Component("objCacheConvertor")
public class ObjCacheConvertor implements CacheConvertor {
    /**
     * 设置缓存
     *
     * @param key
     * @param obj
     */
    @Override
    public void setCache(String key, Object obj, LocalCache localCache) {
        localCache.getObjCache().put(key, key, obj);
    }
}
