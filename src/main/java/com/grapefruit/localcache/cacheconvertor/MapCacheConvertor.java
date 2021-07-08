package com.grapefruit.localcache.cacheconvertor;

import com.google.common.collect.HashBasedTable;
import com.grapefruit.localcache.cache.LocalCache;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
@Component("mapConvertor")
public class MapCacheConvertor implements CacheConvertor {
    /**
     * 设置缓存
     *
     * @param key
     * @param obj
     */
    @Override
    public void setCache(String key, Object obj, LocalCache localCache) {
        HashBasedTable<String, String, Map<String, Object>> mapTable = localCache.getMapCache();
        mapTable.put(key, key, (Map) obj);

        mapTable.isEmpty();
        mapTable.remove("", "");
    }
}
