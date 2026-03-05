// A grid of light bulbs is given, represented as a matrix of size rows x cols, 
// where each cell contains either 0 (off) or 1 (on).

// Your task is to turn all the light bulbs off (0) by following the toggle rule:
//     - In each step, you can choose either an entire row or an entire column 
//     and toggle all its elements (change 0 to 1 and 1 to 0).
    
// At the end, if all light bulbs are turned off, print true, otherwise print false.


// Input Format
// -------------
// Line-1: Read two integers rows and cols(space separated).
// Line-2: Read the matrix of dimension rows X cols.

// Output Format
// --------------
// Print a boolean result.



// Sample input-1:
// ---------------
// 5 5
// 0 0 1 0 0
// 0 0 1 0 0
// 1 1 0 1 1
// 0 0 1 0 0
// 0 0 1 0 0

// Sample output-1:
// ----------------
// true

// Explanation:
// ------------
// 0 0 1 0 0          0 0 1 0 0           0 0 0 0 0
// 0 0 1 0 0   row-3  0 0 1 0 0   cols-3  0 0 0 0 0
// 1 1 0 1 1   --->   0 0 1 0 0   --->    0 0 0 0 0
// 0 0 1 0 0          0 0 1 0 0           0 0 0 0 0
// 0 0 1 0 0          0 0 1 0 0           0 0 0 0 0 


// Sample input-2
// --------------
// 2 2
// 1 1
// 0 1

// Sample output-2
// ---------------
// false

import java.util.*;
class bitdiff{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int arr[][]=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j]=sc.nextInt();
            }
        }
        // String s="";
        // for(int num:arr[0]){
        //     s+=num;
        // }
        // String toggle="";
        // for(char c:s.toCharArray()){
        //     if(c=='1')toggle+='0';
        //     else{
        //         toggle+='1';
        //     }
        // }
        //System.out.println(s);
        //System.out.println(toggle);
        boolean flag=true;
        for(int i=1;i<n;i++){
            if(!check(arr,i)){
                flag=false;
            }
        }
        System.out.println(flag);
        
        
    }
    public static boolean check(int[][] arr, int row){
        int c=0;
        for(int i=0;i<arr[0].length;i++){
            c+=(arr[0][i]^arr[row][i]);
            //if(arr[0][i]!=arr[row][i])c++;
        }
        if(c==arr[0].length || c==0)return true;
        return false;
    }
}