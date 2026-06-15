// text = "thestoryofleetcodeandme"
// words = ["story", "fleet", "leetcode"]
import java.util.*;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class IndexPairStrTrie {
    public List<int[]> indexPairs(String text, String[] words) {
        TrieNode root = new TrieNode();
        // Build Trie
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new TrieNode();
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }
        List<int[]> result = new ArrayList<>();
        // Traverse text
        for (int i = 0; i < text.length(); i++) {
            TrieNode node = root;
            for (int j = i; j < text.length(); j++) {
                char c = text.charAt(j);
                if (node.children[c - 'a'] == null) break;
                node = node.children[c - 'a'];
                if (node.isEnd) {
                    result.add(new int[]{i, j});
                }
            }
        }

        return result;
    }
}

// Build Trie: O(total characters in words)
// Search: O(n²) worst case