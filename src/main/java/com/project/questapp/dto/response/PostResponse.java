package com.project.questapp.dto.response;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostResponse {

	Long id;
	Long userId;
	String username;
	String text;
	String title;
	List<LikeResponse> postLikes;


	public PostResponse(Post entity,List<LikeResponse> likes){
		this.id= entity.getId();
		this.userId=entity.getUser().getId();
		this.username=entity.getUser().getUsername();
		this.title=entity.getTitle();
		this.text=entity.getText();
		this.postLikes=likes;

	}
}
