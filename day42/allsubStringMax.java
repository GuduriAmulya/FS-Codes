/*
Mr Parandhamayya working with words.
He is given a word W, you need to divide the word into N non-empty parts, 
such that all the newly formed words should be distinct, and 
if you append all those words should form original word W.

Your task is to help Mr Parandhamayya to divide the word into N parts,
such that, the value of N should be maximized, and print N.

Input Format:
-------------
Line-1: A string W, a word.

Output Format:
--------------
Print an integer result, the value of N.


Sample Input-1:
---------------

banana

each portion can rANGE FROM 1 char till end..

ba
ban
bana
banan
Sample Output-1:
----------------
4

Explanation: 
------------
One way to divide the word is "b","a","n","ana".
If you divide it like "b","a","n","an","a".The word "a" will be repeated.
So it is not allowed to divide like the second way.


Sample Input-2:
---------------
mississippi


m,i,s,si,ss,ip,pi
Sample Output-2:
----------------
7

Explanation: 
------------
One of the way to divide the word is "m","i","s","si","ssi","p","pi".

NOTE: Subsequences are not allowed.
*/


import java.util.*;
class Solution{
    public static int count=Integer.MIN_VALUE;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        List<String>arr=new ArrayList<>();
        //optimization would be use a SET
        solve(s,0,arr,0);
        System.out.println(count);
    }
    public static void solve(String s, int start, List<String>arr,int c){
        if(start==s.length()){
            //System.out.println(arr);
            count=Math.max(count,c);
            return;
        }
        //multiple partitons can bemode..
        for(int i=start;i<s.length();i++){
            String st=s.substring(start,i+1);
            if(!arr.contains(st)){
                arr.add(st);
                solve(s,i+1,arr,c+1);
                arr.remove(arr.size()-1);
            }
        }
        
    }
}