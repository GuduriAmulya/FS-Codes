// Amogh is an Antiquarian, The person who collects antiques.
// He found a rear keyboard which has following keys,
// Keys are 'N', 'S', 'C' and 'P'

// 1st Key - 'N': Print one character 'N' on Console.
// 2nd Key - 'S': Select the whole Console.
// 3rd Key - 'C': Copy selected content to buffer.
// 4th Key - 'P': Print the buffer on Console, and append it after what has 
// already been printed.

// Now, your task is to find out maximum numbers of 'N's you can print
// after K keystrokes . 

// Input Format:
// -------------
// An integer K

// Output Format:
// --------------
// Print an integer, maximum numbers of 'N's you can print.


// Sample Input-1:
// -------------------
// 3

// Sample Output-1:
// -------------------- 
// 3

// Explanation: 
// ---------------
// We can print at most get 3 N's on console by pressing following key sequence:
// N, N, N



// Sample Input-2:
// -------------------
// 7

// Sample Output-2:
// ---------------------
// 9

// Explanation: 
// ---------------
// We can print at most get 9 N's on console by pressing following key sequence:
// N, N, N, S, C, P, P




import java.util.*;
class PrintN{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int ans=solve(n);
        System.out.println(ans);
    }
    public static int solve(int n){
        if(n<=5)return n;
        //k ranges bw 1 to n-3 because at last we need 3 operations atleast for s,c,p;
        int max=0;
        for(int i=1;i<n-3;i++){
            int a=solve(i)*(n-i-1);
            max=Math.max(max,a);
        }
        return max;
    }
    // public static int solve(int n){
    //     if(n<=5){
    //         return n;
    //     }
    //     int k=0;
    //     int ans=0;
    //     if(n%2!=0){
    //         k=(n/2)+1;
    //         int rem=n-k-2;
    //         ans=k+(rem*k);
    //         //System.out.println(ans);
    //     }
    //     k=(n/2);
    //     int rem=n-k-2;
    //     ans=Math.max(ans,k+(rem*k));
        
    //     return ans;
    // }
    
    
}