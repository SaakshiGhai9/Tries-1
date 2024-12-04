// Time Complexity : O(L) where L is the average lengthof words
// 1. insert O(L) 2. search O(L) 3. startWith O(L)
// Space Complexity : O(N * L) where N is number of words in Trie
// 1. insert O(N * L) 2. search O(N * L ) 3. startsWith O(N * L )
public class ImplementTrie {
    private static class TrieNode{
        TrieNode [] children = new TrieNode[26];
        boolean isEndOfWord;

    }

    private final TrieNode root;

    public ImplementTrie(){
        root = new TrieNode();

    }
    // insert word into Trie

    public void insert(String word){
        TrieNode current = root;
        for (char c : word.toCharArray()){
            int index = c - 'a';// map a-z to 0 to 25
           if(current.children[index] == null){
               current.children[index] = new TrieNode();
           }
           current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    // search trie: returns true if word is in Trie

    public boolean search( String word) {
        TrieNode node = findNode(word);

        return node != null && node.isEndOfWord;
    }


        // returns true if there is any word in Trie that starts with given prefix
    public boolean startsWith(String prefix){
        return findNode(prefix) != null;
    }

    private TrieNode findNode(String str){
        TrieNode current = root;
        for( char c : str.toCharArray()){
            int index = c - 'a';
            if(current.children[index] == null){
                return null; // prefix or word not found
            }
            current = current.children[index];
        }
        return current;
    }

    public static void main (String [] args){
        ImplementTrie trie = new ImplementTrie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // Output: true
        System.out.println(trie.search("app"));     // Output: false
        System.out.println(trie.startsWith("app")); // Output: true

        trie.insert("app");
        System.out.println(trie.search("app"));     // Output: true
    }
}

