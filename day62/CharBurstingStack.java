/*
Murali playing a mobile game, Blast the letters.

In the game he is given a word W and value R.
Murali has to perform the blasting operation as follows:
	- He has to blast the mimeograph M of length R in W, 
	  a mimeograph is a string such that each letter in it should be same.
	- After blasting the mimeograph, the rest of the string on its
	  left side and right side, concatenated together.

Murali has to perform the blasting operation repeatedly, 
until no more blasting is possible. Your task is to return 
the final string after all the blast operations have been done.

Input Format:
-------------
Line-1: A string and an integer, W and R.

Output Format:
--------------
Print a string, the final string after all the blast operations have been done.


Sample Input-1:
--------------- 
ababbaaab 3

Sample Output-1:
----------------
aba


Sample Input-2:
--------------- 
caaabbbaacdddd 2

Sample Output-2:
----------------
cabc

*/


import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        int k=sc.nextInt();
        Stack<int[]>st=new Stack<>();//[int(character),freq]
        for(int i=0;i<s.length();i++){
            int c=s.charAt(i)-'a';
            if(!st.isEmpty() && st.peek()[0]==c){
                int topVal=st.peek()[1];
                st.peek()[1]++;
                if(st.peek()[1]==k){
                    st.pop();
                }
            }
            else{
                st.add(new int[]{c,1});
            }
            
        }
        StringBuilder sb=new StringBuilder();
        while(!st.isEmpty()){
            int[]cur=st.pop();
            char c=(char)(cur[0]+'a');
            for(int i=0;i<cur[1];i++){
                sb.append(c);
            }
        }
        sb.reverse();
        System.out.println(sb.toString());
    }
}