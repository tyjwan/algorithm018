package Algorithm.leetcode.leetcode.editor.cn;
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 例如，上图是一个7 x 3 的网格。有多少可能的路径？ 
//
// 
//
// 示例 1: 
//
// 输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
// 
//
// 示例 2: 
//
// 输入: m = 7, n = 3
//输出: 28 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 10 ^ 9 
// 
// Related Topics 数组 动态规划 
// 👍 754 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class unique-paths {
    // 题目链接：https://leetcode-cn.com/problems/unique-paths/
    public int uniquePaths(int m, int n) {
//        return func1(m, n);
        return func2(m, n);
    }

    /**
     * 思路二：一维dp
     *
     * @param m 行数
     * @param n 列数
     * @return 不同路径数
     */
    private int func2(int m, int n) {
        // 1. 分治子问题：每一个格子实际上能够有的路径数就是他左边的路径数加上他上面的路径数，因为他只有可能从左边或者上边走下来。
        // 2. 定义状态数组：dp[i],表示当前行第i列的格子的不同路径数目
        // 3. DP方程：dp[i] = dp[i - 1] + dp[i]

        // 申请dp数组
        int[] dp = new int[n];

        // 将第一行的每一列初始化为1
//        Arrays.fill(dp, 1);

        // 将第一行的第一列初始化即可
        dp[0] = 1;

        // 循环dp
        for (int i = 0; i < m; i++) {
            // dp[0]永远表示的第一列的值，所以不用改变
            for (int j = 1; j < n; j++) {
                // 这里的dp[j - 1]就相当于左边的不同路径，这里右边的dp[j]就相当于上一层第j列的不同路径
                dp[j] = dp[j - 1] + dp[j];
            }
        }

        return dp[n - 1];
    }

    /**
     * 思路一：二维dp
     *
     * @param m 行数
     * @param n 列数
     * @return 不同路径数
     */
    private int func1(int m, int n) {
        // 1. 分治子问题：每一个格子实际上能够有的路径数就是他左边的路径数加上他上面的路径数，因为他只有可能从左边或者上边走下来。
        // 2. 定义状态数组：dp[i][j],表示第i行第j列的格子的不同路径数目
        // 3. DP方程：dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

        // 申请dp数组
        int[][] dp = new int[m][n];

        // 初始化第一行，第一行所有的格子都只有一条路径
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        // 初始化第一列，第一列所有的格子也只有一条路径
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }

        // 循环进行dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
