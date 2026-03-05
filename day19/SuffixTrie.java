// Mr Suresh is working with the plain text P, a list of words w[], 
// He is converting P into C [the cipher text], C is valid cipher of P, 
// if the following rules are followed:
// 	- The cipher-text C is a string ends with '$' character.
// 	- Every word, w[i] in w[], should be a substring of C, and 
// 	the substring should have $ at the end of it.

// Your task is to help Mr Suresh to find the shortest Cipher C,  
// and return its length.

// Input Format:
// -------------
// Single line of space separated words, w[].

// Output Format:
// --------------
// Print an integer result, the length of the shortest cipher.


// Sample Input-1:
// ---------------
// kmit it ngit

// Sample Output-1:
// ----------------
// 10

// Explanation:
// ------------
// A valid cipher C is "kmit$ngit$".
// w[0] = "kmit", the substring of C, and the '$' is the end character after "kmit"
// w[1] = "it", the substring of C, and the '$' is the end character after "it"
// w[2] = "ngit", the substring of C, and the '$' is the end character after "ngit"


// Sample Input-2:
// ---------------
// ace

// Sample Output-2:
// ----------------
// 4

// Explanation:
// ------------
// A valid cipher C is "ace$".
// w[0] = "ace", the substring of C, and the '$' is the end character after "ace"


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
    public boolean insert(String s){
        TrieNode node=root;
        boolean newStr=false;
        for(int i=s.length()-1;i>=0;i--){ //reverse words to build suffix tree
            int ind=s.charAt(i)-'a';
            if(node.children[ind]==null){
                node.children[ind]=new TrieNode();
                newStr=true;
            }
            node=node.children[ind];
        }
        return newStr; //it is a new word then add the entire word len
    }
    
}
class SuffixTrie{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[]arr=sc.nextLine().split(" ");
        Trie t=new Trie();
        Set<String>st=new HashSet<>(Arrays.asList(arr));
        List<String>l=new ArrayList<>(Arrays.asList(arr));
        Collections.sort(l,(a,b)->b.length()-a.length());
        StringBuilder sb=new StringBuilder();
        int len=0;
        for(String s:l){
            if(t.insert(s)){
                //new..
                sb.append(s+"$");
                len+=(s.length()+1);
            }
        }
        //System.out.println(sb.toString());
        System.out.println(len);
    }
}