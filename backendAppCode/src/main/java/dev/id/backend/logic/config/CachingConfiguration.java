package dev.id.backend.logic.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
    CachingConfiguration: This configuration class enables caching using Spring's @Cacheable annotation and provides a simple CacheManager.

To use caching in your application, you can annotate methods that return data you want to cache with the @Cacheable annotation. For example, in one of your services:

java

@Service
public class MyService {

    @Cacheable("devCache")
    public MyData getData() {
        // Perform some expensive data retrieval
        return data;

 */
@Configuration
@EnableCaching
public class CachingConfiguration {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("devCache");
    }
}
