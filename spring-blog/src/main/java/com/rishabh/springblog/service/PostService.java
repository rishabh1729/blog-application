package com.rishabh.springblog.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.rishabh.springblog.dto.PostDto;
import com.rishabh.springblog.exception.PostNotFoundException;
import com.rishabh.springblog.model.Post;
import com.rishabh.springblog.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private AuthService authService;

	@Autowired
	private PostRepository postRepository;

	public void createPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());

		User username = authService.getCurrentUser()
				.orElseThrow(() -> new IllegalArgumentException("No user logged in"));
		post.setUsername(username.getUsername());
		post.setCreatedOn(Instant.now());
		postRepository.save(post);
	}

	public List<PostDto> showAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(this::mapFromPostToDto).toList();
	}

	private PostDto mapFromPostToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setUsername(post.getUsername());
		return postDto;
	}

	public PostDto readSinglePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
		return mapFromPostToDto(post);
	}
}
