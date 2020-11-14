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
class number-of-islands {
    // 题目链接：https://leetcode-cn.com/problems/number-of-islands/

    // 定义返回结果
    int res = 0;
    public int numIslands(char[][] grid) {
        /**
         * 方法一：递归
         *
         * 时间复杂度：O(MN)，也就是网格的数量
         * 空间复杂度：O(MN)，递归遍历的深度也就是网格数量
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
