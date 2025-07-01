package me.schf.personal.controller.rss;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class Rfc822DateSerializer extends StdSerializer<OffsetDateTime>{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter RFC_822_FORMATTER = DateTimeFormatter
			.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).withZone(ZoneOffset.UTC);

        public Rfc822DateSerializer() {
            super(OffsetDateTime.class);
        }

        @Override
        public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value == null) {
                gen.writeNull();
                return;
            }
            String formatted = RFC_822_FORMATTER.format(value);
            gen.writeString(formatted);
        }
    }