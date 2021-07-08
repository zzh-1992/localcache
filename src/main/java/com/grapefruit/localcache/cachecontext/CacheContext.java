package com.grapefruit.localcache.cachecontext;

import com.grapefruit.localcache.cache.LocalCache;
import com.grapefruit.localcache.annotation.Cache;
import com.grapefruit.localcache.annotation.Mark;
import com.grapefruit.localcache.cacheconvertor.CacheConvertor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 */
@Component
@Setter
@Getter
public class CacheContext implements ApplicationContextAware {

    private ApplicationContext context;

    @Autowired
    private LocalCache localCache;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        initTable();
    }

    public void initTable() {
        Map<String, Object> map = context.getBeansWithAnnotation(Cache.class);
        map.forEach((key, value) -> {
            System.out.println("key:" + key);
            Object bean = context.getBean(key);
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Mark.class)) {
                    Mark annotation = method.getAnnotation(Mark.class);
                    String annotationKey = annotation.key();
                    String annotationValue = annotation.value();
                    if (method.getParameters().length > 0) {
                        System.out.println("方法需要参数,转换失败!!!!!");
                        continue;
                    }
                    try {
                        Object result = method.invoke(bean);
                        // 获取转换器
                        CacheConvertor cacheConvertor = (CacheConvertor) context.getBean(annotationKey);
                        // 设置缓存
                        cacheConvertor.setCache(annotationKey, result, localCache);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("value:" + value);
        });
    }
}
