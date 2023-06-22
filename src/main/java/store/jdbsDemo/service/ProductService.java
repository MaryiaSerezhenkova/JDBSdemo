package store.jdbsDemo.service;

import java.util.List;

import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.dto.ProductDto;

public interface ProductService extends IService<Product, ProductDto> {
	
	List<Product> getByCategory(long categoryId);

}
