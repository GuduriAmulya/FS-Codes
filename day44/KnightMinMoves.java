/*
ASKED IN GT:3...
You are designing an autonomous surveillance drone that operates 
over an infinite 2D grid representing terrain coordinates.

The drone starts at position (0, 0).

In a single step, the drone can move in an L-shaped pattern. From 
a position (p, q), it can move to any of the following positions:
(p − 2, q − 1), (p − 2, q + 1), (p + 2, q − 1), (p + 2, q + 1)
(p − 1, q + 2), (p + 1, q + 2), (p − 1, q − 2), (p + 1, q − 2)

Given two integers m and n, representing the target position (m, n), determine:
The minimum number of steps required for the drone to reach the target 
from (0, 0).


Input Format:
-----------------
Two space separated integers, m and n, position.

Output Format:
------------------
Print an integer, minimum number of steps to reach (m,n).


Sample Input-1:
---------------
2 4

Sample Output-1:
----------------
2

Explanation:
-------------
Initially, you are at (0,0) position, you can reach (2,4) as follows:
(0,0) -> (1, 2) -> (2, 4) 


Sample Input-2:
---------------
4 7

Sample Output-2:
----------------
5

Explanation:
------------
Initially, you are at (0,0) position, you can reach (4,7) as follows:
(0,0) -> (1, 2) -> (2, 4) -> (1, 6) -> (3, 5) -> (4, 7)
*/

import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        int y=sc.nextInt();
        x=Math.abs(x);
        y=Math.abs(y);
        Queue<int[]>q=new LinkedList<>();
       Set<String>visited=new HashSet<>();
        q.offer(new int[]{0,0});
        visited.add("0,0");
        int c=0;
        while(!q.isEmpty()){
            int s=q.size();
            for(int i=0;i<s;i++){
                int[]cur=q.poll();
                if(cur[0]==x && cur[1]==y){
                    System.out.println(c);
                    return;
                }
                int direc[][]={{-1,2},{2,-1},{1,-2},{1,2},{2,1},{-2,-1},{-2,1},{-1,-2}};
                for(int d[]:direc){
                    int x1=d[0]+cur[0];
                    int y1=d[1]+cur[1];
                    if(x1>=-2 && y1>=-2 && x1<=x+2 && y1<=y+2){
                        String key=x1+","+y1;
                        if(!visited.contains(key)){
                            visited.add(key);
                            q.offer(new int[]{x1,y1});
                        }
                    }
                }
            }
            c++;
        }
        
            System.out.println(-1);
        
    }
}