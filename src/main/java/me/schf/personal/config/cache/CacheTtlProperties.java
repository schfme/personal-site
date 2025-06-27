package me.schf.personal.config.cache;
import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cache.ttl")
public class CacheTtlProperties {

    private Duration postByTitleCache;
    private Duration recentPostsCache;
    private Duration recentPostHeadlinesCache;

    public Duration getPostByTitleCache() {
        return postByTitleCache;
    }

    public void setPostByTitleCache(Duration postByTitleCache) {
        this.postByTitleCache = postByTitleCache;
    }

    public Duration getRecentPostsCache() {
        return recentPostsCache;
    }

    public void setRecentPostsCache(Duration recentPostsCache) {
        this.recentPostsCache = recentPostsCache;
    }

    public Duration getRecentPostHeadlinesCache() {
        return recentPostHeadlinesCache;
    }

    public void setRecentPostHeadlinesCache(Duration recentPostHeadlinesCache) {
        this.recentPostHeadlinesCache = recentPostHeadlinesCache;
    }
}
