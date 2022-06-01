package com.sparta.securityprac.security2.repository;

import com.sparta.securityprac.security2.model.Board;
import com.sparta.securityprac.security2.model.Comment;
import com.sparta.securityprac.security2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByOrderByModifiedAtDesc();

    Page<Comment> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    List<Comment> findAllByBoard(Board foundBoard);
}
