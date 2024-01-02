package com.example.api_web_ban_hang.services.interfaces;

import com.example.api_web_ban_hang.models.entities.Comment;
import com.example.api_web_ban_hang.models.request.CommentRequest;

import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface CommentService {

    @Modifying
    Comment addComment(CommentRequest comment);
    List<Comment> findCommentByIdProduct(long id);

}
