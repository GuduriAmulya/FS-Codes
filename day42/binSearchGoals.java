/*
Indian Super League organizing a football match at Eden Gardens.
The teams are Bangalore and Chennai are playing opposite each other.

After Match starts, each team making some goals at certain time, 
for example, goal 'goal[i]' was made by a player at time 'time[i]'.
goal[i]: indicates the team number 0 (Bangalore) or 1 (Chennai).
time[i]: indicates the time of the goal made.

Now, your task is to find out the leading team at given time t[j].

Input Format:
------------------
Line-1: Two integers N and K, no of goals-> goal[i] and no of times-> t[j]
Line-2: N space seperated integers only 0's and 1', goals[i]
Line-3: N space seperated integers, time[i]
Line-4: K space seperated integers , t[i]
 
Output Format:
------------------
Print K space seperated integers as result.


Sample Input-1:
---------------
7 5
0 1 1 0 0 1 0
0 5 10 15 20 25 30
3 12 25 15 24


Sample Output-1:
----------------
0 1 1 0 0

Explanation:
------------
hme 3, the goals are [0], and 0 is leading.
At time 12, the goals are [0,1,1], and 1 is leading.
At time 25, the goals are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent goal)
At time 15, the goals are [0,1,1,0], and 0 is leading (as ties go to the most recent goal)
At time 24, the goals are [0,1,1,0,0], and 0 is leading.

*/

import java.util.*;
class Solution{
    public static int binSearch(int val, int[]time){
        int l=0;
        int r=time.length-1;
        while(l<=r){
            int mid=(l+(r-l)/2);
            if(time[mid]>val){
                r=mid-1;
            }
            else{
                l=mid+1;
            }
        }
        return r;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int goal[]=new int[n];
        int[]lead=new int[n];
        int l1=0;
        int l2=0;
        int leader=-1;
        for(int i=0;i<n;i++){
            goal[i]=sc.nextInt();
            if(goal[i]==0)l1++;
            else if(goal[i]==1)l2++;
            if(l1>l2)leader=0;
            else if(l1==l2){
                leader=goal[i];
            }
            else{
                leader=1;
            }
            lead[i]=leader;
        }
       // System.out.println(Arrays.toString(lead));
        int[]time=new int[n];
        for(int i=0;i<n;i++){
            time[i]=sc.nextInt();
        }
        int query[]=new int[k];
        for(int i=0;i<k;i++){
            query[i]=sc.nextInt();
        }
        for(int q:query){
            int ans=binSearch(q,time);
            System.out.print(lead[ans]+" ");
        }
        // for(int q:query){
        //     int c=0;
        //     int b=0;
        //     int lead=0;
        //     for(int i=0;i<n;i++){
        //         if(time[i]<=q){
        //             if(goal[i]==0)c++;
        //             else{
        //                 b++;
        //             }
        //             lead=goal[i];
        //         }
                
        //     }
        //     if(c==b){
        //         System.out.print(lead);
        //     }
        //     else{
        //         if(c>b){
        //             System.out.print(0);
        //         }
        //         else{
        //             System.out.print(1);
        //         }
        //     }
        //     System.out.print(" ");
        // }
    }
}