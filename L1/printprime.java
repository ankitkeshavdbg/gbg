public class Main {
     public static void main (String[] args)
    {
        int n = 100;
        int noofprime = 0;
        for(int num = 2; num<=n ; num++)
        {
            int count = 0;
            for(int div=2; div*div <=num; div++)
            {
                if(num%div == 0)
                {
                    count++;
                }
            }
            if(count ==0)
            {
                System.out.println(num);
                noofprime++;
            }
        }
        System.out.println("no of prime" + noofprime);
    }
    
}
