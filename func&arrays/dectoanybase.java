import java.util.*;


public class main
{
  public static void main (String args[])
  {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int b = scn.nextInt();
    int a = dectoanybase(n,b);
    System.out.println(a);
  }
  
  public static int dectoanybase(int n , int b)
  {
    int rv = 0;
    int p = 1;
    while(n>0)
    {
      int rem = n%b;
      n = n/b;
      rv = rv+rem*p;
      p=p*10;
    }
    return rv;
  }

}
