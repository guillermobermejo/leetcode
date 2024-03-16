/*
Programmer: Guillermo
Score: 50.8% higher
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