package me.schf.personal.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByOrderByPublicationDateDesc(Pageable pageable);

}
