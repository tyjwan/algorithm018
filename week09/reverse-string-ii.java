package Algorithm.leetcode.leetcode.editor.cn;
//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串 
// 👍 107 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution28 {
    // 题目链接：https://leetcode-cn.com/problems/reverse-string-ii/

    public static void main(String[] args) {
        Solution28 solution = new Solution28();
        solution.reverseStr("abcdefg", 2);
    }

    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder(s);

        int start = 0, end = s.length() - 1;
        while (start <= end) {
            if (end - start + 1 < k) {
                reverse(sb, start, end);
                break;
            } else if (end - start + 1 >= k && end - start + 1 < 2 * k) {
                reverse(sb, start, start + k - 1);
                break;
            } else {
                reverse(sb, start, start + k - 1);
                start += 2 * k;
            }
        }

        return sb.toString();
    }

    public void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, temp);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
