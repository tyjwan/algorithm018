package Algorithm.leetcode.leetcode.editor.cn;
//给你一个整数数组 nums ，和一个整数 target 。
//
// 该整数数组原本是按升序排列，但输入时在预先未知的某个点上进行了旋转。（例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]
// ）。 
//
// 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// nums 肯定会在某个点上旋转 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 二分查找 
// 👍 1062 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class search-in-rotated-sorted-array {
    // 题目链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/

    public int search(int[] nums, int target) {
        /**
         * 方法一：二分法
         *
         * 时间复杂度：O(logN)
         * 空间复杂度：O(1),没有用到额外空间
         */

        // 得到数组的长度
        int len = nums.length;
        // 特殊条件判断
        if (len == 0) {
            return -1;
        }

        // 特殊条件判断
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }

        // 定义左边界，有边界，初始条件下当然就为0，和nums.length-1，也就是数组的初始位置和终止位置
        int left = 0, right = len - 1;

        // 当左边界小于有边界时继续循环（这就是二分模板记住即可）
        while (left <= right) {
            // 算出中间节点的下标，这里用变成left + (right - left) / 2是为了防止left+right超出范围
            // 用移位操作是因为计算机对于位运算更友好，更快
            int mid = left + ((right - left) >> 1);
            // 如果中间元素刚好等于目标值，直接返回下标mid
            if (nums[mid] == target) {
                return mid;
            }

            // 否则，我们要做其他判断，由于这个数组是一个旋转排序数组，所以我们要确定哪边是有序的
            // 如果左边是有序的
            if (nums[0] <= nums[mid]) {
                // 并且target正好在nums[0]---nums[mid]之间，那么我们应该在[0, mid - 1)区间去继续寻找。更新right即可
                // 否则，我们就只能到右边的区间[mid + 1, right)去寻找
                if (nums[mid] > target && nums[0] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 如果左边不是有序的，那么右边就是有序的
                // 如果目标值正好在（mid, len - 1]区间上, 那么我们要去右边继续寻找
                // 否则，我们要去左边找
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        // 走到这里还没返回，证明就是没找到
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
