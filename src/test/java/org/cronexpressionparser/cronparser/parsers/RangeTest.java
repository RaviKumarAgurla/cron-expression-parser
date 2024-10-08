package org.cronexpressionparser.cronparser.parsers;

import org.cronexpressionparser.fieldsegment.Day;
import org.cronexpressionparser.fieldsegment.Month;
import org.cronexpressionparser.fieldsegment.Weekday;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class RangeTest {

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testRangeParserPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Day("1-5");
    assertEquals(d.parse(), List
      .of(1,2,3,4,5));
  }

  @Test
  public void testRangeWithMissingValue()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Weekday("1-");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Invalid segment expression : 1-");
    d.parse();
  }

  @Test
  public void testRangeWithWrongMaximum()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Weekday("1-8");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Range maximum is not valid. Given : 8 Max allowed : 6");
    d.parse();
  }

  @Test
  public void testRangeWithWrongMinimum()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Month("0-6");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Range minimum is not valid. Given : 0 Min allowed : 1");
    d.parse();
  }

  @Test
  public void testRangeWithWrongMinimum2()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Month("13-16");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Range minimum is not valid. Given : 13 Max allowed : 12");
    d.parse();
  }

  @Test
  public void testRangeWithWrongMinimumAndMaximum()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Month("6-0");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Range minimum/maximum are in wrong order. maximum should be : 6 and minimum should be : 0");
    d.parse();
  }
}
