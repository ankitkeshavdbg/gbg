class Main 
{
    public static void main(String[] args) 
    {
      int[] arr = new int[3];
      arr[0] = 45;
      arr[1] = 32;
      arr[2] = 65;
      
      int[] two = arr;
      two[1] = 56;

      
      for(int i = 0; i <arr.length ; i++)
      {
        System.out.println(arr[i]);
      }
        
    }
}
-----------------------------------------------------------------
    
class Main 
{
    public static void swap(int[] arr, int i , int j)
    {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
    public static void main(String[] args) 
    {
      int[] arr = new int[5];
      arr[0] = 45;
      arr[1] = 32;
      arr[2] = 65;
      arr[3] = 54;
      arr[4] = 100;
      
      swap(arr, 0 ,4);
      
      for(int i = 0; i <arr.length ; i++)
      {
        System.out.println(arr[i]);
      }
        
    }
}
