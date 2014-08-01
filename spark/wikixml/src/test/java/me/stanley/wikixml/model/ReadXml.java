package me.stanley.wikixml.model;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import me.stanley.wikixml.util.StringUtil;

import org.junit.Test;

public class ReadXml {

  @Test
  public void test() throws IOException {
    InputStream is = ReadXml.class.getResourceAsStream("/wiki-example.xml");
    // find the expected string in stream
    long pos = StringUtil.getFirstIndex("<page>", is);
    System.out.println(pos);
  }

  @Test
  public void testFirstIndex() throws IOException {
    String test1 = "quick brown jump fox";
    String test2 = "brown jump fox";
    String test_notmatch = "quick jump fox";
    assertEquals(6, getFirstIndexFromString("brown", test1));
    assertEquals(0, getFirstIndexFromString("brown", test2));
    assertEquals(-1, getFirstIndexFromString("brown", test_notmatch));
  }

  private long getFirstIndexFromString(String str, String text)
      throws IOException {
    return StringUtil.getFirstIndex(str, new ByteArrayInputStream(text.getBytes()));
  }

}
