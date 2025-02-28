package me.schf.personal.service;

import java.time.LocalDate;
import java.util.List;

public interface HeadlineProvider {

	public List<String> getHeadlinesOn(LocalDate localDate);

}
