package me.schf.personal.config.database;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PostChangeListener {

	private PostChangeHandler updater;

	public PostChangeListener(PostChangeHandler updater) {
		super();
		this.updater = updater;
	}

	@PostConstruct
	public void init() {
		updater.listenForChanges();
	}

}
