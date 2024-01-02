package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.models.entities.Comment;
import com.example.api_web_ban_hang.models.entities.Product;
import com.example.api_web_ban_hang.models.entities.User;
import com.example.api_web_ban_hang.models.request.CommentRequest;
import com.example.api_web_ban_hang.repos.ProductRepository;
import com.example.api_web_ban_hang.repos.UserRepository;
import com.example.api_web_ban_hang.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<ResponseObject> addComment(@RequestBody CommentRequest rqcomment) {

        User user = Optional.of(userRepository.findById(rqcomment.getId_user()))
                .orElse(null).get();
        Product product = Optional.of(productRepository.findById(rqcomment.getId_product()))
                .orElse(null).get();
        Comment comment = new Comment();
        comment.setStar(rqcomment.getStar());
        comment.setContent(rqcomment.getContent());
        comment.setUser(user);
        comment.setProduct(product);
        return Optional
                .of(ResponseEntity.ok().body(
                        new ResponseObject(
                                HttpStatus.OK.name(),
                                HttpStatus.OK.getReasonPhrase(),
                                commentService.addComment( comment )))
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
