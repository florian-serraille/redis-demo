package com.example.redisdemo;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PierCacheErrorHandler extends CachingConfigurerSupport {
    
    private final String errorMessage = "ERROR";
    
    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(final RuntimeException exception, final Cache cache, final Object key) {
                log.error(errorMessage);
    
            }
    
            @Override
            public void handleCachePutError(final RuntimeException exception, final Cache cache, final Object key,
                                            final Object value) {
                log.error(errorMessage);
    
            }
    
            @Override
            public void handleCacheEvictError(final RuntimeException exception, final Cache cache, final Object key) {
                log.error(errorMessage);
            }
    
            @Override
            public void handleCacheClearError(final RuntimeException exception, final Cache cache) {
                log.error(errorMessage);
            }
        };
    }
}
