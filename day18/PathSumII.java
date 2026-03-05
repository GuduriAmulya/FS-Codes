// Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where
//  the sum of the node values in the path equals targetSum. Each path should be returned 
//  as a list of the node values, not node references.

// A root-to-leaf path is a path starting from the root and ending at any leaf node. 
// A leaf is a node with no children.

// Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
// Output: [[5,4,11,2],[5,8,4,5]]
// Explanation: There are two paths whose sum equals targetSum:
// 5 + 4 + 11 + 2 = 22
// 5 + 8 + 4 + 5 = 22

// Let's say target = 22, and we have this tree:

//          5
//        /   \
//       4     8
//      /     / \
//     11    13  4
//    /  \      / \
//   7    2    5   1
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
class PathSumII{
    public static Node buildTree(int [] arr){
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
    public static void dfs(Node root, int target, int cursum, List<Integer>path, List<List<Integer>>res){

        if(root==null)return;
        cursum+=root.val;
        path.add(root.val);
        if(cursum>target){
            return;
        }
        if(root.left==null && root.right==null && target==cursum){
            res.add(new ArrayList<>(path));
            return;
        }
        dfs(root.left, target,cursum,path,res);
        dfs(root.right,target,cursum,path,res);
        path.remove(path.size()-1);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int target=sc.nextInt();
        int arr[]=new int[a.length];
        for(int i=0;i<a.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        Node root=buildTree(arr);
        List<List<Integer>>res=new ArrayList<>();
        dfs(root, target,0,new ArrayList<>(),res);
        System.out.println(res);
    }
}