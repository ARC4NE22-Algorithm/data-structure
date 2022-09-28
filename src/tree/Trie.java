package tree;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    HashMap<Character, Trie> nodes;
    boolean isEnd, isRoot;

    public Trie(boolean isRoot) {
        this.isRoot = isRoot;
        this.nodes = new HashMap<>();
    }

    /**
     * Add a new word to Trie
     * @param word word to add to Trie
     */
    public void add(String word) {
        Trie curr = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Trie nxt = curr.nodes.get(c);
            if (nxt == null) {
                nxt = new Trie(false);
                curr.nodes.put(c, nxt);
            }
            curr = nxt;
        }
        curr.isEnd = true;
    }

    /**
     * Check whether Trie has a word or not
     * @param word Word to check
     * @return Whether the word exists
     */
    public boolean contains(String word) {
        Trie curr = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Trie nxt = curr.nodes.get(c);
            if (nxt == null) {
                return false;
            }
            curr = nxt;
        }
        return curr.isEnd;
    }

    /**
     * Print all words in Trie
     * @param temp An empty string must be inserted in the call
     */
    public void printAll(String temp) {
        for (Map.Entry<Character, Trie> child : nodes.entrySet()) {
            child.getValue().printAll(temp + child.getKey());
        }

        if (isEnd) {
            System.out.println(temp);
        }
    }
}