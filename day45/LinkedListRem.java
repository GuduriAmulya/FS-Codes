/*
Cliff Shaw is working on the singly linked list.
He is given a list of boxes arranged as singly linked list, where each box is 
printed with a positive number on it and arranged in the list are ascending order.
and numbers on the boxes may be repeated.

Mr Cliff Shaw is performing an operation on the list.
	-  To have only the distinct values in the final list,
	removed all duplicate values.

Your task is to help Mr Cliff to perform the operation and return the updated list

Input Format:
-------------
Line-1: An integer, N number of boxes in list.
Line-2: N space separated integers, boxes as list.

Output Format:
--------------
Print the updated list.


Sample Input-1:
---------------
8
1 1 1 2 2 3 3 4

Sample Output-1:
----------------
1 2 3 4


Sample Input-2:
---------------
5
1 1 1 1 1

Sample Output-2:
----------------
1
*/

import java.util.*;
class Node{
    int val;
    Node next;
    Node(int val){
        this.val=val;
        next=null;
    }
}
class LinkedListRem{
    public static Node addToList(int[] arr){
        int i=0;
        Node head=new Node(arr[i++]);
        Node temp=head;
        while(i<arr.length){
            temp.next=new Node(arr[i++]);
            temp=temp.next;
        }
        
        return head;
    }
    public static Node removeDup(Node head){
       Node cur=head;
       while(cur!=null && cur.next!=null){
           if(cur.val==cur.next.val){
               cur.next=cur.next.next;
           }
           else{
               cur=cur.next;
           }
       }
    
    return head;
        
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        Node head=addToList(arr);
        Node h=removeDup(head);
        Node t=head;
        while(t!=null){
            System.out.print(t.val+" ");
            t=t.next;
        }
    }
}