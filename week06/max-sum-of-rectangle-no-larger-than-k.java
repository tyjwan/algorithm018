package Algorithm.leetcode.leetcode.editor.cn;
//给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
//
// 示例: 
//
// 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
//输出: 2 
//解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 说明： 
//
// 
// 矩阵内的矩形区域面积必须大于 0。 
// 如果行数远大于列数，你将如何解答呢？ 
// 
// Related Topics 队列 二分查找 动态规划 
// 👍 137 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/
    public int maxSumSubmatrix(int[][] matrix, int k) {
        // 找重复性：你会发现，以[i][j]作为右下角元素的最大矩形的数字和与[i-1][j],[i][j-1],[i-1][j-1]相关
        // 定义状态数组：dp[i][j],代表以[i][j]这个坐标所代表的元素作为右下角的最大矩形的数字和
        // DP方程：dp[i][j] = dp[i-1][j]+dp[i,j-1]-dp[i-1][j-1] + matrix[i][j]


        // 得到矩阵的行数
        int row = matrix.length;
        // 得到矩阵的列数
        int col = matrix[0].length;

        // 定义最大值
        int max = Integer.MIN_VALUE;

        // 循环dp
        for (int p = 1; p <= row; p++) {
            for (int q = 1; q <= col; q++) {
                // 申请dp数组
                int[][] dp = new int[row + 1][col + 1];
                for (int i = p; i <= row; i++) {
                    for (int j = q; j <= col; j++) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                        if (dp[i][j] <= k && dp[i][j] > max) {
                            max = dp[i][j];
                        }
                    }
                }
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
