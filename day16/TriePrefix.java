// Imagine you're playing a fantasy video game where secret level codes unlock new 
// worlds. These codes are strings made up of letters, and a level code is only 
// considered valid if every shorter code formed by its leading characters has been
// discovered along the journey. In other words, a code is unlockable only when 
// all of its prefixes are also present in your collection.

// Given a list of strings representing the level codes you’ve collected, find the 
// longest valid level code such that every prefix of that code is in the list. If 
// there is more than one valid code of the same length, choose the one that comes 
// first in alphabetical order. If no such code exists, return an empty string.

// Input Format
// -------------
// Line1: Space separated codes (strings)
 
// Output Format
// --------------
// string 


// Example 1:
// ----------
// Input:
// m ma mag magi magic magici magicia magician magicw
// Output: 
// "magician"

// Explanation: The code "magician" is unlockable because every prefix—"m", "ma", 
// "mag", "magi", "magic", "magici", and "magicia"—is present in the list. Although 
// "magicw" appears too, it fails since its prefix "magica" is missing.


// Example 2:
// ----------
// Input:
// a banana app appl ap apply apple
// Output: 
// "apple"  

// Explanation: Both "apple" and "apply" have every prefix in the list, but "apple" 
// is comes first in alphabetical order.

// Example 3:
// ----------
// Input: 
// abc bc ab abcd
// Output: 
// ""

import java.util.*;
class TrieNode{
    TrieNode[] children;
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
    public boolean searchAllPrefixes(String s){
        TrieNode node=root;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            int index=ch-'a';
            if(node.children[index]==null){
                return false;
            }
            node=node.children[index];
            if(!node.isEnd)return false;
        }
        return true;
    }
}
class TriePrefix{
    public static boolean isValid(Trie t, String s){
        for(int i=1;i<=s.length();i++){
            if(!t.searchAllPrefixes(s.substring(0,i))){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String arr[]=sc.nextLine().split(" ");
        Arrays.sort(arr,(a,b)->a.length()-b.length());
        //System.out.println(Arrays.toString(arr));
        Trie t=new Trie(); 
        for(String s: arr){
            t.insert(s);
        }
        Arrays.sort(arr,(a,b)->{
            if(a.length()!=b.length()){return b.length()-a.length();}
            else{return a.compareTo(b);}
        });
        //System.out.println(Arrays.toString(arr));
        // List<String>ans=new ArrayList<>();
        for(String s: arr){
            if(isValid(t,s)){
                System.out.println(s);
                return;
            }
        }
        // for(int i=0;i<arr.length;i++){
        //     if(t.searchAllPrefixes(arr[i])){
        //         found=true;
        //         System.out.println(arr[i]);
        //         break;
        //         //ans.add(arr[i]);
        //     }
        // }
        
        // if(!found){
        //     System.out.println("");
        // }
        // else{
        //   // System.out.println(ans);
        //     Collections.sort(ans,(a,b)->{
        //         if(b.length()!=a.length()){
        //             return b.length()-a.length();
        //         }
        //         return a.compareTo(b);
        //     });
        //     System.out.println(ans.get(0));
        // }
        
    }
}