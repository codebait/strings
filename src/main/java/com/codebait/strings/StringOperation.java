package com.codebait.strings;

import java.lang.reflect.Field;

public class StringOperation {

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    String a = "Hello";
    String b = "Hello";
    StringBuilder builder = new StringBuilder();
    builder.append("Hello");
    Field value = String.class.getDeclaredField("value");
    value.setAccessible(true);
    byte[] newArray = "World".getBytes();
    value.set(a, newArray);
    String c = "Hello";
    String d = new String("Hello");
    builder.append("Hello");

    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    System.out.println(d);
    System.out.println("Hello");
    System.out.println(Op.word);
    System.out.println(builder.toString());

  }

  public static class Op {

    public static final String word = "Hello";

  }
}
