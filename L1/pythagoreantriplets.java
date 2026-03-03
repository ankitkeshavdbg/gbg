import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
            int a= 5;
            int b= 12;
            int c= 14;
            
            int max = a;
            if(b>= max)
            {
              max= b;
            }
            if(c>= max)
            {
              max= c;
            }
            
            if(max == a)
            {
              boolean flag = ((c*c + b*b)== (a*a));
              System.out.println(flag);
            }
             if(max == b)
            {
              boolean flag = ((a*a + c*c)== (b*b));
              System.out.println(flag);
            }
             if(max == c)
            {
              boolean flag = ((a*a + b*b)== (c*c));
              System.out.println(flag);
            }
            
          
        }
    }
