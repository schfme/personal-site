package me.schf.personal.data;

import org.springframework.core.convert.converter.Converter;

public class TagReadConverter implements Converter<String, Tag> {

	@Override
	public Tag convert(String source) {
		return Tag.valueOf(source.toUpperCase());
	}

}
