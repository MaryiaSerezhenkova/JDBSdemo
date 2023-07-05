package store.jdbsDemo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import store.jdbsDemo.dao.CategoryDao;
import store.jdbsDemo.domain.entity.Category;
import store.jdbsDemo.domain.entity.dto.CategoryDto;
import store.jdbsDemo.domain.entity.mapper.impl.CategoryMapper;
import store.jdbsDemo.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryDao catDao;
	private CategoryMapper mapper;
	

	public CategoryServiceImpl(CategoryDao catDao) {
		super();
		this.catDao = catDao;
	}
	
	public CategoryServiceImpl(CategoryDao catDao, CategoryMapper mapper) {
		super();
		this.catDao = catDao;
		this.mapper = mapper;
	}

	@Override
	public CategoryDto create(CategoryDto item) {
		Category c = new Category();
		c.setName(item.getName());
		c.setDtCreate(LocalDateTime.now());
		c.setDtUpdate(c.getDtCreate());
		return mapper.toDTO(catDao.create(c));
	}

	@Override
	public CategoryDto read(long id) {
		return mapper.toDTO(catDao.get(id));
	}

	@Override
	public List<Category> get() {
		return catDao.getAll();
	}

	

}
