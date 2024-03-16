import java.util.*; 
import java.io.*;

class Main {

  public static String LongestWord(String sen) {
    // code goes here  
    sen = sen.replaceAll("[^a-zA-Z0-9 ]","");
    String[] arr = sen.split(" ");
    
    int index, maxLength;
    index = maxLength = 0;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i].length() > maxLength) {
        maxLength = arr[i].length();
        index = i;
      }
    }

    return arr[index];
  }

  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(LongestWord(s.nextLine())); 
  }

}