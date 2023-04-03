package com.rishabh.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishabh.springblog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
