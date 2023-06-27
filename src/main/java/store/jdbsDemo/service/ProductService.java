package store.jdbsDemo.service;

import java.time.LocalDateTime;
import java.util.List;

import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.dto.ProductDto;

public interface ProductService extends IService<Product, ProductDto> {
	
	List<Product> getByCategory(long categoryId);
	Product update(long id, LocalDateTime dtUpdate, ProductDto item);
    void delete(long id, LocalDateTime dtUpdate);

}
