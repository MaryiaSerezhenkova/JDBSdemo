package store.jdbsDemo.dao.impl;

import store.jdbsDemo.dao.RegionDao;
import store.jdbsDemo.dbo.HikariCPDataSource;

public class RegionDaoSingleton {
	private RegionDao regionDao;
	private volatile static RegionDaoSingleton instance;

	public RegionDaoSingleton() {
		try {
			this.regionDao = new RegionDaoImpl (HikariCPDataSource.getInstance());
		} catch (Exception e) {
			throw new RuntimeException("Возникли проблемы с созданием слоя доступа к данным", e);
		}
	}

	public static RegionDao getInstance() {
		if (instance == null) {
			synchronized (RegionDaoSingleton.class) {
				if (instance == null) {
					instance = new RegionDaoSingleton();
				}
			}
		}
		return instance.regionDao;
	}

}
