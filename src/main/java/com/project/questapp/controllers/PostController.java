package com.project.questapp.controllers;

import com.project.questapp.dto.request.PostCreateRequest;
import com.project.questapp.dto.request.PostUpdateRequest;
import com.project.questapp.dto.response.PostResponse;
import com.project.questapp.entities.Post;
import com.project.questapp.services.PostServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
//deneme pull request
public class PostController {


	private final PostServices service;



	@GetMapping
	public List<PostResponse> getAllPost(@RequestParam Optional<Long> userId){

	 return service.getAllPosts(userId);
	}

	@GetMapping("/{postId}")
	public Post getOnePost(@PathVariable Long postId){

		return  service.getOnePostById(postId);
	}

	@PostMapping()
	public Post createOnePost(@RequestBody PostCreateRequest dto){

		return  service.createOneUser(dto);
	}

	@PutMapping ("/{postId}")
	public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest dto){

		return  service.updateOnePost(postId,dto);
	}

	@DeleteMapping("/{postId}")
	public void deleteOnPost(@PathVariable Long postId){

	service.deleteOnePostById(postId );

	}
}
