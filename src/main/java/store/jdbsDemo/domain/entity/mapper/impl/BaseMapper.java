package store.jdbsDemo.domain.entity.mapper.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import store.jdbsDemo.domain.entity.IEntity;
import store.jdbsDemo.domain.entity.mapper.Mapper;

public abstract class BaseMapper<E extends IEntity, D> implements Mapper<E, D> {
	protected ModelMapper modelMapper;
	private Class<E> entityType;
	private Class<D> dtoType;

	protected void init() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public BaseMapper() {
		super();
		this.entityType = getEntityClass();
		this.dtoType = getDtoClass();
		this.modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@Override
	public D toDTO(E entity) {
		if (entity == null) {
			return null;
		}
		return modelMapper.map(entity, dtoType);
	}

	@Override
	public E toEntity(D dto) {
		if (dto == null) {
			return null;
		}
		return modelMapper.map(dto, entityType);
	}
	protected abstract Class<E> getEntityClass();
	protected abstract Class<D> getDtoClass();
	
	

}