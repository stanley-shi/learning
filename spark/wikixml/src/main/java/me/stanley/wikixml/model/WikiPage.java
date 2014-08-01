package me.stanley.wikixml.model;

public class WikiPage {
  public static class WikiPageRevision {
    long id;
    long parentId;
    String ts;
    String minor;
    String comment;
    String text;
    String sha1;
    String model;
    String format;
    
    
    String contributor_name;
    long contributor_id;

  }

  String title;
  String ns;
  long pageId;
  String redirect;
  WikiPageRevision[] revision;
  
  
  public String toString() {
    return toJsonString();
  }

  public String toJsonString() {
    // TODO
    return null;
  }

  public static WikiPage fromJsonString() {
    // TODO
    return null;
  }
}
