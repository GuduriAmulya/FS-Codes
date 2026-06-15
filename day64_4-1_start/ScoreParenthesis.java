/*
Subodh is interested in playing with Strings,
For a given String 'S', Subodh applies some rules to find the value of 'S'.
The rules are as follows:
	- If it is a balanced () has value 1
	- XY has value X + Y , where X and Y are balanced () strings.
	- (Z) has score 2 * Z , where Z is a balanced parentheses string.
	
Find out the value of given String and print it.

Note: All the given strings are balanced

Input Format:
----------------
A String contains only '(', ')'


Output Format:
------------------
Print an integer as result.


Sample Input-1:
-------------------
()

Sample Output-1:
---------------------
1

Sample Input-2:
-------------------
(())

Sample Output-2:
---------------------
2

Sample Input-3:
-------------------
(()(()))


Sample Output-3:
---------------------
6


*/

import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        Stack<Integer>st=new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                st.add(0);
            }
            else{
                if(st.peek()==0){
                    st.pop();
                    st.push(1);
                }
                else{
                    int sum=0;
                    while(st.peek()!=0){
                        sum+=st.pop();
                    }
                    st.pop();
                    st.push(2*sum);
                }
            }
        }
        int a=0;
        while(!st.isEmpty()){
            a+=st.pop();
        }
        System.out.println(a);
    }
}