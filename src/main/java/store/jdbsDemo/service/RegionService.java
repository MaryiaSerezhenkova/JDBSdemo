package store.jdbsDemo.service;

import java.util.List;

import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.Region;
import store.jdbsDemo.domain.entity.dto.RegionDto;

public interface RegionService extends IService<Region, RegionDto>{
	
	List<Region> getStore();
	void createStore(long productId, long regionId);
	public List<Product> getProductsByRegion(long regionId); 
}
