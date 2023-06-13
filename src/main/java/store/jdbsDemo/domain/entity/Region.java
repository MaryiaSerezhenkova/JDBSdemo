package store.jdbsDemo.domain.entity;

import java.time.LocalDateTime;

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
	

	public long getId() {
		return id;
	}

}
