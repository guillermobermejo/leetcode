/*
Programmer: Guillermo
Language: Java
Score: 42.3% higher

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

    HashSet<Character> set = new HashSet<Character>(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L',
    'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l',
    'm','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','_'));

    if (str.length() < 4 || str.length() > 25)
      return "false";

    if (set.contains(str.charAt(0)) && str.charAt(0) == '_')
      return "false";

    for (char c : str.toCharArray())
      if (!set.contains(c)) return "false";

    return str.charAt(str.length()-1) != '_' ? "true" : "false" ;
  }


  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(CodelandUsernameValidation(s.nextLine())); 
  }

}
