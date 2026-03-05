// A software development company is designing a smart home automation 
// system that uses sensor networks to monitor and control different devices 
// in a house. The sensors are organized in a hierarchical structure, where each 
// sensor node has a unique ID and can have up to two child nodes (left and right).

// The company wants to analyze the left-most sensors in the system to determine
// which ones are critical for detecting environmental changes. The hierarchy of 
// the sensors is provided as a level-order input, where missing sensors are 
// represented as -1.

// Your task is to build the sensor network as a binary tree and then determine 
// the left-most sensor IDs at each level.

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
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the left-most sensor IDs at each level


// Sample Input-1:
// ---------------
// 1 2 3 4 -1 -1 5
//        1
//      2   3
//   4  -1 -1  5
  

// Sample Output-1:
// ----------------
// [1, 2, 4]



// Sample Input-2:
// ---------------
// 3 2 4 1 5
//     3
//   2   4
// 1   5

// Sample Output-2:
// ----------------
// [3, 2, 1]



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
class leftsideTree{
    public static Node buildTree(int arr[]){
        int ind=0;
        List<Integer>ans=new ArrayList<>();
        Node root=new Node(arr[ind++]);
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                Node cur=q.poll();
                if(i==0)ans.add(cur.val);
                if(ind<arr.length && arr[ind]!=-1){
                    cur.left=new Node(arr[ind]);
                    //System.out.println(arr[ind]);
                    q.offer(cur.left);
                }
                ind++;
                if(ind<arr.length &&arr[ind]!=-1){
                    cur.right=new Node(arr[ind]);
                    //System.out.println(arr[ind]);
                    q.offer(cur.right);
                }
                ind++;
            }
            
        }
        System.out.println(ans);
        return root;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int arr[]=new int[a.length];
        for(int i=0;i<a.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        Node root=buildTree(arr);
    }
}
