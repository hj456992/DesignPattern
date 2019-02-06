package Test;

import java.util.*;

/**
 * 并查集练习题
 * Created by houjue on 2019-02-03.
 */
public class UnionFindTest {

    /**
     * 128. 最长连续序列
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     *
     * 要求算法的时间复杂度为 O(n)。
     *
     * 示例:
     *
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     */
    private static class Solution1 {
        // 一般解法，
        //用哈希表存储每个端点值对应连续区间的长度
        //若数已在哈希表中：跳过不做处理
        //若是新数加入：
        //取出其左右相邻数已有的连续区间长度 left 和 right
        //计算当前数的区间长度为：cur_length = left + right + 1
        //根据 cur_length 更新最大长度 max_length 的值
        //更新区间两端点的长度值
        private static int longestConsecutive1(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();

            int maxLength = 0;
            for (int i = 0; i < nums.length; i ++) {
                if (!map.containsKey(nums[i])) {
                    int left = map.getOrDefault(nums[i] - 1, 0);
                    int right = map.getOrDefault(nums[i] + 1, 0);

                    int curLength = left + right + 1;
                    maxLength = Math.max(curLength, maxLength);

                    map.put(nums[i], curLength);
                    map.put(nums[i] - left, curLength);
                    map.put(nums[i] + right, curLength);
                }
            }

            return maxLength;
        }

        // 并查集解法，把相关元素放在一条链路里面
        private static class UnionFind{
            int[] ids;
            int[] rank;

            public UnionFind(int n) {
                ids = new int[n];
                rank = new int[n];
                for (int i = 0; i < n; i ++) {
                    ids[i] = i;
                    rank[i] = 0;
                }
            }

            private int find(int i) {
                while (i != ids[i]) {
                    ids[i] = find(ids[i]);
                    i = ids[i];
                }
                return i;
            }

            public void union(int i, int j) {
                int idI = find(i);
                int idJ = find(j);
                if (idI == idJ) {
                    return;
                }
                if (rank[idI] < rank[idJ]) ids[idI] = idJ;
                else if (rank[idI] > rank[idJ]) ids[idJ] = idI;
                else {
                    ids[idJ] = idI;
                    rank[idI]++;
                }
            }

            public int getMaxLength() {
                int[] count = new int[ids.length];
                int max = 0;
                for (int i = 0; i < ids.length; i ++) {
                    count[find(i)] ++;
                    max = Math.max(max, count[find(i)]);
                }
                return max;
            }
        }

        private static int longestConsecutive(int[] nums) {
            int n = nums.length;

            Map<Integer, Integer> map = new HashMap<>();
            UnionFind uf = new UnionFind(n);
            for (int i = 0; i < n; i ++) {
                if (map.containsKey(nums[i])) {
                    continue;
                }
                map.put(nums[i], i);
                if (map.containsKey(nums[i] - 1)) {
                    uf.union(i, map.get(nums[i] - 1));
                }
                if (map.containsKey(nums[i] + 1)) {
                    uf.union(i, map.get(nums[i] + 1));
                }
            }


            return uf.getMaxLength();
        }

        public static void main(String[] args) {
            System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
        }
    }

    /**
     * 130. 被围绕的区域
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * 示例:
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     *
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
     * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     */
    private static class Solution2 {
        // 广度优先搜索解法1，搜索所有的O节点位置，如果O节点位置在外围，则将其相关联的所有O节点置为不可被填充，其他的填充X
        private static void solve(char[][] board) {
            if (board.length == 0) {
                return;
            }
            int m = board.length;
            int n = board[0].length;
            if (m < 3 || n < 3) {
                return;
            }

            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i ++) {
                for (int j = 0; j < n; j ++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    visited[i][j] = true;
                    if (board[i][j] == 'X') {
                        continue;
                    }
                    LinkedList<Integer> queue = new LinkedList<>();
                    queue.add(i);
                    queue.add(j);

                    int[] arr = new int[m * n * 2];
                    int k = 0;
                    boolean ableFill = true;

                    while (!queue.isEmpty()) {
                        int r = queue.pop();
                        int c = queue.pop();
                        arr[k++] = r;
                        arr[k++] = c;
                        if (r == m - 1 || c == n - 1 || r == 0 || c == 0) {
                            ableFill = false;
                        }

                        if (r > 0 && board[r - 1][c] == 'O' &&  !visited[r - 1][c]) {
                            visited[r - 1][c] = true;
                            queue.add(r - 1);
                            queue.add(c);
                        }

                        if (r < m - 1 && board[r + 1][c] == 'O' && !visited[r + 1][c]) {
                            visited[r + 1][c] = true;
                            queue.add(r + 1);
                            queue.add(c);
                        }

                        if (c > 0 && board[r][c - 1] == 'O' && !visited[r][c - 1]) {
                            visited[r][c - 1] = true;
                            queue.add(r);
                            queue.add(c - 1);
                        }

                        if (c < n - 1 && board[r][c + 1] == 'O'  && !visited[r][c + 1]) {
                            visited[r][c + 1] = true;
                            queue.add(r);
                            queue.add(c + 1);
                        }
                    }

                    if (ableFill) {
                        for (int t = 0; t < k; t += 2) {
                            board[arr[t]][arr[t + 1]] = 'X';
                        }
                    }
                }
            }
        }

        // 广度优先搜索，仅搜索与边界O节点相关联的O节点的位置，并标记为一个常量，以确保其不被填充
        private static void solve1(char[][] board) {
            if (board.length < 3 || board[0].length < 3) {
                return;
            }
            int m = board.length;
            int n = board[0].length;

            LinkedList<Integer[]> queue = new LinkedList<>();

            // 搜索矩阵的外圈，把O给填进去
            for (int i = 0; i < m; i ++) {
                if (board[i][0] == 'O') {
                    queue.add(new Integer[]{i, 0});
                }
                if (board[i][n - 1] == 'O') {
                    queue.add(new Integer[]{i, n - 1});
                }
            }

            for (int i = 1; i < n - 1; i ++) {
                if (board[0][i] == 'O') {
                    queue.add(new Integer[]{0, i});
                }
                if (board[m - 1][i] == 'O') {
                    queue.add(new Integer[]{m - 1, i});
                }
            }

            // 用+号标记不能被填充的O
            while (!queue.isEmpty()) {
                Integer[] index = queue.pop();
                int row = index[0];
                int col = index[1];
                board[row][col] = '+';

                if (row - 1 >= 0 && board[row - 1][col] == 'O') {
                    queue.add(new Integer[]{row - 1, col});
                }

                if (row + 1 <= m - 1 && board[row + 1][col] == 'O') {
                    queue.add(new Integer[]{row + 1, col});
                }

                if (col - 1 >= 0 && board[row][col - 1] == 'O') {
                    queue.add(new Integer[]{row, col - 1});
                }

                if (col + 1 <= n - 1 && board[row][col + 1] == 'O') {
                    queue.add(new Integer[]{row, col + 1});
                }
            }

            for (int i = 0; i < m; i ++) {
                for (int j = 0; j < n; j ++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == '+') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        // 深度优先搜索
        private static int m;
        private static int n;
        private static void solve2(char[][] board) {
            if (board.length < 3 || board[0].length < 3) {
                return;
            }
            m = board.length;
            n = board[0].length;

            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i ++) {
                dfs(board, i, 0, visited);
                dfs(board, i, n-1, visited);
            }
            for (int i = 0; i < n; i ++) {
                dfs(board, 0, i, visited);
                dfs(board, m - 1, i, visited);
            }

            for (int i = 0; i < m; i ++) {
                for (int j = 0; j < n; j ++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == '+') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        private static void dfs(char[][] board, int i, int j, boolean[][] visited) {
            if (i < 0 || i >=m || j < 0 || j >= n || visited[i][j]) {
                return;
            }
            visited[i][j] = true;
            if (board[i][j] == 'X') {
                return;
            }
            if (board[i][j] == 'O') {
                board[i][j] = '+';
            }
            dfs(board, i - 1, j, visited);
            dfs(board, i + 1, j, visited);
            dfs(board, i, j - 1, visited);
            dfs(board, i, j + 1, visited);
        }

        // 并查集解法
        private static class UnionFind {
            int[] root;
            int[] rank;

            public UnionFind(int n){
                root = new int[n];
                rank = new int[n];
                for (int i = 0; i < n; i ++) {
                    root[i] = i;
                    rank[i] = 0;
                }
            }

            private int find(int i) {
                while (i != root[i]) {
                    root[i] = find(root[i]);
                    i = root[i];
                }
                return i;
            }

            public boolean connected(int p, int q) {
                return find(p) == find(q);
            }

            public void union(int p, int q) {
                int i = find(p);
                int j = find(q);
                if (i == j) {
                    return;
                }
                if (rank[i] < rank[j]) {
                    root[i] = root[j];
                } else if (root[i] > root[j]) {
                    root[j] = root[i];
                } else {
                    root[j] = root[i];
                    rank[i] ++;
                }
            }

        }

        private static void solve3(char[][] board) {
            if (board.length < 3 || board[0].length < 3) {
                return;
            }
            int m = board.length;
            int n = board[0].length;

            UnionFind uf = new UnionFind(m * n + 1);
            // 将外圈的O，连接到m * n这个位置，其他位置的O按上下左右进行相连
            for (int i = 0; i < m; i ++) {
                for (int j = 0; j < n; j ++) {
                    if (board[i][j] == 'X') {
                        continue;
                    }
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        uf.union(i * n + j, m * n);
                    } else {
                        if (board[i - 1][j] == 'O') {
                            uf.union((i - 1) * n + j, i * n + j);
                        }

                        if (board[i + 1][j] == 'O') {
                            uf.union((i + 1) * n + j, i * n + j);
                        }

                        if (board[i][j - 1] == 'O') {
                            uf.union(i * n + j - 1, i * n + j);
                        }

                        if (board[i][j + 1] == 'O') {
                            uf.union(i * n + j + 1, i * n + j);
                        }
                    }
                }
            }
            for (int i = 0; i < m; i ++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O' && !uf.connected(i * n + j, m * n)) {
                        board[i][j] = 'X';
                    }
                }
            }
            System.out.println();
        }

        public static void main(String[] args) {
//            solve3(new char[][]{
//                     {'X','X','X','X'}
//                    ,{'X','O','O','X'}
//                    ,{'X','X','O','X'}
//                    ,{'X','O','X','X'}});
            solve3(new char[][]{
                    {'O','O','O'}
                    ,{'O','O','O'}
                    ,{'O','O','O'}
                    ,{'O','O','O'}});
        }
    }

    /**
     * 399. 除法求值
     * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
     *
     * 示例 :
     * 给定 a / b = 2.0, b / c = 3.0
     * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
     * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
     *
     * 输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。
     *
     * 基于上述例子，输入如下：
     *
     * equations(方程式) = [ ["a", "b"], ["b", "c"] ],
     * values(方程式结果) = [2.0, 3.0],
     * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
     */
    private static class Solution3 {
        // 并查集解法，解法来自leetcode，通过递归找到其下一个连接点，依次乘以当前放大的倍数，即val * ratio[0]/ratio[1]
        public static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
            Map<String, String> parents = new HashMap<>();
            Map<String, Double> radio = new HashMap<>();

            for (int i = 0; i < equations.length; i ++) {
                union(parents, radio, equations[i][0], equations[i][1], values[i]);
            }

            double[] res = new double[queries.length];
            for (int i = 0; i < queries.length; i ++) {
                // 集合中不存在或者两个字符串不属于同一个集合
                if (!parents.containsKey(queries[i][0]) || !parents.containsKey(queries[i][1])
                        || !find(parents, queries[i][0], radio).equals(find(parents, queries[i][1], radio))) {
                    res[i] = -1.0;
                } else {
                    res[i] = radio.get(queries[i][0]) / radio.get(queries[i][1]);
                }
            }
            return res;
        }

        private static void union(Map<String, String> parents, Map<String, Double> radio, String word1, String word2, double val) {
            // 初始化并查集，将新加入的元素的权重置为1，
            // 假如b / c = 3.0，b / d = 4.0，
            // pa:b->c，ra:b->3，c->1，
            // pa:b->c，c->d，ra:b->3，c->4/3，d->1
            // val * radio.get(word2) / radio.get(word1)是放大倍数
            if (!parents.containsKey(word1)) {
                parents.put(word1, word1);
                radio.put(word1, 1.0);
            }
            if (!parents.containsKey(word2)) {
                parents.put(word2, word2);
                radio.put(word2, 1.0);
            }

            String p1 = find(parents, word1, radio);
            String p2 = find(parents, word2, radio);

            parents.put(p1, p2);
            radio.put(p1, val * radio.get(word2) / radio.get(word1));
        }

        private static String find(Map<String, String> parents, String word , Map<String, Double> radio) {
            if (word.equals(parents.get(word))) {
                return word;
            }

            String parent = parents.get(word);
            String grandPa = find(parents, parent, radio);

            // parents里存放的是链路，即当前字符串指向的最后一个字符串
            parents.put(word, grandPa);
            System.out.println(word+ "- >" +grandPa);
            // 当前字符串的倍数与其相连字符串倍数的乘积，即为新的倍数
            radio.put(word, radio.get(word) * radio.get(parent));
            System.out.println(word+ "- >" +(radio.get(word)));

            return grandPa;
        }

        public static void main(String[] args) {
            calcEquation(new String[][]{{"b","c"},{"b","d"}}, new double[]{3.0,4.0},new String[][]{{"b","d"}});
        }
    }
}
