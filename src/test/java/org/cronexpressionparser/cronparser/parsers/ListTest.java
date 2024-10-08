package org.cronexpressionparser.cronparser.parsers;

import org.cronexpressionparser.fieldsegment.Minute;
import org.cronexpressionparser.fieldsegment.Weekday;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import static org.junit.Assert.*;
public class ListTest {

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testListPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Minute("1,5");
    assertEquals(d.parse(), List.of(1, 5));
  }

  @Test
  public void testListWithInvalidElement()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Minute("a,15");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Invalid segment expression : a");
    d.parse();
  }

  @Test
  public void testListWithMoreThanMaximumValue()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Weekday("1,8");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("The value for segment is more than the maximum allowed");
    d.parse();
  }

  @Test
  public void testListWithMissingValue()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Weekday("1,");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("List does not have valid expression : 1,");
    d.parse();
  }

  @Test
  public void testListWithValidComplexValues()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Weekday("1-2,5-6");
    assertEquals(d.parse(), List.of(1, 2, 5, 6));
  }

  @Test
  public void testListWithValidComplexValues2()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Weekday("1-4,3-6");
    assertEquals(d.parse(), List.of(1, 2, 3, 4, 5, 6));
  }
}
