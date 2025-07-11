package me.schf.personal.config.site;

public class SiteProperties {

	private ExternalLinkConfig externalLinkConfig;
	private RssFeedConfig rssFeedConfig;

	public static class RssFeedConfig {

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

	public static class ExternalLinkConfig {

		private String gitHubLink;
		private String igLink;
		private String linkedinLink;
		private String resumeLink;
		private String cubeeLink;
		private String aiTunnelLink;

		public String getGitHubLink() {
			return gitHubLink;
		}

		public String getIgLink() {
			return igLink;
		}

		public String getLinkedinLink() {
			return linkedinLink;
		}

		public String getResumeLink() {
			return resumeLink;
		}

		public void setGitHubLink(String gitHubLink) {
			this.gitHubLink = gitHubLink;
		}

		public void setIgLink(String igLink) {
			this.igLink = igLink;
		}

		public void setLinkedinLink(String linkedinLink) {
			this.linkedinLink = linkedinLink;
		}

		public void setResumeLink(String resumeLink) {
			this.resumeLink = resumeLink;
		}

		public String getCubeeLink() {
			return cubeeLink;
		}

		public void setCubeeLink(String cubeeLink) {
			this.cubeeLink = cubeeLink;
		}

		public String getAiTunnelLink() {
			return aiTunnelLink;
		}

		public void setAiTunnelLink(String aiTunnelLink) {
			this.aiTunnelLink = aiTunnelLink;
		}

	}

	public ExternalLinkConfig getExternalLinkConfig() {
		return externalLinkConfig;
	}

	public void setExternalLinkConfig(ExternalLinkConfig externalLinkConfig) {
		this.externalLinkConfig = externalLinkConfig;
	}

	public RssFeedConfig getRssFeedConfig() {
		return rssFeedConfig;
	}

	public void setRssFeedConfig(RssFeedConfig rssFeedConfig) {
		this.rssFeedConfig = rssFeedConfig;
	}

}