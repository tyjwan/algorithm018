package Algorithm.leetcode.leetcode.editor.cn;
//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 动态规划 
// 👍 1178 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class 打家劫舍 {
    // 题目链接：https://leetcode-cn.com/problems/house-robber/
    public int rob(int[] nums) {
        return func1(nums);
    }

    /**
     * 思路一：二维DP
     *
     * @param nums 原始数组
     * @return 能偷到的钱最大值
     */
    private int func1(int[] nums) {
        // 1. 找重复性，也就是子问题。对于这个问题而言，关键是看前一个房子的他偷还是没偷。如果前一个房子偷了，那么当前的最大值也就是前一个房子偷到的值。
        // 如果前一个房子没偷，那么最大值就是第n - 2个房子偷到的钱加上当前房子能偷到的钱
        // 2. 定义状态数组：dp[i][0],dp[i][1], 前者代表第i个房子他没偷，后者代表第i个房子他偷了
        // 3. DP方程：dp[i][1] = dp[i - 1][0] + nums[i], dp[i][0] = Max(dp[i - 1][1], dp[i - 1][0])

        // 边界条件判断
        if (nums.length == 0 || nums == null) {
            return 0;
        }

        // 得到数组的长度
        int length = nums.length;
        // 申请dp数组
        int[][] dp = new int[length][2];

        // 初始化
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        // 循环dp
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        return Math.max(dp[length - 1][0], dp[length - 1][1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
