package store.jdbsDemo.domain.entity;

import java.time.LocalDateTime;

public class Storage implements IEntity {


	private static final long serialVersionUID = 1L;
	
	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private Product product;
	private Region region;
	private int quantity;
	

	public long getId() {
		return id;
	}

}
