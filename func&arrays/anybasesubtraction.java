// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main 
{
    public static void main(String[] args) 
    {
        int n1 = 236 ;
        int n2 = 1212;
        int b = 8;
        int a = getdifference(b,n1,n2);
        System.out.println(a);
    }
    
    public static int getdifference(int b, int n1, int n2)
    {
        int rv = 0;
        int c = 0;
        int pow = 1;
        while(n2>0)
        {
            int d1 = n1%10;
            int d2 = n2%10;
            n1 = n1/10;
            n2 = n2/10;
            int d = 0;
            d2 = d2+c;
            if(d2>=d1)
            {
                c=0;
                d = d2-d1;
            }
            else
            {
                c = -1;
                d = d2 +b -d1;
            }
            rv = rv +d *pow;
            pow = pow*10;
        }
        return rv;
    }
}
