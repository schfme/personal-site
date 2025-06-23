package me.schf.personal.config;

public class RssFeedConfig {

	private String feedDescription;
	private String feedTitle;
	private String feedVersion;
	private int maxFeedEntryCount;

	public String getFeedDescription() {
		return feedDescription;
	}

	public String getFeedTitle() {
		return feedTitle;
	}

	public String getFeedVersion() {
		return feedVersion;
	}

	public int getMaxFeedEntryCount() {
		return maxFeedEntryCount;
	}

	public void setFeedDescription(String feedDescription) {
		this.feedDescription = feedDescription;
	}

	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	public void setFeedVersion(String feedVersion) {
		this.feedVersion = feedVersion;
	}

	public void setMaxFeedEntryCount(int maxFeedEntryCount) {
		this.maxFeedEntryCount = maxFeedEntryCount;
	}

}
