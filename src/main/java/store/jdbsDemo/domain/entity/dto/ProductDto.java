package store.jdbsDemo.domain.entity.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import store.jdbsDemo.domain.validator.CustomLocalDateTimeDesSerializer;
import store.jdbsDemo.domain.validator.CustomLocalDateTimeSerializer;

public @Data class ProductDto {
	private long id;
	@JsonProperty(access = Access.READ_ONLY)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtCreate;
	@JsonProperty(access = Access.READ_ONLY)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtUpdate;

	private String name;
	private double price;
	private long categoryId;

	public ProductDto(String name, double price, long categoryId) {
		super();
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
	}

	public ProductDto() {
		super();
	}

}
