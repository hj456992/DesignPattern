package Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by houjue on 2018/12/6.
 */
public class TrieTest {

    /**
     * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。

     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

     * 示例:

     * 输入:
     * words = ["oath","pea","eat","rain"] and board =
     * [
     * ['o','a','a','n'],
     * ['e','t','a','e'],
     * ['i','h','k','r'],
     * ['i','f','l','v']
     * ]

     * 输出: ["eat","oath"]
     * 说明:
     * 你可以假设所有输入都由小写字母 a-z 组成。
     */
    static class Solution1{
        // 解题思路，使用字典树来存储单词，用深度优先遍历board

        // 字典树节点
        static class TrieNode {
            // 当前节点的值
            char val;
            // 当前节点的孩子
            TrieNode[] children = new TrieNode[26];
            // 标记是否是完整单词
            boolean isWord;

            public TrieNode() {
            }

            public TrieNode(char val) {
                this.val = val;
            }
        }

        // 字典树
        static class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
                // 字典树根节点不存储任何值
                root.val = ' ';
            }

            public void insert(String word) {
                TrieNode node = root;
                for (int i = 0;i < word.length(); i ++) {
                    if (node.children[word.charAt(i) - 'a'] == null) {
                        node.children[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
                    }
                    node = node.children[word.charAt(i) - 'a'];
                }
                node.isWord = true;
            }

            public boolean search(String word) {
                TrieNode node = root;
                for (int i = 0; i < word.length(); i ++) {
                    if (node.children[word.charAt(i) - 'a'] == null) {
                        return false;
                    }
                    node = node.children[word.charAt(i) - 'a'];
                }
                return node.isWord;
            }

            public boolean prefix(String word) {
                TrieNode node = root;
                for (int i = 0; i < word.length(); i ++) {
                    if (node.children[word.charAt(i) - 'a'] == null) {
                        return false;
                    }
                    node = node.children[word.charAt(i) - 'a'];
                }
                return true;
            }
        }

        public static List<String> findWords(char[][] board, String[] words) {
            if (words == null || words.length == 0) {
                return new ArrayList<>();
            }

            // 将字符串插入字典树
            Trie trie = new Trie();
            for (int i = 0; i < words.length; i ++) {
                trie.insert(words[i]);
            }

            int rowLength = board.length;
            int colLength = board[0].length;
            // 标记已访问过的字典树节点
            boolean[][] visited = new boolean[rowLength][colLength];
            String str = "";

            Set<String> res = new HashSet<>();
            for (int row = 0; row < rowLength; row ++) {
                for (int col = 0; col < colLength; col ++) {
                    dfs(row, col, trie, str, visited, board, res);
                }
            }
            return new ArrayList<>(res);
        }

        private static void dfs(int row, int col, Trie trie, String str, boolean[][] visited, char[][] board, Set<String> res) {
            if (row < 0 || row >= board.length || col < 0 || col >= board[0].length){
                return;
            }
            if (visited[row][col]) {
                return;
            }

            str += board[row][col];
            // 递归终止条件，如果搜索树找不到word的前缀，返回null，如果isWord=true，表示找到了单词，返回
            if (trie.search(str)) {
                res.add(str);
            }
            if (!trie.prefix(str)) {
                return;
            }

            // 从上下左右四个维度进行深度搜索
            visited[row][col] = true;
            dfs(row - 1, col, trie, str, visited, board, res);
            dfs(row + 1, col, trie, str, visited, board, res);
            dfs(row , col - 1, trie, str, visited, board, res);
            dfs(row , col + 1, trie, str, visited, board, res);

            // 还原字符串
            visited[row][col] = false;
        }

        public static void main(String[] args) {
            String[] words = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
            char[][] board = new char[][]{
                    {'a','b'},
                    {'a','a'}
            };
            List<String> result = findWords(board, words);
            for (String res : result) {
                System.out.println(res);
            }
        }
    }
}
