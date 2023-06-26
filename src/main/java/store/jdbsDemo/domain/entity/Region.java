package store.jdbsDemo.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import store.jdbsDemo.domain.validator.CustomLocalDateTimeDesSerializer;
import store.jdbsDemo.domain.validator.CustomLocalDateTimeSerializer;

public @Data class Region implements IEntity {

	private static final long serialVersionUID = 1L;

	private long id;
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtCreate;
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime dtUpdate;
	private String name;
	
	private List<Product> productList;

	public long getId() {
		return id;
	}

	public void addProduct(Product p) {
		if (productList == null) {
			productList = new ArrayList<>();
		}
		productList.add(p);
	}
}
