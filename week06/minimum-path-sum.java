package Algorithm.leetcode.leetcode.editor.cn;
//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 721 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class 最小路径和 {
    // 题目链接：https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-by-leetcode-solution/
    public int minPathSum(int[][] grid) {
//        return func1(grid);
        return func2(grid);
    }

    /**
     * 方法二：一维dp
     *
     * @param grid 原始二维矩阵
     * @return 返回最小路径和
     */
    private int func2(int[][] grid) {
        // 拿到的矩阵的行数
        int row = grid.length;
        // 拿到矩阵的列数
        int col = grid[0].length;

        // 开dp数组
        int[] dp = new int[col];

        // 初始化dp
        dp[0] = grid[0][0];
        for (int i = 1; i < col; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }

        // 循环进行dp
        for (int i = 1; i < row; i++) {
            // 每次先初始化第一列，因为第一列一定是前一行的第一列加上当前行的第一列
            dp[0] += grid[i][0];
            for (int j = 1; j < col; j++) {
                // 现在的dp[j - 1]就是真正的当前行的前一列的最小路径和，而dp[j]现在表示的是上一行当前列的最小路径和，更新过后才变成当前行当前列的最小路径和。
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }

        return dp[col - 1];
    }

    /**
     * 方法一：二维dp
     *
     * @param grid 原始二维矩阵
     * @return 返回最小路径
     */
    private int func1(int[][] grid) {
        // 拿到的矩阵的行数
        int row = grid.length;
        // 拿到矩阵的列数
        int col = grid[0].length;

        // 开dp数组
        int[][] dp = new int[row][col];

        // 循环进行dp
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 1. 如果i,j都是0，证明是左上角第一个元素，那它的最小路径和就是他自己所在格子的数字本身
                // 2. 如果i为0，j不为0，证明在第一行，而第一行非左上角元素的最小路径和只取决于这一行的前一个元素（因为第一行的他只能从他左边的元素走过来）
                // 3. 如果i不为0，j为0，证明在第一列，而第一列非左上角元素的最小路径和只取决于这一列的前一个元素。
                // 4. 如果i，j都不为0，那么最小路径和就是左边的格子和上面的格子的最小值加上当前格子的数值
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }

        return dp[row - 1][col - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
