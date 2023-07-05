package store.jdbsDemo.domain.entity.mapper.impl;

import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.dto.ProductDto;

public class ProductMapper extends BaseMapper<Product, ProductDto> {

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	protected Class<ProductDto> getDtoClass() {
		return ProductDto.class;
	}

//	public ProductMapper() {
//		super();
//	}

//	@Override
//	public Product toEntity(ProductDto dto) {
//		Product entity = new Product();
//		entity.setName(dto.getName());
//		entity.setPrice(dto.getPrice());
//		entity.setCategoryId(dto.getCategoryId());
//		entity.setId(dto.getId());
//		entity.setDtCreate(dto.getDtCreate());
//		entity.setDtUpdate(dto.getDtUpdate());
//		return entity;
//		
//	}
//
//	@Override
//	public ProductDto toDTO(Product entity) {
//		ProductDto dto = new ProductDto();
//		dto.setName(entity.getName());
//		dto.setPrice(entity.getPrice());
//		dto.setCategoryId(entity.getCategoryId());
//		dto.setId(entity.getId());
//		dto.setDtCreate(entity.getDtCreate());
//		dto.setDtUpdate(entity.getDtUpdate());
//		
//		return dto;
//	}
//
//	

}