package com.project.questapp.services;

import com.project.questapp.dto.request.CommentCreateRequest;
import com.project.questapp.dto.request.CommentUpdateRequest;
import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

private final CommentRepository repository;
	private final UserServices userService;
	private  final PostServices postService;

	public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {

		if(userId.isPresent()&& postId.isPresent()){
			return repository.findByUserIdAndPostId(userId.get(),postId.get());

		}else if(userId.isPresent()){
			return  repository.findByUserId(userId.get());
		}
		else if(postId.isPresent()){
			return  repository.findByPostId(postId.get());
		}
		else {
			return repository.findAll();
		}
	}

	public Comment getOneCommentById(long commentId) {

		return repository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentCreateRequest dto) {
		User user= userService.getOneUserById(dto.getUserId());
		Post post=postService.getOnePostById(dto.getPostId());

		if(user!=null &&post !=null){
			Comment comment=new Comment();
			comment.setId(dto.getId());
			comment.setPost(post);
			comment.setText(dto.getText());
			comment.setUser(user);
			return repository.save(comment);
		}else {
			return  null;
		}


	}

	public Comment updateOneCommentById(long commentId, CommentUpdateRequest dto) {
		Optional<Comment> comment=repository.findById(commentId);

	if(comment.isPresent())	{
			comment.get().setText(dto.getText());
			return repository.save(comment.get());
		}else{
			return  null;
		}


	}

	public void deleteOneCommentById(Long commentId) {
		repository.deleteById(commentId);
	}
}
