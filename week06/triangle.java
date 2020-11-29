package Algorithm.leetcode.leetcode.editor.cn;
//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 例如，给定三角形： 
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。 
//
// 
//
// 说明： 
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。 
// Related Topics 数组 动态规划 
// 👍 647 👎 0


import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/triangle/description/
    public int minimumTotal(List<List<Integer>> triangle) {
        return func1(triangle);


    }

    /**
     * 思路一：一维dp
     *
     * @param triangle 三角形列表
     * @return 最小路径和
     */
    private int func1(List<List<Integer>> triangle) {
        // 1. 分治子问题，找重复性。每一层除了第一个节点和最后一个节点，其他节点都是可以由上一层的第i个和第i-1个走过来。这就是重复性
        // 2. 定义状态数组
        // 3. DP方程：dp[i] = MIN {dp[i - 1], dp[i]} + triangle[j][i]

        /**
         * 时间复杂度: O(N^2），双层for循环，所以空间复杂度为O(N^2)
         * 空间复杂度：O(N), dp一维数组所占空间为三角形的行数
         */

        // 获取三角形的行数, 行数和最大的列数其实是相等的
        int row = triangle.size();
        // 申请dp数组
        int[] dp = new int[row];

        // 第一层只有一个元素，直接初始化dp[0]即可
        dp[0] = triangle.get(0).get(0);

        // 从第二层开始遍历
        for (int i = 1; i < row; i++) {
//            int col = triangle.get(i).size();
            // 这里需要注意必须从后往前遍历，否则我们前面的先更新了，就会影响后面的值
            // 特殊处理三角形每一层最后一个
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                // 非三角形最后一个和第一个的情况，最小路径和是取决于上一层的第j-1个和第j个
                // 这里的dp[j - 1]就是上一层的第j-1个位置的数，dp[j]就是上一层的第j个位置的数
                dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
            // 特殊处理三角形每一层的第一个
            dp[0] += triangle.get(i).get(0);
        }

//        Arrays.sort(dp);

        // 下面这段代码是为了找出最小值
        int minNum = dp[0];
        for (int i = 1; i < row; i++) {
            minNum = Math.min(minNum, dp[i]);
        }
        return minNum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
