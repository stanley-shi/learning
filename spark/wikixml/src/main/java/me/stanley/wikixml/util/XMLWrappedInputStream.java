package me.stanley.wikixml.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XMLWrappedInputStream extends InputStream {
  InputStream payloadStream;
  InputStream wrapStartStream;
  InputStream wrapEndStream;
  long actualIndex = 0;
  long curPos = 0;
  // This is the tag that we will prepend to the input stream to make it a valid
  // XML structure
  public static final String HEAD_TAG = "<XML_ROOT_WRAPPER>";
  public static final String END_TAG = "</XML_ROOT_WRAPPER>";
  public static final byte[] HEAD_TAG_BYTES = HEAD_TAG.getBytes();
  public static final byte[] END_TAG_BYTES = END_TAG.getBytes();

  public XMLWrappedInputStream(InputStream input) {
    payloadStream = input;
    wrapStartStream = new ByteArrayInputStream(HEAD_TAG_BYTES);
    wrapEndStream = new ByteArrayInputStream(END_TAG_BYTES);
  }

  @Override
  public int read() throws IOException {
    if (wrapStartStream.available() > 0)
      return wrapStartStream.read();
    if (payloadStream.available() > 0)
      return payloadStream.read();
    return wrapEndStream.read();
  }

  public void close() throws IOException {
    try {
      wrapStartStream.close();
    } catch (IOException e) {
      // Closing this wrapStream, should swallow this error;
      e.printStackTrace();
    }
    try {
      wrapEndStream.close();
    } catch (IOException e) {
      // Closing this wrapStream, should swallow this error;
      e.printStackTrace();
    }

    payloadStream.close();
  }

}
