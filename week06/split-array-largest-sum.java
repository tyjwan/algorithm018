package Algorithm.leetcode.leetcode.editor.cn;
//给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
//
// 注意: 
//数组长度 n 满足以下条件: 
//
// 
// 1 ≤ n ≤ 1000 
// 1 ≤ m ≤ min(50, n) 
// 
//
// 示例: 
//
// 
//输入:
//nums = [7,2,5,10,8]
//m = 2
//
//输出:
//18
//
//解释:
//一共有四种方法将nums分割为2个子数组。
//其中最好的方式是将其分为[7,2,5] 和 [10,8]，
//因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
// 
// Related Topics 二分查找 动态规划 
// 👍 382 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/split-array-largest-sum/
    public int splitArray(int[] nums, int m) {
        // 时间复杂度：O(N^2 * M)，3层for循环
        // 空间复杂度：O(N^2),二维dp数组
        return func1(nums, m);
    }

    private int func1(int[] nums, int m) {
        // 1：找重复性
        // 2. 定义状态数组：f[i][j],表示将数组的前i个数分割为j段所能够得到的最大连续子数组和的最小值
        // 3. DP方程：f[i][j] = min{max{f[k][j-1], sub[k+1][i]}},0<=k<=i-1

        // 得到数组的长度
        int len = nums.length;
        // 定义一个前缀和，preSum[i]=sum[0...i)
        int[] preSum = new int[len + 1];
        // 初始化前缀和数组
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        // 申请dp数组
        int[][] dp = new int[len][m + 1];
        // 初始化dp数组，因为要找最小值，所以初值赋值一个很大的值即可
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 分割数为1的情况，即不分割的情况，所有的前缀和就是依次的状态值
        for (int i = 0; i < len; i++) {
            dp[i][1] = preSum[i + 1];
        }

        // 从分割数为2开始递推
        for (int k = 2; k <= m; k++) {
            // i是为了固定有多少个数字参与这件事情，层层网上累。分割成k段，至少要有k个元素吧。而i的下标从0开始，
            // 0就是表示有一个元素的情况，1当然就是代表右两个元素的情况
            for (int i = k - 1; i < len; i++) {
                // j表示第k - 1个区间的最后一个元素的下标，最小值是k-2，因为极端最小就是让前面的每一个数组占一段。最大值是len - 2,因为最后一个位置len-1极端情况你就让他作为第k段。前面的所有为k - 1段。
                // 比如，k=2时，第k-1（第1个）个区间的最后一个元素的下标是0，k = 3时，第k-1个（第2）个区间的最后一个元素是1，也就是nums[0],nums[1]分别为两段
                for (int j = k - 2; j < i; j++) {
                    dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k - 1], preSum[i + 1] - preSum[j + 1]));
                }
            }
        }

        return dp[len - 1][m];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
