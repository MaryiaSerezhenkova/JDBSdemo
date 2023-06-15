package store.jdbsDemo.dao;

import java.util.List;

import store.jdbsDemo.domain.entity.Region;

public interface RegionDao {

	Region create(Region r);
	Region get (long id);
	List<Region> getAll();
}
