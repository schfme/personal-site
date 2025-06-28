package me.schf.personal.config.cache;

import org.cache2k.extra.spring.SpringCache2kCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

	private final CacheTtlProperties cacheTtlProperties;

	public CacheConfig(CacheTtlProperties cacheTtlProperties) {
		super();
		this.cacheTtlProperties = cacheTtlProperties;
	}

	@Bean
	CacheManager cacheManager() {
		var cacheManager = new SpringCache2kCacheManager();

		cacheManager.addCache("postByTitleCache", c -> c.expireAfterWrite(cacheTtlProperties.getPostByTitleCache()));
		cacheManager.addCache("recentPostsCache", c -> c.expireAfterWrite(cacheTtlProperties.getRecentPostsCache()));
		cacheManager.addCache("recentPostHeadlinesCache", c -> c.expireAfterWrite(cacheTtlProperties.getRecentPostHeadlinesCache()));

		return cacheManager;
	}
}
