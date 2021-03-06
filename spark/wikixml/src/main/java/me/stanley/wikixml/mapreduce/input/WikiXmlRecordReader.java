package me.stanley.wikixml.mapreduce.input;

import java.io.IOException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import me.stanley.wikixml.util.StringUtil;
import me.stanley.wikixml.util.XMLWrappedInputStream;
import me.stanley.wikixml.util.XmlUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

/**
 * key is the title of the page, value is the content of the page;
 * 
 * @author stanley
 * 
 */
public class WikiXmlRecordReader extends RecordReader<Text, Text> {
  private static Log LOG = LogFactory.getLog(WikiXmlRecordReader.class);
  private static final String HEAD_STRING = "<page>";
  private long start;
  private long end;
  long curPos;

  private FSDataInputStream fileIn;
  boolean noRecordInCurrentSplit = false;

  static XMLInputFactory factory = XMLInputFactory.newInstance();

  Text curKey;
  Text curValue;
  private XMLEventReader xmlEvtReader;

  @Override
  public void close() throws IOException {
    fileIn.close();
  }

  @Override
  public Text getCurrentKey() throws IOException, InterruptedException {
    return curKey;
  }

  @Override
  public Text getCurrentValue() throws IOException, InterruptedException {
    return curValue;
  }

  @Override
  public float getProgress() throws IOException, InterruptedException {
    if (curPos > end)
      return 1.0f;
    if (curPos < start)
      return 0.0f;
    return (curPos - start) * 1.0f / (end - curPos);
  }

  @Override
  public void initialize(InputSplit genericSplit, TaskAttemptContext context)
      throws IOException, InterruptedException {

    FileSplit split = (FileSplit) genericSplit;
    LOG.info("Initializing with split: " + split.getPath());
    Configuration job = context.getConfiguration();
    start = split.getStart();
    end = start + split.getLength();
    final Path file = split.getPath();

    // open the file and seek to the start of the split
    final FileSystem fs = file.getFileSystem(job);
    fileIn = fs.open(file);
    fileIn.seek(start);

    if (start != 0) {
      // if this is not the first split, we need to skip to the first boundary
      // of our input records;
      long offset = StringUtil.getFirstIndex(HEAD_STRING, fileIn);
      if (offset == 0) {
        fileIn.seek(start + 1);
        offset = StringUtil.getFirstIndex(HEAD_STRING, fileIn);
        if (offset < 0) {
          // no record here???
          noRecordInCurrentSplit = true;
        } else
          start += offset + 1;
      } else if (offset < 0) {
        noRecordInCurrentSplit = true;
      }
      start += offset;
    } else {
      // this is the first split, but we still need to skip the headers;
      long offset = StringUtil.getFirstIndex(HEAD_STRING, fileIn);
      if (offset < 0) {
        noRecordInCurrentSplit = true;
      } else
        start += offset;
    }
    if (!noRecordInCurrentSplit) {
      fileIn.seek(start);
      curPos = start;
    }

    XMLWrappedInputStream xmlinput = new XMLWrappedInputStream(fileIn);
    try {
      xmlEvtReader = factory.createXMLEventReader(xmlinput);
    } catch (XMLStreamException e) {
      throw new IOException("Create xml event reader from split file failed!",
          e);
    }

  }

  @Override
  public boolean nextKeyValue() throws IOException, InterruptedException {
    if (noRecordInCurrentSplit)
      return false;
    if (curKey == null) {
      curKey = new Text();
    }
    if (curValue == null) {
      curValue = new Text();
    }

    // NOTE: in the last split, there're some extra texts that are outside of
    // all pages, we need to skip them
    try {
      if (curPos > end)
        return false;
      String pageStr = XmlUtils.getFullElement(xmlEvtReader, "page");
      curPos += pageStr.length();
      if (pageStr.length() <= 0)
        return false;
      String string = StringUtil.getTitle(pageStr);
      curKey.set(string);
      curValue.set(pageStr);
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
    return true;
  }

}
