class Main 
{
    public static void main(String[] args) 
    {
        int n1 = 236;
        int n2 = 64;
        int b = 8;
        int a = getproduct(b,n1,n2);
        System.out.println(a);
    }
    
    public static int getproduct (int b , int n1 , int n2)
    {
      int rv =0;
      int p=1;
      while(n2>0)
      {
        int d2 = n2%10;
        n2 = n2/10;
        int sdp = getproductwithasingledigit(b,n1,d2);
        rv = getsum(b,rv,sdp*p);
        p = p*10;
      }
      return rv;
    }
    
    public static int getproductwithasingledigit(int b, int n1, int d2)
    {
      int rv = 0;
      int c = 0;
      int p=1;
      while(n1>0 || c >0)
      {
        int d1 = n1%10;
        n1 = n1/10;
        int d = d1*d2 + c;
        c = d/b;
        d = d%b;
        rv = rv +d *p;
        p=p*10;
      }
      return rv;
        
    }
    
    public static int getsum(int b, int n1, int n2)
    {
        int c = 0;
        int rv = 0;
        int p = 1;
        while(n1>0 || n2>0 || c>0)
        {
            int d1= n1%10;
            n1 = n1/10;
            int d2 = n2%10;
            n2 = n2/10;
            int d = d1 +d2 + c;
            c = d/b;
            d = d%b;
            rv = rv + d *p;
            p = p*10;
        }
      return rv;    
    }
}
