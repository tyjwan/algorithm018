package Algorithm.leetcode.leetcode.editor.cn;
//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组 
// 👍 749 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class jump-game-ii {
    // 题目链接：https://leetcode-cn.com/problems/jump-game-ii/
    public int jump(int[] nums) {
        /**
         * 方法一：从后往前
         *
         * 思路：最后一个endpoints，我们往前找最远的跳一步能够到他这里的点，以这个点的下标作为新的endpoints，以此类推
         * 时间复杂度：O(N^2), 两层循环
         * 空间复杂度：O(1), 没有用到额外空间
         */

        // 第一个endpoints就是数组最后一个元素
        int endpoints = nums.length - 1;
        // 用一个变量表示步数
        int steps = 0;

        // 当endpoints走到下标为0的位置时，结束循环了
        while (endpoints > 0) {
            // 遍历的目的是为了找到离endpoints最远的点的下标
            for (int i = 0; i < nums.length; i++) {
                // 如果当前元素的值加上当前的下标就能够到达endpoints
                if (nums[i] + i >= endpoints) {
                    // 那么更新endpoints
                    endpoints = i;
                    // 更新步数
                    ++steps;
                    // 结束此次循环
                    break;
                }
            }
        }

        return steps;


        /**
         * 方法二：从前往后
         *
         * 思路：既然要贪心，那么当我在当前位置每个可跳的位置里面选一个位置，我怎么选呢，我选择我跳过去之后，他能够跳更远的的那个点。也就是nums[i]最大的那个点
         * 时间复杂度：O(N),一次遍历数组即可
         * 空间复杂度：O(1),并没有用到额外的数组空间
         */

        // 用一个变量表示步数
        int steps = 0;
        // 当前的边界
        int endpoints = 0;
        // 元素所能够访问的最大位置
        int maxPosition = 0;

        // 这儿为什么不访问最后一个元素？
        // 在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了。(题目明确说明总是可以跳到最后一个位置）
        for (int i = 0; i < nums.length - 1; i++) {
            // 更新元素所能够访问的最大位置
            maxPosition = Math.max(maxPosition, nums[i] + i);

            // 如果到达了一个边界点，那么我们就要更新这个边界点了（也就是要往下继续跳了），并且步数加一
            if (i == endpoints) {
                endpoints = maxPosition;
                // 跳跃的步数加一
                ++steps;
            }
        }

        return steps;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
