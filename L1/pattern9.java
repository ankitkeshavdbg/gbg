import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
            int n =5;
            for(int i = 1; i<=n; i++)
            {
              for(int j=1; j <=n; j++)
              if(i+j==n+1)
              {
                System.out.print("*\t");
              }
              else if(i==j)
              {
                System.out.print("*\t");
              }
              else
              {
                System.out.print("\t");
              }
              
              System.out.println();
            }
        }
    }
