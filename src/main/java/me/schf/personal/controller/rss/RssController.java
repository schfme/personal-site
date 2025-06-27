package me.schf.personal.controller.rss;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RssController {
	
	private final RssFeed rssFeed;

	public RssController(RssFeed rssFeed) {
		super();
		this.rssFeed = rssFeed;
	}

	@GetMapping(value = "/posts/rss", produces = MediaType.APPLICATION_XML_VALUE)
	public RssFeed getRssFeed() {
		return rssFeed;
	}
}
