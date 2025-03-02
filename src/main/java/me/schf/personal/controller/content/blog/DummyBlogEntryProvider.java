package me.schf.personal.controller.content.blog;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import me.schf.personal.controller.content.ContentProvider;

@Component
public class DummyBlogEntryProvider implements ContentProvider<BlogEntry> {

	@Override
	public List<BlogEntry> getContent() {
		BlogEntry entry1 = new BlogEntry("blog entry 1", LocalDate.now().minusYears(10), "blog-entry-1");
		BlogEntry entry2 = new BlogEntry("blog entry 2", LocalDate.now().minusYears(5), "blog-entry-2");
		BlogEntry entry3 = new BlogEntry("blog entry 3", LocalDate.now().minusYears(1), "blog-entry-3");

		return Arrays.asList(entry1, entry2, entry3);
	}

}
