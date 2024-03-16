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