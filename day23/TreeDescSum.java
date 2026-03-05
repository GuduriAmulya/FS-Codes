// There are some pages in a website, each page links with atmost two other pages.
// Each page displays a number on it. The complete website is given as binary tree 
// using the level order insertion technique.

// You need to return the number of pages where the number in the page is equal to 
// the sum of the numbers of its descendants. A descendant refers to any page that 
// is linked but lower down the tree stucture of the website, no matter how many 
// levels lower.

// Input Format:
// -------------
// Single line of space separated integers, numbers displayed in web-pages as Tree.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 11 3 5 2 1

//     11
//   3    5
// 2   1

// Sample Output-1:
// ----------------
// 2


// Sample Input-2:
// ---------------
// 3 2 1 0 0

//     3
//   2   1
// 0   0
// Sample Output-2:
// ----------------
// 3

// Explanation:
// ------------
// For the pages diplaying the number 0: The sum of descendants is 0,
// since they have no descendant pages.


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
class TreeDescSum{
    public static Node buildTree(int arr[]){
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
    public static int count=0;
    public static int solve(Node root){
        if(root==null)return 0;
        int l1=solve(root.left);
        int l2=solve(root.right);
        if(l1+l2==root.val)count++;
        return l1+l2+root.val;
    }
    
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int n=a.length;
        int arr[]=new int[n];
        for(int i=0;i<n;i++)arr[i]=Integer.parseInt(a[i]);
        Node root=buildTree(arr);
        solve(root);
        System.out.println(count);
    }
}