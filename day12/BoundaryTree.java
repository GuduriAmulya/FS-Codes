// The Indian Army has established multiple military camps at strategic locations 
// along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
// a hierarchical structure, with a main base camp acting as the root of the network.

// To fortify national security, the Government of India has planned to deploy 
// a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
// boundary of the network.

// Structure of Military Camps:
//     - Each military camp is uniquely identified by an integer ID.
//     - A camp can have at most two direct connections:
//         - Left connection → Represents a subordinate camp on the left.
//         - Right connection → Represents a subordinate camp on the right.
//     - If a military camp does not exist at a specific position, it is 
//       represented by -1
	
// Mission: Deploying the S.H.I.E.L.D.
// Your task is to determine the list of military camp IDs forming the outer 
// boundary of the military network. This boundary must be traversed in 
// anti-clockwise order, starting from the main base camp (root).

// The boundary consists of:
// 1. The main base camp (root).
// 2. The left boundary:
//     - Starts from the root’s left child and follows the leftmost path downwards.
//     - If a camp has a left connection, follow it.
//     - If no left connection exists but a right connection does, follow the right connection.
//     - The leftmost leaf camp is NOT included in this boundary.
// 3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
// 4. The right boundary (in reverse order):
//     - Starts from the root’s right child and follows the rightmost path downwards.
//     - If a camp has a right connection, follow it.
//     - If no right connection exists but a left connection does, follow the left connection.
//     - The rightmost leaf camp is NOT included in this boundary.

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
// space separated integers, military-camp IDs.

// Output Format:
// --------------
// Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.


// Sample Input-1:
// ---------------
// 5 2 4 7 9 8 1

//         5
//       2    4
//     7  9  8  1
// Sample Output-1:
// ----------------
// [5, 2, 7, 9, 8, 1, 4]


// Sample Input-2:
// ---------------
// 11 2 13 4 25 6 -1 -1 -1 7 18 9 10

// Sample Output-2:
// ----------------
// [11, 2, 4, 7, 18, 9, 10, 6, 13]


// Sample Input-3:
// ---------------
// 1 2 3 -1 -1 -1 5 -1 6 7

// Sample Output-3:
// ----------------
// [1, 2, 7, 6, 5, 3]


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
    public static Node buildTree(int[] arr){
        Node root=new Node(arr[0]);
        Queue<Node>q=new LinkedList<>();
        int ind=1;
        q.offer(root);
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(ind<arr.length && arr[ind]!=-1){
                cur.left=new Node(arr[ind]);
                q.offer(cur.left);
            }
            ind++;
            if(ind<arr.length && arr[ind]!=-1){
                cur.right=new Node(arr[ind]);
                q.offer(cur.right);
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
    public static void leftTraversal(Node root,List<Integer>leftans){
        if(root==null || (root.left==null && root.right==null))return;
        leftans.add(root.val);
        System.out.print(root.val+" ");
        if(root.left!=null){
            leftTraversal(root.left,leftans);
        }
        else{
            leftTraversal(root.right,leftans);
        }
        return;
    }
    public static void leafTraversal(Node root, List<Integer>leaf){
        if(root==null)return;
        if(root.left==null && root.right==null){
            leaf.add(root.val);
            System.out.print(root.val+" ");
            return;
        }
        leafTraversal(root.left,leaf);
        leafTraversal(root.right,leaf);
        
    }
    public static void rightTraversal(Node root){
        if(root==null ||(root.left==null && root.right==null))return;
        if(root.right!=null){
            rightTraversal(root.right);
        }
        else{
            rightTraversal(root.left);
        }
        System.out.print(root.val+" ");
    }
    public static void boundaryTraversal(Node root){
        if(root==null)return;
        List<Integer>ans=new ArrayList<>();
        //ans.add(root.val);//root.
        System.out.print(root.val+" ");
        //left
        List<Integer>leftans=new ArrayList<>();
        leftTraversal(root.left,leftans);
        //System.out.println(leftans);
        //leaff
        List<Integer>leaf=new ArrayList<>();
        leafTraversal(root,leaf);
        // System.out.println(leaf);
        rightTraversal(root.right);
        
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int arr[]=new int[a.length];
        for(int i=0;i<a.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        Node root=buildTree(arr);
        //inorder(root);
        boundaryTraversal(root);
    }
}