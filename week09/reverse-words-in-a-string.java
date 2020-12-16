package Algorithm.leetcode.leetcode.editor.cn;
//给定一个字符串，逐个翻转字符串中的每个单词。
//
// 说明： 
//
// 
// 无空格字符构成一个 单词 。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 
//
// 示例 1： 
//
// 输入："the sky is blue"
//输出："blue is sky the"
// 
//
// 示例 2： 
//
// 输入："  hello world!  "
//输出："world! hello"
//解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入："a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 示例 4： 
//
// 输入：s = "  Bob    Loves  Alice   "
//输出："Alice Loves Bob"
// 
//
// 示例 5： 
//
// 输入：s = "Alice does not even like bob"
//输出："bob like even not does Alice"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 包含英文大小写字母、数字和空格 ' ' 
// s 中 至少存在一个 单词 
// 
//
// 
// 
//
// 
//
// 进阶： 
//
// 
// 请尝试使用 O(1) 额外空间复杂度的原地解法。 
// 
// Related Topics 字符串 
// 👍 248 👎 0


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/reverse-words-in-a-string/
    public String reverseWords(String s) {
        // 思路1：语言特性
        // 时间复杂度：O(N)
        // 空间复杂度：O(N),用了一个列表
//        return func1(s);

        // 思路2: 自己反转,有问题，中间的单词有可能有空格
//        return func2(s);

        // 思路3：所有的函数都自己实现下
        // 去掉左右两边的空格
        return func3(s);
    }

    private String func3(String s) {
        StringBuilder sb = trimSpace(s);

        // 反转整个字符串
        reverseAll(sb, 0, sb.length() - 1);

        // 反转字符串中的单词
        reverseWord(sb);

        return sb.toString();
    }

    private void reverseWord(StringBuilder sb) {
        int start = 0, end = 0;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }

            reverseAll(sb, start, end - 1);
            start = end + 1;
            ++end;
        }
    }

    private void reverseAll(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, temp);
        }
    }

    private StringBuilder trimSpace(String s) {
        int len = s.length() - 1;
        int start = 0;

        // 去掉字符串开头的空格
        while (start <= len && ' ' == s.charAt(start)) {
            start++;
        }

        // 去掉字符串结尾的空格
        while (start <= len && ' ' == s.charAt(len)) {
            len--;
        }

        // 去掉字符串中间的空格
        StringBuilder sb = new StringBuilder();
        while (start <= len) {
            char c = s.charAt(start);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            start++;
        }

        return sb;
    }

    private String func2(String s) {
        s = s.trim();
        String[] sArray = s.split(" ");
        int end = sArray.length - 1;
        int start = 0;
        while (start <= end) {
            String temp = sArray[start];
            sArray[start++] = sArray[end];
            sArray[end--] = temp;
        }

        return String.join(" ", sArray);
    }

    private String func1(String s) {
        // 去掉开头和结尾的空格字符
        s = s.trim();
        List<String> sArray = Arrays.asList(s.split(" "));
        Collections.reverse(sArray);
        return String.join(" ", sArray);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
