package me.stanley.wikixml.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLStreamException;

import org.junit.Test;

public class XmlUtilsTest {

  @Test
  public void testGetFullElement() throws XMLStreamException, IOException {
    InputStream input = XmlUtilsTest.class.getResourceAsStream("/page-snippet.txt");
    String res = XmlUtils.getFullElement(input, "page");
    String res2 = XmlUtils.getFullElement(input, "page");
    System.out.println(res);
    System.out.println(res2);
  }

}
