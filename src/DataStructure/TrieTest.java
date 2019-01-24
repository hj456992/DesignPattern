package DataStructure;

/**
 * 描述:
 * 字典树题目练习
 *
 * @author 侯珏
 * @create 2018-12-05 23:48
 */
public class TrieTest {

    /**
     * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
     *
     * 示例:
     *
     * Trie trie = new Trie();
     *
     * trie.insert("apple");
     * trie.search("apple");   // 返回 true
     * trie.search("app");     // 返回 false
     * trie.startsWith("app"); // 返回 true
     * trie.insert("app");
     * trie.search("app");     // 返回 true
     * 说明:
     *
     * 你可以假设所有的输入都是由小写字母 a-z 构成的。
     * 保证所有输入均为非空字符串。
     */
    static class Solution1 {

        static class Trie {
            class TrieNode {
                char val;
                boolean isWord;
                TrieNode[] children = new TrieNode[26];
                TrieNode() {
                }
                TrieNode(char val) {
                    this.val = val;
                }
            }

            TrieNode root;

            /** Initialize your data structure here. */
            public Trie() {
                root = new TrieNode();
                // trie树根节点不存任何数据，数据均在边上
                root.val = ' ';
            }

            /** Inserts a word into the trie. */
            public void insert(String word) {
                TrieNode ws = root;
                for (char c : word.toCharArray()) {
                    if (ws.children[c - 'a'] == null) {
                        ws.children[c - 'a'] = new TrieNode(c);
                    }
                    ws = ws.children[c - 'a'];
                }
                ws.isWord = true;
            }

            /** Returns if the word is in the trie. */
            public boolean search(String word) {
                TrieNode ws = root;
                for (char c : word.toCharArray()) {
                    if (ws.children[c - 'a'] == null) {
                        return false;
                    }
                    ws = ws.children[c - 'a'];
                }
                return ws.isWord;
            }

            /** Returns if there is any word in the trie that starts with the given prefix. */
            public boolean startsWith(String prefix) {
                TrieNode ws = root;
                for (char c : prefix.toCharArray()) {
                    if (ws.children[c - 'a'] == null) {
                        return false;
                    }
                    ws = ws.children[c - 'a'];
                }
                return true;
            }
        }

        public static void main(String[] args) {
            Trie trie = new Trie();

            trie.insert("apple");
            System.out.println(trie.search("apple"));   // 返回 true
            System.out.println(trie.search("app"));     // 返回 false
            System.out.println(trie.startsWith("app")); // 返回 true
            trie.insert("app");
            System.out.println(trie.search("app"));     // 返回 true
        }
    }
}
