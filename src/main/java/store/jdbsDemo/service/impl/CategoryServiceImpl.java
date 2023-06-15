package store.jdbsDemo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import store.jdbsDemo.dao.CategoryDao;
import store.jdbsDemo.domain.entity.Category;
import store.jdbsDemo.domain.entity.dto.CategoryDto;
import store.jdbsDemo.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryDao catDao;
	

	public CategoryServiceImpl(CategoryDao catDao) {
		super();
		this.catDao = catDao;
	}
	@Override
	public Category create(CategoryDto item) {
		Category c = new Category();
		c.setName(item.getName());
		c.setDtCreate(LocalDateTime.now());
		c.setDtUpdate(c.getDtCreate());
		return catDao.create(c);
	}

	@Override
	public Category read(long id) {
		return catDao.get(id);
	}

	@Override
	public List<Category> get() {
		return catDao.getAll();
	}

	@Override
	public Category update(long id, LocalDateTime dtUpdate, CategoryDto item) {
		return null;
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		
	}

}
