package me.schf.personal.config;

public class SiteProperties {

	private ExternalLinkConfig externalLinkConfig;
	private RssFeedConfig rssFeedConfig;

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