package com.example.api_web_ban_hang.services.imp;

import com.example.api_web_ban_hang.models.entities.Comment;
import com.example.api_web_ban_hang.repos.CommentRepository;
import com.example.api_web_ban_hang.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return Optional
                .ofNullable(commentRepository.save(comment))
                .orElseThrow(() -> new RuntimeException("Failed to add comment"));
    }

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll(Sort.by("star").descending());
    }
}
