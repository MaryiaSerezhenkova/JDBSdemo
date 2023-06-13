package store.jdbsDemo.dao.impl;

import store.jdbsDemo.dao.ProductDao;
import store.jdbsDemo.dbo.HikariCPDataSource;

public class ProductDaoSingleton {
	private ProductDao productDao;
	private volatile static ProductDaoSingleton instance;

	public ProductDaoSingleton() {
		try {
			this.productDao = new ProductDaoImpl (HikariCPDataSource.getInstance());
		} catch (Exception e) {
			throw new RuntimeException("Возникли проблемы с созданием слоя доступа к данным", e);
		}
	}

	public static ProductDao getInstance() {
		if (instance == null) {
			synchronized (ProductDaoSingleton.class) {
				if (instance == null) {
					instance = new ProductDaoSingleton();
				}
			}
		}
		return instance.productDao;
	}

}
