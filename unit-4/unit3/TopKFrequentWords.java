import java.util.*;

// Trie Node
class TrieNode {

    TrieNode[] children;
    boolean isEnd;
    int frequency;

    TrieNode() {
        children = new TrieNode[26];
        isEnd = false;
        frequency = 0;
    }
}

// Word + Frequency
class Word {

    String text;
    int freq;

    Word(String text, int freq) {
        this.text = text;
        this.freq = freq;
    }
}

public class TopKFrequentWords {

    TrieNode root = new TrieNode();

    // Insert word into Trie
    public void insert(String word, int freq) {

        TrieNode current = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEnd = true;
        current.frequency = freq;
    }

    // DFS traversal
    public void dfs(
            TrieNode node,
            StringBuilder currentWord,
            List<Word> words
    ) {

        if (node == null) {
            return;
        }

        if (node.isEnd) {
            words.add(
                new Word(
                    currentWord.toString(),
                    node.frequency
                )
            );
        }

        for (int i = 0; i < 26; i++) {

            if (node.children[i] != null) {
                currentWord.append((char) ('a' + i));
                dfs(node.children[i],currentWord,words);
                currentWord.deleteCharAt(currentWord.length() - 1);
            }
        }
    }

    public List<String> topKFrequent(String[] words,int k) {

        // Step 1: Count frequencies
        Map<String, Integer> map =
                new HashMap<>();

        for (String word : words) {

            map.put(word,map.getOrDefault(word,0)+1);
        }

        // Step 2: Insert unique words into Trie
        for (String word : map.keySet()) {
            insert(word,map.get(word));
        }

        // Step 3: DFS
        List<Word> collected =
                new ArrayList<>();

        dfs(
                root,
                new StringBuilder(),
                collected
        );

        // Step 4: Sort
        collected.sort(
            (a, b) -> {

                if (a.freq == b.freq) {
                    return a.text.compareTo(b.text);
                }

                return b.freq - a.freq;
            }
        );

        // Step 5: Return top K
        List<String> answer =
                new ArrayList<>();

        for (
                int i = 0;
                i < k && i < collected.size();
                i++
        ) {

            answer.add(
                    collected.get(i).text
            );
        }

        return answer;
    }

    public static void main(String[] args) {

        String[] words = {
                "i",
                "love",
                "writing",
                "i",
                "love",
                "coding"
        };

        int k = 2;

        TopKFrequentWords obj =
                new TopKFrequentWords();

        System.out.println(
                obj.topKFrequent(
                        words,
                        k
                )
        );
    }
}