package com.in28minutes.mockito;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SpyTest {

  /**
   * Note: For spy, the logic inside add function was executed where else for mock it was not
   *
   */

  @Test
  public void creatingASpyOnArrayList() {
    List<String> listSpy = spy(ArrayList.class);

    // Not able to perform this operations with a mock
    listSpy.add("Ranga");
    listSpy.add("in28Minutes");

    verify(listSpy).add("Ranga");
    verify(listSpy).add("in28Minutes");

    assertEquals(2, listSpy.size());
    assertEquals("Ranga", listSpy.get(0));
  }

  @Test
  public void check_mocklist_logic_evoked() {
    String ELEMENT = "element";

    List<String> listMock = mock(ArrayList.class);
    assertEquals(0, listMock.size());

    listMock.add(ELEMENT);
    verify(listMock).add(ELEMENT);

    assertEquals(0, listMock.size());
  }

  @Test
  public void creatingASpyOnArrayList_overridingSpecificMethods() {
    List<String> listSpy = spy(ArrayList.class);
    listSpy.add("Ranga");
    listSpy.add("in28Minutes");

    stub(listSpy.size()).toReturn(-1);

    assertEquals(-1, listSpy.size());
    assertEquals("Ranga", listSpy.get(0));

    // @Spy Annotation
  }

}