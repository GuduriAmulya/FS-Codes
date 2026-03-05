// You are given some tokens printed with unique numbers on it and an integer T.
// Your task is to find the least number of tokens that you need to make up the 
// value T, by adding the numbers printed on all the tokens. 
// If you cannot make the value T, by any combination of the tokens, return -1.

// NOTE: Assume that you have unlimited set of tokens of each number type.

// Input Format:
// -------------
// Line-1: Space separated integers tokens[], number printed on tokens.
// Line-2: An integer T.

// Output Format:
// --------------
// Print an integer, minimum number of tokens to make the value T.


// Sample Input-1:
// ---------------
// 1 2 5
// 11

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// 5+5+1 = 11


// Sample Input-2:
// ---------------
// 2 4
// 15

// Sample Output-2:
// ----------------
// -1


import java.util.*;
class coinchange{
    public static int count=Integer.MAX_VALUE;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s[]=sc.nextLine().split(" ");
        int arr[]=new int[s.length];
        for(int i=0;i<s.length;i++){
            arr[i]=Integer.parseInt(s[i]);
        }
        int k=sc.nextInt();
        Arrays.sort(arr);
        //dp working!
        int dp[]=new int[k+1];
        Arrays.fill(dp,k+1);
        dp[0]=0;
        for(int p:arr){
            for(int i=p;i<=k;i++){
                dp[i]=Math.min(dp[i],dp[i-p]+1);
            }
        }
        //System.out.println(Arrays.toString(dp));
        if(dp[k]>k){
            System.out.println(-1);
        }
        else{
            System.out.println(dp[k]);
        }
        //solve(0,arr,k,0);
        // if(count==Integer.MAX_VALUE){
        //     System.out.println(-1);
        //     return;
        // }
        // System.out.println(count);
        
    }
    //backtrack=>TLE
    public static void solve(int ind, int[] arr,int target,int c){
        if(target==0){
            count=Math.min(count,c);
            return;
        }
        for(int i=ind;i<arr.length;i++){
            if(arr[i]>target)break;
                //System.out.println("takingL "+arr[i]);
                solve(i,arr,target-arr[i],c+1);
                //System.out.print(count+" ");
            
        }
        
    }
}