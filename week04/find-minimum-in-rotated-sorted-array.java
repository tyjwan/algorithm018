package Algorithm.leetcode.leetcode.editor.cn;
//假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
//
// 请找出其中最小的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -5000 <= nums[i] <= 5000 
// nums 中的所有整数都是 唯一 的 
// nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转 
// 
// Related Topics 数组 二分查找 
// 👍 295 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class find-minimum-in-rotated-sorted-array {
    // 题目链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
    public int findMin(int[] nums) {
        /**
         * 方法一：二分法
         *
         * 时间复杂度：O(logN), N即为数组元素的个数
         * 空间复杂度：O(1)
         */

        if (nums.length == 1) {
            return nums[0];
        }

        // 定义左边界，有边界，初始条件下当然就为0，和nums.length-1，也就是数组的初始位置和终止位置
        int left = 0, right = nums.length - 1, len = right;

        // 筛选出数组本身有序的情况
        if (nums[right] > nums[left]) {
            // 数组本身有序的情况下，第一个元素即为数组的最小元素
            return nums[left];
        }

        // 当左边界小于有边界时继续循环（这就是二分模板记住即可）
        while (left <= right) {
            // 算出中间节点的下标，这里用变成left + (right - left) / 2是为了防止left+right超出范围
            // 用移位操作是因为计算机对于位运算更友好，更快
            int mid = left + ((right - left) >> 1);

            // 这就是边界点, 边界点就是数组的值不随下标的增大而增大的那两个点
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            // 如果左边是有序的
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
