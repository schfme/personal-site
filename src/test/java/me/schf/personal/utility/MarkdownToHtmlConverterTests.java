package me.schf.personal.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarkdownToHtmlConverterTests {

	private MarkdownToHtmlConverter converter;

	@BeforeEach
	public void setUp() {
		converter = new MarkdownToHtmlConverter();
	}

	@Test
	void test_helloWorld() {
		String markdown = "# Hello World";
		String expectedHtml = "<h1>Hello World</h1>\n";

		String actualHtml = converter.convertToHtml(markdown);

		assertEquals(expectedHtml, actualHtml);
	}

}
