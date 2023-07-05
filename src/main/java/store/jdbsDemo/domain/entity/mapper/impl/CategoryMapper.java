package store.jdbsDemo.domain.entity.mapper.impl;

import store.jdbsDemo.domain.entity.Category;
import store.jdbsDemo.domain.entity.dto.CategoryDto;

public class CategoryMapper extends BaseMapper<Category, CategoryDto> {
	
	
	
	@Override
	protected Class<Category> getEntityClass() {
		return Category.class;
	}

	@Override
	protected Class<CategoryDto> getDtoClass() {
		return CategoryDto.class;
	}

}
