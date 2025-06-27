package me.schf.personal.utility;

import me.schf.api.client.model.Post;
import me.schf.api.client.model.Post.TagsEnum;
import me.schf.api.client.model.PostHeadline;
import me.schf.personal.service.domain.PostDto;
import me.schf.personal.service.domain.PostDto.PostHeadlineDto;

public class PostConverter {

	private PostConverter() {
		super();
	}

	public static PostHeadlineDto toDto(PostHeadline headline) {
		if (headline == null) {
			return null;
		}

		PostHeadlineDto dto = new PostHeadlineDto();
		dto.setTitle(headline.getTitle());
		dto.setPublicationDate(headline.getPublicationDate());
		dto.setBlurb(headline.getBlurb());
		return dto;
	}

	public static PostHeadline fromDto(PostHeadlineDto dto) {
		if (dto == null) {
			return null;
		}

		PostHeadline headline = new PostHeadline();
		headline.setTitle(dto.getTitle());
		headline.setPublicationDate(dto.getPublicationDate());
		headline.setBlurb(dto.getBlurb());
		return headline;
	}

	public static PostDto toDto(Post post) {
		if (post == null) {
			return null;
		}
		
		PostDto dto = new PostDto();
		dto.setAuthor(post.getAuthor());
		dto.setMarkdownText(post.getMarkdownText());
		dto.setSharePost(post.getSharePost());

		dto.setTags(post.getTags().stream()
				.map(Enum::name)
				.toList());

		dto.setPostHeadline(PostConverter.toDto(post.getPostHeadline()));
		return dto;
	}

	public static Post fromDto(PostDto dto) {
		if (dto == null) {
			return null;
		}

		Post post = new Post();
		post.setAuthor(dto.getAuthor());
		post.setMarkdownText(dto.getMarkdownText());
		post.setSharePost(dto.getSharePost());

		post.setTags(dto.getTags().stream()
				.map(String::toUpperCase)
				.map(TagsEnum::valueOf)
				.toList());

		post.setPostHeadline(PostConverter.fromDto(dto.getPostHeadline()));
		return post;
	}

}
