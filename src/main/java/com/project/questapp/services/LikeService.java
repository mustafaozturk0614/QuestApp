package com.project.questapp.services;

import com.project.questapp.dto.request.LikeCreateRequest;

import com.project.questapp.dto.response.LikeResponse;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class LikeService {
	private LikeRepository repository;
	private UserServices userService;
	private PostServices postService;
	public LikeService(LikeRepository likeRepository, UserServices userService,
			PostServices postService) {
		this.repository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
		List<Like> list;
		if(userId.isPresent() && postId.isPresent()) {
			list = repository.findByUserIdAndPostId(userId.get(), postId.get());
		}else if(userId.isPresent()) {
			list = repository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			list = repository.findByPostId(postId.get());
		}else
			list = repository.findAll();
		return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
	}

	public Like getOneLikeById(Long LikeId) {
		return repository.findById(LikeId).orElse(null);
	}

	public Like createOneLike(LikeCreateRequest request) {
		User user = userService.getOneUserById(request.getUserId());
		Post post = postService.getOnePostById(request.getPostId());
		if (user != null && post != null) {
			Like likeToSave = new Like();
			likeToSave.setId(request.getId());
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			return repository.save(likeToSave);
		} else
			return null;
	}

	public void deleteOneLikeById(Long likeId) {
		repository.deleteById(likeId);
	}
}
