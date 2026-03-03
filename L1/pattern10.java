import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
            int n  = 5;
            int os = 2;
            int is = -1;
            for(int i = 1; i<=5; i++)
            {
              for(int j =1; j<=os; j++)
              {
                System.out.print("\t");
              }
              System.out.print("*\t");
              for(int j =1; j<=is; j++)
              {
                System.out.print("\t");
              }
              if(i>1 && i<5)
              {
                System.out.print("*\t");
              }
              if(i<=n/2)
              {
                os--;
                is+=2;
              }
              else
              {
                os++;
                is-=2;
              }
              
              System.out.println();
            }
        }
    }
