package Algorithm.leetcode.leetcode.editor.cn;
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 732 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class 移动零 {
    public void moveZeroes(int[] nums) {
        // 申请一个数组的方法
        int[] b = new int[nums.length];
        int i = 0, j = nums.length - 1;

        for (int k = 0; k < nums.length; k++) {
            if (nums[k] != 0) {
                b[i] = nums[k];
                i++;
            } else {
                b[j] = 0;
                j--;
            }
        }

        // 双指针法，定义一个j指针变量，指向的是当前可以放置非0元素的位置
        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];

                if (i != j) {
                    nums[i] = 0;
                }

                j++;
            }
        }

        // 双指针法，先把非0元素全移到前面，然后再在末尾补0
        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }

        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }

        // 双指针交换法：一直交换，同第一种双指针方法的思路感觉是类似的
        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
