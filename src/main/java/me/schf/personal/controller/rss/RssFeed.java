package me.schf.personal.controller.rss;

import java.util.ArrayDeque;
import java.util.Deque;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class RssFeed {

	@JacksonXmlProperty(isAttribute = true)
	private String version;

	@JacksonXmlProperty(localName = "channel")
	private Channel channel;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public void offerItem(RssEntry item) {
		this.channel.offerItem(item);
	}

	public static class Builder {
		private final RssFeed rssFeed = new RssFeed();

		public Builder version(String version) {
			rssFeed.setVersion(version);
			return this;
		}

		public Builder channel(Channel channel) {
			rssFeed.setChannel(channel);
			return this;
		}

		public RssFeed build() {
			return rssFeed;
		}
	}

	public static class Channel {
		private String title;
		private String link;
		private String description;
		@JsonIgnore
		private int maxSize;

		@JacksonXmlElementWrapper(useWrapping = false)
		@JacksonXmlProperty(localName = "item")
		private Deque<RssEntry> items;

		public Channel() {
			items = new ArrayDeque<>();
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Deque<RssEntry> getItems() {
			return items;
		}

		public void offerItem(RssEntry item) {
			if (maxSize == items.size()) {
				items.pollFirst();
			}
			items.offerLast(item);
		}

		public int getMaxSize() {
			return maxSize;
		}

		public void setMaxSize(int maxSize) {
			this.maxSize = maxSize;
		}

		public static class Builder {
			private final Channel channel = new Channel();

			public Builder title(String title) {
				channel.setTitle(title);
				return this;
			}

			public Builder link(String link) {
				channel.setLink(link);
				return this;
			}

			public Builder description(String description) {
				channel.setDescription(description);
				return this;
			}

			public Builder maxSize(int maxSize) {
				channel.setMaxSize(maxSize);
				return this;
			}

			public Channel build() {
				return channel;
			}
		}
	}
}
