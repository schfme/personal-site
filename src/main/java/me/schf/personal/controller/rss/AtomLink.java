package me.schf.personal.controller.rss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record AtomLink(
    @JacksonXmlProperty(isAttribute = true, localName = "rel")
    String rel,

    @JacksonXmlProperty(isAttribute = true, localName = "href")
    String href
) {}