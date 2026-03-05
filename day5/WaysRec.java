// You are a robot explorer navigating a vast digital maze stored as a string of 
// digits. Each digit or pair of digits on the path represents a movement instruction, 
// which translates to a specific direction using the following map:
    
//     "1" → Move 'A'
    
//     "2" → Move 'B'
    
//     ...
    
//     "26" → Move 'Z'

// However, the maze has tricky encoding rules. Sometimes a single digit can be 
// read alone, and sometimes two digits together form a valid move — but not every 
// combination is valid. For example, "05" is invalid, while "5" is fine. Your 
// robot can only navigate using valid instruction steps, and you must find how 
// many unique navigation sequences the robot can follow to reach the end of the maze.

// Given the string s of digits, determine the total number of distinct ways the 
// robot can interpret and follow the path from start to end without making an 
// invalid move.

// If no valid navigation is possible, return 0.


// Input Format:
// -------------
// A string s.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 123

// Sample Output-1:
// ----------------
// 3

// Explanation:
// ------------
// 123 can be converted as: ABC, LC, AW


// Sample Input-2:
// ---------------
// 326

// Sample Output-2:
// ----------------
// 2

// Explanation:
// ------------
// 326 can be converted as: CBF, CZ








import java.util.*;
class WaysRec{
    public static int dp[];
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        dp=new int[s.length()];
        Arrays.fill(dp,-1);
        int ans=solve(s,0);
        //System.out.println(Arrays.toString(dp));
        System.out.println(ans);
    }
    public static int solve(String s, int ind){
        if(ind>=s.length()){
            //System.out.println("Found");
            return 1;//found
        }
        int ways=0;
        if(dp[ind]!=-1)return dp[ind];
        for(int i=1;i<=2;i++){
            //try
            if(ind+i>s.length())break;
            String sub=s.substring(ind,ind+i);
            //System.out.println(sub);
            if(isValid(sub)){
                //System.out.println("next ind"+(ind+i));
                ways+=solve(s,ind+i);
            }
        }
        return dp[ind]=ways;
    }
    public static boolean isValid(String s){
        int num=Integer.parseInt(s);
        if(s.length()>1 && s.charAt(0)=='0')return false;
        if(num>=1 && num<=26){
            //System.out.println("Valid");
            return true;
        }
        return false;
    }
}