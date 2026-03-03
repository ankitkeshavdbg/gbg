import java.util.*;

public class main
{
  public static void main (String[] args)
  {
    int n = 99590;
    int d = 9;
    int f = getdigfrequency(n,d);
    System.out.println("frequency is\t" + f);
  }
  
  public static int getdigfrequency(int n, int d)
  {
    int rv = 0;
    
    while(n>0)
    {
      int dig = n%10;
      n = n/10;
      if(dig == d)
      {
        rv++;
      }
    }
    return rv;
  }
}
