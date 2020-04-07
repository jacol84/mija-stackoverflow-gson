package pl.jac.mija.gson.serialize;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Custom {
  @Test
  public void serialize() {
    //given
    Demo demo = new Demo();
    demo.total = 100;
    demo.name = "hello world";
    //when
    String json = new Gson().toJson(demo);
    //then
    assertEquals("{\"name\":{\"value\":\"hello world\",\"xyzEnabled\":true},\"total\":100}", json);
  }

  @Test
  public void deserialize() {
    //given
    String json = "{  \"name\": \"hello world\",  \"total\": 100}";
    //when
    Demo demo = new Gson().fromJson(json, Demo.class);
    //then
    assertEquals("hello world", demo.name);
    assertEquals(100, demo.total);
  }

}
