package me.stanley.wikixml.mapreduce.input;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import me.stanley.wikixml.model.WikiPage;

import org.apache.hadoop.io.Writable;

public class WikiPageWritable implements Writable{
  WikiPage page;

  /**
   * The enclosing WikiPage item is converted to an JSon string and write to the DataOutput
   */
  public void write(DataOutput out) throws IOException {
    // TODO Auto-generated method stub
    
  }

  /**
   * Read from the DataInput and convert the JSon string to WikiPage object
   */
  public void readFields(DataInput in) throws IOException {
    // TODO Auto-generated method stub
    
  }

  public WikiPage getPage() {
    return page;
  }

  public void setPage(WikiPage page) {
    this.page = page;
  }

}
