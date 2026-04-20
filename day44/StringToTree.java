/*
Imagine you’re decoding a secret message that outlines the hierarchical structure 
of a covert spy network. The message is a string composed of numbers and parentheses. 
Here’s how the code works:

- The string always starts with an agent’s identification number, this is the 
  leader of the network.
- After the leader’s ID, there can be zero, one, or two segments enclosed in 
  parentheses. Each segment represents the complete structure of one subordinate 
  network.
- If two subordinate networks are present, the one enclosed in the first (leftmost) 
  pair of parentheses represents the left branch, and the second (rightmost) 
  represents the right branch.

Your mission is to reconstruct the entire spy network hierarchy based on this 
coded message.

Example 1:
Input: 4(2(3)(1))(6(5))
Output: [4, 2, 6, 3, 1, 5]

Spy network:
        4
       / \
      2   6
     / \  /
    3   1 5

Explanation:
Agent 4 is the leader.
Agent 2 (with its own subordinates 3 and 1) is the left branch.
Agent 6 (with subordinate 5) is the right branch.

Example 2:
Input: 4(2(3)(-1))(6(5)(7))
Output: [4, 2, 6, 3, -1, 5, 7]

Spy network:
         4
       /   \
      2     6
     / \    / \
    3   -1 5   7

Explanation:
Agent 4 leads the network.
Agent 2 with subordinates 3 and 1 forms the left branch.
Agent 6 with subordinates 5 and 7 forms the right branch.


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
class Solution{
    public static Node solve(String s){
        Stack<Node>st=new Stack<>();
        int i=0;
        int num=0;
        int sign=1;
        if(s.charAt(i)=='-'){
            sign=-1;
            i++;
            }
        while(i<s.length() && Character.isDigit(s.charAt(i))){
            num=num*10+(s.charAt(i)-'0');
            i++;
            }
        Node root=new Node(num);
        st.add(root);
        while(!st.isEmpty() && i<s.length()){
            if(s.charAt(i)=='('){
                i++;
                sign=1;
                if(s.charAt(i)=='-'){
                    sign=-1;
                    i++;
                }
                num=0;
                while(i<s.length() && Character.isDigit(s.charAt(i))){
                    num=num*10+(s.charAt(i)-'0');
                    i++;
                }
                //System.out.println(num);
                Node newN=new Node(num*sign);
                Node par=st.peek();
                if(par.left==null){
                    par.left=newN;
                }
                else{
                    par.right=newN;
                }
                st.add(newN);
            }
            else if(s.charAt(i)==')'){
               st.pop();
               i++;
            }
        }
        return root;
        
    }
    public static void levelOrder(Node root){
        Queue<Node>q=new LinkedList<>();
        q.offer(root);
        List<Integer>ans=new ArrayList<>();
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                Node cur=q.poll();
                    ans.add(cur.val);
                if(cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
        }
        System.out.println(ans);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        Node root=solve(s);
        levelOrder(root);
       // System.out.println(root.val+" "+root.left.val+" "+root.right.val);
        
    }
}