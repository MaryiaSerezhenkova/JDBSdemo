package store.jdbsDemo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import store.jdbsDemo.dao.ProductDao;
import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.dto.ProductDto;
import store.jdbsDemo.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private final ProductDao prodDao;

	public ProductServiceImpl(ProductDao prodDao) {
		super();
		this.prodDao = prodDao;
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

		return prodDao.update(id, dtUpdate, readed);
	}

	public List<Product> getByCategory(long categoryId) {
		return prodDao.getByCategory(categoryId);
	}
}
