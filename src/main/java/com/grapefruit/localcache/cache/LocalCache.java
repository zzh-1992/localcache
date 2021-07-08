package com.grapefruit.localcache.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.HashBasedTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
@Component
@Setter
@Getter
public class LocalCache {
    private HashBasedTable<String, String, Object> objCache;
    private HashBasedTable<String, String, List<?>> listCache;
    private HashBasedTable<String, String, Map<String, Object>> mapCache;

    private Cache<String, Object> nullCache;

    @PostConstruct
    public void init() {
        objCache = HashBasedTable.create();
        listCache = HashBasedTable.create();
        mapCache = HashBasedTable.create();

        nullCache = CacheBuilder.newBuilder()
                // 弱引用
                .weakKeys()
                // 最大数量
                .maximumSize(10000)
                // 缓存生存时间
                .expireAfterAccess(5 * 60, TimeUnit.SECONDS)
                .build();
    }
}
