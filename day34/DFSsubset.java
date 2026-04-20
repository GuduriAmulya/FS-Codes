/*
Manoj is working on sets and relations.
He is given a set S consist of N integers from 1 to N, without duplicates.
The set S may contain any shuffled order of 1 to N numbers.
And also P number of subsets also given where each subset of size Q.
Each subset is a subsequence of shuffled set S.

Manoj has to check, using the given subsets can he form the set S uniquely 
or not. i.e., the order of numbers in the subsets remains unchanged and 
can form only one unique super set using the subsets, and 
the unique super set should be S.

Your task is to help Manoj to check whether it is possible to form 
the shuffled set S uniquely from the given subsets.

For example: 
-----------
If given shuffled set is [2,4,6] and subsets are [2,4] [2,6].
You can form the following sets, [2,4,6] or [2,6,4].

So, you should return false, as the given subsets form more than 1 set.

Simply, there should be always only one unique super set can be formed.
and that set should be equal to S.


Input Format:
-------------
Line-1: An integer N, size of the shuffled array.
Line-2: N space separated integers, shuffled set S.
Line-3: Two space separated integers P and Q, number of subsets, size of subsets
Next P lines: Q space separated integers, non repeated integers from [1-N]

Output Format:
--------------
Print a boolean value, can you form the shuffled set S uniquely or not.


Sample Input-1:
---------------
4
1 3 2 4
3 2
1 2
3 2
3 4

indegree==
1-->0
3-->0
2-->3,4-->1
since both 1 and 3 have indegree 0 no uniqueness is possible.. return false..
Sample Output-1:
----------------
false

Explanation: 
------------
The subsets are [1,2], [3,2] and [3,4] cannot
form the given shuffled set [1,3,2,4].
It can form another set as [1,3,4,2] also.


Sample Input-2:
---------------
4
1 3 2 4
4 2
1 2
3 2
1 3
2 4

1->3->2->4 true..
Sample Output-2:
----------------
true

Explanation: 
------------
The subsets are [1,2], [3,2], [1,3] and [2,4] can uniquely 
form the given shuffled set [1,3,2,4].


Sample Input-3:
---------------
5
1 3 5 4 2
3 3
3 4 2
5 4 2
1 3 5

1->3->5->4->2 yes..
Sample Output-3:
----------------
true

Explanation: 
------------
The subsets are [3,4,2], [5,4,2], and [1,3,5] can uniquely 
form the given shuffled set [1,3,5,4,2].


*/

//Use toposort.. indegree=0, push into the q, then check if the size==1 if not no unique set can be formed..
// 

import java.util.*;
class Solution{
    public static List<List<Integer>>l;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int p=sc.nextInt();
        int q=sc.nextInt();
        l=new ArrayList<>();
        for(int i=0;i<=n;i++){
            l.add(new ArrayList<>());
        }
        int indegree[]=new int[n+1];
        int query[][]=new int[p][q];
        for(int i=0;i<p;i++){
            for(int j=0;j<q;j++){
                query[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<p;i++){
            int f=query[i][0];
            for(int j=1;j<q;j++){
                int cur=query[i][j];
                l.get(f).add(cur);
                indegree[cur]++;
                f=cur;
            }
        }
        Queue<Integer>queue=new LinkedList<>();
        for(int i=1;i<=n;i++){
            if(indegree[i]==0){
                queue.offer(i);
            }
        }
        int ind=0;
        while(!queue.isEmpty()){
            if(queue.size()>1){//if queue has more than 1 ele, no uniqueness so return false
                System.out.println("false");
                return;
            }
            int cur=queue.poll();
            if(arr[ind++]!=cur){
                System.out.println("false");
                return;
            }
            for(int neigh:l.get(cur)){
                indegree[neigh]--;
                if(indegree[neigh]==0)queue.offer(neigh);
            }
        }
        System.out.println(ind==n);
        //System.out.println(l);
        //start dfs from arr[0];
        //System.out.println(dfs(arr[0],1,arr));
    }
    //dfs wrong.. how will it ensure uniquenes. do topo sort..
    // public static boolean dfs(int start, int nextInd,int arr[]){
    //     if(nextInd==arr.length)return true;
    //     for(int neigh:l.get(start)){
    //         if(arr[nextInd]==neigh){
    //             //System.out.println("yesL "+arr[nextInd]);
    //             if(dfs(arr[nextInd],nextInd+1,arr))return true;
    //         }
    //     }
    //     return false;
    // }
}