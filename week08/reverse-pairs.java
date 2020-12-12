package Algorithm.leetcode.leetcode.editor.cn;
//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 241 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/reverse-pairs/
    public int reversePairs(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }

        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int start, int end) {
        // 递归终止条件
        if (start >= end) {
            return 0;
        }

        // 计算出中间节点
        int mid = (start + end) >> 1;
        // 当前层翻转对的数量, 初始化为0
        // 但是逻辑上，他等于，左半边翻转对的数量 + 右半边翻转对的数量 + 左右合并起来的翻转对的数量
        // 举个例子说：2 1 3 4 7 8 5 6反转对的数量就等于
        //（2 1 3 4）翻转对的数量 + （7 8 5 6)翻转对的数量 + （2 1 3 4）与（7 8 5 6）之间翻转对的数量
        int cnt = 0;

        cnt = mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end);

        // 下面就是要开始merge的操作，一遍merge一边得出当前层左半边与右半边的翻转对数量
        int l = start, i = start, r = end, c = 0;
        // 临时数组装排好序的数字
        int[] temp = new int[end - start + 1];
        // 遍历看前半边和后半边到底有多少翻转对
        for (int j = mid + 1; j <= end; j++) {
            // 记录左半边从左到右有多少个比2*nums[j]小的，直到遇到比他大的
            // 因为左半边和右半边都是排好序的，当左半边遇到了一个比当前nums[j]*2大的
            // 左半边后面的也一定比当前的nums[j] * 2大，就不用再去判断了。
            while (i <= mid && nums[i] <= 2 * (long)nums[j]) {
                i++;
            }
            // 目的是将左半边和右半边合并起来排序记录到临时数组中
            // 这个就是归并排序的部分
            while (l <= mid && nums[l] <= nums[j]) {
                temp[c++] = nums[l++];
            }
            // 当上面循环走完的时候，左半边中比右半边当前数字小的数字都已经加入到临时数组了
            // 所以将当前右半边数字加入到数组中
            temp[c++] = nums[j];
            // 更新反转对数量，由于i是有少个小于等于2*nums[j]的，所以mid - i + 1就是有多少大于他的
            cnt += mid - i + 1;
        }

        // for循环完了只能保证右半边肯定都加入数组了，左半边不一定
        while (l <= mid) {
            temp[c++] = nums[l++];
        }
        // 将temp赋值给nums
        for (int p = 0; p < temp.length; p++) {
            nums[start + p] = temp[p];
        }

        return cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
