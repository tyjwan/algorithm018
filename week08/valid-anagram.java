package Algorithm.leetcode.leetcode.editor.cn;
//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 267 👎 0有效的字母异位词


import java.lang.reflect.Array;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接；https://leetcode-cn.com/problems/valid-anagram/
    public boolean isAnagram(String s, String t) {
        // 排序
//        return func2(s, t);

        // HashMap存储每一个字符出现的次数，在第二个字符串中每遇到一个就把这个次数减一。
        // 如果最后所有的字符对应的次数都是0，就代表是

        // 数组标记的方式，实际上你可以发现因为是字母，所以完全就不用hashmap了
//        return func1(s, t);
    }

    private boolean func2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);
        return Arrays.equals(s1, s2);
    }

    private boolean func1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] tag = new int[26];

        for (char c : s.toCharArray()) {
            tag[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            tag[c - 'a']--;
            if (tag[c - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
