/*
Few people are living in a township of size N*N, where each 1*1 part 
of the township is either a villa or a swimming pool. 
You are given the structure of township as a 2d matrix of size N*N, 
filled with 0's and 1's, where '0'-indicates a swimming pool and 
'1'- indiactes a villa. 

Your task is to find a swimming pool, such that its distance to 
the nearest villa(s) is maximized, and return the distance.

If the township contains only the villas or only swimming pools, print '-1'. 

The distance used in this problem is the Manhattan distance: 
the distance between two cells (a0, b0) and (a1, b1) is |a0 - a1| + |b0 - b1|


Input Format:
-------------
Line-1: An integer N, size of the 2d matrix.
Next N lines: N space separated integers, matrix[][] either 0 or 1.

Output Format:
--------------
An integer, maximum distance.


Sample Input-1:
---------------
4
1 0 1 1
0 0 0 0
1 0 1 0
1 0 0 1

0,1  0,2
0+1
Sample Output-1:
----------------
2

Explanation: 
------------
The swimming pool at (1, 1) is with distance 2 from the nearest villas.


Sample Input-2:
---------------
4
1 0 0 0
0 0 0 0
1 0 0 0
0 1 0 1

Sample Output-2:
----------------
3

Explanation: 
------------
The swimming pool at (0,3) or (1, 2) are with distance 3 from the nearest villas.

*/

import java.util.*;
class LandWaterBfs{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[][]=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=sc.nextInt();
            }
        }
        int ans=-1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==0){
                    //bfs.. return min dist
                    boolean[][]visited=new boolean[n][n];
                    int a =bfs(arr,i,j,visited);
                    //System.out.println("a: "+a+" for i: "+i+" j: "+j);
                    ans=Math.max(ans,a);
                }
            }
        }
        System.out.println(ans);
    }
    public static int bfs(int arr[][],int st_I,int st_J,boolean visited[][]){
        Queue<int[]>q=new LinkedList<>();
        q.offer(new int[]{st_I,st_J,0});
        visited[st_I][st_J]=true;
        while(!q.isEmpty()){
            int direc[][]={{0,1},{1,0},{0,-1},{-1,0}};
                int cur[]=q.poll();
                if(arr[cur[0]][cur[1]]==1){
                    //System.out.println("Got: "+cur[0]+" "+cur[1]);
                    return cur[2];
                }
                for(int d[]:direc){
                    int x=d[0]+cur[0];
                    int y=d[1]+cur[1];
                    if(x>=0 && x<arr.length && y>=0 && y<arr[0].length && !visited[x][y]){
                    visited[x][y]=true;
                    q.offer(new int[]{x,y,cur[2]+1});
                    }
            }
            
        }
        return -1;
    }
}