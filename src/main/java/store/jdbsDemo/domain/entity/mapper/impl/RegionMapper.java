package store.jdbsDemo.domain.entity.mapper.impl;

import store.jdbsDemo.domain.entity.Region;
import store.jdbsDemo.domain.entity.dto.RegionDto;

public class RegionMapper extends BaseMapper<Region, RegionDto> {
	
	
	
	@Override
	protected Class<Region> getEntityClass() {
		return Region.class;
	}

	@Override
	protected Class<RegionDto> getDtoClass() {
		return RegionDto.class;
	}

}
