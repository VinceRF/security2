package com.sparta.securityprac.security2.repository;

import com.sparta.securityprac.security2.model.Board;
import com.sparta.securityprac.security2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByOrderByModifiedAtDesc();
}
