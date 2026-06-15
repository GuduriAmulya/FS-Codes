// words = ["a", "ap", "app", "appl", "apple", "apply"]
// /o/p=>apple
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
}

class Solution {
    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new TrieNode();
            node = node.children[c - 'a'];
        }
        node.isEnd = true;
    }

    public String longestWord(String[] words) {
        for (String word : words) insert(word);

        return dfs(root, "");
    }

    private String dfs(TrieNode node, String path){
        String res = path;
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null && node.children[i].isEnd) {
                char c = (char)(i + 'a');
                String temp = dfs(node.children[i], path + c);

                if (temp.length() > res.length() ||
                   (temp.length() == res.length() && temp.compareTo(res) < 0)) {
                    res = temp;
                }
            }
        }

        return res;
    }
}


// Build: O(N * L)
// DFS: O(26 * nodes) ≈ O(N * L)