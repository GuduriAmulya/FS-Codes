/*
You are working in a genetics laboratory where you are tasked with correcting 
DNA sequences. Each DNA strand is represented as a sequence of characters 'A', 
'C', 'G', and 'T'. Sometimes, due to mutations or errors during sequencing, the 
DNA strand (originalDNA) must be modified to match a targetDNA sequence exactly.

You can perform the following mutation operations:
- Insert a nucleotide (A, C, G, or T) into the DNA strand.
- Delete a nucleotide from the DNA strand.
- Replace a nucleotide with another one.

Each operation counts as one step.

Your task is to find the minimum number of mutation operations needed to 
transform the originalDNA into the targetDNA.

Input format:
-------------
2 space seperated strings, originalDNA, targetDNA

Output format:
--------------
An integer, the minimum number of mutation operations


Example 1:
-----------
Input:
ACGT AGT

Output:
1

Explanation:
Delete 'C': "ACGT" → "AGT"
Only 1 mutation is needed.

Example 2:
----------
Input:
GATTAC GCATGCU

Output:
4

Explanation:
- Replace 'A' with 'C': "GATTAC" → "GCTTAC"
- Replace 'T' with 'A': "GCTTAC" → "GCATAC"
- Replace 'A' with 'G': "GCATAC" → "GCATGC"
- Insert 'U' at the end: "GCATGC" → "GCATGCU"

Thus, 4 mutations are needed.

*/

import java.util.*;
class Solution{
    public static int dp[][];
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String original=sc.next();
        String target=sc.next();
        dp=new int[original.length()+1][target.length()+1];
        for(int r[]:dp){
            Arrays.fill(r,-1);
        }
        //at every position if the char at both the index is not same i can perform any 3 ops.. insert char with A,C,G,T i++,replace with target char i++,j++
        //or delete the char at the orig pos, i++,  return min 
        int a=solve(original,target,0,0);
        System.out.println(a);
    }
    public static int solve(String original, String target, int ind1,int ind2){
        // if((ind1==original.length()) && (ind2==target.length())){
        //     //ans=Math.min(ans,cnt);
        //     return cnt;
        // }
        if(ind2==target.length()){
            return (original.length()-ind1);
        }
        if(ind1==original.length()){
            //we need extra taregt.leng-ind2 ele
            return (target.length()-ind2);
        }
        if(dp[ind1][ind2]!=-1)return dp[ind1][ind2];
        //3 choices..
        int insert=0;
        int del=0;
        int replace=0;
        if(original.charAt(ind1)!=target.charAt(ind2)){
            replace=1+solve(original,target,ind1+1,ind2+1);//replacing with target char..
            insert=1+solve(original,target,ind1,ind2+1);
            del=1+solve(original,target,ind1+1,ind2);
            //System.out.println("For i1: "+ind1+" i2: "+ind2+" r: "+replace+" ins: "+insert+" del: "+del);
        }
        else{
            return dp[ind1][ind2]=solve(original,target,ind1+1,ind2+1);
        }
        return dp[ind1][ind2]=Math.min(replace,Math.min(insert,del));
    }
}