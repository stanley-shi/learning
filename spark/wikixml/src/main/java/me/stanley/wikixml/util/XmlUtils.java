package me.stanley.wikixml.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XmlUtils {
  private static Log LOG = LogFactory.getLog(XmlUtils.class);
  static XMLInputFactory factory = XMLInputFactory.newInstance();
  static XMLOutputFactory oFac = XMLOutputFactory.newInstance();

  /**
   * XMLEventReader will read data into an internal buffer
   * 
   * @param input
   * @param elementName
   * @return
   * @throws XMLStreamException
   * @throws IOException
   */
  public static String getFullElement(XMLEventReader reader, String elementName)
      throws XMLStreamException, IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    XMLEventWriter writer = oFac.createXMLEventWriter(output);
    try {
      XMLEvent event = null;
      boolean ending = false;
      boolean starting = false;
      while (true) {
        event = reader.nextEvent();
        switch (event.getEventType()) {
        case XMLStreamConstants.START_DOCUMENT:
          LOG.debug("START_DOCUMENT event is not supposed to be here");
          continue;
        case XMLStreamConstants.START_ELEMENT:
          StartElement sevent = event.asStartElement();
          if (LOG.isTraceEnabled())
            LOG.trace("Start Element: " + sevent.getName());
          if (sevent.getName().getLocalPart().equals(elementName)) {
            LOG.debug("Found element " + elementName + ", start writing..");
            starting = true;
            writer.add(event);
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          EndElement evt = event.asEndElement();
          if(starting){
            writer.add(event);
          }
          if (evt.getName().toString().equalsIgnoreCase(elementName)) {
            ending = true;
          }
          LOG.debug("End Element:" + evt.getName());
          
          break;
        case XMLStreamConstants.END_DOCUMENT:
          LOG.warn("END_DOCUMENTATION event is not expected here.");
          break;
        default:
          writer.add(event);
        }
        if (ending)
          break;
      }
    } finally {
    }
    return new String(output.toByteArray());
  }
}
