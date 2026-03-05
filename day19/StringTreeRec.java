// Imagine you’re decoding a secret message that outlines the hierarchical structure 
// of a covert spy network. The message is a string composed of numbers and parentheses. 
// Here’s how the code works:

// - The string always starts with an agent’s identification number, this is the 
//   leader of the network.
// - After the leader’s ID, there can be zero, one, or two segments enclosed in 
//   parentheses. Each segment represents the complete structure of one subordinate 
//   network.
// - If two subordinate networks are present, the one enclosed in the first (leftmost) 
//   pair of parentheses represents the left branch, and the second (rightmost) 
//   represents the right branch.

// Your mission is to reconstruct the entire spy network hierarchy based on this 
// coded message.

// Example 1:
// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//        / \
//       2   6
//      / \  /
//     3   1 5

// Explanation:
// Agent 4 is the leader.
// Agent 2 (with its own subordinates 3 and 1) is the left branch.
// Agent 6 (with subordinate 5) is the right branch.

// Example 2:
// Input: 4(2(3)(-1))(6(5)(7))
// //preorder=[4,2,3,-1,6,5,7]
// Output: [4, 2, 6, 3, -1, 5, 7]

// Spy network:
//          4
//        /   \
//       2     6
//      / \   / \
//     3  -1 5   7

// Explanation:
// Agent 4 leads the network.
// Agent 2 with subordinates 3 and 1 forms the left branch.
// Agent 6 with subordinates 5 and 7 forms the right branch.

//timeout
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
    public static Node stackTree(String s){
        Stack<Node>st=new Stack<>();
        int num=0;
        int i=0;
        while(i<s.length() && Character.isDigit(s.charAt(i))){
            num=num*10+(s.charAt(i)-'0');
            i++;
            }
        Node root=new Node(num);
        st.add(root);
        while(!st.isEmpty() && i<s.length()){
            if(s.charAt(i)=='('){
                i++;
                //push to stack..
                int sign=1;
                if(s.charAt(i)=='-'){
                    sign=-1;
                    i++;
                }
                num=0;
                while(i<s.length() && Character.isDigit(s.charAt(i))){
                    num=num*10+(s.charAt(i)-'0');
                    i++;
                }
                num=num*sign;
                Node n=new Node(num);
                //System.out.print("adding "+num);
                st.add(n);
            }
            else if(s.charAt(i)==')'){
                i++;//skip that )
                Node top=st.pop();
                //System.out.println("popping: "+top.val);
                Node parent=st.peek();
                if(parent.left==null){
                    parent.left=top;
                }
                else{
                    parent.right=top;
                }
            }
        }
        return root;
        
    }
    public static void levelOrder(Node root){
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
        String s=sc.nextLine();
        // List<Integer>l=new ArrayList<>();
        // l.add(s.charAt(0)-'0');
        // for(int i=1;i<s.length();i++){
        //     char c=s.charAt(i);
        //     if(Character.isDigit(c)){
        //         if(s.charAt(i-1)=='-'){
        //             int  n=Integer.parseInt(s.substring(i-1,i+1));
        //             //System.out.println(n);
        //             l.add(n);
        //         }
        //         else{
        //             l.add(c-'0');
        //         }
        //     }
        // }
        //System.out.println(l);//preorder
        
        Node root=stackTree(s);
        levelOrder(root);
    }
}