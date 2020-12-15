package Algorithm.leetcode.leetcode.editor.cn;
//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串 
// 👍 297 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution25 {
    // 题目链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string/
    public int firstUniqChar(String s) {
        // 暴力法,超时
        // 时间复杂度：O(N^2)，两层for循环
        // 空间复杂度：O(N),用了一个一维数组
//        return func1(s);

        // HashMap
        // 时间复杂度：O(N), 两个for循环并列
        // 空间复杂度：O(N), 用了一个HashMap
//        return func2(s);

        // 用一个数组
        // 时间复杂度：O(N), 两个for循环并列
        // 空间复杂度：O(1), 用了一个一维数组,长度26
        return func3(s);
    }


    private int func3(String s) {
        int[] tag = new int[26];

        char[] sArray = s.toCharArray();

        for (int i = 0; i < sArray.length; i++) {
            tag[sArray[i] - 'a'] ++;
        }

        for (int i = 0; i < sArray.length; i++) {
            if (tag[sArray[i] - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    private int func2(String s) {
        Map<Character, Integer> hashMap = new HashMap<>();

        char[] sArray = s.toCharArray();

        for (int i = 0; i < sArray.length; i++) {
            hashMap.put(sArray[i], hashMap.getOrDefault(sArray[i], 0) + 1);
        }

        for (int i = 0; i < sArray.length; i++) {
            if (hashMap.get(sArray[i]) == 1) {
                return i;
            }
        }

        return -1;
    }

    private int func1(String s) {
        char[] sArray = s.toCharArray();

        for (int i = 0; i < sArray.length; i++) {
            int count = 0;
            for (int j = 0; j < sArray.length; j++) {
                if (i != j && sArray[i] == sArray[j]) {
                    count++;
                }
            }
            if (count == 0) {
                return i;
            }
        }

        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
