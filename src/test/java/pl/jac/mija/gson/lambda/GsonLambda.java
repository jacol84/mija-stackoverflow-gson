package pl.jac.mija.gson.lambda;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class GsonLambda {
  @Test
  public void test() {
    //given
    final String json = getJson();
    Gson gson = new Gson();
    Type rowListType = new TypeToken<List<Map<String, Object>>>() {
    }.getType();
    final List<Map<String, Object>> rows = gson.fromJson(json, rowListType);
    //when
    List<Map<String, Object>> newRows = rows.stream()
            .filter(r -> r.containsKey("item"))
            .filter(r -> {
              List<Map<String, String>> item = (List<Map<String, String>>) r.get("item");
              List<Map<String, String>> newList = item.stream().filter(x -> !x.get("d1").toUpperCase().equals("ST")).collect(Collectors.toList());
              r.put("item", newList);
              return !newList.isEmpty();
            })
            .collect(Collectors.toList());
    //then
    assertEquals("[{\"containertype\":\"check2\",\"item\":[{\"d1\":\"Pt\"}],\"contenttype\":\"test\"}]", gson.toJson(newRows));
  }

  @NotNull
  private String getJson() {
    return "[\n" +
            "{\n" +
            "  \"containertype\": \"check2\",\n" +
            "  \"item\": [\n" +
            "    {\n" +
            "      \"d1\": \"St\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"d1\": \"Pt\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"d1\": \"St\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"contenttype\": \"test\"\n" +
            "},\n" +
            "{\n" +
            "  \"containertype\": \"check2\",\n" +
            "  \"item\": [\n" +
            "    {\n" +
            "      \"d1\": \"St\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"d1\": \"St\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"d1\": \"st\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"contenttype\": \"test\"\n" +
            "}" +
            "]";
  }
}
