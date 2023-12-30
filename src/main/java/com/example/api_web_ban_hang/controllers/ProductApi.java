package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductApi {
	@Autowired
	private IProductService productService;

	@GetMapping("/infor-product/{id}")
	public ResponseEntity<ResponseObject> findProductById(@PathVariable(name = "id") long id) {
		return Optional
				.of(ResponseEntity.ok()
						.body(new ResponseObject(HttpStatus.OK.name(), HttpStatus.OK.getReasonPhrase(),
								productService.findById(id))))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObject(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.getReasonPhrase(), "")));
	}

	@GetMapping("/search")
	public ResponseEntity<ResponseObject> findProductById(@RequestParam(name = "name") String input,
			@RequestParam(name = "quantity", required = false) Integer quantity) {
		return ResponseEntity.ok()
				.body(ResponseObject.builder()
						.object(productService.findByNameProduct(input).stream()
								.limit(quantity != null ? quantity : Long.MAX_VALUE))
						.status(HttpStatus.OK.name()).message(HttpStatus.OK.getReasonPhrase()).build());
	}

}