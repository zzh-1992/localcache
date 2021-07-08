package com.grapefruit.localcache.cacheconvertor;

import com.grapefruit.localcache.cache.LocalCache;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
@Component("listConvertor")
public class ListCacheConvertor implements CacheConvertor {
    /**
     * 设置缓存
     *
     * @param key
     * @param obj
     */
    @Override
    public void setCache(String key, Object obj, LocalCache localCache) {
        localCache.getListCache().put(key, key, (List) obj);
    }
}
