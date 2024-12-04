// Time Complexity : building the Trie - inserting N nodes of length L  = O(N * L)
// replacing words  - Splitting O(S) where S is length of sentence . Searching - O(S * W) where W is the average length of each word

//Total time complexity O(N * L + S* W) N is number of roots in dictionary , L Average length of roots, S length of sentence, W Average length of words in sentence

//Space complexity :  Trie O(N * L) for storing roots and O(S) auxillary storage for split sentence words
// total space complexity O(N * L + S)

import java.util.*;

public class ReplaceWords {

    // Trie Node class
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }

    // Trie class for root management
    private static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isEndOfWord = true;
        }

        public String findRoot(String word) {
            TrieNode current = root;
            StringBuilder root = new StringBuilder();
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    break;
                }
                root.append(c);
                current = current.children[index];
                if (current.isEndOfWord) {
                    return root.toString();
                }
            }
            return word; // Return the original word if no root is found
        }
    }

    public static String replaceWords(List<String> dictionary, String sentence) {
        // Build the Trie from the dictionary
        Trie trie = new Trie();
        for (String root : dictionary) {
            trie.insert(root);
        }

        // Replace words in the sentence with their roots
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(trie.findRoot(word));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Example 1
        List<String> dictionary1 = Arrays.asList("cat", "bat", "rat");
        String sentence1 = "the cattle was rattled by the battery";
        System.out.println(replaceWords(dictionary1, sentence1)); // Output: "the cat was rat by the bat"

        // Example 2
        List<String> dictionary2 = Arrays.asList("a", "b", "c");
        String sentence2 = "aadsfasf absbs bbab cadsfafs";
        System.out.println(replaceWords(dictionary2, sentence2)); // Output: "a a b c"
    }
}
