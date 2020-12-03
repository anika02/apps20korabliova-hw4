
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.Test;

import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.*;

import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

/**
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }

    @Test
    public void testLoad() {
        pm.load("a aa aaa", "b bb bbb", "ab");
        assertEquals(7, pm.size());
        assertEquals(10, pm.load("hello world", "alphabet"));
    }

    @Test
    public void testContains() {
        pm.delete("abc");

        assertFalse(pm.contains("abc"));
        assertTrue(pm.contains("abce"));
        assertTrue(pm.contains("abcde"));
        assertTrue(pm.contains("abcdef"));
        assertFalse(pm.contains("abcdefe"));
    }

    @Test
    public void testDelete() {
        String pref = "ab";
        assertTrue(pm.delete("abc"));
        assertFalse(pm.delete("abc"));

        Iterable<String> result = pm.wordsWithPrefix(pref);
        String[] expResult = {"abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
        assertEquals(4, pm.size());
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K_One() {
        String pref = "ab";

        pm.load("ab");
        Iterable<String> resultLess = pm.wordsWithPrefix(pref, 1);

        String[] expResultLess = {"abc"};

        assertThat(resultLess, containsInAnyOrder(expResultLess));

        Iterable<String> resultMore = pm.wordsWithPrefix(pref, 5);

        String[] expResultMore = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(resultMore, containsInAnyOrder(expResultMore));
    }

    @Test
    public void testSize() {
        assertEquals(5, pm.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgExc() {
        pm.wordsWithPrefix("a");
    }

}
