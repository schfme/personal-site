package me.schf.personal.config.database;

import java.time.ZoneOffset;
import java.util.Date;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;
import me.schf.personal.controller.rss.PubSubHubbubNotifier;
import me.schf.personal.controller.rss.RssEntry;
import me.schf.personal.controller.rss.RssFeed;
import me.schf.personal.service.domain.PostDto;
import me.schf.personal.service.domain.PostDto.PostHeadlineDto;
import reactor.core.Disposable;

@Component
public class ReactivePostChangeListener {

	private final ReactiveMongoTemplate reactiveMongoTemplate;
	private final RssFeed rssFeed;
	private final CacheManager cacheManager;
	private final PubSubHubbubNotifier hubNotifier;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Disposable subscription;

	public ReactivePostChangeListener(ReactiveMongoTemplate reactiveMongoTemplate, RssFeed rssFeed,
			CacheManager cacheManager, PubSubHubbubNotifier hubNotifier) {
		super();
		this.reactiveMongoTemplate = reactiveMongoTemplate;
		this.rssFeed = rssFeed;
		this.cacheManager = cacheManager;
		this.hubNotifier = hubNotifier;
	}

	@EventListener(ApplicationReadyEvent.class)
    public void startListening() {
        var filter = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("operationType").is("insert"))
        );

        var options = ChangeStreamOptions.builder()
                .filter(filter)
                .returnFullDocumentOnUpdate()
                .build();

		var changeStream = reactiveMongoTemplate.changeStream(options, Document.class);

        subscription = changeStream.subscribe(event -> {
            Document doc = event.getBody(); // full document of the changed record
            if (doc != null) {
                logger.info("New reactive post inserted: {}", doc.toJson());
                PostDto post = convertToPostDto(doc);
                evictRecentPostCache();
                if (Boolean.TRUE.equals(post.getSharePost())) {
                    updateRssFeed(post);
                    hubNotifier.notifyHub();
                }
            }
        });

    }

	private void evictRecentPostCache() {
		clearCache("recentPostsCache");
		clearCache("recentPostHeadlinesCache");
	}

	private void updateRssFeed(PostDto post) {
		rssFeed.offerItem(RssEntry.fromPostDto(post));
	}

    @PreDestroy
    public void stopListening() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
            logger.info("Stopped reactive MongoDB change stream listener.");
        }
    }

    private void clearCache(String cacheName) {
        var cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            logger.warn("Cache '{}' not found.", cacheName);
        }
    }

    private PostDto convertToPostDto(Document doc) {
        var dto = new PostDto();
        var headline = new PostHeadlineDto();

        headline.setTitle(doc.getString("title"));
        headline.setBlurb(doc.getString("description"));

        Object pubDate = doc.get("publication_date");
        if (pubDate instanceof Date date) {
            headline.setPublicationDate(date.toInstant().atOffset(ZoneOffset.UTC));
        }

        dto.setPostHeadline(headline);
        dto.setAuthor(doc.getString("author"));
        dto.setMarkdownText(doc.getString("markdown_text"));
        dto.setTags(doc.getList("tags", String.class));
        dto.setSharePost(doc.getBoolean("sharePost", true));

        return dto;
    }
}
