package me.schf.personal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class DummyHeadlineProvider implements HeadlineProvider {

	@Override
	public List<String> getHeadlinesOn(LocalDate localDate) {
		return Stream.generate(() -> "minecraft").limit(20).toList();
	}

}
