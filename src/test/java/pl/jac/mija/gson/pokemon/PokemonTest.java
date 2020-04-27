package pl.jac.mija.gson.pokemon;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PokemonTest {
  @Test
  public void parserWithJsonParser() {
    //given
    String json = getJson();
    Boolean isDefault = null;
    //when
    JsonElement element = JsonParser.parseString(json);
    if (element.isJsonObject()) {
      JsonObject asJsonObject = element.getAsJsonObject(); // is your Object
      JsonElement element1 = asJsonObject.get("is_default");
      if (element1.isJsonPrimitive()) {
        isDefault = element1.getAsBoolean();
      }
    }
    //then
    assertTrue(isDefault);
  }

  @Test
  public void parserWithMapOfObject() {
    //given
    String json = getJson();
    //when
    LinkedTreeMap<String, Object> linkedTreeMap = new Gson().fromJson(json, LinkedTreeMap.class);
    Boolean isDefault = (Boolean) linkedTreeMap.get("is_default");
    //then
    assertTrue(isDefault);
  }


  public String getJson() {
    return "{\n" +
            "  " +
            "\"id\": 12,\n" +
            "  \"name\": \"butterfree\",\n" +
            "  \"base_experience\": 178,\n" +
            "  \"height\": 11,\n" +
            "  \"is_default\": true,\n" +
            "  \"order\": 16,\n" +
            "  \"weight\": 320,\n" +
            "  \"abilities\": [\n" +
            "    {\n" +
            "      \"is_hidden\": true,\n" +
            "      \"slot\": 3,\n" +
            "      \"ability\": {\n" +
            "        \"name\": \"tinted-lens\",\n" +
            "        \"url\": \"https://pokeapi.co/api/v2/ability/110/\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";
  }
}

