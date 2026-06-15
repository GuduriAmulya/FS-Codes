/*
In a large tech company, servers are arranged in a hierarchical 
structure forming a Binary Tree.

Each server is uniquely identified by an integer ID.

When two servers need to communicate, their data is routed through 
their closest common ancestor server to minimize latency.


Your task is to determine the Lowest Common Ancestor (LCA) of two 
given servers in the network.


Network Rules:
--------------
- The structure follows a Binary Tree.
- Each node contains a unique integer.
- If a node is missing, it is represented as -1.


Input Format:
-------------
Line-1: Space separated integers representing level-order traversal
Line-2: Two integers P and Q


Output Format:
--------------
Print a single integer representing the LCA of P and Q


Sample Input-1:
---------------
3 5 1 6 2 0 8 -1 -1 -1 7 4
7 4

         3
      5       1
    6  2      0  8
-1 -1 -1 7   4
Sample Output-1:
----------------
3


Explanation:
------------
The lowest common ancestor of nodes 5 and 1 is 3.


Sample Input-2:
---------------
3 5 1 6 2 0 8 -1 -1 7 4
5 4

       3
   5       1
 6    2   0  8
-1-1  7 4

Sample Output-2:
----------------
5


Explanation:
------------
Node 5 is an ancestor of node 4.

*/

import java.util.*;
class Node{
    int val;
    Node left;
    Node right;
    Node(int val){
        this.val=val;
        left=null;
        right=null;
    }
}
class LCA{
    public static Node buildTree(int []arr){
        if(arr[0]==-1)return null;
        Node root=new Node(arr[0]);
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
        int i=1;
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(i<arr.length && arr[i]!=-1){
                cur.left=new Node(arr[i]);
                q.offer(cur.left);
            }
            i++;
            if(i<arr.length && arr[i]!=-1){
                cur.right=new Node(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }
    public static Node lca(Node root, int p, int q){
        if(root==null)return null;
        if(root.val==p || root.val==q)return root;
        Node l=lca(root.left,p,q);
        Node r=lca(root.right,p,q);
        if(l!=null && r!=null){
            return root;
        }
        if(l==null)return r;
        return l;
        
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int p=sc.nextInt();
        int q=sc.nextInt();
        int n=a.length;
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        Node root=buildTree(arr);
        Node k=lca(root,p,q);
        System.out.println(k.val);
        
    }
}