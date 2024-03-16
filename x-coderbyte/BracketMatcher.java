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