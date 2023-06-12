package store.jdbsDemo.domain.validator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class JsonConverter {

	

	public static LocalDateTime convert(long unixTime) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime), TimeZone.getDefault().toZoneId());
	}

}