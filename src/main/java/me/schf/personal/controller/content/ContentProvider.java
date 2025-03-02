package me.schf.personal.controller.content;

import java.util.List;

public interface ContentProvider<T extends Content> {
	
	public List<T> getContent();

}
