package Algorithm.leetcode.leetcode.editor.cn;
//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划 
// 👍 849 👎 0


import scala.Int;

//leetcode submit region begin(Prohibit modification and deletion)
class maximum-product-subarray {
    // 题目链接：https://leetcode-cn.com/problems/maximum-product-subarray/
    public int maxProduct(int[] nums) {
        // 时间复杂度：O(N)，一层for循环
        // 空间复杂度：O(N),开了两个dp数组
//        return func1(nums);

        // 时间复杂度：O(N)，一层for循环
        // 空间复杂度：O(1),只是使用了几个变量，所以是O(1)的复杂度。
        return func2(nums);

    }

    private int func2(int[] nums) {
        // 判空，边界条件
        if (nums.length == 0) {
            return 0;
        }

        // 获取数组长度
        int length = nums.length;

        // 方法一用的数组，其实我们想想，完全可以不用数组，用变量即可做这件事情了
        int preMax = nums[0];
        int preMin = nums[0];
        int curMax = nums[0];
        int curMin = nums[0];
        int res = nums[0];

        // 循环dp
        for (int i = 1; i < length; i++) {
            curMax = Math.max(preMax * nums[i], Math.max(nums[i], preMin * nums[i]));
            curMin = Math.min(preMin * nums[i], Math.min(nums[i], preMax * nums[i]));
            res = Math.max(res, curMax);

            // 当前一次循环结束后，当前的最大值，对于下一轮而言就是preMax了
            preMax = curMax;
            // 当前一次循环结束后，当前的最小值，对于下一轮而言就是preMin了
            preMin = curMin;
        }

        return res;
    }

    /**
     * 思路一：一维DP
     *
     * @param nums 原始数组
     * @return 最大乘积
     */
    private int func1(int[] nums) {
        // 找重复性，当前的最大乘积只有两种情况，一种是前面连乘的我要保留和我当前的值乘一起，一种是我不要。而是当前的值自立门户
        // 定义状态数组：dp[i]，当前位置的最大乘积值
        // DP方程：dp[i] = Max{dp[i - 1] * nums[i], nums[i]}
        // 上面的思路是错的，因为如果当数组中只有偶数个负数的时候，最大值应该是所有的元素相乘。
        // 考虑当前位置如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样就可以负负得正，
        // 并且我们希望这个积尽可能「负得更多」，即尽可能小。如果当前位置是一个正数的话，我们更希望以它前一个位置结尾的
        // 某个段的积也是个正数，并且希望它尽可能地大。

        // 重新定义DP方程：dpMax = Max{dpMax[i - 1] * nums[i], nums[i], dpMin[i - 1] * nums[i]}
        //  dpMin = Min{dpMax[i - 1] * nums[i], nums[i], dpMin[i - 1] * nums[i]}

        // 判空，边界条件
        if (nums.length == 0) {
            return 0;
        }

        // 得到数组的长度
        int length = nums.length;
        // 申请dp数组
        int[] dpMax = new int[length];
        int[] dpMin = new int[length];

        // 初始化dp[0]
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        // 定义记录最大值的变量
        int max = dpMax[0];

        // 循环进行dp
        for (int i = 1; i < length; i++) {
            // 记录每一次的最大值最小值，这样我们就可以在最后的真正得到想要的最大值，也就是前面的负数才有可能会被后面的负数知道
            dpMax[i] = Math.max(dpMax[i - 1] * nums[i], Math.max(dpMin[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(dpMax[i - 1] * nums[i], nums[i]));
            // 每一轮当前的最大值都与整体最大值比较下，这样遍历完我们选出的就是总体最大值
            max = Math.max(dpMax[i], Math.max(dpMin[i], max));
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
