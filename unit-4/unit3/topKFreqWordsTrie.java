import java.util.*;

class Solution {

    // Trie Node
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
        int freq = 0;
        String word = "";
    }

    TrieNode root = new TrieNode();

    // Insert word into Trie
    private void insert(String word, int freq) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
        node.freq = freq;
        node.word = word;
    }

    // DFS traversal of Trie
    private void dfs(TrieNode node, PriorityQueue<TrieNode> pq, int k) {
        if (node == null) return;

        if (node.isEnd) {
            pq.offer(node);
            if (pq.size() > k) {
                pq.poll(); // remove least priority
            }
        }

        for (int i = 0; i < 26; i++) {
            dfs(node.children[i], pq, k);
        }
    }

    public List<String> topKFrequent(String[] words, int k) {

        // 1. Count Frequencies
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // 2. Insert into Trie
        for (String word : map.keySet()) {
            insert(word, map.get(word));
        }

        // 3. Min Heap
        PriorityQueue<TrieNode> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.freq == b.freq) {
                    return b.word.compareTo(a.word); // reverse lex
                }
                return a.freq - b.freq; // min heap by freq
            }
        );

        // 4. DFS Traversal
        dfs(root, pq, k);

        // 5. Extract results
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().word);
        }

        Collections.reverse(result); // highest freq first
        return result;
    }
}
//O(V+E)
