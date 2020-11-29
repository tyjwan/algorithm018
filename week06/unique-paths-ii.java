package Algorithm.leetcode.leetcode.editor.cn;
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//输出：2
//解释：
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
//
// 示例 2： 
//
// 
//输入：obstacleGrid = [[0,1],[0,0]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// m == obstacleGrid.length 
// n == obstacleGrid[i].length 
// 1 <= m, n <= 100 
// obstacleGrid[i][j] 为 0 或 1 
// 
// Related Topics 数组 动态规划 
// 👍 447 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class unique-paths-ii {
    // 题目链接：https://leetcode-cn.com/problems/unique-paths-ii/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        return func1(obstacleGrid);
        return func2(obstacleGrid);
    }

    /**
     * 思路二：二维DP
     *
     * @param obstacleGrid 障碍物矩阵
     * @return 不同路径数
     */
    private int func2(int[][] obstacleGrid) {
        // 1. 分治子问题：每一个格子实际上是由他上面的格子和左边的格子决定，障碍物的格子设置为0即可
        // 2. 定义状态数组，dp[i], 表示当前层的第i列的不同路径数
        // 3. DP方程：空地 dp[i] += dp[i - 1]  障碍物 dp[i] = 0

        // 获取矩阵的行
        int row = obstacleGrid.length;
        // 获取矩阵的列
        int col = obstacleGrid[0].length;

        // 申请dp数组
        int[] dp = new int[col];

        // 初始化第一个数据
        dp[0] = 1;

        // 循环dp
        // 由于第0行刚刚已经初始化了，所以从第一行开始遍历
        for (int i = 0; i < row; i++) {
            // 当前层的dp[0]有可能是石头，所以要判断一下,如果是0，将其赋值成0
//            if (obstacleGrid[i][0] == 1) {
//                dp[0] = 0;
//            }
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0){ // 当前格子不是障碍物，j也不大于0，证明是第一列非障碍物，这种格子我们不用做任何改变
                    // 这里的dp[j - 1]就相当于左边的不同路径，这里右边的dp[j]就相当于上一层第j列的不同路径
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }

        return dp[col - 1];
    }

    /**
     * 思路一：二维dp
     *
     * @param obstacleGrid 障碍物详情的二维矩阵
     * @return 不同路径数
     */
    private int func1(int[][] obstacleGrid) {
        // 1. 分治子问题，每一个格子实际上还是只由他上面的格子和他下面的格子决定，只不过多了障碍物，那有障碍物的格子他的不同路径数就是0即可
        // 2. 定义状态数组：dp[i][j],表示第i行第j列的不同路径数
        // 3. DP方程： 空地：dp[i][j] = dp[i][j - 1] + dp[i - 1][j]。 障碍物：dp[i][j] = 0

        // 获取矩阵的行
        int row = obstacleGrid.length;
        // 获取矩阵的列
        int col = obstacleGrid[0].length;

        // 申请dp数组
        int[][] dp = new int[row + 1][col + 1];

        // 初始化, 因为dp数组故意多申请了一行一列，多的一行一列就是为了来适配第一行第一列没有左没有上的情况的
        dp[0][1] = 1;

        // 循环dp
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // 如果有障碍物，当前格子的不同路径数为0，因为不可能有一种走法能走到石头上
                // 否则，就代表是空地，利用我们的dp方程即可
                if (obstacleGrid[i - 1][j - 1] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[row][col];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
