package store.jdbsDemo.domain.entity.dto;

import lombok.Data;

public @Data class ProductDto {

	private String name;
	private double price;
	private long categoryId;

	
}
