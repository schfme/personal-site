package me.schf.personal.config;

import java.time.LocalDate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.schf.personal.controller.rss.RssEntry;
import me.schf.personal.controller.rss.RssFeed;
import me.schf.personal.data.PostService;

@Configuration
public class ContentConfig {

	@Bean
	LocalDate today() {
		return LocalDate.now();
	}

	@Bean
	@ConfigurationProperties("site-properties")
	SiteProperties siteProperties() {
		return new SiteProperties();

	}
	
	@Bean
	RssFeed rssFeed(SiteProperties siteProperties, PostService postService) {
		RssFeedConfig config = siteProperties.getRssFeedConfig();
		
		RssFeed.Channel channel = new RssFeed.Channel.Builder()
				.title(config.getFeedTitle())
				.link("schf.me/posts/rss")
				.description(config.getFeedDescription())
				.maxSize(config.getMaxFeedEntryCount())
				.build();
		
		postService.getTopNMostRecentPosts(config.getMaxFeedEntryCount())
				.stream()
				.map(RssEntry::fromPost)
				.forEach(channel::offerItem);
		
		return new RssFeed.Builder()
				.version(config.getFeedVersion())
				.channel(channel)
				.build();				
	}

}
