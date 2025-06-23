package me.schf.personal.controller.rss;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import me.schf.personal.data.Post;

public record RssEntry(

		@JacksonXmlProperty(localName = "title") String title,

		@JacksonXmlProperty(localName = "link") String link,

		@JacksonXmlProperty(localName = "description") String description,

		@JacksonXmlProperty(localName = "pubDate") @JsonDeserialize(using = ZonedDateTimeDeserializer.class) ZonedDateTime pubDate,

		@JacksonXmlProperty(localName = "author") String author) {
	
	
	public static RssEntry fromPost(Post post) {
		return new RssEntry(post.getTitle(),
				"https://schf.me/posts/%s".formatted(post.getTitle()), 
				post.getDescription(), 
				post.getPublicationDate(), 
				post.getAuthor()
			);		
	}
	
	
}