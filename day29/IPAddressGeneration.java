
// Pramod plans to design a program that generates all possible valid IP addresses from 
// a given string S.
// It is guaranteed that S contains only digits.

// Help Pramod by designing a program that returns all valid IP addresses generated from S.
// The IP addresses must be printed in lexicographic order.

// Note:

// - A valid IP address consists of exactly four integers, each ranging from 0 to 255, separated by single dots (.).
// - IP address segments cannot contain leading zeros.
// - Valid IP addresses must fall within the range 0.0.0.0 to 255.255.255.255.

// Examples of invalid IP addresses: 123.012.234.255, 123.234.345.34.

// Input Format:
// -------------
// A string S, contains only digits [0-9].

// Output Format:
// --------------
// Print all possible IP addresses which are valid.


// Sample Input-1:
// ---------------
// 23323311123

// Sample Output-1:
// ----------------
// [233.233.11.123, 233.233.111.23]


// Sample Input-2:
// ---------------
// 12345678

// Sample Output-2:
// ----------------
// [1.234.56.78, 12.34.56.78, 123.4.56.78, 123.45.6.78, 123.45.67.8]


// Sample Input-3:
// ---------------
// 02550255

// Sample Output-3:
// ----------------
// [0.25.50.255, 0.255.0.255]


import java.util.*;
class IPAddressGeneration{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        // each fragment size at atmost 3, if >1 then should not start with 0..
        List<String>ans=new ArrayList<>();
        solve(s,0,ans,new ArrayList<>());
        System.out.println(ans);
    }
    public static void solve(String s ,int ind, List<String>ans, List<String>path){
        if(ind==s.length() && path.size()==4){
            System.out.println(path);
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<path.size();i++){
                sb.append(path.get(i));
                if(i!=path.size()-1){
                    sb.append(".");
                }
            }
            ans.add(sb.toString());
            return;
        }
        // every segments size is from 1 to 3] 
        for(int i=1;i<=3;i++){
            if(i+ind>s.length())break;
            String sub=s.substring(ind,ind+i);
            System.out.println("sub: "+sub);
            if(isValid(sub)){
                path.add(sub);
                solve(s,ind+i,ans,path);
                path.remove(path.size()-1);
            }
        }
    }
    public static boolean isValid(String s){
        if(s.length()>1 && s.charAt(0)=='0')return false;//cannot strt with 0 if len>1
        int val=Integer.parseInt(s);
        if(val>0 && val<=255)return true;
        return false;
    }

}
