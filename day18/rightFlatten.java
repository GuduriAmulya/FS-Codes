// You are a gardener designing a beautiful floral pathway in a vast botanical garden. The garden is currently overgrown with plants, trees, and bushes
// arranged in a complex branching structure, much like a tree. Your task is to carefully prune and rearrange the plants to form a single-file
// walking path that visitors can follow effortlessly.

// To accomplish this, you must flatten the garden’s layout into a linear sequence while following these rules:
// 1. The garden path should maintain the same PlantNode structure, where the right branch connects to the next plant in the sequence,
// and the left branch is always trimmed (set to null).
// 2. The plants in the final garden path should follow the same arrangement as a pre-order traversal of the original garden layout. 

// Example 1:
// Input:
// 1 2 5 3 4 -1 6

// Output:
// 1 2 3 4 5 6

// Explanation:
// input structure:
//        1
//       / \
//      2   5
//     / \    \
//    3   4    6
   
// output structure:
// 	1
// 	 \
// 	  2
// 	   \
// 		3
// 		 \
// 		  4
// 		   \
// 			5
// 			 \
// 			  6
			  


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
class rightFlatten{
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
    public static Node flatten(Node root){
        if(root==null)return root;
        Node leftT=flatten(root.left);
        Node rightT=flatten(root.right);
        if(leftT!=null){
            Node temp=leftT;
            while(temp.right!=null){
                temp=temp.right;
            }
            temp.right=rightT;
            root.right=leftT;
            root.left=null;
        }
        // root.right=leftT;
        // Node temp=root;
        // while(temp.right!=null){
        //     temp=temp.right;
        // }
        // root.left=null;
        //temp.right=rightT;
        return root;
    }
    public static void levelOrder(Node root){
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            for(int i=0;i<q.size();i++){
                Node cur=q.poll();
                System.out.print(cur.val+" ");
                if(cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
            //System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int arr[]=new int[a.length];
        for(int i=0;i<a.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        Node root=buildTree(arr);
        Node newR=flatten(root);
        levelOrder(newR);
    }
}