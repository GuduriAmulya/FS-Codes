// Imagine you are a secret agent tasked with sending encoded messages. 
// Each original message is a string of lowercase letters, and you can disguise 
// it by replacing selected, non-adjacent segments with the count of characters 
// in those segments. However, the encoding must follow strict rules: only 
// non-empty segments can be replaced, replacements cannot be adjacent, and any 
// numbers used must not have leading zeros.

// For instance, the message "substitution" can be encoded in various ways, such as:

// • "s10n" (keeping 's', replacing the next 10 characters with 10, and ending with 'n')  
// • "sub4u4" (keeping "sub", replacing 4 characters, then 'u', and replacing 4 more characters)  
// • "12" (replacing the entire message with its length)  
// • "su3i1u2on" (using a different pattern of replacements)  
// • "substitution" (leaving the message unaltered)

// Invalid encodings include:

// • "s55n" (because the replaced segments are adjacent)  
// • "s010n" (the number 010 has a leading zero)  
// • "s0ubstitution" (attempts to replace an empty segment)

// Your task is: given an original message and an encoded version, 
// determine if the encoded version is a valid secret code for the message.


// Sample Input-1: 
// ---------------
// internationalization
// i12iz4n
  
// Sample Output-1: 
// ----------------
// true  

// Explanation: 
// ------------
// "internationalization" can be encoded as "i12iz4n" by keeping 'i', 
// replacing 12 letters, keeping "iz", replacing 4 letters, and then ending with 'n'.


// Sample Input-2: 
// ---------------
// apple
// a2e
  
// Sample Output-2:
// ----------------
// false  

// Explanation: 
// ------------
// The encoding "a2e" does not correctly represent "apple".

// Time Complexity: O(n) where n=max(len(word),len(abbr))
// Auxiliary Space:  O(1).

import java.util.*;
class AbbreviationWord{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String t=sc.nextLine();
        int i=0;
        int j=0;
        boolean flag=true;
        while(i<s.length() && j<t.length()){
            char c1=s.charAt(i);
            char c2=t.charAt(j);
            if(Character.isLetter(c1) && Character.isLetter(c2)){
                if(c1==c2){
                    i++;
                    j++;
                }
                else{
                    flag=false;
                    break;
                }
            }
            else if(Character.isDigit(c2)){
                //target is a number..
                int num=0;
                //cannot start with 0
                if(c2=='0'){
                    flag=false;
                    break;
                }
                while(j<t.length() && !Character.isLetter(t.charAt(j))){
                    char b=t.charAt(j);
                    num=num*10+(b-'0');
                    j++;
                }
                //System.out.println(num);
                //skip num chars in s.
                if(i+num>s.length()){
                    flag=false;
                    break;
                }
                i+=num;
                if(j<t.length() && Character.isDigit(t.charAt(j))){
                    flag=false;
                    break;
                }
               // System.out.println(i+" "+j);
            }
        }
        if(flag==false){
            System.out.println("false");
        }
        else{
            if(i==s.length() && j==t.length()){
                System.out.println("true");
            }
            else{
                System.out.println("false");
            }
        }
    }
}