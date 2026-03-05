// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6
    
// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100


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
class TreeRemoveEdgeMaxProd{
    public static int maxProd=1;
    public static Node buildTree(int[] arr){
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
    public static int sumTree(Node root){
        if(root==null)return 0;
        int cursum=root.val;
        int leftSum=sumTree(root.left);
        int rightSum=sumTree(root.right);
        return cursum+leftSum+rightSum;
    }
    public static void solve(Node root,int totalSum){
        //cut at evry right node..
        if(root==null)return ;
        int rightSum=sumTree(root.right);
        int leftSum=totalSum-rightSum;
        maxProd=Math.max(maxProd,rightSum*leftSum);
        //or cut at left 
        int l=sumTree(root.left);
        int r=totalSum-l;
        maxProd=Math.max(maxProd,l*r);
        //System.out.print(maxProd+" ");
        
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s[]=sc.nextLine().split(" ");
        int arr[]=new int[s.length];
        for(int i=0;i<s.length;i++){
            arr[i]=Integer.parseInt(s[i]);
        }
        Node root=buildTree(arr);
        int totalSum=sumTree(root);
        //System.out.println(totalSum);
        solve(root,totalSum);
        System.out.println(maxProd);
    }
}