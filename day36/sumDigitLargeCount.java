/*
A digit sum is the sum of all the digits of a number.
e.g., 123=> 1 + 2 + 3 => 6, So, digit sum of 123 is 6.

You are given an integer N. 
Find the digit sum of each number from 1 to N.
And group them according to their digit sum.

Your task is to find and print the number of groups have the largest size.

Input Format:
-------------
An integer N

Output Format:
--------------
Print an integer, number of groups with largest size.

Sample Input-1:
---------------
13

Sample Output-1:
----------------
4

Explanation:
------------
There are 9 groups formed: [1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9]. 
There are 4 groups having largest size-2.


Sample Input-2:
---------------
24

Sample Output-2:
----------------
5

*/


import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<Integer,List<Integer>>mp=new HashMap<>();
        for(int i=1;i<=n;i++){
            int s=calsum(i);
            mp.putIfAbsent(s,new ArrayList<>());
            mp.get(s).add(i);
        }
        //System.out.println(mp);
        int maxlen=0;
        for(Map.Entry<Integer,List<Integer>>e:mp.entrySet()){
            int l=e.getValue().size();
            maxlen=Math.max(maxlen,l);
        }
        int c=0;
        for(Map.Entry<Integer,List<Integer>>e:mp.entrySet()){
            if(e.getValue().size()==maxlen){
                c++;
            }
        }
        System.out.println(c);
    }
    public static int calsum(int n){
        int sum=0;
        int temp=n;
        while(temp>0){
            sum+=temp%10;
            temp=temp/10;
        }
        return sum;
    }
}