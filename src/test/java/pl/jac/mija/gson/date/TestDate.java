package pl.jac.mija.gson.date;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TestDate {

  @Test
  public void test1() {
    //given
    JsonDateTimeFormat date = new JsonDateTimeFormat();
    String stringDate = "2020-12-04T15:12:31";
    //when
    Date parse = date.parse(stringDate);
    //then
    assertEquals(parse.getTime(), 1607091151000L);
  }

  @Test
  public void test2() {
    //given
    JsonDateTimeFormat date = new JsonDateTimeFormat();
    String stringDate = "2020-11-02T15:15:48.983000";
    //when
    Date parse = date.parse(stringDate);
    //then
    assertEquals(parse.getTime(), 1604326548983L);
  }

  @Test
  public void test3() {
    //given
    JsonDateTimeFormat date = new JsonDateTimeFormat();
    String stringDate = "2020-12-04T14:12:31.213000Z";
    //when
    Date parse = date.parse(stringDate);
    //then
    assertEquals(parse.getTime(), 1607087551213L);
  }

  @Test
  public void test4() {
    //given
    JsonDateTimeFormat date = new JsonDateTimeFormat();
    String stringDate = "2020-12-04";
    //when
    Date parse = date.parse(stringDate);
    //then
    assertEquals(parse.getTime(), 1607036400000L);
  }

}
