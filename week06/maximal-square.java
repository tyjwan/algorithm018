package Algorithm.leetcode.leetcode.editor.cn;
//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
//
// 
//
// 示例： 
//
// 
//输入：
//matrix = [["1","0","1","0","0"],
//          ["1","0","1","1","1"],
//          ["1","1","1","1","1"],
//          ["1","0","0","1","0"]]
//
//输出：4 
// Related Topics 动态规划 
// 👍 622 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/maximal-square/
    public int maximalSquare(char[][] matrix) {
        // 时间复杂度：O(N^2),两层for循环
        // 空间复杂度，O(N^2),开了二维数组进行dp
        return func1(matrix);
    }

    /**
     * 思路一：二维dp
     *
     * @param matrix 原始矩阵
     * @return 最大正方形面积
     */
    private int func1(char[][] matrix) {
        // 1. 找重复性。可以发现，矩阵中每一个元素的最大正方形都和他的长宽有关，而长宽直接和他左边的元素，上面的元素，斜对角上的元素相关
        // 2. 定义状态数组dp[i][j],代表以第i行第j列元素为右下角的能够形成的最大正方形的边的长度。
        // 3. DP方程：dp[i][j] = Min{dp[i-1][j], dp[i][j-1], dp[i-1][j-1]} + 1

        // 边界条件判断
        if (matrix.length == 0) {
            return 0;
        }

        // 获取二维矩阵的长宽。
        int row = matrix.length;
        int col = matrix[0].length;

        // 申请DP数组
        int[][] dp = new int[row + 1][col + 1];

        // 记录最大值
        int max = 0;

        // 循环dp
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // 如果当前这个元素为0
                if (matrix[i - 1][j - 1] == '0') {
                    // 则以他为右下角所能围成的正方形最大面积就是0，将dp数组置为0
                    dp[i][j] = 0;
                } else { // 否则，用dp方程进行求解即可
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        return max * max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
