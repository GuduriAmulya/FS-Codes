/*
Vishal has given a task to find the successor of a given alphabet 'alp' 
in a given string str[]. The alphabets in the given string str[] are 
all lowercase letters and always in non-decreasing order.

A successor of 'alp' is the next smallest alphabet exist in the string str[],
which is greater than 'alp'. Assume that alphabets can be considered in 
cyclic fashion. For example: Successor for alp='z' in str[]="bcd" is 'b'.

Help Vishal to return the answer.

Can you solve it in O(log(n)) complexity?

Input Format:
-------------
Line-1:A string str[]
Line-2: a character represents an alphabet.
 
Output Format:
--------------
Print a character which is the successor.
 
Constraints:
 2 <= str.length<= 10^4
'alp' is a lowercase letter.

 
Sample Input-1:
---------------
adghijl
f 
Sample Output-1:
----------------
g

Sample Input-2:
---------------
abcdefghi
j

Sample Output-2:
----------------
a

*/


import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        // sc.nextInt();
        String c=sc.next();
        char ch=c.charAt(0);
        int l=0;
        int h=s.length()-1;
        int ans=0;
        while(l<=h){
            int mid=(l+h)/2;
            if(s.charAt(mid)>ch){
                ans=mid;
                h=mid-1;
            }
            else{
                l=mid+1;
            }
        }
        System.out.print(s.charAt(ans));
        // boolean found=false;
        // for(int i=0;i<s.length();i++){
        //     if(s.charAt(i)>c){
        //         found=true;
        //         System.out.println(s.charAt(i));
        //         break;
        //     }
        // }
        // if(!found){
        //     System.out.println(s.charAt(0));
        // }
        
    }
}