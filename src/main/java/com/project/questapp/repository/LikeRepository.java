package com.project.questapp.repository;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
	List<Like> findByUserIdAndPostId(Long userId, Long postId);


	List<Like> findByUserId(long userId);

	List<Like> findByPostId(long postId);
}
