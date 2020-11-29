package Algorithm.leetcode.leetcode.editor.cn;
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 2327 👎 0 最大子序和


//leetcode submit region begin(Prohibit modification and deletion)
class maximum-subarray {
    // 题目链接：https://leetcode-cn.com/problems/maximum-subarray/
    public int maxSubArray(int[] nums) {
        // 暴力法，时间复杂度n
//        int tmp = nums[0];
//        int max = tmp;
//
//        for (int i = 1; i < nums.length; i++) {
//            if (tmp + nums[i] > nums[i]) {
//                max = Math.max(max, tmp + nums[i]);
//                tmp += nums[i];
//            } else {
//                max = Math.max(max, nums[i]);
//                max = Math.max(max, tmp);
//                tmp = nums[i];
//            }
//        }
//
//        return max;

        // 动态规划
//        int pre = 0;
//        int max = nums[0];
//
//        for (int every : nums) {
//            pre = Math.max(every, pre + every);
//            max = Math.max(max, pre);
//        }
//
//        return max;

        // 1. 找重复性，不难发现，每遍历到一个地方，只有两种可能，一种就是当前元素和之前的截断，一种就是不截断
        // 2. 定义状态数组。dp[i],dp[i]表示到第i个位置为止的最大子序和
        // 3. DP方程：dp[i] = MAX{dp[i - 1], 0} + nums[i],这里如果dp[i-1]是小于0的，那他无论如何都会拖我当前nums[i]的后腿，我干嘛要加上他对不对
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            max = Math.max(dp[i], max);
        }

        return max;

        // 分治法
//        return maxSubArrayDivideWithBorder(nums, 0, nums.length-1);
    }

    private int maxSubArrayDivideWithBorder(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        int mid = start + (end - start)/2;
        // 计算左边序列的最大值
        int leftMax = maxSubArrayDivideWithBorder(nums, 0, mid);
        // 计算右边序列的最大值
        int rightMax = maxSubArrayDivideWithBorder(nums, mid + 1, end);

        // 计算最大值横跨左边和右边序列情况的最大值

        // 先计算横跨左边和右边序列中左边的最大值
        int leftCrossMax = Integer.MIN_VALUE;
        int leftCrossSum = 0;
        for (int i = mid; i >= 0; i--) {
            leftCrossSum += nums[i];
            leftCrossMax = Math.max(leftCrossMax, leftCrossSum);
        }

        // 再计算横跨左边和右边序列中右边的最大值
        int rightCrossMax = Integer.MIN_VALUE;
        int rightCrossSum = 0;
        for (int i = mid + 1; i <= end; i++) {
            rightCrossSum += nums[i];
            rightCrossMax = Math.max(rightCrossMax, rightCrossSum);
        }

        // 这个时候就可以计算横跨情况下的最大值了
        int crossMax = leftCrossMax + rightCrossMax;
        return Math.max(crossMax, Math.max(leftMax, rightMax));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
