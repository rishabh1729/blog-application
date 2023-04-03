package com.rishabh.springblog.dto;

import lombok.Data;

@Data
public class PostDto {

	private Long id;
	
	private String username;
	
	private String title;
	
	private String content;
}
