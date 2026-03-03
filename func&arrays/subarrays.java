import java.io.*;
import java.util.*;

class Main 
{
  
  public static void main(String[] args) throws Exception
  {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for(int i = 0; i<arr.length ; i++)
    {
      arr[i] = scn.nextInt();
    }
    
    for(int i = 0; i< arr.length; i++)
    {
      for(int j = i; j<arr.length; j++)
      {
        for(int k =i; k<=j ; k++)
        {
          System.out.print(arr[k] + "\t");
        }
        
        System.out.println();
      }
    }
  }
  
}

--------------------------------------

  5
1
2
3
4
5

STDIN
Output:

1	
1	2	
1	2	3	
1	2	3	4	
1	2	3	4	5	
2	
2	3	
2	3	4	
2	3	4	5	
3	
3	4	
3	4	5	
4	
4	5	
5
