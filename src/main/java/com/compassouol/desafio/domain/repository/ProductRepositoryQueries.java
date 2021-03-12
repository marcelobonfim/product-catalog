package com.compassouol.desafio.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.compassouol.desafio.domain.model.Product;

public interface ProductRepositoryQueries {
	
	List<Product> findProductsByQueryParam(String q, BigDecimal min_price, BigDecimal max_price);

}
