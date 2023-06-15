package store.jdbsDemo.dao.impl;

import store.jdbsDemo.dao.CategoryDao;
import store.jdbsDemo.dbo.HikariCPDataSource;

public class CategoryDaoSingleton {
	private CategoryDao categoryDao;
	private volatile static CategoryDaoSingleton instance;

	public CategoryDaoSingleton() {
		try {
			this.categoryDao = new CategoryDaoImpl (HikariCPDataSource.getInstance());
		} catch (Exception e) {
			throw new RuntimeException("Возникли проблемы с созданием слоя доступа к данным", e);
		}
	}

	public static CategoryDao getInstance() {
		if (instance == null) {
			synchronized (CategoryDaoSingleton.class) {
				if (instance == null) {
					instance = new CategoryDaoSingleton();
				}
			}
		}
		return instance.categoryDao;
	}

}
