package Algorithm.leetcode.leetcode.editor.cn;
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 9408 👎 0


import java.util.HashMap;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class 两数之和 {
    public int[] twoSum(int[] nums, int target) {
        // 暴力法
//        int[] res = new int[2];
//
//        for (int i = 0; i <= nums.length - 2; i++) {
//            for (int j = i + 1; j <= nums.length - 1; j++) {
//                if (nums[i] + nums[j] == target) {
//                    res[0] = i;
//                    res[1] = j;
//                    return res;
//                }
//            }
//        }
//
//        return res;

        // 利用hash加速，一层for循环
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[] {map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }

        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
