package store.jdbsDemo.service.impl;

import store.jdbsDemo.dao.impl.CategoryDaoSingleton;
import store.jdbsDemo.domain.entity.mapper.impl.CategoryMapper;
import store.jdbsDemo.service.CategoryService;

public class CategoryServiceSingleton {
	 private final CategoryService categoryService;
	    private volatile static CategoryServiceSingleton firstInstance = null;

	    public CategoryServiceSingleton() {
	        this.categoryService = new CategoryServiceImpl(CategoryDaoSingleton.getInstance(), new CategoryMapper());
	    }

	    public static CategoryService getInstance() {
	        synchronized (CategoryServiceSingleton.class) {
	            if (firstInstance == null) {
	                firstInstance = new CategoryServiceSingleton();
	            }
	        }
	        return firstInstance.categoryService;
	    }
	}


