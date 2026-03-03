import java.util.*;

public class displayArray {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        
        System.out.println("Forward Display:");
        displayArr(arr, 0);
        
        System.out.println("\nReverse Display:");
        displayArrReverse(arr, 0);
    }

    // Weapon 1: Pre-order (Printing while building the stack)
    public static void displayArr(int[] arr, int idx){
        if(idx == arr.length){
            return;
        }

        System.out.print(arr[idx] + " "); // Self Work
        displayArr(arr, idx + 1);        // Faith
    }

    // Weapon 2: Post-order (Printing while collapsing the stack)
    public static void displayArrReverse(int[] arr, int idx){
        if(idx == arr.length){
            return;
        }

        displayArrReverse(arr, idx + 1); // Faith (Go to the end first)
        
        // This line waits! It only executes when the call above it returns.
        System.out.print(arr[idx] + " "); // Self Work
    }
}