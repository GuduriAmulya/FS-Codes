/*
Ram and Bheem are using a Desktop Computer.One day they found that keyboard 
is defective in which if you type backspace button,it will print '$', 
instead of removing one previous character.

Bheem and Ram have tried to type one word each on the same keyboard.
Return true, if both tried to type the same word. Otherwise return false.

Note:backspace for an empty text will continue empty.

Input Format:
-------------
Line-1:Two space seperated strings represents words w1,w2.

 
Output Format:
--------------
Print a boolean result.
 
Constraints:

    1 <= w1.length, w2.length <= 200
    w1 and w2 only contain lowercase letters and '$' characters.


 
Sample Input-1:
---------------
pq$r  pt$r

Sample Output-1:
----------------
true

Explanation:
------------
Both wants to type 'pr'

Sample Input-2:
---------------
se$$at cea$$t

Sample Output-2:
----------------
false

Sample Input-3:
---------------
s$$at ce$$at

Sample Output-2:
----------------
true

Explanation:
------------
Both wants to type 'at'.


*/
//container =>linux process


import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<a[0].length();i++){
            char ch=a[0].charAt(i);
            if(Character.isLetter(ch)){
                sb.append(ch);
            }
            else{
                if(sb.length()!=0){
                sb.deleteCharAt(sb.length()-1);
                }
            }
        }
        StringBuilder sb1=new StringBuilder();
        for(int i=0;i<a[1].length();i++){
            char ch=a[1].charAt(i);
            if(Character.isLetter(ch)){
                sb1.append(ch);
            }
            else{
                if(sb1.length()!=0){
                sb1.deleteCharAt(sb1.length()-1);
                }
            }
        }
        //System.out.println(" "+sb1.toString());
        System.out.println(sb.toString().equals(sb1.toString()));
    }
}