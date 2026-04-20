/*
BCCI wants to select the group of bowlers for an upcoming test-series, 
you want to choose the group with highest number of wickets, which is 
the sum of wickets taken by all the bowlers in that group.

However, the bowler group is not allowed to have any disputes. A dispute
exists if a younger bowler has strictly highest wickets than an older bowler. 
A dispute does not occur between bowlers of the same age.

You are given information of N bowlers as two lists, wickets and ages, 
where each wickets[i] and ages[i] represents the wickets and age of 
the i-th bowler, respectively, return the highest number of wickets 
of all possible bowler groups.


Input Format:
-------------
Line-1: An integer N, number of bowlers.
Line-2: N space separated integers, wickets[].
Line-3: N space separated integers, ages[].

Output Format:
--------------
An integer, highest number of wickets of all possible bowler groups.


Sample Input-1:
---------------
4
5 3 5 6
3 5 4 2

//not always best to choose the highest wicket!!...
//so recursion... iterate over and for each iteration consider one index.. take it compulsarily..

Sample Output-1:
----------------
10

Explanation:
------------
It is best to choose 2 bowlers of age 3 and 4. 


Sample Input-2:
---------------
5
5 3 5 6 3
2 5 4 2 1
pq=> desc order of wickets..
{6,2},{5,2},{5,4},{3,5},{3,1}
max=6
6+6+ (5 not poss) as age is 4 great but wisktes less than max.. same with {3,5}, 
//choose bowlers such that.. the wickets> 
Sample Output-2:
----------------
14

Explanation:
------------
It is best to choose 3 bowlers of age 1 and 2. 
Notice that you are allowed to choose multiple bowlers of the same age.

Sample Input-3:
---------------
5
1 3 5 10 15
1 2 3 4 5

Sample Output-3:
----------------
34

Explanation:
------------
You can choose all the bowlers.

*/

//optimal using dp..
// int[] dp = new int[n];
//         int ans = 0;

//         for (int i = 0; i < n; i++) {
//             dp[i] = players[i][1]; // take itself

//             for (int j = 0; j < i; j++) {
//                 if (players[j][1] <= players[i][1]) {
//                     dp[i] = Math.max(dp[i], dp[j] + players[i][1]);
//                 }
//             }

//             ans = Math.max(ans, dp[i]);
//         }

//         System.out.println(ans);
import java.util.*;
class Solution{
    public static int solve(int[][]players,int ind, int prevWicket){
        if(ind==players.length)return 0;
        int notTake=solve(players,ind+1,prevWicket);
        int take=0;
        if(players[ind][1]>=prevWicket){
            take=players[ind][1]+solve(players,ind+1,players[ind][1]);
        }
        return Math.max(take,notTake);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int wick[]=new int[n];
        for(int i=0;i<n;i++)wick[i]=sc.nextInt();
        int age[]=new int[n];
        for(int i=0;i<n;i++)age[i]=sc.nextInt();
        int players[][]=new int[n][2];
        for(int i=0;i<n;i++){
            players[i][0]=age[i];
            players[i][1]=wick[i];
        }        
        Arrays.sort(players,(a,b)->{
            if(a[0]==b[0])return a[1]-b[1];//if age same then asend of wickets
            return a[0]-b[0];//asec of age..
        });
        //System.out.println(Arrays.deepToString(players));
        System.out.println(solve(players, 0, 0));
        // System.out.println(ans);

        //not
        // PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->b[0]-a[0]);//desc of wickets..
        // for(int i=0;i<n;i++){
        //     pq.offer(new int[]{wick[i],age[i]});
        // }
        // int sum=0;
        // int max=0;
        // int prevAge=0;
        // while(!pq.isEmpty()){
        //     int[]cur=pq.poll();
        //     int w=cur[0];
        //     int a=cur[1];
        //     if(w>=max && a>=prevAge){
        //         sum+=w;
        //         prevAge=a;
        //         max=w;
        //     }
        //     else if(w<=max && a<=prevAge){
        //         sum+=w;
        //         prevAge=a;
        //     }
        // }
        // System.out.println(sum);
        
        
    }
}