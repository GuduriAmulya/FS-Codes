// Charlie Brown is working with strings,
// He is a given a string S. He wants to find out the maximum substring 'MaxSub'.

// MaxSub is a substring which appears atleast twice in S with Maximum length. 

// Your task is to help Charlie Brown to find the Maximum Substring MaxSub,
// and print the length of MaxSub. If there is MaxSub, return 0.

// Input Format:
// -------------
// A string S.

// Output Format:
// --------------
// Print an integer, length of MaxSub


// Sample Input-1:
// ---------------
// babababba

// Sample Output-1:
// ----------------
// 5

// Explanation: 
// ------------
// The Maximum substring is 'babab' , which occurs 2 times.


// Sample Input-2:
// ---------------
// abbbbba

// Sample Output-2:
// ----------------
// 4

// Explanation: 
// ------------
// The Maximum substring is 'bbbb' , which occurs 2 times.


// Sample Input-3:
// ---------------
// vignesh

// Sample Output-3:
// ----------------
// 0

//using triee..
import java.util.*;
class TrieNode{
    TrieNode children[];
    int count;
    TrieNode(){
        children=new TrieNode[26];
        this.count=0;
    }
}
class Trie{
    TrieNode root;
    Trie(){
        root=new TrieNode();
    }
    static int maxlen=0;
    public void insert(String s){
        //insert every suffix..
        TrieNode node=root;
        int depth=0;
        for(int i=0;i<s.length();i++){
            int ind=s.charAt(i)-'a';
            if(node.children[ind]==null){
                node.children[ind]=new TrieNode();
            }
            node=node.children[ind];
            node.count++;
            depth++;
            if(node.count>=2){
                maxlen=Math.max(maxlen,depth);
            }
        }
        
    }
}
class MaxSubarrayTrie{
static int maxlen=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        Trie t=new Trie();
        for(int i=0;i<s.length();i++){
            t.insert(s.substring(i));
        }
        System.out.println(t.maxlen);

    }
}