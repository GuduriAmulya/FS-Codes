/*
In the dangerous Binary Sea, pirate ships are arranged in a Binary Search Tree 
based on their treasure value.

Two pirates (P and Q) want to meet at the safest common island. 
This island must be the Lowest Common Ancestor (LCA) of their positions.

Using the BST property:
- Smaller treasures lie to the left
- Larger treasures lie to the right

Find the safest meeting island.

Input Format:
-------------
Line-1: Integer N
Line-2: N space separated integers (BST insertion order)
Line-3: Two integers P and Q

Output Format:
--------------
Print the LCA value

Sample Input-1:
---------------
9
50 30 70 20 40 60 80 35 45
20 40
 
 
           50
      30        70
  20  40       60  80
     35 45
Sample Output-1:
----------------
30

Sample Input-2:
---------------
7
15 10 20 8 12 17 25
8 12

Sample Output-2:
----------------
10

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
class Solution{
    public static Node insert(Node root,int val){
        if(root==null)return new Node(val);
        if(val<root.val){
            root.left=insert(root.left,val);
        }
        else{
            root.right=insert(root.right,val);
        }
        return root;
        
    }
    public static Node lcaBST(Node root, int p,int q){
        if(root==null|| root.val==p || root.val==q){
            return root;
        }
        if(root.val>p && root.val<q){
            return root;
        }
        else if(root.val>q){
            //meaning lies in left side..
            return lcaBST(root.left,p,q);
        }
        return lcaBST(root.right,p,q);
        
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int p=sc.nextInt();
        int q=sc.nextInt();
        if(p>q){
            int temp=q;
            q=p;
            p=temp;
        }
        Node root=null;
        for(int i=0;i<n;i++){
            root=insert(root,arr[i]);
        }
        Node k=lcaBST(root,p,q);
        System.out.println(k.val);
        
    }
}