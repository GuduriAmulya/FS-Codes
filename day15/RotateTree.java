// VishnuVardan is working with Decision Trees for AI-based predictions.
// To analyze alternative outcomes, Kishore has planned to flip the decision 
// tree horizontally to simulate a reverse processing approach.

// Rules for Flipping the Decision Tree:
// 	- The original root node becomes the new rightmost node.
// 	- The original left child becomes the new root node.
// 	- The original right child becomes the new left child.
// This transformation is applied level by level recursively.

// Note:
// ------
// - Each node in the given tree has either 0 or 2 children.
// - Every right node in the tree has a left sibling sharing the same parent.
// - Every right node has no further children (i.e., they are leaf nodes).

// Your task is to help VishnuVardan flip the Decision Tree while following 
// the given transformation rules.

// Input Format:
// -------------
// Space separated integers, nodes of the tree.

// Output Format:
// --------------
// Print the list of nodes of flipped tree as described below.


// Sample Input-1:
// ---------------
// 4 2 3 5 1

// Sample Output-1:
// ----------------
// 5 1 2 3 4


// Sample Input-2:
// ---------------
// 4 2 5

// Sample Output-2:
// ----------------
// 2 5 4

//  Wrong not getting
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
class RotateTree{
    public static Node buildTree(int[]arr){
        int i=0;
        Node root=new Node(arr[0]);
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
        i++;
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(i<arr.length){
                cur.left=new Node(arr[i]);
                q.offer(cur.left);
                i++;
            }
            if(i<arr.length){
                cur.right=new Node(arr[i]);
                q.offer(cur.right);
                i++;
            }
        }
        return root;
    }
    public static void inorder(Node root){
        if(root==null)return;
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
    public static Node rotate(Node root){
        if(root==null || root.left==null){
            return root;
        }
        Node newR=rotate(root.left);
        root.left.left=root.right;
        root.left.right=root;
        root.left=null;
        root.right=null;
        return newR;
    }
    public static void levelOrder(Node root){
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            Node cur=q.poll();
            System.out.print(cur.val+" ");
            if(cur.left!=null){
                q.offer(cur.left);
            }
            if(cur.right!=null){
                q.offer(cur.right);
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int arr[]=new int[a.length];
        for(int i=0;i<arr.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        Node root=buildTree(arr);
        Node newR=rotate(root);
        //inorder(root);
        levelOrder(newR);
    }
}