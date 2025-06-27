package me.schf.personal.controller.rss;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import me.schf.personal.service.domain.PostDto;

public record RssEntry(

		@JacksonXmlProperty(localName = "title") String title,

		@JacksonXmlProperty(localName = "link") String link,

		@JacksonXmlProperty(localName = "description") String description,

		@JacksonXmlProperty(localName = "pubDate") OffsetDateTime pubDate,

		@JacksonXmlProperty(localName = "author") String author) {
	
	
	public static RssEntry fromPostDto(PostDto postDto) {
		return new RssEntry(postDto.getPostHeadline().getTitle(),
				"https://schf.me/posts/%s".formatted(postDto.getPostHeadline().getTitle()), 
				postDto.getPostHeadline().getBlurb(), 
				postDto.getPostHeadline().getPublicationDate(), 
				postDto.getAuthor()
			);		
	}
	
	
}