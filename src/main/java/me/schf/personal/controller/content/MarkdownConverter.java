package me.schf.personal.controller.content;

import org.springframework.stereotype.Component;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

@Component
public class MarkdownConverter {

	private final Parser parser;
	private final HtmlRenderer renderer;

	public MarkdownConverter() {
		this.parser = Parser.builder().build();
		this.renderer = HtmlRenderer.builder().build();
	}

	public String convertToHtml(String markdown) {
		return renderer.render(parser.parse(markdown));
	}
}