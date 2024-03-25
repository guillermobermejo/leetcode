/*
Programmer: Guillermo
Language: Java
Score: 42.3% higher

Question:
Bracket Matcher
Have the function BracketMatcher(str) take the str parameter being passed and return 1 if the brackets are 
correctly matched and each one is accounted for. Otherwise return 0. For example: if str is "(hello (world))", 
then the output should be 1, but if str is "((hello (world))" the the output should be 0 because the brackets 
do not correctly match up. Only "(" and ")" will be used as brackets. If str contains no brackets return 1.

Examples:
Input: "(coder)(byte))"
Output: 0

Input: "(c(oder)) b(yte)"
Output: 1
*/

import java.util.*; 
import java.io.*;

class Main {

  public static String BracketMatcher(String str) {
    // code goes here  
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);

      if (c == '(') stack.push(')');
      else if(c == ')') {
        if (stack.isEmpty() || stack.pop() != ')') 
          return "0";
      }
    }

    return stack.isEmpty() == true ? "1" : "0";
  }

  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(BracketMatcher(s.nextLine())); 
  }

}
