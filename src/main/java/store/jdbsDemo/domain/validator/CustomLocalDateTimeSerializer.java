package store.jdbsDemo.domain.validator;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    protected CustomLocalDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    protected CustomLocalDateTimeSerializer() {
        this(null);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider sp) throws IOException {
        gen.writeString(Long.toString(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
     //   gen.writeNumber(value.toInstant(Z));
    }

}