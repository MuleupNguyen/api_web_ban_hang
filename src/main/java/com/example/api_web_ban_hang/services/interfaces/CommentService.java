package com.example.api_web_ban_hang.services.interfaces;

import com.example.api_web_ban_hang.models.entities.Comment;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface CommentService {

    @Modifying
    Comment addComment(Comment comment);
    List<Comment> findAllComments();

}
