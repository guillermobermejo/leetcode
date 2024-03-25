/*
Programmer: Guillermo
Language: Java
Score: 42.3% higher

Longest Word
Have the function LongestWord(sen) take the sen parameter being passed and return the longest word in the string. 
If there are two or more words that are the same length, return the first word from the string with that length. 
Ignore punctuation and assume sen will not be empty. Words may also contain numbers, for example "Hello world123 567"

Examples:
Input: "fun&!! time"
Output: time

Input: "I love dogs"
Output: love
*/

import java.util.*; 
import java.io.*;

class Main {

  public static String LongestWord(String sen) {
    // code goes here  
    return Arrays.stream(sen.replaceAll("[^a-zA-Z0-9 ]","").split(" "))
              .max(Comparator.comparingInt(String::length))
              .get();
  }

  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(LongestWord(s.nextLine())); 
  }

}
