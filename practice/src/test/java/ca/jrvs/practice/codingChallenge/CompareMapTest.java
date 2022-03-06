package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class CompareMapTest {

  private CompareMap compareMap;

  @Before
  public void setUp() {
    compareMap = new CompareMap();
  }

  @Test
  public void compareMaps() {
    Map<String, Integer> m1 = new HashMap<>();
    Map<String, Integer> m2 = new HashMap<>();
    Map<String, Integer> m3 = new HashMap<>();
    m1.put("test1", 1);
    m1.put("test2", 2);
    m1.put("test3", 3);
    m2.put("test1", 1);
    m2.put("test2", 2);
    m2.put("test3", 3);
    m3.put("test1", 1);
    m3.put("test2", 2);
    m3.put("test3", 4);
    assertTrue(compareMap.compareMap(m1, m2));
    assertFalse(compareMap.compareMap(m1, m3));

  }
}
