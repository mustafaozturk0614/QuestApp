package com.project.questapp.dto.response;

import com.project.questapp.entities.Like;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LikeResponse {

	Long id;
	Long userId;
	Long postId;

	public LikeResponse(Like like) {
		this.id=like.getId();
		this.userId=like.getUser().getId();
		this.postId=like.getPost().getId();
	}
}
