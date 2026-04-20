/*
Mr Rajendra Tapadia is given a number N as string to Mr Satyam, and ask him 
to find the number of ways to make that number N equal to zero by using 
the following steps:
    - He need to perform either '+' or '-' operation between two adjacent digits
    - You can repeat the above step to make the N value to 0.
    
For example: if N is 454522 then it's possible to perform the '+'/'-' operations 
the following way, 4+5-4-5-2+2, 4-5-4+5-2+2, 4+5-4-5+2-2 or 4-5-4+5+2-2.
A total of 4 ways.

Your task is to help Mr Satyam to find the number of ways possible to make N to 0
using the above steps. Print "invalid", if it is not possible.

Input Format:
-------------
A String, the number N.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
13741

Sample Output-1:
----------------
2

Explanation: 
------------
The ways are, 1+3-7+4-1 and 1-3+7-4-1.


Sample Input-2:
---------------
2351

Sample Output-2:
----------------
invalid

*/

import java.util.*;
class OperationsToMake0{
    public static int count=0;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        List<Integer>list=new ArrayList<>();
        for(char c:s.toCharArray()){
            list.add(c-'0');
        }
        solve(list,1,list.get(0));
        if(count==0){
            System.out.println("invalid");
            return;
        }
        System.out.println(count);
    }
    public static void solve(List<Integer>list, int ind,int val){
        if(ind==list.size() && val==0){
           // System.out.println("found");
            count++;
            return;
        }
        else if(ind==list.size()){
            return;
        }
        //for every index.. try 2 operations..
        //System.out.println("adding "+list.get(ind)+" val: "+(list.get(ind)+val));
        solve(list,ind+1,val+list.get(ind));
         //System.out.println("removing "+list.get(ind)+" val: "+(val-list.get(ind)));
        solve(list,ind+1,val-list.get(ind));
        
            
    }
}