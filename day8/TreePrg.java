// You are developing an application for a garden management system where each tree 
// in the garden is represented as a binary tree structure. The system needs to 
// allow users to plant new trees in a systematic way, ensuring that each tree is 
// filled level by level.

// A gardener wants to:
//  - Plant trees based on user input.
//  - Ensure trees grow in a balanced way by filling nodes level by level.
//  - Inspect the garden layout by performing an in-order traversal, which helps 
//    analyze the natural arrangement of trees.

// Your task is to implement a program that:
//     - Accepts a list of N tree species (as integers).
//     - Builds a binary tree using level-order insertion.
//     - Displays the in-order traversal of the tree.

// Input Format:
// -------------
// - An integer N representing the number of tree plants.
// - A space-separated list of N integers representing tree species.

// Output Format:
// --------------
// A list of integers, in-order traversal of tree.


// Sample Input:
// -------------
// 7
// 1 2 3 4 5 6 7

// Sample Output:
// --------------
// 4 2 5 1 6 3 7


// Explanation:
// ------------
// The tree looks like this:

//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// The in order is : 4 2 5 1 6 3 7



// NODE Reference:

// class Node {
//     int data;
//     Node left, right;
    
//     public Node(int data) {
//         this.data = data;
//         left = right = null;
//     }
// }
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
class Tree{
    Node root;
    // void insert(int arr[]){
    //     root=new Node(arr[0]);
    //     Queue<Node>q=new LinkedList<>();
    //     q.offer(root);
    //     int ind=1;
    //     while(!q.isEmpty()){
    //         Node cur=q.poll();
    //         if(cur.left==null){
    //             cur.left=new Node(arr[ind++]);
    //             q.offer(cur.left);
    //         }
    //         if(cur.right!=null){
    //             cur.right=new Node(arr[ind++]);
    //             q.offer(cur.right);
    //         }
    //     }
    // }
    void insert(Node root,int val){
        Node temp=root;
        if(temp==null){
            root=new Node(val);
            return;
        }
        Queue<Node>q=new LinkedList<Node>();
        q.offer(temp);
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(cur.left==null){
                cur.left=new Node(val);
                return;
            }
            q.offer(cur.left);
            if(cur.right==null){
                cur.right=new Node(val);
                return;
            }
            q.offer(cur.right);
        }
    }
    void inorder(Node root){
        if(root==null)return;
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
}
class TreePrg{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        Tree t=new Tree();
        Node root=new Node(arr[0]);
        
    // t.insert(arr);
        for(int i=1;i<n;i++){
            t.insert(root,arr[i]);
        }
        t.inorder(root);
        
    }
}