// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and 
// row number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Example 1:
// Input:
// 3 9 20 -1 -1 15 7
// Output: 
// [[9],[3,15],[20],[7]]

// Explanation:
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Example 2:
// Input:
// 3 9 8 4 0 1 7
// Output: 
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]

// Library Organization Rules:
// 1. Each column represents a shelf from left to right.
// 2. Books on the same shelf are arranged from top to bottom.
// 3. If books share the same position, they are arranged left to right in order 
// of appearance.


//recursion 1 tt not passing 
// 1 2 3 4 10 9 11 -1 5 -1 -1 -1 -1 -1 -1 -1 6 this one due to recursion so do level order..
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
class Pair{
    Node n;
    int level;
    Pair(Node n,int level){
        this.n=n;
        this.level=level;
    }
}
class verticalOrder{
    public static Node buildTree(int arr[]){
        if(arr[0]==-1)return null;
        Node root=new Node(arr[0]);
        int i=1;
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
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
    // public static void solve(Node root, Map<Integer,List<Integer>>mp, int level){
    //     if(root==null)return;
    //     mp.computeIfAbsent(level, k-> new ArrayList<>()).add(root.val);
    //     solve(root.left,mp,level-1);
    //     solve(root.right,mp,level+1);
    // }
    public static void solve(Node root, Map<Integer,List<Integer>>mp){
        Queue<Pair>q=new LinkedList<>();
        q.offer(new Pair(root,0));
        while(!q.isEmpty()){
            Pair pair=q.poll();
            int lev=pair.level;
            Node cur=pair.n;
            mp.computeIfAbsent(lev,k->new ArrayList<>()).add(cur.val);
            if(cur.left!=null){
                q.offer(new Pair(cur.left,lev-1));
            }
            if(cur.right!=null){
                q.offer(new Pair(cur.right,lev+1));
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
        Map<Integer,List<Integer>>mp=new TreeMap<>();
        solve(root,mp);
        System.out.println(mp.values());
        // for(Map.Entry<Integer,List<Integer>>e:mp.entrySet()){
        //     System.out.println(e.getValue());
        // }
    }
}