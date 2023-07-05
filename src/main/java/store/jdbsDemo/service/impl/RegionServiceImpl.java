package store.jdbsDemo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import store.jdbsDemo.dao.RegionDao;
import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.Region;
import store.jdbsDemo.domain.entity.dto.RegionDto;
import store.jdbsDemo.domain.entity.mapper.impl.RegionMapper;
import store.jdbsDemo.service.RegionService;

public class RegionServiceImpl implements RegionService {
	
	private final RegionDao regDao;
	private RegionMapper mapper;

	public RegionServiceImpl(RegionDao regDao) {
		super();
		this.regDao = regDao;
	}
	

	public RegionServiceImpl(RegionDao regDao, RegionMapper mapper) {
		super();
		this.regDao = regDao;
		this.mapper = mapper;
	}


	@Override
	public RegionDto create(RegionDto item) {
		Region r = new Region();
		r.setName(item.getName());
		r.setDtCreate(LocalDateTime.now());
		r.setDtUpdate(r.getDtCreate());
		return mapper.toDTO(regDao.create(r));
	}

	@Override
	public RegionDto read(long id) {
		
		return mapper.toDTO(regDao.get(id));
	}

	@Override
	public List<Region> get() {
		return regDao.getAll();
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
