package me.schf.personal.controller.posts;

import java.time.format.DateTimeFormatter;

import me.schf.personal.data.Post;

public class PostHeadline {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");

	public static PostHeadline fromPost(Post fullPost) {
		return new PostHeadline(fullPost.getTitle(), FORMATTER.format(fullPost.getPublicationDate()),
				fullPost.getDescription());
	}

	private String description;
	private String publicationDate;
	private String title;

	public PostHeadline(String title, String publicationDate, String description) {
		super();
		this.title = title;
		this.publicationDate = publicationDate;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
