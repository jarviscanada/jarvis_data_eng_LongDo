package ca.jrvs.practice.codingChallenge;

public class Atoi {

  /**
   * Big O: O(n) Justification: The parseInt method will read the string char by char and convert it
   * to int number.
   */
  public int atoiJavaBuiltin(String string) {
    return Integer.parseInt(string.trim());
  }


  /**
   * Big O: O(n)
   */
  public int myAtoi(String string) {
    int sign = 1;
    int result = 0;
    int index = 0;
    int n = string.length();

    // Discard all spaces from the beginning of the input string.
    while (index < n && string.charAt(index) == ' ') {
      index++;
    }

    //sign =+ 1, if it's positive number, otherwise sign = -1.
    if (index < n && string.charAt(index) == '+') {
      sign = 1;
      index++;
    } else if (index < n && string.charAt(index) == '-') {
      sign = -1;
      index++;
    }

    //Traverse next digits of string and stop if it is not digits
    while (index < n && Character.isDigit(string.charAt(index))) {
      int digit = string.charAt(index) - '0';

      //Check overflow and underflow condition
      if ((result > Integer.MAX_VALUE / 10) || (result == Integer.MAX_VALUE / 10
          && digit > Integer.MAX_VALUE % 10)) {
        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }

      //Append current digit to result
      result = 10 * result + digit;
      index++;
    }

    return sign * result;
  }
}
