class SuffixTreeNode {
    static final int ALPHABET_SIZE = 26;
    SuffixTreeNode[] children = new SuffixTreeNode[ALPHABET_SIZE];
    boolean isEndOfWord;
    
    SuffixTreeNode() {
        isEndOfWord = false;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            children[i] = null;
        }
    }
}

public class suffixTree {
    private SuffixTreeNode root;
    
    public suffixTree() {
        root = new SuffixTreeNode();
    }
    
    // Insert all suffixes of a word into the tree
    public void insertAllSuffixes(String word) {
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            insert(word.substring(i));
        }
    }
    
    // Insert a single word into the tree
    private void insert(String word) {
        SuffixTreeNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (index < 0 || index >= SuffixTreeNode.ALPHABET_SIZE) {
                return;
            }
            if (current.children[index] == null) {
                current.children[index] = new SuffixTreeNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }
    
    // Search for a pattern in the suffix tree
    public boolean search(String pattern) {
        pattern = pattern.toLowerCase();
        SuffixTreeNode current = root;
        for (char ch : pattern.toCharArray()) {
            int index = ch - 'a';
            if (index < 0 || index >= SuffixTreeNode.ALPHABET_SIZE) {
                return false;
            }
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return true;
    }
    
    // Display all suffixes
    public void displayAllSuffixes(String word) {
        System.out.println("All suffixes of '" + word + "':");
        for (int i = 0; i < word.length(); i++) {
            System.out.println(word.substring(i));
        }
    }
    
    public static void main(String[] args) {
        suffixTree tree = new suffixTree();
        String word = "banana";
        
        tree.displayAllSuffixes(word);
        tree.insertAllSuffixes(word);
        
        System.out.println("\nSearching patterns:");
        System.out.println("'ana' found: " + tree.search("ana"));
        System.out.println("'nan' found: " + tree.search("nan"));
        System.out.println("'ban' found: " + tree.search("ban"));
        System.out.println("'xyz' found: " + tree.search("xyz"));
    }
}