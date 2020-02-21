package com.codebait.strings;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;


public class StringPoolTest {

  @Test
  void stringPool1() throws NoSuchFieldException, IllegalAccessException {
    String hi1 = "Hello";
    String hi2 = "Hello";

    assertSame(hi1, hi2, "String");
    assertSame(getInternalValue(hi1), getInternalValue(hi2), "Strings value array");
  }

  @Test
  void stringPool2() throws NoSuchFieldException, IllegalAccessException {
    String hi1 = "Hello";
    String hi2 = new String("Hello");

    assertNotSame(hi1, hi2, "String");
    assertSame(getInternalValue(hi1), getInternalValue(hi2), "Strings value array");

  }

  @Test
  void stringPool3() throws NoSuchFieldException, IllegalAccessException {
    String hi1 = "Hello";
    String hi2 = new String(hi1);

    assertNotSame(hi1, hi2, "String");
    assertSame(getInternalValue(hi1), getInternalValue(hi2), "Strings value array");

  }

  @Test
  void stringPool4() throws NoSuchFieldException, IllegalAccessException {
    String hi1 = "Hello";
    String hi2 = "Hel" + "lo";

    assertSame(hi1, hi2, "String");
    assertSame(getInternalValue(hi1), getInternalValue(hi2), "Strings value array");
  }

  @Test
  void stringPool5() throws NoSuchFieldException, IllegalAccessException {
    String hi1 = "Hello";
    String lo = "lo";
    String hi2 = "Hel" + lo;

    assertNotSame(hi1, hi2, "String");
    assertNotSame(getInternalValue(hi1), getInternalValue(hi2), "Strings value array");
  }

  @Test
  void stringPool6() throws NoSuchFieldException, IllegalAccessException {
    String hi1 = "Hello";
    String lo = "lo";
    String hi2 = ("Hel" + lo).intern();

    assertSame(hi1, hi2, "String");
    assertSame(getInternalValue(hi1), getInternalValue(hi2), "Strings value array");
  }

  @Test
  void stringPool7() throws NoSuchFieldException, IllegalAccessException, IOException {
    String path = ClassLoader.getSystemResource("hi.txt").getPath();
    List<String> strings = Files.readAllLines(Paths.get(path));
    String hi1 = strings.get(0);
    String hi2 = strings.get(1);

    assertEquals(hi1, hi2);
    assertNotSame(hi1, hi2, "String");
    assertNotSame(getInternalValue(hi1), getInternalValue(hi2), "Strings value array");
  }

  @Test
  void stringPool8() throws NoSuchFieldException, IllegalAccessException {
    StringBuilder stringBuilder = new StringBuilder("Hello");
    String hi1 = "Hello";

    String hi2 = stringBuilder.toString();

    assertNotSame(hi1, hi2, "String");
    assertNotSame(getInternalValue(hi1), getInternalValue(hi2), "Strings value array");
  }

  @Test
  void stringPool9() throws NoSuchFieldException, IllegalAccessException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Hello");
    String hi1 = stringBuilder.toString();
    String hi2 = stringBuilder.toString();

    assertNotSame(hi1, hi2, "String");
    assertNotSame(getInternalValue(hi1), getInternalValue(hi2), "Strings value array");

  }

  private static byte[] getInternalValue(String s)
      throws NoSuchFieldException, IllegalAccessException {
    final Field value = String.class.getDeclaredField("value");
    value.setAccessible(true);
    return (byte[]) value.get(s);
  }
}
