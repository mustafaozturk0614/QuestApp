package com.project.questapp.controllers;

import com.project.questapp.dto.request.CommentCreateRequest;
import com.project.questapp.dto.request.CommentUpdateRequest;
import com.project.questapp.entities.Comment;
import com.project.questapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {


	private final CommentService service ;

	@GetMapping
   public List<Comment> getAllComments(@RequestParam Optional<Long> postId , Optional<Long>userId){
		return service.getAllCommentsWithParam(userId,postId);

	}

	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable Long commentId){
		return  service.getOneCommentById(commentId);
	}

	@PostMapping()
	public  Comment createOneCommet(@RequestBody CommentCreateRequest dto){

		return  service.createOneComment(dto);

	}

	@PutMapping("/{commentId}")
	public  Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest dto){
		return  service.updateOneCommentById(commentId,dto);

	}

	@DeleteMapping("/{commentId}")
	public  void deleteOneCommentById(long commentId){

		service.deleteOneCommentById(commentId);
	}

}
