package pl.jac.mija.gson.uuid;

import org.junit.Test;

import java.util.UUID;

public class ParseToUUID {
  @Test
  public void test() {

    UUID uuid = UUID.randomUUID();

    System.out.println(uuid);
  }
  @Test
  public void test2() {

    String x = "3B578C494C7C47CA8E2DA59C7E631B2C";
    String imne = "414B7AF0A3694FA4AF79ACEF988C8161";


    UUID uuid = UUID.randomUUID();


    String s = x.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
    UUID uuid1 = UUID.fromString(s);
    System.out.println(uuid);
    System.out.println(uuid1);
  }
}
