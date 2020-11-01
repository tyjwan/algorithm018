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
// 👍 267 👎 0


import java.lang.reflect.Array;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        // 方法1： 数组标记的方式（其实也可以用hashmap，不过这个题目的场景我们用数组即可）
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

        // 方法2： 排序
        if (s.length() != t.length()) {
            return false;
        }

        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);

        return Arrays.equals(s1, s2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
