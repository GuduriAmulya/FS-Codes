// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. 

// The output should list the nodes in the order they appear in pre-order.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers, the nodes in in-order traversal.
// A space-separated list of N integers, the nodes in post-order traversal.

// Output Format:
// --------------
// Print the list of the nodes in pre-order.


// Sample Input:
// -------------
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1

// Sample Output:
// --------------
// [1, 2, 4, 5, 3, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

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

class InPostPre{
    static int postIndex;
    public static Node buildTree(int[]in,int[]post,int start, int end,Map<Integer,Integer>inOrderMp){
        if(start>end)return null;
        int rootval=post[postIndex--];//get root val from postorder..
        Node root=new Node(rootval);
        int inorderIndex=inOrderMp.get(rootval);
        root.right=buildTree(in,post,inorderIndex+1,end,inOrderMp);
        root.left=buildTree(in, post, start, inorderIndex-1, inOrderMp);
        return root;
    }
    public static void preorder(Node root){
        if(root==null)return;
        System.out.print(root.val+" ");
        preorder(root.left);
        preorder(root.right);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int in[]=new int[n];
        for(int i=0;i<n;i++)in[i]=sc.nextInt();
        int post[]=new int[n];
        for(int i=0;i<n;i++) post[i]=sc.nextInt();
        Map<Integer,Integer>inOrderMp=new HashMap<>();
        for(int i=0;i<n;i++){
            inOrderMp.put(in[i],i);
        }
        postIndex=n-1;//as the root is the last val in post order traversal..
        Node root=buildTree(in,post,0,n-1,inOrderMp);
        preorder(root);


    }
}