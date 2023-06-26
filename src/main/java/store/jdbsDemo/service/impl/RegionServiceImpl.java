package store.jdbsDemo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import store.jdbsDemo.dao.RegionDao;
import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.Region;
import store.jdbsDemo.domain.entity.dto.RegionDto;
import store.jdbsDemo.service.RegionService;

public class RegionServiceImpl implements RegionService {
	
	private final RegionDao regDao;

	public RegionServiceImpl(RegionDao regDao) {
		super();
		this.regDao = regDao;
	}

	@Override
	public Region create(RegionDto item) {
		Region r = new Region();
		r.setName(item.getName());
		r.setDtCreate(LocalDateTime.now());
		r.setDtUpdate(r.getDtCreate());
		return regDao.create(r);
	}

	@Override
	public Region read(long id) {
		
		return regDao.get(id);
	}

	@Override
	public List<Region> get() {
		return regDao.getAll();
	}

	@Override
	public Region update(long id, LocalDateTime dtUpdate, RegionDto item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Region> getStore() {
		return regDao.getStore();
	}

	@Override
	public void createStore(long productId, long regionId) {
		regDao.createStore(productId, regionId);
	}


	@Override
	public List<Product> getProductsByRegion(long regionId) {
		return regDao.getProductsByRegion(regionId);
	}



}
