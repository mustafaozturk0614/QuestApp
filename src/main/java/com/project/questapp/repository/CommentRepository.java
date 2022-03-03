package com.project.questapp.repository;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
	List<Comment> findByUserIdAndPostId(long userId, long postId);

	List<Comment> findByUserId(long userId);

	List<Comment> findByPostId(long postId);
}
