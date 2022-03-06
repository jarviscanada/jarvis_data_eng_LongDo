package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

  public int[] twoSumBruteForce(int[] nums, int target) {
    int[] result = new int[2];
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (i != j && nums[i] + nums[j] == target) {
          result[0] = i;
          result[1] = j;
        }
      }
    }
    return result;
  }

  public int[] twosumHash(int[] nums, int target) {
    int[] result = new int[2];
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int diff = target - nums[i];
      if (hashMap.containsKey(diff)) {
        result[0] = hashMap.get(diff);
        result[1] = i;
        break;
      }
      hashMap.put(nums[i], i);
    }
    return result;
  }
}
