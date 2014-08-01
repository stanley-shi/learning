package me.stanley.wikixml.model;

public class TestReturn {
  public static void main(String[] args){
    System.out.println(test());
  }
  
  public static String test(){
    try{
      return test2();
    }
    finally{
      System.out.println("lalala");
    }
  }
  
  public static String test2(){
    System.out.println("test2");
    return "shengjun";
  }
  
}
