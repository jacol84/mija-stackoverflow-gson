package pl.jac.mija.gson.serialize;

import com.google.gson.*;

import java.lang.reflect.Type;

public class Xyz implements JsonSerializer<String>, JsonDeserializer<String> {

  @Override
  public JsonElement serialize(String element, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject object = new JsonObject();
    object.addProperty("value", element);
    object.addProperty("xyzEnabled", true);
    return object;
  }

  @Override
  public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    return json.getAsString();
  }
}
