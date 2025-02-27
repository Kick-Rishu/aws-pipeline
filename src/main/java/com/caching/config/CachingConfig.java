package com.caching.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * The type Caching config.
 */
@Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {

    /**
     * The constant FORWARD_GEOCODE.
     */
    public static final String FORWARD_GEOCODE = "geocoding";
    /**
     * The constant REVERSE_GEOCODE.
     */
    public static final String REVERSE_GEOCODE = "reverse-geocoding";

    /**
     * Cache manager.
     *
     * @return the cache manager
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(FORWARD_GEOCODE, REVERSE_GEOCODE);
    }

    @Scheduled(fixedDelay = 2000)
    public void clearCache() {
        Cache cache= cacheManager().getCache(REVERSE_GEOCODE);
        if (cache==null) {
return;
        }
        log.error("{}", cache.getNativeCache());
    }
}
