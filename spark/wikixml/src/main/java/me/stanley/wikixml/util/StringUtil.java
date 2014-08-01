package me.stanley.wikixml.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class StringUtil {
  public static class MyInt {
    public MyInt(int i) {
      data = i;
    }
    int data;
  }

  public static long getFirstIndex(String str, InputStream is)
      throws IOException {
    byte[] bstr = str.getBytes();
    Set<MyInt> matches = new HashSet<MyInt>();
    Set<MyInt> toRemove = new HashSet<MyInt>();
    int pos = 0;
    while (is.available() > 0) {
      int data = is.read();
      pos++;
      if (matches.size() > 0) {
        // has a previous match
        for (MyInt i : matches) {
          if (data == bstr[i.data + 1]) {
            i.data++;
            if (i.data == bstr.length - 1) {
              // matches!!!
              return pos - bstr.length;
            }
          } else
            toRemove.add(i);
        }
        matches.removeAll(toRemove);
        toRemove.clear();
      }
      if (data == bstr[0]) {
        matches.add(new MyInt(0));
      }
    }
    return -1;
  }
}
