package Test;

import java.util.*;

/**
 * 广度优先搜索练习
 * Created by houjue on 2019-02-02.
 */
public class BFSTest {
    /**
     * 200. 岛屿的个数
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，
     * 并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
     *
     * 示例 1:
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     * 输出: 1
     *
     * 示例 2:
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * 输出: 3
     */
    private static class Solution {
        private static int m;
        private static int n;

        // 思路，染色法（深度优先实现），将1周围的1包括自己均涂为0，直至周围没有0为止
        private static int numIslands(char[][] grid) {
            if (grid.length == 0) {
                return 0;
            }

            m = grid.length;
            n = grid[0].length;
            int i = 0;
            int j = 0;
            int count = 0;
            while (i < m && j < n) {
                if (grid[i][j] == '1') {
                    count ++;
                    drawZero(grid, i, j);
                }
                if (j == n - 1) {
                    i ++;
                    j = 0;
                } else {
                    j ++;
                }
            }
            return count;
        }

        private static void drawZero(char[][] grid, int i, int j) {
            // 终止条件
            if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') {
                return;
            }
            // 将自己和上下左右均涂为0
            grid[i][j] = '0';
            // 上
            drawZero(grid, i - 1, j);
            // 下
            drawZero(grid, i + 1, j);
            // 左
            drawZero(grid, i, j - 1);
            // 右
            drawZero(grid, i, j + 1);
        }

        // 解法2，染色法（广度优先实现）
        private static int numIslands1(char[][] grid) {
            if (grid.length == 0) {
                return 0;
            }

            m = grid.length;
            n = grid[0].length;

            int count = 0;
            LinkedList<Integer> queue = new LinkedList<>();
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i ++) {
                for (int j = 0; j < n; j ++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    visited[i][j] = true;
                    if (grid[i][j] == '1') {
                        count ++;
                        queue.add(i);
                        queue.add(j);
                        while (!queue.isEmpty()) {
                            int row = queue.pop();
                            int col = queue.pop();
                            grid[row][col] = '0';
                            // 上
                            if (row - 1 >= 0 && grid[row - 1][col] == '1' && !visited[row - 1][col]) {
                                visited[row - 1][col] = true;
                                queue.add(row - 1);
                                queue.add(col);
                            }
                            // 下
                            if (row + 1 < m && grid[row + 1][col] == '1' && !visited[row + 1][col]) {
                                visited[row + 1][col] = true;
                                queue.add(row + 1);
                                queue.add(col);
                            }
                            // 左
                            if (col - 1 >= 0 && grid[row][col - 1] == '1' && !visited[row][col - 1]) {
                                visited[row][col - 1] = true;
                                queue.add(row);
                                queue.add(col - 1);
                            }
                            // 右
                            if (col + 1 < n && grid[row][col + 1] == '1' && !visited[row][col + 1]) {
                                visited[row][col + 1] = true;
                                queue.add(row);
                                queue.add(col + 1);
                            }
                        }
                    }
                }
            }
            return count;
        }


        public static void main(String[] args) {
            System.out.println(numIslands1(new char[][]{
                     {'1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','0','1','0','1','1'}
                    ,{'0','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','0'}
                    ,{'1','0','1','1','1','0','0','1','1','0','1','1','1','1','1','1','1','1','1','1'}
                    ,{'1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}
                    ,{'1','0','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}
                    ,{'1','0','1','1','1','1','1','1','0','1','1','1','0','1','1','1','0','1','1','1'}
                    ,{'0','1','1','1','1','1','1','1','1','1','1','1','0','1','1','0','1','1','1','1'}
                    ,{'1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','1','1'}
                    ,{'1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1'}
                    ,{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}
                    ,{'0','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1'}
                    ,{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}
                    ,{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}
                    ,{'1','1','1','1','1','0','1','1','1','1','1','1','1','0','1','1','1','1','1','1'}
                    ,{'1','0','1','1','1','1','1','0','1','1','1','0','1','1','1','1','0','1','1','1'}
                    ,{'1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','0'}
                    ,{'1','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','0'}
                    ,{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}
                    ,{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}
                    ,{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}
            }));
            System.out.println(numIslands1(new char[][]{
                    {'1'}}));
        }
    }

    /**
     * 547. 朋友圈
     * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
     *
     * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     *
     * 示例 1:
     *
     * 输入:
     * [[1,1,0],
     *  [1,1,0],
     *  [0,0,1]]
     * 输出: 2
     * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
     * 第2个学生自己在一个朋友圈。所以返回2。
     * 示例 2:
     *
     * 输入:
     * [[1,1,0],
     *  [1,1,1],
     *  [0,1,1]]
     * 输出: 1
     * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
     */
    private static class Solution1 {
        private static int n;

        // 思路，并查集
        public static int findCircleNum(int[][] grid) {
            if (grid.length == 0) {
                return 0;
            }

            n = grid.length;
            UnionFind uf = new UnionFind(grid, n);
            for (int i = 0; i < n; i ++) {
                for (int j = i; j < n; j ++) {
                    if (grid[i][j] == 1) {
                        // 判断左边和上边是否有1，即朋友关系，有则进行union
                        // 上方
                        for (int k = i - 1; k >= 0; k --) {
                            if (grid[k][j] == 1) {
                                // 只要找到最近的一个朋友即可
                                uf.union(i * n + j, k * n + j);
                                break;
                            }
                        }
                        // 左边
                        for (int k = j - 1; k >= i; k --) {
                            if (grid[i][k] == 1) {
                                uf.union(i * n + j, i * n + k);
                                break;
                            }
                        }
                    }
                }
            }

            return uf.count;
        }

        // 定义一个并查集
        static class UnionFind {
            // 每个朋友圈的头目
            private int friends[];
            // 朋友圈的数量
            private int count;

            public UnionFind(int[][] grid, int n) {
                count = 0;
                // 有多少人最多多少个朋友圈
                friends = new int[n * n];
                for (int i = 0; i < n; i ++) {
                    // 走一个半区
                    for (int j = i; j < n; j ++) {
                        if (grid[i][j] == 1) {
                            // 将二维坐标转为一维坐标
                            friends[i * n + j] = i * n + j;
                            count ++;
                        }
                    }
                }
            }

            private int find(int i) {
                if (i != friends[i])
                    friends[i] = find(friends[i]);
                return friends[i];
            }

            public void union(int i, int j) {
                int friendI = find(i);
                int friendJ = find(j);
                if (friendI == friendJ) {
                    return;
                }
                if (friendI < friendJ) {
                    friends[friendJ] = friendI;
                } else {
                    friends[friendI] = friendJ;
                }
                count --;
            }
        }

        public static void main(String[] args) {
            System.out.println(findCircleNum(new int[][]{
                     {1,0,0,1}
                    ,{0,1,1,0}
                    ,{0,1,1,1}
                    ,{1,0,1,1}
            }));

            System.out.println(findCircleNum(new int[][]{
                     {1,1,1}
                    ,{1,1,1}
                    ,{1,1,1}
            }));
            System.out.println(findCircleNum(new int[][]{
                     {1,1,1,1,1}
                    ,{1,1,1,1,1}
                    ,{1,1,1,1,1}
                    ,{1,1,1,1,1}
                    ,{1,1,1,1,1}
            }));

            System.out.println(findCircleNum(new int[][]{
                     {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0}
                    ,{0,1,1,0,0,0,0,0,0,0,0,0,0,1,0}
                    ,{0,1,1,0,0,0,0,0,0,0,0,1,0,0,1}
                    ,{0,0,0,1,0,1,0,0,1,0,0,0,0,1,0}
                    ,{0,0,0,0,1,0,0,0,0,0,0,1,0,0,0}
                    ,{0,0,0,1,0,1,0,0,0,0,0,1,0,0,0}
                    ,{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0}
                    ,{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0}
                    ,{1,0,0,1,0,0,0,0,1,1,1,0,0,1,0}
                    ,{0,0,0,0,0,0,0,0,1,1,0,1,1,0,0}
                    ,{0,0,0,0,0,0,0,0,1,0,1,1,0,0,0}
                    ,{0,0,1,0,1,1,0,0,0,1,1,1,0,0,0}
                    ,{0,0,0,0,0,0,0,0,0,1,0,0,1,0,1}
                    ,{0,1,0,1,0,0,0,0,1,0,0,0,0,1,0}
                    ,{0,0,1,0,0,0,0,0,0,0,0,0,1,0,1}
            }));
        }
    }
}
