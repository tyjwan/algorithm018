package Algorithm.leetcode.leetcode.editor.cn;
//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组 
// 👍 919 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class jump-game {
    // 题目链接：https://leetcode-cn.com/problems/jump-game/
    public boolean canJump(int[] nums) {
        /**
         * 方法一：贪心算法
         *
         * 时间复杂度：O(N),数组遍历一遍即可
         * 空间复杂度：O(1)，并没有使用额外空间
         */

        // 定义当前需要跳到的元素下标，初始当然就是最后一个元素的下标
        int endpoints = nums.length - 1;
        // 循环遍历，从后往前
        for (int i = nums.length - 1; i >= 0; i--) {
            // 如果当前下标所在元素的值+下标大于等于目标下标，证明只要前面的元素能够跳到当前位置，那么就一定能够跳到最后位置
            if (nums[i] + i >= endpoints) {
                // 所以将endpoint更新为i
                endpoints = i;
            }
        }
        // 假设我从后往前一共有三个endpoints
        // 如果最后endpoints为0，证明第三个也就是最后一个endp可以由第二个跳过去，第二个endp可以由第一个跳过去，
        // 那第一个就能跳到最后一个endp。第一个endp一定要为0，也就是起始位置
        // 反之，则不行
        return endpoints == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
