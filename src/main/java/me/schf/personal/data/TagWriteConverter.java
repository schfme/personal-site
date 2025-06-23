package me.schf.personal.data;

import org.springframework.core.convert.converter.Converter;

public class TagWriteConverter implements Converter<Tag, String> {

	@Override
	public String convert(Tag source) {
		return source.name().toLowerCase();
	}

}
