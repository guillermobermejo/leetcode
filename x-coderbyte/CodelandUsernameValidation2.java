/*
Programmer: Guillermo
Language: Java
Score: 50.8% higher

Question:
Codeland Username Validation
Have the function CodelandUsernameValidation(str) take the str parameter being passed and determine if the string is a 
valid username according to the following rules:

1. The username is between 4 and 25 characters.
2. It must start with a letter.
3. It can only contain letters, numbers, and the underscore character.
4. It cannot end with an underscore character.

If the username is valid then your program should return the string true, otherwise return the string false.

Examples:
Input: "aa_"
Output: false

Input: "u__hello_world123"
Output: true
*/

import java.util.*; 
import java.io.*;

class Main {

  public static String CodelandUsernameValidation(String str) {
    // code goes here  

    if (str.length() < 4 || str.length() > 25)
      return "false";

    if (!((str.charAt(0) >= 65 && str.charAt(0) <=90) 
        || (str.charAt(0) >= 97 && str.charAt(0) <=122)))
      return "false";

    for (char c : str.toCharArray()){
      if (!((c >= 65 && c <=90) || (c >= 97 && c <=122)) 
          && !(c >= 48 && c <= 57) 
          && c != 95)
        return "false";
    }

    return str.charAt(str.length()-1) != 95 ? "true" : "false" ;
  }


  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(CodelandUsernameValidation(s.nextLine())); 
  }

}
