package store.jdbsDemo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import store.jdbsDemo.dao.ProductDao;
import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.dto.ProductDto;
import store.jdbsDemo.domain.entity.mapper.impl.ProductMapper;
import store.jdbsDemo.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private final ProductDao prodDao;
	private ProductMapper mapper;

	public ProductServiceImpl(ProductDao prodDao) {
		super();
		this.prodDao = prodDao;
	}
	public ProductServiceImpl(ProductDao prodDao, ProductMapper mapper) {
		super();
		this.prodDao = prodDao;
		this.mapper = mapper;
	}

	public ProductDto read(long id) {
		return mapper.toDTO(prodDao.get(id));
	}

	public List<Product> get() {
		return prodDao.getAll();
	}

	public void delete(long id, LocalDateTime dtUpdate) {
		Product readed = prodDao.get(id);
		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
		}

		prodDao.delete(id, dtUpdate);

	}

	

	@Override
	public ProductDto update(long id, LocalDateTime dtUpdate, ProductDto item) {
		Product readed = prodDao.get(id);

		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
		}

		readed.setDtUpdate(LocalDateTime.now());
		readed.setName(item.getName());
		readed.setCategoryId(item.getCategoryId());

		return mapper.toDTO(prodDao.update(id, dtUpdate, readed));
	}

	public List<Product> getByCategory(long categoryId) {
		return prodDao.getByCategory(categoryId);
	}
	public ProductDto create(ProductDto item) {
		Product p = new Product();
		p.setName(item.getName());
		p.setPrice(item.getPrice());
		p.setCategoryId(item.getCategoryId());
		p.setDtCreate(LocalDateTime.now());
		p.setDtUpdate(p.getDtCreate());
		return mapper.toDTO(prodDao.create(p));
		
	}
	
}
