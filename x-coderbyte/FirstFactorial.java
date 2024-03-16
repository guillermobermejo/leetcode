/*
Programmer: Guillermo
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

class Main {

  public static int FirstFactorial(int num) {
    // code goes here  
    if (num == 0 || num == 1)
      return 1;

    int[] arr = new int[num+1];
    arr[0] = 1;
    arr[1] = 1;

    for (int i = 2; i < arr.length; i++) 
      arr[i] = arr[i-1] * i;

    return arr[num];
  }

  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(FirstFactorial(s.nextLine())); 
  }

}
