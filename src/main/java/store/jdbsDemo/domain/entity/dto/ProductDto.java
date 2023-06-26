package store.jdbsDemo.domain.entity.dto;

import lombok.Data;

public @Data class ProductDto {

	private String name;
	private double price;
	private long categoryId;

	public ProductDto(String name, double price, long categoryId) {
		super();
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
	}

}
