package Algorithm.leetcode.leetcode.editor.cn;
//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 动态规划 
// 👍 426 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class house-robber-ii {
    // 题目链接：https://leetcode-cn.com/problems/house-robber-ii/
    public int rob(int[] nums) {
        // 时间复杂度：O(N),一层for循环即可
        // 空间复杂度：O(2N),N为房子的数量，2是代表每个房子有可能偷有可能不偷
        return func1(nums);
    }

    /**
     * 思路一：二维DP
     *
     * @param nums 原始数据
     * @return 能够偷到的最大值
     */
    private int func1(int[] nums) {
        // 1. 找重复性，也就是子问题。对于这个问题而言，关键是看前一个房子的他偷还是没偷。如果前一个房子偷了，那么当前的最大值也就是前一个房子偷到的值。
        // 如果前一个房子没偷，那么最大值就是第n - 2个房子偷到的钱加上当前房子能偷到的钱
        // 2. 定义状态数组：dp[i][0],dp[i][1], 前者代表第i个房子他没偷，后者代表第i个房子他偷了
        // 3. DP方程：dp[i][1] = dp[i - 1][0] + nums[i], dp[i][0] = Max(dp[i - 1][1], dp[i - 1][0])

        // 以上描述与打家劫舍1没有任何差别。唯一有点不一样的就是这道题是一个闭环。那么我们可以采用的方式就是强行砸开一个口子
        // 要么第一个我不偷，要么最后一个我不偷，这两种情形分别用打家劫舍1的方式算出来，取最大值即可。

        // 边界条件判断
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        // 获取数组长度
        int length = nums.length;
        // 申请dp数组
        int[][] dp = new int[length][2];

        // 最后一个不要
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        // 循环dp
        for (int i = 1; i < length - 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        // 得到第一种情况的最大值
        int firstMax = Math.max(dp[length - 2][0], dp[length - 2][1]);

        // 第一个元素不要
        dp[1][0] = 0;
        dp[1][1] = nums[1];

        // 循环dp
        for (int i = 2; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        // 得到第二种情况的最大值
        int secondMax = Math.max(dp[length - 1][0], dp[length - 1][1]);

        return Math.max(firstMax, secondMax);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
