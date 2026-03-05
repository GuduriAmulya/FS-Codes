// Bubloo is working with computer networks, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) 
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication 
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1' (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2), 
// determine the minimum number of network hops (edges) required to 
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.


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
class AncestorDistance{
    public static Node buildTree(int arr[]){
        int ind=0;
        if(arr[0]==-1)return null;
        Node root=new Node(arr[ind++]);
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(ind<arr.length){
                cur.left=new Node(arr[ind]);
                q.offer(cur.left);
            }
            ind++;
            if(ind<arr.length){
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
    public static Node lowestCommonAncestor(Node root, int v1, int v2){
        if(root==null)return null;
        if( (root.val==v1 || root.val==v2))return root;
        Node leftCheck=lowestCommonAncestor(root.left,v1,v2);
        Node rightCheck=lowestCommonAncestor(root.right,v1,v2);
        if(leftCheck !=null && rightCheck !=null){
            return root;
        }
        if(leftCheck==null && rightCheck!=null)return rightCheck;
        return leftCheck;
    }
    public static int minDist(Node root, int v1){
        if(root==null)return -1;
        if(root.val==v1){
            return 0;
        }
        int hl=minDist(root.left,v1);
        int hr=minDist(root.right,v1);
        if(hl==-1 && hr==-1)return -1;
        if(hl==-1)return 1+hr;
        return 1+hl;
        
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int arr[]=new int[a.length];
        for(int i=0;i<a.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        int v1=sc.nextInt();
        int v2=sc.nextInt();
        Node root=buildTree(arr);
        //inorder(root);
        Node lca=lowestCommonAncestor(root,v1,v2);
       // System.out.println(lca.val);
        int h=minDist(lca,v1)+minDist(lca,v2);
        System.out.println(h);
        
    }
}