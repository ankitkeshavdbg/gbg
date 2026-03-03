import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
          for(int i =1; i<=5; i++)
          {
            for(int j=1; j<=5; j++)
            {
              if(i==1)
              {
                if(j<=3 || j==5)
                {
                  System.out.print("*\t");
                }
                else
                {
                  System.out.print("\t");
                }
              }
              else if(i<=2)
              {
                if(j==3||j==5)
                {
                  System.out.print("*\t");
                }
                else
                {
                  System.out.print("\t");
                }
              }
              else if(i==3)
              {
                System.out.print("*\t");
              }
              else if(i<5)
              {
                if(j==1||j==3)
                {
                  System.out.print("*\t");
                }
                else
                {
                  System.out.print("\t");
                }
              }
              else
              {
                if(j==1 || j>=3)
                {
                  System.out.print("*\t");
                }
                else
                {
                  System.out.print("\t");
                }
              }
              
            }
          System.out.println();
          }
        }
    }

*	*	*		*	
		*		*	
*	*	*	*	*	
*		*			
*		*	*	*

import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
          for(int i =1; i<=5; i++)
          {
            for(int j=1; j<=5; j++)
            {
              if(i==1)
              {
                if(j==1 || j>=3)
                {
                  System.out.print("*\t");
                }
                else
                {
                  System.out.print("\t");
                }
              }
              else if(i<=2)
              {
                if(j==1||j==3)
                {
                  System.out.print("*\t");
                }
                else
                {
                  System.out.print("\t");
                }
              }
              else if(i==3)
              {
                System.out.print("*\t");
              }
              else if(i<5)
              {
                if(j==3||j==5)
                {
                  System.out.print("*\t");
                }
                else
                {
                  System.out.print("\t");
                }
              }
              else
              {
                if(j==5 || j<=3)
                {
                  System.out.print("*\t");
                }
                else
                {
                  System.out.print("\t");
                }
              }
              
            }
          System.out.println();
          }
        }
    }


*		*	*	*	
*		*			
*	*	*	*	*	
		*		*	
*	*	*		*

