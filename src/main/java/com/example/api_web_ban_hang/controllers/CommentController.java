package com.example.api_web_ban_hang.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.models.request.CommentRequest;
import com.example.api_web_ban_hang.services.interfaces.CommentService;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping()
    public ResponseEntity<ResponseObject> addComment(@RequestBody CommentRequest rqcomment) {
        return Optional
                .of(ResponseEntity.ok().body(
                        new ResponseObject(
                                HttpStatus.CREATED.name(),
                                HttpStatus.CREATED.getReasonPhrase(),
                                commentService.addComment(rqcomment)))
                ).get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findCommentByIdProduct(@PathVariable(name = "id") long id) {
        return Optional
                .of(ResponseEntity.ok()
                        .body(new ResponseObject(HttpStatus.OK.name(),
                                HttpStatus.OK.getReasonPhrase(),
                                commentService.findCommentByIdProduct(id))))
                .orElse(ResponseEntity.ok()
                        .body(new ResponseObject(HttpStatus.NOT_FOUND.name(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                "")));
    }
}
