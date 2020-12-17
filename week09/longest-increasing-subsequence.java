package Algorithm.leetcode.leetcode.editor.cn;
//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 二分查找 动态规划 
// 👍 1217 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/
    public int lengthOfLIS(int[] nums) {
        // 思路一：动态规划
        // 定义状态数组：dp[i], 代表第i个位子结尾的最长子序列的长度
        // 状态转移方程：如果nums[i] > nums[i - 1] , dp[i] = max(dp[0...(i - 1)] + 1。否则，dp[i] = 1;
        // 时间复杂度: O(N^2),两层for循环
        // 空间复杂度：O(N), 使用了一个一维数组
//        return func1(nums);

        // 思路二：贪心 + 二分
        // 动态规划说到底就是纯暴力，所以是N^2的时间复杂度，那么我们运用贪心保证一个数组d是递增的
        return func2(nums);
    }

    private int func2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] d = new int[n + 1];
        d[len] = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = l + ((r - l) >> 1);
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                // pos这时就记录了最右边的比nums[i]小的位置
                d[pos + 1] = nums[i];
            }
        }

        return len;
    }

    private int func1(int[] nums) {
        // 得到数组的长度
        int len = nums.length;
        // 定义最终的结果，也就是最长的上升序列
        int res = 0;
        // 申请一维的dp数组
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
