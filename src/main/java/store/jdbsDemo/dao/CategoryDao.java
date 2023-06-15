package store.jdbsDemo.dao;

import java.util.List;

import store.jdbsDemo.domain.entity.Category;

public interface CategoryDao {
	
	Category create(Category c);
	Category get (long id);
	List<Category> getAll();

}


