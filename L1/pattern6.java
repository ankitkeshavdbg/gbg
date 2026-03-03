import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
            int n =5;
            int sp=1;
            int st=3;
            for(int i = 1; i<=n; i++)
            {
              for(int j=1; j <=st; j++)
              {
                System.out.print("*\t");
              }
              for(int j=1; j <=sp; j++)
              {
                System.out.print("\t");
              }
              for(int j=1; j <=st; j++)
              {
                System.out.print("*\t");
              }
              if(i<=n/2)
              {
                st--;
                sp+=2;
              }
              else
              {
                st++;
                sp-=2;
              }
            
              System.out.println();
            }
        }
    }
