package ua.edu.ucu.tries;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class RWayTrieTest {

    private RWayTrie trie;

    @Before
    public void init() {
        trie = new RWayTrie();
        String[] words = new String[]{"she", "sells", "show",
                "sea", "shells", "by", "the", "see", "shore"};
        for (String word : words) {
            trie.add(new Tuple(word, word.length()));
        }
    }

    @Test
    public void testAddContains() {
        assertFalse(trie.contains("start"));
        String word = "start";
        trie.add(new Tuple(word, word.length()));
        assertTrue(trie.contains("start"));
        assertEquals(10, trie.size());
    }

    @Test
    public void testAddRepeated() {
        assertTrue(trie.contains("show"));
        assertEquals(9, trie.size());
        String word = "show";
        trie.add(new Tuple(word, word.length()));
        assertEquals(9, trie.size());
    }

    @Test
    public void testWords() {
        Iterable<String> result = trie.words();
        String[] expResult = {"she", "sells", "show",
                "sea", "shells", "by", "the", "see", "shore"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefixDelete() {
        String pref = "sh";

        Iterable<String> result = trie.wordsWithPrefix(pref);
        String[] before = {"she", "shells", "shore", "show"};

        assertThat(result, containsInAnyOrder(before));
        assertEquals(9, trie.size());

        assertFalse(trie.delete("sho"));
        assertTrue(trie.delete("show"));
        assertFalse(trie.delete("show"));

        result = trie.wordsWithPrefix(pref);
        String[] after = {"she", "shells", "shore"};

        assertThat(result, containsInAnyOrder(after));
        assertEquals(8, trie.size());

    }

    @Test
    public void testSize() {
        String[] words = new String[]{"hello", "my",
                "world", "love", "you"};
        int counter = 9;
        for (String word : words) {
            trie.add(new Tuple(word, word.length()));
            assertEquals(++counter, trie.size());
        }
    }
}
