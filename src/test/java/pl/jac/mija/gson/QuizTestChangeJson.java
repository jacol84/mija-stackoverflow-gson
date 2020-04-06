package pl.jac.mija.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuizTestChangeJson {
  @Test
  public void changeJson() {
    //given
    String json = getNewJsonOneProposeOptions();
    Type type = new TypeToken<List<Quiz>>() {
    }.getType();
    //when
    List<Quiz> list = new Gson().fromJson(json, type);
    //then
    assertEquals(1, list.size());
    String correctAnswer = list.get(0).correctAnswer;
    assertEquals(correctAnswer, list.get(0).answerOptions[1]);
    assertEquals(1, Arrays.asList(list.get(0).answerOptions).indexOf(correctAnswer)); // key is string
  }

  @NotNull
  private String getNewJsonOneProposeOptions() {
    return "   [{\n" +
            "        \"question\": \"Who is the 'Modern Love' rock star singer?\",\n" +
            "        \"imageUrl\": \"https://postimg.cc/2VL1Y1jd\",\n" +
            "        \"answerOptions\": [\n" +
            "             \"Jaimie Hendrix\",\n" +
            "             \"David Bowie\",\n" +
            "             \"Jim Morrison\",\n" +
            "             \"Elvis Presley\"\n" +
            "        ],\n" +
            "        \"correctAnswer\": \"David Bowie\"\n" +
            "    }]";
  }
}

class Quiz {
  String question;
  String imageUrl;
  String[] answerOptions;
  String correctAnswer;
}