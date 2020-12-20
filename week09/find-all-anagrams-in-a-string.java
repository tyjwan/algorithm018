package Algorithm.leetcode.leetcode.editor.cn;
//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
//
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。 
//
// 说明： 
//
// 
// 字母异位词指字母相同，但排列不同的字符串。 
// 不考虑答案输出的顺序。 
// 
//
// 示例 1: 
//
// 
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
// 
//
// 示例 2: 
//
// 
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
// 
// Related Topics 哈希表 
// 👍 431 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution36 {

    public static void main(String[] args) {
//        Solution36 s = new Solution36();
//        s.findAnagrams("cbaebabacd", "abc");

    }

    // 题目链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
    public List<Integer> findAnagrams(String s, String p) {
        // 错误思路
//        return func1(s, p);

        // 滑动窗口
        // 时间复杂度：O(MN)
        // 空间复杂度：O(N)
        return func2(s, p);
    }

    private List<Integer> func2(String s, String p) {
        List<Integer> res = new ArrayList<>();

        if (s.length() ==0 || p.length() ==0 || p.length() > s.length()) {
            return res;
        }

        int lenS = s.length();
        int lenP = p.length();
        int start = 0, end = 0;

        int[] dict = new int[26];
        char[] sArray = s.toCharArray();
        char[] pArray = p.toCharArray();

        for (char c : pArray) {
            dict[c - 'a']++;
        }
        int[] window = new int[26];

        while (end < lenS) {
            int current = sArray[end] - 'a';
            end++;
            window[current] += 1;
            while (window[current] > dict[current]) {
                int curL = sArray[start] - 'a';
                window[curL] -= 1;
                start++;
            }
            if (end - start == lenP) {
                res.add(start);
            }
        }

        return res;
    }

    private List<Integer> func1(String s, String p) {
        // 定义结果集
        List<Integer> res = new ArrayList<>();

        // 边界条件
        if (p.length() > s.length()) {
            return res;
        }

        int lenP = p.length();

        char[] sAraay = s.toCharArray();
        char[] pArray = s.toCharArray();
        int[] temp = new int[26];

        for (char c : pArray) {
            temp[c - 'a']++;
        }

        int[] everyTemp = temp;

        int start = 0, lenS = s.length();
        while (start < lenS) {
            while (start < lenP) {
                everyTemp[sAraay[start] - 'a']--;
                start++;
            }

            boolean tag = true;
            for (int i : everyTemp) {
                if (i != 0) {
                    tag = false;
                    break;
                }
            }

            if (tag) {
                res.add(start - lenP);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
