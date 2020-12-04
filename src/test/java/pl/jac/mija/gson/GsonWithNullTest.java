package pl.jac.mija.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import kotlin.NotImplementedError;
import org.junit.Test;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//https://stackoverflow.com/questions/61023485/gson-deserializing-null-value-not-working
public class GsonWithNullTest {

  @Test
  public void deserializeWithNull() {
    //given
    String inputJson = "{\"phone\":null, \"address\":\"underworld\"}";
    //when
    LinkedTreeMap<String, Object> map = new Gson().fromJson(inputJson, LinkedTreeMap.class);
    boolean phone = map.containsKey("phone");
    //then
    assertEquals(true, phone);
  }

  @Test
  public void deserializeWithoutNull_V1_use_adapter() {
    //given
    String inputJson = "{\"phone\":null, \"address\":\"underworld\"}";
    //when

    Gson gson = new GsonBuilder().registerTypeAdapter(LinkedTreeMap.class, new MyAdapterSkipNull()).create();
    LinkedTreeMap<String, Object> map = gson.fromJson(inputJson, LinkedTreeMap.class);
    //then
    boolean isPhone = map.containsKey("phone");
    boolean isAddress = map.containsKey("address");
    assertEquals(false, isPhone);
    assertEquals(true, isAddress);
  }

  @Test
  public void deserializeWithoutNull_V2_use_post_filter_null() {
    //given
    String inputJson = "{\"phone\":null, \"address\":\"underworld\"}";
    //when

    Gson gson = new GsonBuilder().registerTypeAdapter(LinkedTreeMap.class, new MyAdapterSkipNull()).create();
    LinkedTreeMap<String, Object> map = new Gson().fromJson(inputJson, LinkedTreeMap.class);
    Map<String, Object> collect = map.entrySet().stream().filter(x -> x.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    //then
    boolean isPhone = collect.containsKey("phone");
    boolean isAddress = collect.containsKey("address");
    assertEquals(false, isPhone);
    assertEquals(true, isAddress);
  }

  @Test
  public void serializeWithoutNull() {
    //given
    Map<String, Object> map = new HashMap<>();
    map.put("phone", null);
    map.put("address", "underworld");
    //when
    Gson gson = new GsonBuilder().serializeNulls().create();
    String json = gson.toJson(map);
    //then
    List<String> answert = new ArrayList<>();
    answert.add("{\"address\":\"underworld\",\"phone\":null}");
    answert.add("{\"phone\":null,\"address\":\"underworld\"}");
    assertTrue(answert.contains(json));
  }

  @Test
  public void serializeWithNull() {
    //given
    Map<String, Object> map = new HashMap<>();
    map.put("phone", null);
    map.put("address", "underworld");
    //when
    Gson gson = new Gson();
    String json = gson.toJson(map);
    //then
    assertEquals("{\"address\":\"underworld\"}", json);
  }

}

class MyAdapterSkipNull extends TypeAdapter<LinkedTreeMap<String, Object>> {


  @Override
  public void write(JsonWriter out, LinkedTreeMap<String, Object> value) throws IOException {
    throw new NotImplementedError();
  }

  @Override
  public LinkedTreeMap<String, Object> read(JsonReader in) throws IOException {
    JsonToken peek = in.peek();
    if (peek == JsonToken.NULL) {
      in.nextNull();
      return null;
    }
    TypeAdapter<Object> objectTypeAdapter = ObjectTypeAdapter.FACTORY.create(new Gson(), TypeToken.get(Object.class));
    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
    in.beginObject();
    while (in.hasNext()) {
      String key = in.nextName();
      JsonToken peek1 = in.peek();
      if (JsonToken.NULL.equals(peek1)) {
        in.skipValue(); //skip NULL
      } else {
        Object read = objectTypeAdapter.read(in);
        map.put(key, read);
      }
    }
    in.endObject();
    return map;
  }
}