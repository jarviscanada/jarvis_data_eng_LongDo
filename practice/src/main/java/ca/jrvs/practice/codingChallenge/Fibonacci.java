package ca.jrvs.practice.codingChallenge;

public class Fibonacci {

  /**
   * Big O: O(2^n)
   */
  public int fibonacciRecursive(int num) {
    if (num == 0) {
      return 0;
    }
    if (num == 1) {
      return 1;
    }
    return fibonacciRecursive(num - 1) + fibonacciRecursive(num - 2);
  }

  /**
   * BIg O: O(n)
   */
  public int fibonacciDP(int num) {
    int[] dp = new int[num + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= num; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[num];
  }
}
