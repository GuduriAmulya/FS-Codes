// Raju is organizing a friendly competition where each participant submits a 
// secret code representing their choices on a series of questions which are 
// answered as yes or no. Each code is a number, and when you convert it to binary, 
// every digit indicates a specific answer: 0 for "no" and 1 for "yes." 

// The uniqueness of each participant's code lies in how much it differs from another's. 
// To measure this, you compare two codes digit by digit and count the number of 
// positions where their answers disagree.

// Given an array of these integer-encoded codes, help Raju to calculate the total 
// sum of these disagreement counts for every possible pair of participants.

// Example 1:
// ----------
// Input: 
// 5 13 3

// Output: 
// 6

// Explanation:
// Converting to binary (using four bits for clarity):

// 5 is 0101
// 13is 1101
// 3 is 0011

// 0th bit pos all 1's 0 0's so 3*0=0
// 1st bit pos 1 1's 2 0's =>2*1=2
// 2nd bit pos 2 1's and 1 0's =>2*1=>2
// 3rd bit pos 1 1's and 2 0's =>2*1=>2
//sum them up=>6
//5 ^13=>1000 (xor)(xoring gives u which bit is flipped ie the bit which is diff)
//3rd bit is different thats the reason we got 3rd bit as 1..
//now calculate the number of set bits..
//for(i=0..32) for(int num: nums)if(xor&(1<<i)!=0)count++;


// Now, compare each pair and count the number of positions with different digits:

// Comparing 5 (0101) and 13 (1101) gives 1 differences.
// Comparing 5 (0101) and 3 (0011) gives 2 differences.
// Comparing 13 (1101) and 3 (0011) gives 3 differences.
// Total differences = 1 + 2 + 3 = 6.


//optimal using bit manipulation

// public class TotalDisagreement {

//     public static int totalHammingDistance(int[] nums) {
//         int n = nums.length;
//         int result = 0;

//         // Check each bit position (0 to 31 for int)
//         for (int bit = 0; bit < 32; bit++) {
//             int countOnes = 0;

//             for (int num : nums) {
//                 if ((num & (1 << bit)) != 0) {
//                     countOnes++;
//                 }
//             }

//             int countZeros = n - countOnes;
//             result += countOnes * countZeros;
//         }

//         return result;
//     }

//     public static void main(String[] args) {
//         int[] nums = {5, 13, 3};
//         System.out.println(totalHammingDistance(nums)); // Output: 6
//     }
// }

import java.util.*;
class binarydiff{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String arr[]=sc.nextLine().split(" ");
        int num[]=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            num[i]=Integer.parseInt(arr[i]);
        }
        int count=0;
        for(int i=0;i<num.length;i++){
            count+=(check(num[i],num,i));
        }
        System.out.println(count);
    }
    public static int check(int number, int[]num,int index){
        int ans=0;
        String s1=Integer.toBinaryString(number);
        for(int i=index+1;i<num.length;i++){
            String s2=Integer.toBinaryString(num[i]);
            //System.out.println(s1+" "+s2);
            int diff=calcDiff(s1,s2);
            //System.out.println("diff: "+diff);
            ans+=diff;
        }
        return ans;
    }
    public static int calcDiff(String s1,String s2){
        while(s1.length()<s2.length()){
            s1='0'+s1;
        }
        while(s2.length()<s1.length()){
            s2='0'+s2;
        }
        //System.out.println(s1+" "+s2);
        int v=0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                v++;
            }
        }
        return v;
    }
}