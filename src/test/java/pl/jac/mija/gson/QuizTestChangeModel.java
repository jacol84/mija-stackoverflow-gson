package pl.jac.mija.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class QuizTestChangeModel {
  @Test
  public void changeModelQuizKeyString() {
    //given
    String json = getJsonOriginal();
    Type type = new TypeToken<List<QuizV2_String>>() {
    }.getType();
    //when
    List<QuizV2_String> list = new Gson().fromJson(json, type);
    //then
    assertEquals(1, list.size());
    assertEquals("David Bowie", list.get(0).answerOptions.get(0).get("2")); // key is string
  }

  @Test
  public void changeModelQuizKeyLong() {
    //given
    String json = getJsonOriginal();
    Type type = new TypeToken<List<QuizV2_Long>>() {
    }.getType();
    //when
    List<QuizV2_Long> list = new Gson().fromJson(json, type);
    //then
    assertEquals(1, list.size());
    assertEquals("David Bowie", list.get(0).answerOptions.get(0).get(2L)); // key is string
  }

  @Test
  public void changeModelQuizFindAnswer() {
    //given
    String json = getJsonOriginal();
    Type type = new TypeToken<List<QuizV2_String>>() {
    }.getType();
    //when
    List<QuizV2_String> list = new Gson().fromJson(json, type);
    //then
    String correctAnswer = list.get(0).correctAnswer;
    String key = list.get(0).answerOptions.get(0).entrySet().stream().filter(x -> correctAnswer.equals(x.getValue())).map(Map.Entry::getKey).findFirst().orElse(null);
    assertEquals("2", key);
    assertEquals("David Bowie", list.get(0).answerOptions.get(0).get(key));
  }

  @NotNull
  private String getJsonOriginal() {
    return "   [{\n" +
            "        \"question\": \"Who is the 'Modern Love' rock star singer?\",\n" +
            "        \"imageUrl\": \"https://postimg.cc/2VL1Y1jd\",\n" +
            "        \"answerOptions\": [{\n" +
            "            \"1\": \"Jaimie Hendrix\",\n" +
            "            \"2\": \"David Bowie\",\n" +
            "            \"3\": \"Jim Morrison\",\n" +
            "            \"4\": \"Elvis Presley\"\n" +
            "        }],\n" +
            "        \"correctAnswer\": \"David Bowie\"\n" +
            "    }]";
  }
}


class QuizV2_String {
  String question;
  String imageUrl;
  List<Map<String, String>> answerOptions;
  String correctAnswer;
}

class QuizV2_Long {
  String question;
  String imageUrl;
  List<Map<Long, String>> answerOptions;
  String correctAnswer;
}