package Algorithm.leetcode.leetcode.editor.cn;
//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组 
// 👍 720 👎 0


import netscape.security.UserTarget;

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class 旋转数组 {
    public void rotate(int[] nums, int k) {
        // 方法一：暴力法
//        for (int i = 0; i < k; i++) {
//            int pre = nums[nums.length - 1];
//            for (int j = 0; j < nums.length; j++) {
//                int temp = nums[j];
//                nums[j] = pre;
//                pre = temp;
//            }
//        }

        // 方法二：使用额外空间复杂度的数组
//        int[] help = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            help[(i + k) % nums.length] = nums[i];
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = help[i];
//        }

        // 方法三：环形替换
//        k = k % nums.length;
//        int count = 0;
//
//        for (int start = 0; count < nums.length; start++) {
//            int cur = start;
//            int pre = start;
//
//            do {
//                int next = (cur + k) % nums.length;
//                int temp = nums[next];
//                nums[next] = nums[pre];
//                nums[pre] = temp;
//                cur = next;
//                count++;
//            } while (start != cur);
//        }

        // 方法四：使用反转
        k = k % nums.length;
        nums = reverse(0, nums.length - 1, nums);
        nums = reverse(0, k - 1, nums);
        nums = reverse(k, nums.length - 1, nums);
    }

    public int[] reverse(int start, int end, int[] nums) {
        while (start < end) {
            int temp = nums[end];
            nums[end--] = nums[start];
            nums[start++] = temp;
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
