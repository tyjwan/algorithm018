package Algorithm.leetcode.leetcode.editor.cn;
//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 字符串 
// 👍 260 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);

        int start = 0, end = 0, len = s.length();
        while (end < len) {
            if ((end + 1 < len && sb.charAt(end + 1) == ' ') || end + 1 == len) {
                reverse(sb, start, end);
                start = end + 2;
                end = start;
            } else {
                end++;
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
