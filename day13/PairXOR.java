// Imagine you're managing a busy warehouse where every product is delivered in pairs to ensure proper stocking. However, due to a mix-up at the shipping dock, 
// two unique product IDs ended up without their matching pair, while all other products arrived as complete pairs.
// Your task is to identify these two solitary product IDs.

// You're given list of product IDs. In this list, every product ID appears exactly twice except for two IDs that appear only once. 
// Return these two unique product IDs in any order.

// You must design an algorithm that runs in linear time and uses only constant extra space.


// Example 1:
// ----------
// Input: 
// 101 102 101 103 102 105  

//     1100101  =>101
//     1100110  =>102
// XOR= 0000011 sets=2
//      1100101  =>101
// XOR= 1100110 set 4
//      1100111  =>103
// XOR= 0000001 set 1
//      1100110  =>102
// XOR= 1100111 set 5
//      1101001  =>105
// XOR =0001110 set 3 number =14 , -14= 1's comp+1  1110001+1=>1110010
// -XOR=1110010
// AND= 0000010 =>mask...=> 1st position...
// check 1st position in binary notation of the numbers (0 based indexing) from right..
// 1st pos =0 (101,105,101) XOR=> 105
// 1st pos =1(102,103,102) XOR=>103...


// Output: 
// [103, 105] 


// Explanation: The IDs 103 and 105 appear only once, while all other IDs appear twice. [105, 103] is also an acceptable answer.

// Example 2:
// -----------
// Input: 121 136
// Output: [121, 136] 

import java.util.*;
class PairXOR{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int arr[]=new int[a.length];
        for(int i=0;i<a.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        int xor=0;
        for(int n:arr){
            // System.out.println(Integer.toBinaryString(n));
            xor=xor^n;
            // System.out.println("XOR: "+xor+" bin: "+Integer.toBinaryString(xor));
        }
        int diff= xor & -xor; //gives rightmost set bit.. 0000010 =2
        
        System.out.println(diff);
        int a1=0;
        int b=0;
        for(int n:arr){
            if((n&diff)==0){
                a1^=n;
            }
            else{
                b^=n;
            }
        }
        System.out.print(a1+" "+b);
        //check the pos.
        // while(xor!=0){
        //     int i=0;
        //     for(int n:arr){
        //         if(xor&(1<<i)!=0)
        //     }
        // }
        
    }
}