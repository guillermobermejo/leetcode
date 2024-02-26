import java.util.*; 
import java.io.*;

class Main {

  public static String LongestWord(String sen) {
    // code goes here  
    return Arrays.stream(sen.split("[^a-zA-Z0-9]"))
              .max(Comparator.comparingInt(String::length))
              .get();
  }

  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(LongestWord(s.nextLine())); 
  }

}
