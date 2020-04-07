package pl.jac.mija.gson.simple;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TestJsonReader {
  @Test
  public void deserialize() throws IOException {
    //given
    String path = "G:\\praca\\app\\stackoverflow\\mijaGson\\src\\test\\java\\pl\\jac\\mija\\gson\\simple\\json.json";
    String json = Files.lines(Paths.get(path), StandardCharsets.UTF_8).findFirst().orElse("{}"); //--> =  "{\"var1\" : true, \"var2\" : false}";
    //when
    Boolean javaVar1 = readJson(json, "var1");
    Boolean javaVar2 = readJson(json, "var2");
    //then
    assertEquals(true, javaVar1);
    assertEquals(false, javaVar2);
  }

  private Boolean readJson(String json, String keyValue) throws IOException {
    JsonReader in = new JsonReader(new StringReader(json));
    TypeAdapter<Object> objectTypeAdapter = ObjectTypeAdapter.FACTORY.create(new Gson(), TypeToken.get(Object.class));
    in.beginObject();
    while (in.hasNext()) {
      String key = in.nextName();
      if (key.equals(keyValue)) {
        return (Boolean) objectTypeAdapter.read(in);
      }
      in.skipValue();
    }
    in.endObject();
    return null;
  }

  ;
}



