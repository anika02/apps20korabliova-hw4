package ua.edu.ucu.tries;

import ua.edu.ucu.immutable.Queue;

public class RWayTrie implements Trie {
    private static final int R = 26;
    private static final int INDEX_START = 'a';
    private Node root = new Node();
    private int size = 0;

    private static class Node {
        private Object value;
        private final Node[] next = new Node[R];
    }

    @Override
    public void add(Tuple t) {
        if (!contains(t.term)) {
            root = put(root, t, 0);
            ++size;
        }
    }

    @Override
    public boolean contains(String word) {
        Node x = get(root, word, 0);
        return (x != null) && (x.value != null);
    }

    @Override
    public boolean delete(String word) {
        if (contains(word)) {
            root = delete(root, word, 0);
            --size;
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue q = new Queue();
        collect(get(root, s, 0), s, q);
        return q::iterator;
    }

    @Override
    public int size() {
        return size;
    }

    // ------------------ method from book with some changes ------------------
    private Node put(Node x, Tuple t, int d) {
        Node temp;
        if (x == null) {
            temp = new Node();
        } else {
            temp = x;
        }
        if (d == t.term.length()) {
            temp.value = t.weight;
            return temp;
        }
        char c = t.term.charAt(d);
        temp.next[c - INDEX_START] = put(temp.next[c - INDEX_START], t, d + 1);
        return temp;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c - INDEX_START], key, d + 1);
    }

    private Node delete(Node x, String key, int d) {
        if (d == key.length()) {
            x.value = null;
        } else {
            char c = key.charAt(d);
            x.next[c - INDEX_START] = delete(
                    x.next[c - INDEX_START], key, d + 1);
        }
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

    private void collect(Node x, String pre, Queue q) {
        if (x == null) {
            return;
        }
        if (x.value != null) {
            q.enqueue(pre);
        }
        for (int c = 0; c < R; c++) {
            collect(x.next[c], pre + (char) (c + INDEX_START), q);
        }
    }
}
