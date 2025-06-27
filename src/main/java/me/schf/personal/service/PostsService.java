package me.schf.personal.service;

import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import me.schf.api.client.api.PostHeadlinesApi;
import me.schf.api.client.api.PostsApi;
import me.schf.personal.config.site.SiteProperties;
import me.schf.personal.service.domain.PostDto;
import me.schf.personal.service.domain.PostDto.PostHeadlineDto;
import me.schf.personal.utility.PostConverter;

@Component
public class PostsService {

	private final PostsApi postsApi;
	private final PostHeadlinesApi postHeadlinesApi;
	private final SiteProperties siteProperties;
	private final CacheManager cacheManager;

	public PostsService(PostsApi postsApi, PostHeadlinesApi postHeadlinesApi, SiteProperties siteProperties,
			CacheManager cacheManager) {
		this.postsApi = postsApi;
		this.postHeadlinesApi = postHeadlinesApi;
		this.siteProperties = siteProperties;
		this.cacheManager = cacheManager;
	}

	public PostDto getPostByTitle(String title) {
		return PostConverter.toDto(
				postsApi.getPosts(null, null, title, null, null, null).getFirst()
		);
	}

	@Cacheable(value = "recentPostsCache", key = "'recent'")
	public List<PostDto> getRecentPosts() {
		return postsApi.getRecentPosts(siteProperties.getRssFeedConfig().getMaxFeedEntryCount())
				.stream()
				.map(PostConverter::toDto)
				.toList();
	}

	public List<PostHeadlineDto> getRecentPostHeadlines() {
		Cache cache = cacheManager.getCache("recentPostsCache");

		// if we've recently already called getRecentPosts, we can get the headlines from that.
		if (cache != null) {
			@SuppressWarnings("unchecked")
			List<PostDto> cachedPosts = (List<PostDto>) cache.get("recent");
			if (cachedPosts != null) {
				return cachedPosts.stream()
						.map(PostDto::getPostHeadline)
						.toList();
			}
		}
		// else, we'll make the lighter api call to getRecentPostHeadlines
		return postHeadlinesApi.getRecentPostHeadlines(siteProperties.getRssFeedConfig().getMaxFeedEntryCount())
				.stream()
				.map(PostConverter::toDto)
				.toList();
	}

}
