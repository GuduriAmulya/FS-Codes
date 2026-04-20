/*
Mr. James professor at MIT, as a part of assignment he created a problem statement 
related to words. He gave a word w and asked them to design a method to return 
the longest substring w1 in word w, where w1 in reverse is also equal to w1.

NOTE: Alphabets are case sensitive ('A' and 'a' are not same).


Input Format:
-------------
A string S, consist of lowercase/uppercase letters or/and digits

Output Format:
--------------
Print a string.


Sample Input-1:
---------------
abbbabbcbbacdb

Sample Output-1:
----------------
abbcbba


Sample Input-2:
---------------
thedivideriswide

Sample Output-2:
----------------
edivide

*/



import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        int ans=0;
        int start=0;
        int end=0;
        int len=0;
        for(int i=0;i<s.length()-1;i++){
            //odd length..
            int l1[]=solve(s,i,i);//left and right.. extend from both sides..
            int l2[]=solve(s,i,i+1);
            System.out.print("for: "+i+" ");
            if(l1[1]-l1[0]+1>len){
                start=l1[0];
                end=l1[1]+1;
                len=l1[1]-l1[0]+1;
            }
            if(l2[1]-l2[0]+1>len){
                start=l2[0];
                end=l2[1]+1;
                len=l2[1]-l2[0]+1;
            }
            //System.out.println(s.substring(l1[0],l1[1]+1)+" "+s.substring(l2[0],l2[1]+1));
            
        }
        System.out.println(s.substring(start,end));
        //System.out.println(ans);
    }
    public static int[] solve(String s ,int l, int r){
        if(r==s.length())return new int[]{l,r};
        int st=l;
        int e=r;
        int len=1;
        l=l-1;
        r=r+1;
        while(l>=0 && r<s.length()){
            if(s.charAt(l)!=s.charAt(r)){
                return new int[]{l+1,r-1};
            }
            l--;
            r++;
        }
        return new int[]{st,e};
    }
}