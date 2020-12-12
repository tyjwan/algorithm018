package Algorithm.leetcode.leetcode.editor.cn;
//给你两个数组，arr1 和 arr2，
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组 
// 👍 147 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class relative-sort-array {
    // 题目链接：https://leetcode-cn.com/problems/relative-sort-array/
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 计数排序
        return func1(arr1, arr2);
    }

    private int[] func1(int[] arr1, int[] arr2) {
        // 结果集
        int[] res = new int[arr1.length];

        int maxNum = 0;
        // 选出最大值
        for (int every : arr1) {
            maxNum = Math.max(maxNum, every);
        }

        // 申请一个数组，长度为刚刚选出来的最大值
        int[] mark = new int[maxNum + 1];

        // 记录数组里的每个数有多少个
        for (int every : arr1) {
            mark[every] += 1;
        }

        // 遍历arr2，将数组2中有的数按照顺序放到结果集中
        int j = 0;
        for (int every : arr2) {
            for (int i = 0; i < mark[every]; i++) {
                res[j++] = every;
            }
            // 这个元素加完了之后将mark数组清0
            mark[every] = 0;
        }

        // 此时数组mark中只剩下那些下标没在arr2中出现过的
        // 也就是现在只有arr2中没出现过的元素对应到mark的下标对应的值是大于0的。
        for (int i = 0; i < mark.length; i++) {
            for (int k = 0; k < mark[i]; k++) {
                res[j++] = i;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
