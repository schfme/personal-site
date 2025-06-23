package me.schf.personal.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	@Cacheable("getAllPostsByTitle")
	public Map<String, Post> getAllPostsByTitle() {
		List<Post> posts = postRepository.findAll();
		return posts.stream()
				.collect(Collectors.toMap(Post::getTitle, post -> post));
	}
	
    @Cacheable("getTopNMostRecentPosts")
    public List<Post> getTopNMostRecentPosts(int n) {
        return postRepository.findByOrderByPublicationDateDesc(PageRequest.of(0, n));
    }

}
