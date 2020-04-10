package pl.jac.mija.gson.formData;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FormatData {

  @Test
  public void testDeserializeArray() {
    //given
    String json = "[{\"ventas\":\"08/04/2020\"}, {\"ventas\":\"08/03/2020\"}]";
    Type type = new TypeToken<ArrayList<Tienda>>() {
    }.getType();
    //when
    List<Tienda> demo = new GsonBuilder().setDateFormat("dd/MM/yyy").create().fromJson(json, type);
    //then
    assertEquals(2, demo.size());
    assertEquals(1586296800000L, demo.get(0).ventas.getTime());
    assertEquals(1583622000000L, demo.get(1).ventas.getTime());
  }

  @Test
  public void testSimpleDeserialize() {
    //given
    String json = "{\"ventas\":\"08/04/2020\"}";
    //when
    Tienda demo = new GsonBuilder().setDateFormat("dd/MM/yyy").create().fromJson(json, Tienda.class);
    //then
    assertEquals(1586296800000L, demo.ventas.getTime());
  }


  @Test
  public void testSerialize() {
    //given
    Tienda demo = new Tienda();
    demo.ventas = new Date(1586296800000L);
    //when
    String json = new GsonBuilder().setDateFormat("dd/MM/yyy").create().toJson(demo);
    //then
    assertEquals("{\"ventas\":\"08/04/2020\"}", json);
  }
}