package pl.jac.mija.gson.enu;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SortEnum {

  @Test
  public void test1() {
    //given
    PanelOrderAction commandToWork = PanelOrderAction.COMMAND_TO_WORK;
    //then
    assertEquals(3, commandToWork.ordinal());
  }

  @Test
  public void test2() {
    //given
    Arrays.asList(PanelOrderAction.values()).stream().forEach(x -> System.out.println("na" + x.name() + " -> " + x.ordinal()));
    //then

  }

  @Test
  public void test3() {
    //given
    List<PanelOrderAction> list = Arrays.asList(PanelOrderAction.STOP, PanelOrderAction.CANCEL, PanelOrderAction.COMMAND_TO_WORK, PanelOrderAction.CONFIRM);
    //when
    list.sort(Comparator.naturalOrder());
    //then
    System.out.println(list);
  }
}
