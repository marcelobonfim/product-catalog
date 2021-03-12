package com.compassouol.desafio.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.desafio.domain.ProductService;
import com.compassouol.desafio.domain.dto.ProductDTO;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO addProduct(@Valid @RequestBody ProductDTO productDTO) {
		return productService.addProduct(productDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> putProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
		ProductDTO changeProductDTO = productService.changeProduct(id, productDTO);

		return ResponseEntity.ok(changeProductDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
		ProductDTO productDTO = productService.getProduct(id);

		if (productDTO != null) {
			return ResponseEntity.ok(productDTO);
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<ProductDTO> allProducts() {
		return productService.getProducts();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		if (!productService.existisProduct(id)) {
			return ResponseEntity.notFound().build();
		}

		productService.deleteProduct(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/search")
	public List<ProductDTO> search(String q, BigDecimal min_price, BigDecimal max_price) {
		return productService.getSearchProducts(q, min_price, max_price);
	}

}
