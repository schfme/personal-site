package me.schf.personal.controller.rss;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class AtomLinkSerializer extends StdSerializer<AtomLink> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AtomLinkSerializer() {
        super(AtomLink.class);
    }

    @Override
    public void serialize(AtomLink link, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (!(gen instanceof ToXmlGenerator)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        ToXmlGenerator xmlGen = (ToXmlGenerator) gen;
        XMLStreamWriter staxWriter = xmlGen.getStaxWriter();

        try {
            staxWriter.setPrefix("atom", "http://www.w3.org/2005/Atom");
            staxWriter.writeStartElement("atom", "link", "http://www.w3.org/2005/Atom");
            staxWriter.writeAttribute("rel", link.rel());
            staxWriter.writeAttribute("href", link.href());
            staxWriter.writeEndElement();
        } catch (XMLStreamException e) {
            throw new IOException("Failed to write atom:link", e);
        }
    }
}