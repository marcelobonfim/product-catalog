package com.compassouol.desafio.domain.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.compassouol.desafio.domain.dto.ProductDTO;
import com.compassouol.desafio.domain.model.Product;

@Component
public class ProductMapper {
	
	private final ModelMapper modelMapper;

	public ProductMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public Product dtoToEntity(ProductDTO productDTO) {
		Product product = modelMapper.map(productDTO, Product.class);
		return product;
	}
	
	public ProductDTO entityToDto(Product product) {
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		return productDTO;	
	}

}
