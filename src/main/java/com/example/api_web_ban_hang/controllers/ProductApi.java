package com.example.api_web_ban_hang.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.api_web_ban_hang.dto.ProductDTO;
import com.example.api_web_ban_hang.models.entities.Product;
import com.example.api_web_ban_hang.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.services.interfaces.IProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductApi {
    @Autowired
    private IProductService productService;
    @Autowired
    private ProductRepository productRepository;


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
														  @PageableDefault(size = 30, page = 0) @SortDefaults({
																  @SortDefault(sort = "listedPrice", direction = Sort.Direction.DESC) }) Pageable pageable) {
		return ResponseEntity.ok()
				.body(new ResponseObject(HttpStatus.OK.name(), HttpStatus.OK.getReasonPhrase(),
						productService.findByNameProduct(input, pageable)));
	}

    @GetMapping("/fitter-product-hot")
    public ResponseEntity<ResponseObject> findProductHotByBrand(@RequestParam(name = "brand") String brand,
                                                                @PageableDefault(size = 3, page = 0) @SortDefaults({
                                                                        @SortDefault(sort = "listedPrice", direction = Sort.Direction.DESC)}) Pageable pageable) {
        return Optional.ofNullable(
                        ResponseEntity.ok()
                                .body(new ResponseObject("Ok", "OK",
                                        productService.findProductByBrandWithOptionSort(brand, pageable))))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.getReasonPhrase(), "")))
                ;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> findAllProduct(
            @PageableDefault(size = 6, page = 0) @SortDefaults({
                    @SortDefault(sort = "listedPrice", direction = Sort.Direction.DESC)}) Pageable pageable) {
        return Optional.ofNullable(
                        ResponseEntity.ok()
                                .body(new ResponseObject("Ok", "OK",
                                        productService.findAll(pageable))))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.getReasonPhrase(), "")))
                ;
    }
	@GetMapping("/filter")
	public ResponseEntity<ResponseObject> findByTypeProduct_IdOrBrand_IdOrIdSex(
			@RequestParam(name = "type", required = false) Integer idType,
			@RequestParam(name = "brand", required = false) Long idBrand,
			@RequestParam(name = "sex", required = false) Integer idSex,
			@PageableDefault(size = 6, page = 0) @SortDefaults({
					@SortDefault(sort = "listedPrice", direction = Sort.Direction.DESC)}) Pageable pageable) {
		List<ProductDTO> products;

		switch (countNonNullParams(idType, idBrand, idSex)) {
			case 1:
				products = handleSingleParam(idType, idBrand, idSex, pageable);
				break;
			case 2:
				products = handleTwoParams(idType, idBrand, idSex, pageable);
				break;
			case 3:
				products = handleThreeParams(idType, idBrand, idSex, pageable);
				break;
			default:
				// Handle other cases if needed
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ResponseObject(HttpStatus.BAD_REQUEST.name(), "Invalid number of parameters", ""));
		}

		return Optional.ofNullable(
						ResponseEntity.ok()
								.body(new ResponseObject("Ok", "OK", products)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObject(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.getReasonPhrase(), "")))
				;
	}

	private int countNonNullParams(Object... params) {
		return (int) Arrays.stream(params).filter(Objects::nonNull).count();
	}

	private List<ProductDTO> handleSingleParam(Integer idType, Long idBrand, Integer idSex, Pageable pageable) {
		return productService.findByTypeProduct_IdOrBrand_IdOrIdSex(idType, idBrand, idSex, pageable);
	}

	private List<ProductDTO> handleTwoParams(Integer idType, Long idBrand, Integer idSex, Pageable pageable) {
		if(idType == null){
			return productService.findByBrand_IdAndIdSex(idBrand, idSex, pageable);
		} else if (idBrand == null) {
			return productService.findByTypeProduct_IdAndIdSex(idType, idSex, pageable);
		} else if (idSex == null){
			return productService.findByTypeProduct_IdAndBrand_Id(idType, idBrand, pageable);
		}
		return null;
	}

	private List<ProductDTO> handleThreeParams(Integer idType, Long idBrand, Integer idSex, Pageable pageable) {
		return productService.findByTypeProduct_IdAndBrand_IdAndIdSex(idType,idBrand,idSex,pageable);
	}
}