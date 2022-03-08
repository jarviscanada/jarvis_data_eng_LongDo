package ca.jrvs.practice.codingChallenge;


public class ValidPalindrome {

  /**
   * Big O: O(n)
   */
  public boolean isPalindromePointer(String s) {
    int left = 0;
    int right = s.length() - 1;
    while (left < right) {
      while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
        left++;
      }

      while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
        right--;
      }

      if (left < right && Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(
          s.charAt(right--))) {
        return false;
      }
    }

    return true;
  }


  /**
   * Big O: O(n)
   */
  public boolean isPalidromeRecur(String s) {
    return isPalindrome(s, 0, s.length() - 1);
  }

  private boolean isPalindrome(String s, int left, int right) {
    if (left >= right) {
      return true;
    } else if (!Character.isLetterOrDigit(s.charAt(left)) && Character.isLetterOrDigit(
        s.charAt(right))) {
      return isPalindrome(s, left + 1, right);
    } else if (Character.isLetterOrDigit(s.charAt(left)) && !Character.isLetterOrDigit(
        s.charAt(right))) {
      return isPalindrome(s, left, right - 1);
    } else if (Character.isLetterOrDigit(s.charAt(left)) && Character.isLetterOrDigit(
        s.charAt(right)) &&
        (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))) {
      return false;
    } else {
      return isPalindrome(s, left + 1, right - 1);
    }
  }

}
