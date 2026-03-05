// A satellite scans a long strip of land divided into N zones.
// Each zone emits a certain energy value (positive or negative).

// The satellite scanner can analyze exactly k consecutive zones 
// at a time. As the scanner moves from left to right (one zone at 
// a time), it computes the total energy of the current window.

// Given an integer array representing energy values and a window 
// size k, return the maximum sum of any contiguous subarray of length k.

// Input Format:
// -----------------
// Two integers N and K
// N space separated integers representing energy values

// Output Format:
// ------------------
// An integer — maximum total energy


// Sample Input
// ------------------
// 5 2
// 4 -1 2 10 -3

// Sample Output
// ------------------
// 12

// Explanation
// ---------------
// All possible subarrays of length K = 2:
// Subarray	Sum
// [4, -1]	3
// [-1, 2]	1
// [2, 10]	12 ✅
// [10, -3]	7

import java.util.*;
class MaxOfWinow{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int max=Integer.MIN_VALUE;
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=arr[i];
        }
        max=Math.max(max,sum);
        for(int i=k;i<n;i++){
            sum+=arr[i];
            sum-=arr[i-k];
            //System.out.println(sum);
            max=Math.max(max,sum);
        }
        System.out.println(max);
    }
}