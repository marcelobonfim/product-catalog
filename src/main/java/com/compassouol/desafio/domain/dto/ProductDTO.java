package com.compassouol.desafio.domain.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	@Id
	private Long id;
	
	@NotBlank(message = "Name is empty")
	private String name;
	
	@NotBlank(message = "Description is empty")
	private String description;
	
	@NotNull
	@Min(value = 0, message = "Price shoulde be greater than zero or equal zero")
	private BigDecimal price;

}
