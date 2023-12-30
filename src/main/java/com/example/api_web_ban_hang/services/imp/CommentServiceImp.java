package com.example.api_web_ban_hang.services.imp;

import com.example.api_web_ban_hang.models.entities.Comment;
import com.example.api_web_ban_hang.repos.CommentRepository;
import com.example.api_web_ban_hang.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return Optional
                .of(commentRepository.save(comment))
                .orElseThrow(() -> new RuntimeException("Failed to add comment"));
    }


    @Override
    public List<Comment> findCommentByIdProduct(long id) {
        return Optional.of(commentRepository.findByProduct_Id(id)
                .stream()
                .sorted(Comparator.comparing(Comment::getStar).reversed())
                .collect(Collectors.toList())).orElse(null);
    }
}
