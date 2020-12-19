package Algorithm.leetcode.leetcode.editor.cn;
//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串 
// 👍 299 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution34 {
    // 题目链接：https://leetcode-cn.com/problems/valid-palindrome-ii/
    public boolean validPalindrome(String s) {
        // 双指针加记忆化寻找
        // 时间复杂度：O(N)
        // 空间复杂度：O(N)
        char[] sArray = s.toCharArray();
        int start = 0, end = s.length() - 1;
        int delete = 0;
        // 记录上一次不相等时删除的是哪一个
        int lastLeft = -1, lastRight = -1;
        while (start <= end) {
            if (sArray[start] != sArray[end]) {
                if (delete == 0) {
                    lastLeft = start;
                    lastRight = end;
                    start++;
                    delete++;
                } else if (delete == 1){
                    start = lastLeft;
                    end = lastRight - 1;
                    delete++;
                } else {
                    return false;
                }
            } else {
                start++;
                end--;
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
