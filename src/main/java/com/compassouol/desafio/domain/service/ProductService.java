package com.compassouol.desafio.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compassouol.desafio.domain.dto.ProductDTO;
import com.compassouol.desafio.domain.exception.EntityNotFoundException;
import com.compassouol.desafio.domain.mappers.ProductMapper;
import com.compassouol.desafio.domain.model.Product;
import com.compassouol.desafio.domain.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	public List<ProductDTO> getProducts() {
		return productRepository.findAll().stream().map(p -> this.productMapper.entityToDto(p))
				.collect(Collectors.toList());
	}

	public ProductDTO addProduct(ProductDTO productDTO) {
		Product product = this.productMapper.dtoToEntity(productDTO);

		productRepository.save(product);

		return this.productMapper.entityToDto(product);
	}

	public ProductDTO getProduct(Long id) {
		return productRepository.findById(id).map(p -> this.productMapper.entityToDto(p)).orElse(null);
	}

	public ProductDTO changeProduct(Long id, ProductDTO productDTO) {
		Product changeProduct = productRepository.findById(id).map(product -> {
			product.setName(productDTO.getName());
			product.setDescription(productDTO.getDescription());
			product.setPrice(productDTO.getPrice());
			return productRepository.save(product);
		}).orElseThrow(() -> new EntityNotFoundException("Product id " + id + " not found"));

		return this.productMapper.entityToDto(changeProduct);
	}

	public boolean existisProduct(Long id) {
		return productRepository.existsById(id);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public List<ProductDTO> getSearchProducts(String q, BigDecimal min_price, BigDecimal max_price) {
		return productRepository.findProductsByQueryParam(q, min_price, max_price).stream().map(p -> this.productMapper.entityToDto(p))
				.collect(Collectors.toList());
	}

}
