package pl.jac.mija.gson.date;



import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.regex.Pattern;

class JsonDateTimeFormat {
  private Integer length;
  private Pattern compile;
  private DateTimeFormatter formatter;

  public boolean match(String date) {
    return date != null && date.length() == length && compile.matcher(date).matches();
  }

  public Date parse(String date) {
    try {
      DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd[ HH:mm:ss.SSSSSS]")
              .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
              .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
              .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
              .toFormatter();
      return Date.from(LocalDateTime.parse(date, dateTimeFormatter).toInstant(OffsetDateTime.now().getOffset()));


    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return null;
  }

  public void setPattern(String pattern) {
    formatter = DateTimeFormatter.ofPattern(pattern);
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Integer getLength() {
    return length;
  }

  public void setRegex(String regex) {
    compile = Pattern.compile(regex);
  }
}

