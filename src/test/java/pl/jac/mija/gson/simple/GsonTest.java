package pl.jac.mija.gson.simple;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GsonTest {
  @Test
  public void deserialize() throws IOException {
    //given
    String path = "G:\\praca\\app\\stackoverflow\\mijaGson\\src\\test\\java\\pl\\jac\\mija\\gson\\simple\\json.json";
    String json = Files.lines(Paths.get(path), StandardCharsets.UTF_8).findFirst().orElse("{}"); //--> =  "{\"var1\" : true, \"var2\" : false}";

    //when
    Map<String, Boolean> map = new Gson().fromJson(json, LinkedTreeMap.class);
    Boolean javaVar1 = map.get("var1");
    Boolean javaVar2 = map.get("var2");
    //then
    assertEquals(true, javaVar1);
    assertEquals(false, javaVar2);
  }

}
