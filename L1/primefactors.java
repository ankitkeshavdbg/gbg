import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
            int n = 1440;
            for(int div = 2 ; div *div <=n; div++)
            {
              while (n%div ==0)
              {
                n=n/div;
                System.out.println(div);
              }
            }
            if(n!=1)
              {
                System.out.println(n);
              }
        }
    }
