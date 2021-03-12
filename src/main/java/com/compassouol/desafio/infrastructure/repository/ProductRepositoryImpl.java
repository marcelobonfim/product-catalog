package com.compassouol.desafio.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.compassouol.desafio.domain.model.Product;
import com.compassouol.desafio.domain.repository.ProductRepositoryQueries;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Product> findProductsByQueryParam(String q, BigDecimal min_price, BigDecimal max_price) {
		var jpql = new StringBuilder();
		jpql.append("from Product where 0 = 0 ");
		
		var parametros = new HashMap<String, Object>();
		
		if (StringUtils.hasLength(q)) {
			jpql.append("and name like :name ");
			parametros.put("name", "%" + q + "%");
			
			jpql.append("and description like :description ");
			parametros.put("description", "%" + q + "%");
		}
		
		if (min_price != null) {
			jpql.append("and price >= :min_price ");
			parametros.put("min_price", min_price);
		}
		
		if (max_price != null) {
			jpql.append("and price <= :max_price ");
			parametros.put("max_price", max_price);
		}
		
		TypedQuery<Product> query = manager
				.createQuery(jpql.toString(), Product.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();
	}

}
