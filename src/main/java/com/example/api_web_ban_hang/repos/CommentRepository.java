package com.example.api_web_ban_hang.repos;

import com.example.api_web_ban_hang.models.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByProduct_Id(long id);
}
