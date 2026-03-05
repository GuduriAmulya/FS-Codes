// Write a program to construct a binary tree from level-order input, while treating -1 
// as a placeholder for missing nodes. The program reads input, constructs the tree, 
// and provides an in-order traversal to verify correctness.

// Input Format:
// ---------------
// Space separated integers, level order data (where -1 indiactes null node).

// Output Format:
// -----------------
// Print the in-order data of the tree.

// NODE REFERENCE
// --------------
// class Node {
//     int value;
//     Node left, right;

//     public Node(int value) {
//         this.value = value;
//         this.left = null;
//         this.right = null;
//     }
// }


// Sample Input:
// ----------------
// 1 2 3 -1 -1 4 5

// Sample Output:
// ----------------
// 2 1 4 3 5

// Explanation:
// --------------
//     1
//    / \
//   2   3
//      / \
//     4   5


// Sample Input:
// ----------------
// 1 2 3 4 5 6 7

// Sample Output:
// ----------------
// 4 2 5 1 6 3 7

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4  5 6  7


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

class treewithminus{
    public static Node buildTree(int[] arr){
        Node root=new Node(arr[0]);
        if(arr[0]==-1)return null;
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
        int ind=1;
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(cur.left==null && ind<arr.length){
                int val=arr[ind];
                if(val!=-1){
                    cur.left=new Node(val);
                    q.offer(cur.left);
                }
            }
            ind++;
            if(cur.right==null && ind<arr.length){
                int val=arr[ind];
                if(val!=-1){
                    cur.right=new Node(val);
                    q.offer(cur.right);
                }
            }
            ind++;
        }
        return root;
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
        int a[]=new int [arr.length];
        for(int i=0;i<arr.length;i++){
            a[i]=Integer.parseInt(arr[i]);
        }
        Node root=buildTree(a);
        inorder(root);
    }
}