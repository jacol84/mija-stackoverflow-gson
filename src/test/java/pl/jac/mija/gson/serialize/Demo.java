package pl.jac.mija.gson.serialize;


import com.google.gson.annotations.JsonAdapter;

public class Demo {
  @JsonAdapter(Xyz.class)
  public String name;
  public int total;
}
