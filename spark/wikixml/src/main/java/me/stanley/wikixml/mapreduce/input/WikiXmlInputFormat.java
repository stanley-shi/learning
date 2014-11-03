package me.stanley.wikixml.mapreduce.input;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class WikiXmlInputFormat extends FileInputFormat<Text, Text>{

  @Override
  public WikiXmlRecordReader createRecordReader(InputSplit arg0,
      TaskAttemptContext arg1) throws IOException, InterruptedException {
    return new WikiXmlRecordReader();
  }

}
