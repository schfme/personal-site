package me.schf.personal.config.rss;

import java.time.OffsetDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import me.schf.personal.config.site.SiteProperties;
import me.schf.personal.config.site.SiteProperties.RssFeedConfig;
import me.schf.personal.controller.rss.AtomLink;
import me.schf.personal.controller.rss.AtomLinkSerializer;
import me.schf.personal.controller.rss.Rfc822DateSerializer;
import me.schf.personal.controller.rss.RssEntry;
import me.schf.personal.controller.rss.RssFeed;
import me.schf.personal.service.PostsService;

@Configuration
public class RssConfig {
	
	@Bean
	RssFeed rssFeed(SiteProperties siteProperties, PostsService postsService) {
		RssFeedConfig config = siteProperties.getRssFeedConfig();
		
		var channel = new RssFeed.Channel.Builder()
				.title(config.getFeedTitle())
				.link("https://schf.me")
				.description(config.getFeedDescription())
				.maxSize(config.getMaxFeedEntryCount())
				.addAtomLink(new AtomLink("hub", "https://pubsubhubbub.appspot.com/"))
				.addAtomLink(new AtomLink("self", "https://schf.me/posts/rss/"))
				.build();
		
		postsService.getRecentPosts().stream()
			.map(RssEntry::fromPostDto)	
			.forEach(channel::offerItem);
		
		return new RssFeed.Builder()
				.version(config.getFeedVersion())
				.channel(channel)
				.build();				
	}
	
	@Bean
	SimpleModule simpleModule() {
	    SimpleModule module = new SimpleModule();
	    module.addSerializer(AtomLink.class, new AtomLinkSerializer());
	    module.addSerializer(OffsetDateTime.class, new Rfc822DateSerializer());
	    return module;
	}
	
	@Bean
	MappingJackson2XmlHttpMessageConverter xmlHttpMessageConverter(SimpleModule simpleModule) {
	    XmlMapper xmlMapper = new XmlMapper();
	    xmlMapper.registerModule(simpleModule);
	    return new MappingJackson2XmlHttpMessageConverter(xmlMapper);
	}

}
