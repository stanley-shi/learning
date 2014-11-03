package me.stanley.wikixml.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import me.stanley.wikixml.mapreduce.input.WikiXmlRecordReader;
import me.stanley.wikixml.util.XMLWrappedInputStream;
import me.stanley.wikixml.util.XmlUtils;

import org.junit.Test;

public class XmlUtilsTest {
  static XMLInputFactory factory = XMLInputFactory.newInstance();

  @Test
  public void testGetFullElement() throws XMLStreamException, IOException {
    InputStream input = XmlUtilsTest.class
        .getResourceAsStream("/page-snippet.txt");
    XMLWrappedInputStream xmlinput = new XMLWrappedInputStream(input);
    XMLEventReader evtReader = factory.createXMLEventReader(xmlinput);
    String res = XmlUtils.getFullElement(evtReader, "page");
    String res2 = XmlUtils.getFullElement(evtReader, "page");
    System.out.println(res);
    System.out.println(StringUtil.getTitle(res));
    // System.out.println("=======");
    // System.out.println(res2);
  }

  // @Test
  public void pureTest() throws XMLStreamException {
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLOutputFactory oFac = XMLOutputFactory.newInstance();
    InputStream input = XmlUtilsTest.class
        .getResourceAsStream("/page-snippet.txt");
    XMLWrappedInputStream xmlinput = new XMLWrappedInputStream(input);
    XMLEventReader reader = factory.createXMLEventReader(xmlinput);

    XMLEventWriter writer = oFac.createXMLEventWriter(System.out);
    while (reader.hasNext()) {
      XMLEvent evt = reader.nextEvent();
      writer.add(evt);
    }
    writer.flush();
    reader.close();
    writer.close();
  }
}
