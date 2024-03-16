/*
Programmer: Guillermo
Score: 42.3% higher

Question:
First Factorial
Have the function FirstFactorial(num) take the num parameter being passed and return the factorial of it. 
For example: if num = 4, then your program should return (4 * 3 * 2 * 1) = 24. For the test cases, the range 
will be between 1 and 18 and the input will always be an integer.

Examples:
Input: 4
Output: 24

Input: 8
Output: 40320
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
