package com.example.api_web_ban_hang.services.imp;

import com.example.api_web_ban_hang.models.entities.Comment;
import com.example.api_web_ban_hang.models.entities.Product;
import com.example.api_web_ban_hang.models.entities.User;
import com.example.api_web_ban_hang.models.request.CommentRequest;
import com.example.api_web_ban_hang.repos.CommentRepository;
import com.example.api_web_ban_hang.repos.ProductRepository;
import com.example.api_web_ban_hang.repos.UserRepository;
import com.example.api_web_ban_hang.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public Comment addComment(CommentRequest rqcomment) {

		User user = Optional.of(userRepository.findById(rqcomment.getId_user())).orElse(null).get();
		Product product = Optional.of(productRepository.findById(rqcomment.getId_product())).orElse(null).get();
		Comment comment = new Comment();
		comment.setStar(rqcomment.getStar());
		comment.setContent(rqcomment.getContent());
		comment.setUser(user);
		comment.setProduct(product);
		return Optional.of(commentRepository.save(comment))
				.orElseThrow(() -> new RuntimeException("Failed to add comment"));
	}

	@Override
	public List<Comment> findCommentByIdProduct(long id) {
		return Optional
				.of(commentRepository.findByProduct_Id(id).stream()
						.sorted(Comparator.comparing(Comment::getStar).reversed()).collect(Collectors.toList()))
				.orElse(null);
	}
}
