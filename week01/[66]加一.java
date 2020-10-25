//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组 
// 👍 528 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class 加一 {
    public int[] plusOne(int[] digits) {
        // 递归解法
//        return plus(digits, digits.length - 1);

        // 进位解法
        for (int i = digits.length - 1; i >= 0 ; i--) {
            digits[i] += 1;
            digits[i] = digits[i] % 10;
            if (digits[i]%10 != 0) {
                return digits;
            }
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    private int[] plus(int[] digits, int length) {
        if (length < 0) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            System.arraycopy(digits, 0, newDigits, 1, newDigits.length - 1);
            return newDigits;
        }

        if (digits[length] + 1 == 10) {
            digits[length] = 0;
            digits = plus(digits, length - 1);
        } else {
            digits[length] += 1;
        }

        return digits;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
