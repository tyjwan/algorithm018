package Algorithm.leetcode.leetcode.editor.cn;
//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学 
// 👍 263 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class power-of-two {
    // 题目链接：https://leetcode-cn.com/problems/power-of-two/
    public boolean isPowerOfTwo(int n) {
        // 暴力法
        // 如果一个数要是2的幂，那注定他的二进制1的个数注定只能是一个

//        while ((n & 1) == 0) {
//            n >>= 1;
//        }
//        return n == 1;

        // 更好的思路，既然有且仅有一个1，那我们就把他唯一的那个1干掉
//        if (n == 0) {
//            return false;
//        }
//
//        long x = n;
//
//        x &= (x - 1);
//
//        return x == 0;

        // 得到最低位的1即可
        if (n == 0) {
            return false;
        }
        long x = n;
        return (x & (-x)) == x;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
