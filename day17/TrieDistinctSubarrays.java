// At university of Chicago a Computer Science programing faculty as a part of 
// teaching passion, in order to make newly joined students more enthusiastic 
// in learning the subject he is given a problem at the first day of semester.
// The student who solved it first, will be awarded with a cash prize. In regard 
// to this he asked the students to work on concept related to strings, he gave a 
// task to read a word and find the count of all the turn of phrases of the word, 
// and the phrases should be distinct.

// Now it’s your time to create a method which satisfies the above program.
// The turn of phrases of a word is obtained by deleting 
// any number of characters (possibly zero) from the front of the word and
// any number of characters (possibly zero) from the back of the word.

// Input Format:
// -------------
// A single string, the word contains only lowercase alphabets [a-z].

// Output Format:
// --------------
// Print an integer, number of distinct phrases possible.


// Sample Input-1:
// ---------------
// aabbaba

// Sample Output-1:
// ----------------
// 21

// Explanation:
// -------------
// The turn of phrases of the word, which are distinct as follows:
// a, b, aa, bb, ab, ba, aab, abb, bab, bba, aba, aabb, abba, bbab, baba, 
// aabba, abbab, bbaba, aabbab, abbaba, aabbaba


// Sample Input-2:
// ---------------
// kmithyd

// Sample Output-2:
// ----------------
// 28

import java.util.*;
class TrieNode{
    TrieNode[]children;
    boolean isEnd;
    TrieNode(){
        children=new TrieNode[26];
        isEnd=false;
    }
}
class Trie{
    TrieNode root;
    Trie(){
        root=new TrieNode();
    }
    public void insert(String s){
        TrieNode node=root;
        for(char c:s.toCharArray()){
            int ind=c-'a';
            if(node.children[ind]==null){
                node.children[ind]=new TrieNode();
            }
            node=node.children[ind];
        }
        node.isEnd=true;
    }
    public boolean search(String s){
        TrieNode node=root;
        for(char c:s.toCharArray()){
            int ind=c-'a';
            if(node.children[ind]==null)return false;
            node=node.children[ind];
        }
        return node.isEnd;
    }
    public int countFn(Trie t,String s){
        int count=0;
        for(int i=0;i<s.length();i++){
            TrieNode node=root;
            for(int j=i;j<s.length();j++){
                if(t.search(s.substring(i,j+1))==false){
                    System.out.print(s.substring(i,j+1)+" ");
                    count++;
                    t.insert(s.substring(i,j+1));
                }
            }
        }
        return count;
    }
}
class TrieDistinctSubarrays{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        Trie t=new Trie();
        int c=t.countFn(t,s);
        System.out.println(c);
        
        
    }
}