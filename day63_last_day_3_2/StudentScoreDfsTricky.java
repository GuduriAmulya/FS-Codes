/*
In a class of N students, each student has secured different marks in 
final exams, and different levels of decency, 
The students are numbered from 0,1,2,.. N-1.

The class teacher defines, compMarks[i] = [p,q], indiactes 
Student-p secured more marks than Student-q. This information is 
provided based on valid observations only. And, decentScore[p] = d, 
indicates Student-p has decency level- d.

Your task is to find and return the result[] as follows:
	- result[p] = q, where Student-q has the least level of decency
	  [i.e., the student-q with the smallest value of decentScore[q] ]
	  among all the students who scores >= Student-p.


Input Format:
-------------
Line-1: An integer N, number of students.
Next 'N-1' lines: Two space separated integers p and q, compMarks[i] = [p, q] 
Last Line: N space separated integers, decentScore[].

Output Format:
--------------
Print N space separated integers as result


Sample Input-1:
---------------
6
1 3
2 4
3 5
0 3
3 4
4 2 6 8 3 1

Sample Output-1:
----------------
0 1 2 1 1 5

Explanation:
------------
result[3] = 1.
Student-1 has secured more marks than Student-3,
Student-0 has secured more marks than Student-3.
Among [1, 0], the only Student who has least decentScore is Student-1.

result[4] = 1.
Student-1 has secured more marks than Student-3, who secured more marks than 3.
Student-2 has secured more marks than Student-4.
Among [1,2,3], the only Student who has least decentScore is Student-1.


*/

import java.util.*;
class Solution{
    public static List<List<Integer>>adj;
    public static int[]dec;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[][]=new int[n][2];
        adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<n-1;i++){
            arr[i][0]=sc.nextInt();
            arr[i][1]=sc.nextInt();
            adj.get(arr[i][1]).add(arr[i][0]);
        }
        
        dec=new int[n];
        for(int i=0;i<n;i++){
            dec[i]=sc.nextInt();
        }
        int ans[]=new int[n];
        Arrays.fill(ans,-1);
        for(int i=0;i<n;i++){
            dfs(i,ans);
        }
        System.out.println(Arrays.toString(ans));
    }
    public static int dfs(int i, int[]ans){
        if(ans[i]!=-1)return ans[i];
        int best=i;
        for(int neigh:adj.get(i)){
            int a=dfs(neigh,ans);
            if(dec[a]<dec[best]){
                best=a;
            }
        }
        return ans[i]=best;
    }
}

//dec=[4 2 6 8 3 1]
// 3--->[0,1] min(8,4,2)=>2 ie student =>1
//4-->[2,3] 3 inturn connected to 0 and 1, =>min(3,6,8,4,2)=>2 ie student 1
//5 -->3 , 3 connected to 0 , 1 =>min(1,8,4,2)=>1 ie stude=5