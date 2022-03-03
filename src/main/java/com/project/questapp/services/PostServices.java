package com.project.questapp.services;

import com.project.questapp.dto.request.PostCreateRequest;
import com.project.questapp.dto.request.PostUpdateRequest;
import com.project.questapp.dto.response.LikeResponse;
import com.project.questapp.dto.response.PostResponse;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class PostServices {


	private PostRepository repository;
	private LikeService likeService;
	private UserServices userServices;


	public PostServices(PostRepository postRepository,
				UserServices userService) {
			this.repository = postRepository;
			this.userServices = userService;
		}

		@Autowired
		public void setLikeService(LikeService likeService) {
			this.likeService = likeService;
		}
	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list;
		if(userId.isPresent()){
			list=repository.findByUserId(userId.get());
		}else {
			list=repository.findAll();
		}
		return list.stream().map(p -> {
			List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
			return new PostResponse(p, likes);}).collect(Collectors.toList());


	}

	public Post getOnePostById(long postId) {

		return  repository.findById(postId).orElse(null);
	}

	public Post createOneUser(PostCreateRequest dto) {
	 User user=	userServices.getOneUserById(dto.getUserId());
	if(user==null){

		return null;
	}else {
		Post post=Post.builder().id(dto.getId()).text(dto.getText()).title(dto.getTitle()).user(user).build();

		return repository.save(post);
	}

	}

	public Post updateOnePost(long id , PostUpdateRequest dto) {
		Optional<Post> post=repository.findById(id);

		if(post.isPresent()){
			post.get().setText(dto.getText());
			post.get().setTitle(dto.getTitle());
			 repository.save(post.get());
			 return post.get();

		}else {
			return  null;
		}


	}

	public void deleteOnePostById(long id) {
		 repository.deleteById(id);
	}
}
