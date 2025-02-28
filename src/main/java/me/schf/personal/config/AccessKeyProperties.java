package me.schf.personal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "apikeys")
public class AccessKeyProperties {

	private String mediastack;

	public String getMediastack() {
		return mediastack;
	}

	public void setMediastack(String mediastack) {
		this.mediastack = mediastack;
	}

}
