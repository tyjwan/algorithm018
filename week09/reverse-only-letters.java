package Algorithm.leetcode.leetcode.editor.cn;
//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 字符串 
// 👍 70 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/reverse-only-letters/
    public String reverseOnlyLetters(String S) {
        StringBuilder sb = new StringBuilder(S);

        reverse(sb, 0, sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
//        Solution32 t = new Solution32();
//        t.reverseOnlyLetters("ab-cd");
    }

    public void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char startWord = sb.charAt(start);
            char endWord = sb.charAt(end);
            if (! (('a' <= startWord && startWord <= 'z') || ('A' <= startWord && startWord <= 'Z'))) {
                start += 1;
                continue;
            } else if (! (('a' <= endWord && endWord <= 'z') || ('A' <= endWord && endWord <= 'Z'))) {
                end -= 1;
                continue;
            } else {
                char temp = startWord;
                sb.setCharAt(start++, endWord);
                sb.setCharAt(end--, temp);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
