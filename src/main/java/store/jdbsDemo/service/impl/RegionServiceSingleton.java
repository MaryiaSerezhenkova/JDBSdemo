package store.jdbsDemo.service.impl;

import store.jdbsDemo.dao.impl.RegionDaoSingleton;
import store.jdbsDemo.service.RegionService;

public class RegionServiceSingleton {
	 private final RegionService regionService;
	
    private volatile static RegionServiceSingleton firstInstance = null;

    public RegionServiceSingleton() {
        this.regionService = new RegionServiceImpl(RegionDaoSingleton.getInstance());
    }

    public static RegionService getInstance() {
        synchronized (RegionServiceSingleton.class) {
            if (firstInstance == null) {
                firstInstance = new RegionServiceSingleton();
            }
        }
        return firstInstance.regionService;
    }
}
