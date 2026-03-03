import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
          int sp = 2;
          int st = 1;
          for(int i = 1; i<= 5; i++)
          {
            for(int j =1; j<=sp; j++)
            {
              if(i==3)
              {
                System.out.print("*\t");
              }
              else
              {
                System.out.print("\t");
              }
            }
            for(int j=1; j<=st ; j++)
            {
              System.out.print("*\t");
            }
            if(i<=2)
            {
              st++;
            }
            else
            {
              st--;
            }
             System.out.println();
          }
        }
    }
