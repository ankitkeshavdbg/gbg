import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
            for(int i = 0; i<5;i++)
            {
              int icj = 1;
              for(int j=0; j<=i; j++)
              {
                System.out.print(icj + "\t");
                int icjp1 = icj * (i-j)/ (j+1);
                icj = icjp1;
              }
              System.out.println();
            }
        }
    }
