package me.schf.personal.config.database;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;

import me.schf.personal.controller.rss.RssEntry;
import me.schf.personal.controller.rss.RssFeed;
import me.schf.personal.service.domain.PostDto;
import me.schf.personal.service.domain.PostDto.PostHeadlineDto;

@Component
public class PostChangeHandler {

	private final MongoTemplate mongoTemplate;
	private final RssFeed rssFeed;
	private final CacheManager cacheManager;
	
	private final Logger logger = LoggerFactory.getLogger(PostChangeHandler.class);

	public PostChangeHandler(MongoTemplate mongoTemplate, RssFeed rssFeed, CacheManager cacheManager) {
		super();
		this.mongoTemplate = mongoTemplate;
		this.rssFeed = rssFeed;
		this.cacheManager = cacheManager;
	}

    @Async
    public void listenForChanges() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("posts");

	    collection.watch()
        .fullDocument(FullDocument.UPDATE_LOOKUP)
        .forEach((ChangeStreamDocument<Document> change) -> {
            if ("insert".equals(change.getOperationType().getValue())) {
            	logger.info("New post was added.");
                Document doc = change.getFullDocument();

                PostDto newPost = convertToPostDto(doc); 

                if (Boolean.TRUE.equals(newPost.getSharePost())) {
                	// push new db entry to rss feed
                	logger.info("Updating RSS feed.");
                	rssFeed.offerItem(RssEntry.fromPostDto(newPost));
                	// clear caches to force reload
                	logger.info("Clearing recent post caches.");
                    cacheManager.getCache("recentPostsCache").clear();
                    cacheManager.getCache("recentPostHeadlinesCache").clear();
                }
            }
        });
    }
    
	private PostDto convertToPostDto(Document doc) {
	    PostDto dto = new PostDto();

	    PostHeadlineDto headline = new PostHeadlineDto();
	    headline.setTitle(doc.getString("title"));
	    headline.setBlurb(doc.getString("description"));

	    Object pubDateObj = doc.get("publication_date");
	    if (pubDateObj instanceof Date date) {
	        OffsetDateTime odt = date.toInstant().atOffset(ZoneOffset.UTC);
	        headline.setPublicationDate(odt);
	    }

	    dto.setPostHeadline(headline);
	    dto.setAuthor(doc.getString("author"));
	    dto.setMarkdownText(doc.getString("markdown_text"));
	    dto.setTags(doc.getList("tags", String.class));

	    Boolean sharePost = doc.containsKey("sharePost") ? doc.getBoolean("sharePost") : Boolean.TRUE;
	    dto.setSharePost(sharePost);

	    return dto;
	}
    
}
