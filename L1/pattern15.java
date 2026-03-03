import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
          int sp = 2;
          int st = 1;
          int val =1;
          for(int i = 1; i<= 5; i++)
          {
            for(int j=1; j<=sp ; j++)
            {
              System.out.print("\t");
            }
            int cval = val;
            for(int j =1 ; j<=st; j++)
            {
                System.out.print(cval+ "\t");
                if(j<=st/2)
                {
                cval++;
                }
                else
                {
                  cval--;
                }
            }
            if(i<=2)
            {
              sp--;
              st+=2;
              val++;
            }
            else
            {
              sp++;
              st-=2;
              val--;
            }
            System.out.println();
          }
        }
    }


Output:

		1	
	2	3	2	
3	4	5	4	3	
	2	3	2	
		1	
