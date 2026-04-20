/*
You are navigating a spaceship through a galaxy represented as an m x n space map.  
The spaceship starts from the top-left sector (sector[0][0]) and its mission is 
to safely reach the bottom-right sector (sector[m-1][n-1]).

Each sector on the map can either be clear (0) or blocked by an asteroid field (1).  
The spaceship can only move right or downward at any moment.  
It cannot pass through sectors with asteroid fields.

Return the total number of distinct safe routes the spaceship can take to reach 
its destination at the bottom-right corner.


Input format:
-------------
2 space seperated integers, m & n
next m lines of representing the sector 

Output format:
--------------
An integer, the total number of distinct safe routes



Example 1:
----------
Input:
3 3
0 0 0
0 1 0
0 0 0

Output:
2

Explanation:  
There’s one asteroid field blocking the center of the 3×3 map.  
Two possible safe navigation paths:
- Move → Move → Down → Down
- Down → Down → Move → Move

Example 2:
---------
Input:
2 2
0 1
0 0

Output:
1


Constraints:
- m == sectorMap.length
- n == sectorMap[i].length
- 1 <= m, n <= 100
- sectorMap[i][j] is either 0 (clear) or 1 (asteroid field)
*/

import java.util.*;
class Solution{
    public static int count=0;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int arr[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=sc.nextInt();
            }
        }
        if(arr[0][0]!=0 || arr[m-1][n-1]!=0){
            System.out.println(0);
            return;
        }
        solve(0,0,arr);
        System.out.println(count);
    }
    public static void solve(int i, int j, int[][]arr){
        if(i==arr.length-1 && j==arr[0].length-1 && arr[i][j]==0){
            count++;
            return;
        }
        int[][]direc={{0,1},{1,0}};
        for(int d[]:direc){
            int x=d[0]+i;
            int y=d[1]+j;
            if(x>=0 && x<arr.length && y>=0 && y<arr[0].length && arr[x][y]==0){
                solve(x,y,arr);
            }
        }
    }
}