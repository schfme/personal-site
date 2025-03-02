package me.schf.personal.controller.content.music;

import java.time.LocalDate;

import me.schf.personal.controller.content.Content;

public record Music(String trackName, LocalDate trackDate, String link) implements Content{

	@Override
	public String getName() {
		return this.trackName;
	}

	@Override
	public LocalDate getDate() {
		return this.trackDate;
	}

	@Override
	public String getLink() {
		return this.link;
	}

}
