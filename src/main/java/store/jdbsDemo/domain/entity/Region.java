package store.jdbsDemo.domain.entity;

import java.time.LocalDateTime;

public class Region implements IEntity {


	private static final long serialVersionUID = 1L;
	
	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private String name;
	

	public long getId() {
		return id;
	}

}
