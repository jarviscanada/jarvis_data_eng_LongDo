package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * Ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-187f9bc323f14195949c94bed7a7eb45
 */
public class CompareMap {

  /**
   * Big O: O(n) Justification: equals will compare each key and value pair
   */
  public <K, V> boolean compareMap(Map<K, V> m1, Map<K, V> m2) {
    return m1.equals(m2);
  }
}
