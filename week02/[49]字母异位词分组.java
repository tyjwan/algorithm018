package Algorithm.leetcode.leetcode.editor.cn;
//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 505 👎 0


import scala.Int;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class 字母异位词分组 {
    // 题目地址：https://leetcode-cn.com/problems/group-anagrams/
    public List<List<String>> groupAnagrams(String[] strs) {
        // 方法一: 排序法
        // 判空
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // 用hashmap来存储，异位词排序后肯定是一样的，可以作为键值
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] temp = s.toCharArray();
            // 对字符数组排序
            Arrays.sort(temp);
            // 将排序好的字符串作为hashmap的key
            String key = String.valueOf(temp);
            // 如果map中还没有这个键值
            if (!map.containsKey(key)) {
                // 那么将这个键值写进去
                map.put(key, new ArrayList<>());
            }
            // 将这个字符串加到它所对应的键值的列表里面
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());


        // 方法二： 计数法
        // 判空
        if (strs.length == 0 || strs == null) {
            return new ArrayList<>();
        }

        // 用hashmap来存储，异位词如果映射为数字最终加起来的值肯定是一样的，可以作为键值
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] temp = new char[26];

            for (char c : s.toCharArray()) {
                temp[c - 'a'] ++;
            }
            // 此for循环结束之后，temp所代表的信息已经是和他的字母异位词一样的！

            // 将这个信息作为key
            String key = String.valueOf(temp);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
