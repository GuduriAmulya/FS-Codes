// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3

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
class PrePost{
    public static int preInd;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String pr[]=sc.nextLine().split(" ");
        int pre[]=new int[pr.length];
        for(int i=0;i<pr.length;i++){
            pre[i]=Integer.parseInt(pr[i]);
        }
        int[]post=new int[pre.length];
        for(int i=0;i<post.length;i++){
            post[i]=sc.nextInt();
        }
        preInd=0;
        Node root=buildTree(pre,post,0,post.length-1);
        List<List<Integer>>ans=levelOrder(root);
        System.out.println(ans);
    }
    public static Node buildTree(int[]pre,int[]post,int start,int end){
        if(start>end)return null;
        Node root=new Node(pre[preInd++]);
        if(start==end)return root;
        int i;
        for(i=start;i<=end;i++){
            if(post[i]==pre[preInd])break;
        }
        if(i<=end){
            root.left=buildTree(pre,post,start,i);
            root.right=buildTree(pre,post,i+1,end-1);
        }
        return root;
    }
    public static List<List<Integer>>levelOrder(Node root){
        List<List<Integer>>ans=new ArrayList<>();
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer>level=new ArrayList<>();
            while(size-->0){
                Node cur=q.poll();
                level.add(cur.val);
                if(cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
            ans.add(new ArrayList<>(level));
            
        }
        return ans;
    }
    // public static void inorder(Node root){
    //     if(root==null)return;
    //     inorder(root.left);
    //     System.out.print(root.val+" ");
    //     inorder(root.right);
    // }
}