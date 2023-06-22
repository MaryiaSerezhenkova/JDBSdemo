package store.jdbsDemo.domain.validator;

import java.util.ArrayList;
import java.util.List;

import store.jdbsDemo.domain.entity.dto.RegionDto;

public class RegionValidator {
	public void validate(RegionDto dto) {
		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("DTO is null");
		}
		if (dto.getName() == null || dto.getName().isBlank()) {
			errors.add("Name is not valid");
		}

		if (errors.size() > 0) {
			throw new ValidationException(String.join(", ", errors));
		}
	}
}
