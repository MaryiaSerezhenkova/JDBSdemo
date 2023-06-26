package store.jdbsDemo.dao;

import java.util.List;
import java.util.Map;

import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.Region;

public interface RegionDao {

	Region create(Region r);

	Region get(long id);

	List<Region> getAll();

	List<Product> getProductsByRegion(long regionId);

	List<Region> getStore();

	void createStore(long productId, long regionId);
	


}
