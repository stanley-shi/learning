package me.stanley.wikixml.util;

import java.io.IOException;
import java.io.InputStream;

public class XMLWrappedInputStream extends InputStream {
  InputStream payload;
  long actualIndex = 0;
  long curPos = 0;
  // This is the tag that we will prepend to the input stream to make it a valid
  // XML structure
  public static final String HEAD_TAG = "XML_ROOT_WRAPPER";
  public static final byte[] HEAD_TAG_BYTES= HEAD_TAG.getBytes();
  
  public XMLWrappedInputStream(InputStream input) {
    payload = input;
  }

  @Override
  public int read() throws IOException {
    // TODO Auto-generated method stub
    
    return 0;
  }

}
