/*
There are a series of bulbs numbered from 1 to n and initially all bulbs are 
turned off.

Now there are n istructions to be followed sequentially which are represented 
in an array a[],where a[i] is the value where you have to turn on a[i]-th bulb
in the series.

After every instructions you need to find a contiguous series of bulbs which 
are turned on such that it cannot be extended in either direction.

Given an integer k return the recent instruction at which there exists exactly 
'k'contiguous series of bulbs. If no such series exists, return -1.

Input Format:
-------------
Line-1: an integer n represents the number of instructions
Line-2: n space seperated integers represents instructions to be followed sequentially.
Line-3: An integer k.

Output Format:
--------------
return an integer represents recent instruction number.

Sample Input-1:
---------------
5
3 5 1 2 4
1

Sample Output-1:
----------------
4

Explanation:
------------
Step 1: "00100", sets: ["1"]
Step 2: "00101", sets: ["1", "1"]
Step 3: "10101", sets: ["1", "1", "1"]
Step 4: "11101", sets: ["111", "1"]
Step 5: "11111", sets: ["11111"]
The recent step at which there exists a group of size 1 is step 4.


Sample Input-2:
---------------
5
3 1 5 4 2
2

Sample Output-2:
----------------
-1

Explanation:
-------------
Step 1: "00100", sets: ["1"]
Step 2: "10100", sets: ["1", "1"]
Step 3: "10101", sets: ["1", "1", "1"]
Step 4: "10111", sets: ["1", "111"]
Step 5: "11111", sets: ["11111"]
No group of size 2 exists during any step.

*/ 

import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int k=sc.nextInt();
        int ans=-1;
        for(int i=0;i<n;i++){
            if(isPossible(arr,i,k)){
                ans=i+1;
            }
        }
        System.out.println(ans);
        // int l=0;
        // int h=n-1;
        // int ans=-1;
        // while(l<=h){
        //     int mid=l+(h-l)/2;
        //     if(isPossible(arr,mid,k)){
        //         ans=mid+1;
        //         l=mid+1;
        //     }
        //     else{
        //         h=mid-1;
        //     }
        // }
        //System.out.println(ans);
     }
    public static boolean isPossible(int arr[],int mid, int k){
        int newA[]=new int[arr.length];
        for(int i=0;i<=mid;i++){
            int val=arr[i]-1;
            newA[val]=1;
            //count contiguous series of bulbs..
            
        }
        if(contigousPossible(newA,k)){
                return true;
            }
        return false;
    }
    public static boolean contigousPossible(int []arr, int k){
        int len=0;
        int l=0;
        //[1,0,1,1]
        for(int r=0;r<arr.length;r++){
            if(arr[r]==1){
                len++;
                // System.out.println(Arrays.toString(arr));
            }
            else{
                if(k==len)return true;
                len=0;
            }
        }
        if(k==len)return true;
        return false;
    }
}