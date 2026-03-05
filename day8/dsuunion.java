// There are N computers in a network, all the computers are connected as tree 
// structure. And one new connection is added in the Network. The computers in 
// the network are identified with their IDs, the IDs are numbered between 1 to N.

// The connections in the network is given as coonection[i] = [comp-A, comp-B], 
// there is a connection between comp-A and comp-B.

// Your task is to remove a connection in the network and print it, so that 
// all the computers are connected as tree structure. If there are multiple 
// options to remove, remove the connection that occurs last in the input.


// Input Format:
// -------------
// Line-1: Two space separated integers N, number of computers.
// Next N lines: Two space separated integers, comp-A & comp-B.

// Output Format:
// --------------
// Print the connection which is removed.


// Sample Input-1:
// ---------------
// 6
// 1 2  
// 3 4
// 3 6
// 4 5
// 5 6
// 2 3

// p(2)=1, p(4)=p(3)=3 =>1,p(6)=p(3)=3 => 1 ,p(5)=p(4)=4,p(6)=p(5)=p(4)=4=>1,p(3)=p(2)=1
// 1--2--3--4--5
//       |     |
//       6 ----|
// Sample Output-1:
// ---------------
// 5 6


// Sample Input-2:
// ---------------
// 4
// 1 2
// 2 3
// 3 4
// 2 4


// 1--2--3--4
//    |     |
//    -------
// Sample Output-2:
// ---------------
// 2 4


import java.util.*;
class DSU{
    int parent[];
    DSU(int n){
        parent=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }
    public int find(int x){
        if(parent[x]!=x){
             parent[x]=find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x,int y){
        int p1=find(x);
        int p2=find(y);
        if(p1==p2)return;
        if(p1<p2)parent[p2]=p1;
        else{
            parent[p1]=p2;
        }
    }
    
}
class dsuunion{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[][]=new int[n][2];
        for(int i=0;i<n;i++){
            arr[i][0]=sc.nextInt();
            arr[i][1]=sc.nextInt();
        }
        DSU dsu=new DSU(n);
        int u=0;
        int v=0;
        for(int i=0;i<n;i++){
            if(dsu.parent[arr[i][0]-1]==dsu.parent[arr[i][1]-1]){
                u=arr[i][0];
                v=arr[i][1];
            }
            else{
                dsu.union(arr[i][0]-1,arr[i][1]-1);
            }
        }
        System.out.println(u+" "+v);
    }
}