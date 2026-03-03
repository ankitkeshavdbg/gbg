import java.util.*;

public class main
{
  public static void main (String[] args)
  {
    int n =5;
    int r =2;
    
    int nfact =1;
    for(int i=1; i<=n; i++)
    {
      nfact = nfact * i;
    }
    
    int nmrfact = 1;
    for(int i= 1; i <=n-r; i++)
    {
      nmrfact= nmrfact*i;
    }
    
    int npr = nfact/nmrfact;
    System.out.println(n+ "P" + r + "=" + npr);
  }
}

--------------------------------------------------------------------------------------------------------------
import java.util.*;

public class main
{
  public static int fact (int x)
  {
    int rv =1;
    for(int i =1; i<= x ; i++)
    {
      rv = rv *i;
    }
    return rv;
  }
  public static void main (String[] args)
  {
    int n =4;
    int r =2;
    
    int nfact =fact (n);
    int nmrfact =fact (n-r);
    int npr = nfact/nmrfact;
    System.out.println(n+ "P" + r + "=" + npr);
  }
}

---------------------------------------------------------------------------------------------------------
  USING DISPLAY FUNCTION

  import java.util.*;

public class main
{
  public static void display(int n, int r, int npr)
  {
    System.out.println(n+ "P" + r + "=" + npr);
  }
  public static int fact (int x)
  {
    int rv =1;
    for(int i =1; i<= x ; i++)
    {
      rv = rv *i;
    }
    return rv;
  }
  public static void main (String[] args)
  {
    int n =4;
    int r =2;
    
    int nfact =fact (n);
    int nmrfact =fact (n-r);
    int npr = nfact/nmrfact;
    display(n,r,npr);
  }
}
