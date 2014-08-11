package me.stanley.wikixml.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.stream.XMLEventFactory;
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
    XMLEventFactory efac = XMLEventFactory.newInstance();
    writer.add(efac.createStartDocument());
    try {
      XMLEvent event = null;
      boolean ending = false;
      boolean isStarted = false;
      while (reader.hasNext()) {
        event = reader.nextEvent();
        switch (event.getEventType()) {
        case XMLStreamConstants.START_DOCUMENT:
          LOG.debug("START_DOCUMENT event is not supposed to be here");
          continue;
        case XMLStreamConstants.START_ELEMENT:
          StartElement sevent = event.asStartElement();
          if (LOG.isTraceEnabled())
            LOG.trace("Start Element: " + sevent.getName());
          if (!isStarted)
            if (sevent.getName().getLocalPart().equals(elementName)) {
              LOG.debug("Found element " + elementName + ", now start writing..");
              isStarted = true;
            }
          break;
        case XMLStreamConstants.END_ELEMENT:
          EndElement eevent = event.asEndElement();
          if (LOG.isTraceEnabled())
            LOG.trace("End Element:" + eevent.getName());
          if (isStarted)
            if (eevent.getName().getLocalPart().equalsIgnoreCase(elementName)) {
              ending = true;
            }
          break;
        case XMLStreamConstants.END_DOCUMENT:
          LOG.warn("END_DOCUMENTATION event is not expected here.");
          break;
        default:
          break;
        }
        if (isStarted) {
//          LOG.debug("writing: " + event);
          writer.add(event);
        }
        if (ending)
          break;
      }
    } finally {
    }
    writer.add(efac.createEndDocument());
    writer.flush();
    writer.close();

    return new String(output.toByteArray());
  }
}
