import java.util.*;


public class main
{
  public static void main (String args[])
  {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int b1 = scn.nextInt();
    int b2 = scn.nextInt();
    int a = getvalue(n,b1,b2);
    System.out.println(a);
  }
  
  public static int getvalue(int n, int b1, int b2)
  {
    int dec = anybasetodec(n,b1);
    int dig = dectoanybase(dec,b2);
    return dig;
    
  }
  public static int anybasetodec(int n , int b)
  {
    int rv = 0;
    int p = 1;
    while(n>0)
    {
      int rem = n%10;
      n = n/10;
      rv = rv+rem*p;
      p=p*b;
    }
    return rv;
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
