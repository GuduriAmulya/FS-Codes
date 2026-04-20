/*
A Train  has to go through N railway-stations, the stations are in a cyclic route. 
Each railway-station 'k' has coal[k].

The Train has unlimited coal tank and it consumes cost[i] of coal to travel 
from railway-station i to its next railway-station (i+1). 

The Train starts with an empty coal tank at one of the railway-stations.

Return the starting railway station's index,
If you can travel through all the railway-stations and 
reaches the starting-station in the clockwise direction, 
otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.

Input Format:
-------------
Line-1 -> Space separated integers coal[i]. 
Line-2 -> Space separated integers cost[i].

Output Format:
--------------
Print the station number if possible,
Otherwise print -1.


Sample Input-1:
---------------
1 2 3 4 5
3 4 5 1 2

//if i start from 0th coal=1, but i have to spend 3 to reach next station so not possible.. coal=0, 
// now i=1 coal=>2, cost to go to next station =4 >coal so nope.. coal=0,
i=2 coal=3, but cost =5>3 so initialize coal=0;
i=3, coal=4, cost=1 yes.. so coal=3 ind=4 coal=>3+5=8, cost=2.. so coal=6, ind=0, now from ind=0.. coal+1=>7, 7-3=4 ind=1, 
coal=4,ind=1, coal=>4+2=6-4=2, ind=2 2+3=5-5=0 ind=3 reached from where we started..
Sample Output-1:
----------------
3

Explanation:
------------
Start at station 3 (index 3) and fill up with 4 unit of coal. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. The coal is just enough to travel back to station 3.
Therefore, return 3 as the starting index.

Sample Input-1:
---------------
2 3 4
3 4 3

Sample Output-1:
----------------
-1

Explanation:
------------
You can't start at station 0 or 1, as there is not enough coal to travel to the next station.
Let's start at station 2 and fill up with 4 unit of coal. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of coal but you only have 3.
Therefore, you can't travel around all the stations and reach the starting staton once 
no matter where you start
*/

import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String c[]=sc.nextLine().split(" ");
        int coal[]=new int[c.length];
        for(int i=0;i<c.length;i++){
            coal[i]=Integer.parseInt(c[i]);
        }
        int cost[]=new int[c.length];
        for(int i=0;i<c.length;i++){
            cost[i]=sc.nextInt();
        }
        int n=coal.length;
        //actual answer
         int total = 0;
        int cur = 0;
        int start = 0;

        for(int i=0;i<n;i++){
            int diff = coal[i] - cost[i];
            total += diff;
            cur += diff;

            if(cur < 0){
                start = i + 1;
                cur = 0;
            }
        }

        if(total < 0)
            System.out.println(-1);
        else
            System.out.println(start);
    }

    //wrong.. but passing test cases..
        // int cur=0;
        //     int n=c.length;
        // int start =0;
        // int i=0;
        // boolean found=false;
        // for(i=0;i<2*c.length;i++){
        //     if(i>n && i%n==start){
        //         //System.out.println(i+" found");
        //         found=true;
        //         break;
        //     }
        //     cur+=coal[i%n];
        //     if(cost[i%n]>cur){
        //         cur=0;
        //         start=i+1;
        //     }
        //     else{
        //         cur-=cost[i%n];
        //     }
        // }
        // if(!found){
        //     System.out.println(-1);
        // }
        // else{
        //     System.out.println(start);
        // }
        
    }
