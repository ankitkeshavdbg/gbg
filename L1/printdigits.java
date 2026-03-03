import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
            int n = 754;
            int nod = 0;
            int temp = n;
            while(temp!=0)
                {
                    temp = temp /10;
                    nod++;
                }
            int div = (int)Math.pow(10,nod-1);
            while(div!=0)
            {
              int q = n/div;
              System.out.println(q);
              n=n%div;
              div= div/10;
            }
        }
    }
