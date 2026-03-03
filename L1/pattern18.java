import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
          int sp = 0;
          int st = 7;
          for(int i = 1; i<= 7; i++)
          {
            for(int j =1; j<=sp; j++)
            {
                System.out.print("\t");
            }
            for(int j=1; j<=st ; j++)
            {
              if(i>1 && i<=3 && j>1 && j<st)
              {
                System.out.print("\t");
              }
              else
              {
                System.out.print("*\t");
              }
            }
            for(int j =1; j<=sp; j++)
            {
                System.out.print("\t");
            }
            if(i<=3)
            {
              st-=2;
              sp++;
            }
            else
            {
              sp--;
              st+=2;
            }
             System.out.println();
          }
        }
    }
