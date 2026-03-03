import java.util.*;

public class main
    {
        public static void main (String[] args)
        {
            int n = 27931;
            int r = 3;
            int nod=0;
            int rot = 0;
            int temp = n;
            while(temp!=0)
            {
              temp = temp/10;
              nod++;
            }
            if(r>nod)
            {
              r=r/nod;
            }
            if(r<0)
            {
              r= r+nod;
            }
            int divisor = (int)Math.pow(10,r);
            int multiplier  = (int)Math.pow(10,nod-r);
            int dig = 0;
            dig = n%divisor;
            n = n/divisor;
            rot = dig*multiplier+n;
            System.out.println(rot);
        }
    }
