// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Refernce Node:
// --------------
// class TreeNode {
//     Integer val;
//     TreeNode left, right;
    
//     TreeNode(Integer val) {
//         this.val = val;
//         this.left = this.right = null;
//     }
// }


// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

//     1
//    2  3
//  5      5
// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]

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
class RightSideView{
    public static Node buildTree(int arr[]){
        if(arr[0]==-1)return null;
        int ind=0;
        Node root=new Node(arr[ind++]);
        Queue<Node>pq=new LinkedList<>();
        pq.offer(root);
        while(!pq.isEmpty() && ind<arr.length){
            Node cur=pq.poll();
            if(arr[ind]!=-1){
                cur.left=new Node(arr[ind]);
                pq.offer(cur.left);
            }
            ind++;
            if(ind<arr.length){
            if(arr[ind]!=-1){
                cur.right=new Node(arr[ind]);
                pq.offer(cur.right);
            }
            ind++;
            }
        }
        return root;
    }
    public static void rightView(Node root){
        if(root==null)return;
        Queue<Node>q=new LinkedList<>();
        List<Integer>ans=new ArrayList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=size;i>0;i--){
                Node cur=q.poll();
                if(i==1){
                    ans.add(cur.val);
                }
                if(cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
        }
        System.out.println(ans);
    }
    public static void inorder(Node root){
        if(root==null)return;
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String arr[]=sc.nextLine().split(" ");
        int a[]=new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            a[i]=Integer.parseInt(arr[i]);
        }
        Node root=buildTree(a);
        //inorder(root);
        rightView(root);
    }
}