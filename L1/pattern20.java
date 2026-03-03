import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
          for(int i =1; i<=5; i++)
          {
            for(int j=1; j<=5; j++)
            {
              if(j==1 || j==5)
              {
                System.out.print("*\t");
              }
              else if(i>2 &&(i==j || i+j==6))
              {
                System.out.print("*\t");
              }
              else
              {
                System.out.print("\t");
              }
              
            }
          System.out.println();
          }
        }
    }

*				*	
*				*	
*		*		*	
*	*		*	*	
*				*
