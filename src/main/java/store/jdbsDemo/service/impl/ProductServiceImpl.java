package store.jdbsDemo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import store.jdbsDemo.dao.ProductDao;
import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.dto.ProductDto;
import store.jdbsDemo.service.ProductService;

public class ProductServiceImpl implements ProductService{
	
	private final ProductDao prodDao;

	public ProductServiceImpl(ProductDao prodDao) {
		super();
		this.prodDao = prodDao;
	}

	public Product create(Product item) {
		item.setDtCreate(LocalDateTime.now());
		item.setDtUpdate(item.getDtCreate());
		return prodDao.create(item);
	}

	public Product read(long id) {
		return prodDao.get(id);
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
	public Product create(ProductDto item) {
		Product p = new Product();
		p.setName(item.getName());
		p.setPrice(item.getPrice());
		p.setCategoryId(item.getCategoryId());
		p.setDtCreate(LocalDateTime.now());
		p.setDtUpdate(p.getDtCreate());
		return prodDao.create(p);
	}
	

	@Override
	public Product update(long id, LocalDateTime dtUpdate, ProductDto item) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
