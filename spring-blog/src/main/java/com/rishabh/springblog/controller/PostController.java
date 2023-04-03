package com.rishabh.springblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishabh.springblog.dto.PostDto;
import com.rishabh.springblog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping
	public ResponseEntity<Object> createPost(@RequestBody PostDto postDto) {
		postService.createPost(postDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> showAllPosts() {
		return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable Long id) {
		return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
	}
}
