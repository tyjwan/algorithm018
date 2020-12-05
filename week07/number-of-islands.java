package Algorithm.leetcode.leetcode.editor.cn;
//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 852 👎 0


//leetcode submit region begin(Prohibit modification and deletion)



class Solution {
    // 自定义并查集
    class UnionFind {
        // 连通分量的个数
        private int count;
        // parent数组
        private int[] parent;

        public int getCount() {
            return count;
        }

        // 构造函数，用于初始化并查集，初始状态连通分量个数（count）就是传进来的n的值
        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            // 初始化将每个元素的parent指向自己即可
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * 找到某个元素的领头元素
         * @param n 要找n的领头元素
         * @return 返回领导元素值
         */
        public int find(int n) {
            int p = n;
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            // 下面是状态压缩
            while (n != parent[n]) {
                int temp = parent[n];
                parent[n] = p;
                n = temp;
            }
            return p;
        }

        /**
         * 合并连通分量
         * @param x 找到x的领头元素
         * @param y 找到y的领头元素
         */
        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);

            if (rootx == rooty) {
                return;
            }

            parent[rootx] = rooty;
            // 合并之后连通分量-1
            count--;
        }
    }
    // 题目链接：https://leetcode-cn.com/problems/number-of-islands/

    // 定义返回结果
    int res = 0;
    private int row;
    private int col;
    public int numIslands(char[][] grid) {
//        return func1(grid);
        // 边界条件
        if (grid[0].length == 0) {
            return 0;
        }

        row = grid.length;
        col = grid[0].length;

        // 记录空地的数量
        int spaces = 0;
        UnionFind unionFind = new UnionFind(row * col);
        // 这里的方向变成了两个方向，其实我们只用从左上扫描到右下，也就是每次遍历只扫描右边元素和下面元素即可。
        // 因为假如左边元素或者上面元素和他是一个岛屿，我们是不会错过的，我们一定在上一次或者上一行遍历就已经将当前
        // 的元素与之加入到一个集合中去了。
        int[][] directions = {{1, 0}, {0, 1}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 如果是空地
                if (grid[i][j] == '0') {
                    // 则空地数量加一
                    spaces++;
                } else {
                    // 否则，证明当前行列代表的是岛屿，那么我们就要向右向下探测是否同样是岛屿
                    for (int[] direction : directions) {
                        int newx = i + direction[0];
                        int newy = j + direction[1];
                        // 如果满足条件并且是岛屿，将其和之前的岛屿加入到一个集合中
                        if (newx < row && newy < col && grid[newx][newy] == '1') {
                            unionFind.union(getIndex(i, j), getIndex(newx, newy));
                        }
                    }
                }
            }
        }
        return unionFind.getCount() - spaces;
    }

    private int getIndex(int i, int j) {
        return i * col + j;
    }

    private int func1(char[][] grid) {
        /**
         * 方法一：递归
         *
         * 时间复杂度：
         * 空间复杂度：
         */

        // 判断边界条件
        if (grid == null || grid.length == 0) {
            return res;
        }

        // 用m, n记录二维数组的行数和列数，便于后面的遍历
        int m = grid.length;
        int n = grid[0].length;

        // 遍历二维数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ++res;
                    // 进行递归遍历将整个岛屿从1变为0，以免重复的发现相同的岛屿
                    dfs(i, j, grid);
                }
            }
        }

        return res;
    }

    public void dfs(int i, int j, char[][] grid) {
        // dfs里面也需要拿到二维数组的行数和列数用以判断递归出口
        int m = grid.length;
        int n = grid[0].length;
        // 递归出口，如果行数比0小；行数大于等于数组行数；列数比0小，列数大于等于数组列数；或者当前位置的字符是0，直接return
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }

        // 程序走到这里证明grid[i][j]一定是‘1’，所以我们先将这个值改成0，然后再进行递归
        grid[i][j] = '0';
        // 递归遍历他的上面
        dfs(i, j - 1, grid);
        // 递归遍历他的下面
        dfs(i, j + 1, grid);
        // 递归遍历他的左边
        dfs(i - 1, j, grid);
        // 递归遍历他的右边
        dfs(i + 1, j, grid);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
