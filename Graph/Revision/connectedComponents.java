import java.util.*;
public class connectedComponents {

    public static int printStairPath(int n){
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }



            int i1= printStairPath(n-1);
            int i2= printStairPath(n-2);
            int i3= printStairPath(n-3);

            int total = i1 + i2 + i3;
            return total;

    }

    public static void main(String[] args){
        int n = 10;
        int total= printStairPath(n);
        System.out.println(total);
        
    
    }
}
