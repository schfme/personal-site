package me.schf.personal.service.domain;

import java.time.OffsetDateTime;
import java.util.List;

public class PostDto {

	private PostHeadlineDto postHeadline;
	private String author;
	private String markdownText;
	private List<String> tags;
	private Boolean sharePost;

	public PostHeadlineDto getPostHeadline() {
		return postHeadline;
	}

	public void setPostHeadline(PostHeadlineDto postHeadline) {
		this.postHeadline = postHeadline;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMarkdownText() {
		return markdownText;
	}

	public void setMarkdownText(String markdownText) {
		this.markdownText = markdownText;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Boolean getSharePost() {
		return sharePost;
	}

	public void setSharePost(Boolean sharePost) {
		this.sharePost = sharePost;
	}

	public static class PostHeadlineDto {

		private String title;
		private OffsetDateTime publicationDate;
		private String blurb;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public OffsetDateTime getPublicationDate() {
			return publicationDate;
		}

		public void setPublicationDate(OffsetDateTime publicationDate) {
			this.publicationDate = publicationDate;
		}

		public String getBlurb() {
			return blurb;
		}

		public void setBlurb(String blurb) {
			this.blurb = blurb;
		}
	}

}
