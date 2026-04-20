/*
Imagine you are the curator of a historic library, where books are arranged in a 
unique catalog system based on their publication years. The library’s archive is 
structured like a hierarchical tree, with each book’s publication year stored at 
a node. You are given the nodes of this catalog tree starting with main node
and a list of query years.

For each query year, you need to find two publication years:
- The first is the latest year in the archive that is less than or equal to the 
  query year. If no such book exists, use -1.
- The second is the earliest year in the archive that is greater than or equal 
  to the query year. If no such book exists, use -1.

Display the results as an list of pairs, where each pair corresponds to a query.

Example 1:
----------
Input: 
2006 2002 2013 2001 2004 2009 2015 2014
2002 2005 2016

Output:
[[2002, 2002], [2004, 2006], [2015, -1]] 

Archive Structure:

          2006
         /    \
     2002     2013
     /   \     /   \
  2001  2004  2009  2015
                     /
                  2014
                  
Explanation:  
- For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
  the earliest publication year that is ≥ 2002 is also 2002.  
- For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
  the earliest publication year that is ≥ 2005 is 2006.  
- For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
  there is no publication year ≥ 2016, so we output -1 for the second value.

Example 2:
----------
Input:  
2004 2009
2003

Output:
[[-1, 2004]]

Explanation:  
- For the query 2003, there is no publication year ≤ 2003, while the earliest 
  publication year that is ≥ 2003 is 2004.

Constraints:
- The total number of books in the archive is in the range [2, 10^5].
- Each publication year is between 1 and 10^6.
- The number of queries n is in the range [1, 10^5].
- Each query year is between 1 and 10^6.

*/

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
class BSTQuery{
    public static Node insert(Node root, int val){
        if(root==null)return new Node(val);
        if(val<root.val){
            root.left=insert(root.left,val);
        }
        else{
            root.right=insert(root.right,val);
        }
        return root;
    }
    public static Node low(Node root,int val){
        Node anslow=null;
        while(root!=null){
            if(root.val<=val){
                anslow=root;
                root=root.right;
            }
            else{
                root=root.left;
            }
        }
        return anslow;
    }
    public static Node high(Node root,int val){
        Node anshigh=null;
        while(root!=null){
            if(root.val>=val){
                anshigh=root;
                root=root.left;
            }
            else{
                root=root.right;
            }
        }
        return anshigh;
    }
    
    //  public static void levelOrder(Node root){
    //      Queue<Node>q=new LinkedList<>();
    //      q.offer(root);
    //      while(!q.isEmpty()){
    //          int size=q.size();
    //          for(int i=0;i<size;i++){
    //              Node cur=q.poll();
    //              System.out.print(cur.val+" ");
    //              if(cur.left!=null){
    //                  q.offer(cur.left);
    //              }
    //              if(cur.right!=null){
    //                  q.offer(cur.right);
    //              }
    //              }
    //          System.out.println();
    //      }
    //  }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a[]=sc.nextLine().split(" ");
        int arr[]=new int[a.length];
        for(int i=0;i<a.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        String q[]=sc.nextLine().split(" ");
        Node root=null;
        for(int i=0;i<arr.length;i++){
            root=insert(root,arr[i]);
        }
        List<List<Integer>>list=new ArrayList<>();
        for(int i=0;i<q.length;i++){
            int query=Integer.parseInt(q[i]);
            Node l=low(root,query);
            Node h=high(root,query);
            int lval=(l==null)?-1:l.val;
            int hval=(h==null)?-1:h.val;
           list.add(Arrays.asList(lval,hval));
        }
        System.out.println(list);
        //levelOrder(root);
        
    }
}