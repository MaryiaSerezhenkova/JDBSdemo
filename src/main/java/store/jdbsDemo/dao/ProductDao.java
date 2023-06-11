package store.jdbsDemo.dao;

import java.util.List;

import store.jdbsDemo.domain.entity.Product;

public interface ProductDao {
	
	long create(Product p);
	long update(Product p);
	void delete(long id);
	Product get (long id);
	List<Product> getAll();

}
