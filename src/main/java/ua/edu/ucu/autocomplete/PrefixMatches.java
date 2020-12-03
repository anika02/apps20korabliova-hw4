package ua.edu.ucu.autocomplete;

import ua.edu.ucu.immutable.Queue;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

/**
 * @author andrii
 */
public class PrefixMatches {

    private final Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    private void illegalArgExc(String pref) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException("length of prefix must be more than 1");
        }
    }

    public int load(String... strings) {
        for (String text : strings) {
            for (String word : text.split("\\s+")) {
                if (word.length() > 2) {
                    trie.add(new Tuple(word, word.length()));
                }
            }
        }
        return size();
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        illegalArgExc(pref);
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        illegalArgExc(pref);
        int len = (pref.length() == 2) ? 3 : pref.length();
        Queue chosenWords = new Queue();
        for (String word : trie.wordsWithPrefix(pref)) {
            if ((2 < word.length()) && (word.length() < len + k)) {
                chosenWords.enqueue(word);
            }
        }
        return chosenWords::iterator;
    }

    public int size() {
        return trie.size();
    }
}
