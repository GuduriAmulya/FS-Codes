// Imagine you're the chief curator at a renowned museum that houses a rare collection 
// of ancient artifacts. These artifacts are arranged in a special display that 
// follows a strict rule: any artifact positioned to the left of another has a lower 
// value, and any artifact on the right has a higher value. 

// Your task is to transform this display into what is known as a "Greater Artifact 
// Display." In this new arrangement, each artifact’s new value will be its original 
// value plus the sum of the values of all artifacts that are more valuable than it.

// Example 1:
// ----------
// input=
// 4 2 6 1 3 5 7
// output=
// 22 27 13 28 25 18 7

// Explanation:
// Input structure 
//        4
//       / \
//      2   6
//     / \ / \
//    1  3 5  7

// Output structure:
//         22
//       /   \
//      27   13
//     / \   / \
//    28 25 18  7

// Reverse updates:
// - Artifact 7: 7
// - Artifact 6: 6 + 7 = 13
// - Artifact 5: 5 + 13 = 18
// - Artifact 4: 4 + 18 = 22
// - Artifact 3: 3 + 22 = 25
// - Artifact 2: 2 + 25 = 27
// - Artifact 1: 1 + 27 = 28


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
// 4 2 6 1 3 5 7
class BSTGreaterArtifact{
    public static Node buildTree(int[] arr){
        if(arr[0]==-1)return null;
        Node root=new Node(arr[0]);
        int ind=1;
        Queue<Node>q=new LinkedList<>();
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
    public static int sum;
    public static void solve(Node root){
        if(root==null)return;
        solve(root.right);
        sum+=root.val;
        root.val=sum;
        solve(root.left);
    }
    public static void printLevelTree(Node root){
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
        for(int i=0;i<a.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        
        Node root=buildTree(arr);
        solve(root);
        printLevelTree(root);
        
    }
}
