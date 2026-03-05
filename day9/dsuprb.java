// You are a database integrity engineer working for a global cloud company. 
// Your team maintains a distributed database network, where each server either:
//     - Stores equivalent data to another server (serverX == serverY).
//     - Stores different data from another server (serverX != serverY).

// The transitive consistency rule must be followed:
//     - If A == B and B == C, then A == C must be true.
//     - If A == B and B != C, then A != C must be true.

// Your task is to analyze the given constraints and determine whether they 
// follow transitive consistency. If all relations are consistent, return true; 
// otherwise, return false

// Input Format:
// -------------
// Space separated strnigs, list of relations

// Output Format:
// --------------
// Print a boolean value, whether transitive law is obeyed or not.


// Sample Input-1:
// ---------------
// a==b c==d c!=e e==f

// a,b  c,d  e,f
// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// a==b b!=c c==a

// a,b,c
// first add all == union
// Sample Output-2:
// ----------------
// false

// Explanation:
// ------------
// {a, b} form one equivalence group.
// {c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
// However, b != c contradicts b == a and c == a.

// Sample Input-3:
// ---------------
// a==b b==c c!=d d!=e f==g g!=d

// a,b,c  f,g
// Sample Output-3:
// ----------------
// true

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
        else{
            parent[p2]=p1;
        }
    }
}
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        DSU dsu=new DSU(26);
        String arr[]=sc.nextLine().split(" ");
        for(String s:arr){
             if(s.charAt(1)!='!'){
             char c1=s.charAt(0);
             char c2=s.charAt(3);
                dsu.union(c1-'a',c2-'a');
             }
        }
        boolean flag =true;
        for(String s:arr){
            if(s.charAt(1)=='!'){
                int v1=s.charAt(0)-'a';
                int v2=s.charAt(3)-'a';
                if(dsu.find(v1)==dsu.find(v2)){
                    flag=false;
                    break;
                }
            }
        }
        //System.out.println(Arrays.toString(dsu.parent));
        if(flag){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }
}