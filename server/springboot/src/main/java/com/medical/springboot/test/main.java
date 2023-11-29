package com.medical.springboot.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class main {
  public static void main(String[] args) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date date = formatter.parse("2020-01-13");
      Date date2 = formatter.parse("2020-01-13");
      if(date.before(date2)){
        System.out.println("date before date2");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
