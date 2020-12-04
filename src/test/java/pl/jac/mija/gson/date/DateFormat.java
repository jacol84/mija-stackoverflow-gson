package pl.jac.mija.gson.date;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

class JsonDateTimeFormat {
  public Date parse(String date) {
    try {
      DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd['T'HH:mm:ss[.SSSSSS['Z']]]")
              .parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
              .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0).toFormatter();
      return Date.from(LocalDateTime.parse(date, dateTimeFormatter).toInstant(OffsetDateTime.now().getOffset()));


    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return null;
  }

}

