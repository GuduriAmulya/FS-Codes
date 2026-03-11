// Coach Arjun is training a cricket team and is experimenting with a new fitness 
// evaluation drill. He provided the fitness scores of N players in the team. As 
// part of the drill, he asked the team manager to keep track and perform these 
// two specific operations on the players' fitness scores:

// 1. bestFitness(start, end) - Report the maximum fitness score among the players 
//    whose jersey numbers lie between the positions start and end inclusive.
// 2. improveFitness(index, newScore) - Update the fitness score of the player at 
//    the specific index position with a new fitness score newScore.

// Your task is to efficiently handle these requests by using a Segment Tree data structure.

// Input Format:  
// -------------
// Line-1: Two integers N and Q, representing the number of players and the total 
//         number of queries respectively.
// Line-2: N space-separated integers representing the initial fitness scores of 
//         the players.
// The next Q lines: Each line contains three integers: 
//     - First integer (option) specifies the type of query:
//       - If option = 1, the next two integers (start, end) represent the range 
//         of jersey numbers (inclusive) for which to report the maximum fitness.
//       - If option = 2, the next two integers (index, newScore) represent the 
//         player's index to update and their new fitness score.

// Output Format:  
// --------------
// - Output an integer value for every bestFitness query, representing the maximum 
//   fitness score within the specified range.

// Sample Input:  
// -------------

// 8 5
// 1 2 13 4 25 16 17 28
// 1 2 6        //bestFitness
// 1 0 7        //bestFitness
// 2 2 18       //improveFitness
// 2 4 36       //improveFitness
// 1 2 7        //bestFitness


// Sample Output:  
// --------------
// 25
// 28
// 36

import java.util.Scanner;

public class SegTreeQuery{
    static int[]tree;
    static int[]arr;
    static void build(int node,int start, int end){
        if(start==end){
            tree[node]=arr[start];
            return;
        }
        int mid=(start+end)/2;
        build(2*node+1,start,mid);
        build(2*node+2,mid+1,end);
        tree[node]=Math.max(tree[2*node+1],tree[2*node+2]);
    }
    static int query(int node,int start, int end, int l, int r){
        if(l>end || r<start){
            return Integer.MIN_VALUE;
        }
        if(l<=start && r>=end){
            return tree[node];
        }
        int mid=(start+end)/2;
        int left=query(2*node+1,start,mid,l,r);
        int right=query(2*node+2,mid+1,end,l,r);
        return Math.max(left,right);
    }
    //update index..
    static void update(int node, int start,int end, int index, int value){
        if(start==end){
            arr[index]=value;
            tree[node]=value;
            return;
        }
        int mid=(start+end)/2;
        if(index<=mid){
            update(2*node+1,start,mid,index,value);
        }
        else{
            update(2*node+2,mid+1,end,index,value);
        }
        tree[node]=Math.max(tree[2*node+1],tree[2*node+2]);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int Q=sc.nextInt();
        arr=new int[N];
        tree=new int[4*N];
        for(int i=0;i<N;i++){
            arr[i]=sc.nextInt();
        }
        build(0,0,N-1);//zero based index..
        while(Q-->0){
            int type=sc.nextInt();
            if(type==1){
                int l=sc.nextInt();
                int r=sc.nextInt();
                System.out.println(query(0,0,N-1,l,r));
            }
            else{
                int ind=sc.nextInt();
                int val=sc.nextInt();
                update(1,0,N-1,ind,val);
            }
        }
    }
}