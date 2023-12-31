package store.jdbsDemo.dao;

import java.time.LocalDateTime;
import java.util.List;

import store.jdbsDemo.domain.entity.Product;

public interface ProductDao {
	
	Product create(Product p);
	Product update(long id, LocalDateTime dtUpdate, Product p);
	void delete(long id, LocalDateTime dtUpdate);
	Product get (long id);
	List<Product> getAll();
	List<Product> getByCategory(long categoryId);

}
