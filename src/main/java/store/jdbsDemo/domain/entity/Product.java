package store.jdbsDemo.domain.entity;

import java.time.LocalDateTime;

import lombok.Data;

public @Data class Product implements IEntity {


	private static final long serialVersionUID = 1L;
	
	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private String name;
	private double price;
	private Category category;
	private long categoryId;

	public long getId() {
		return id;
	}
}
