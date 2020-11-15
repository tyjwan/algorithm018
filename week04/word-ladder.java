package Algorithm.leetcode.leetcode.editor.cn;
//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 639 👎 0


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class word-ladder {
    // 题目链接：https://leetcode-cn.com/problems/word-ladder/
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /**
         * 方法一：单向广度优先遍历
         *
         * 时间复杂度：O(N * C * 26), N代表字典中元素个数，C代表单词的平均长度，26就是26个字母
         * 空间复杂度：O(N), wordSet, queue, visited都用到了额外空间
         */

        // 首先将单词字典放到hashSet中，方便我们通过O(1)的时间查找
        Set<String> wordSet = new HashSet<>(wordList);

        // 边界条件判断,如果字典大小为0或者字典中不包含endWord，直接返回0
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        // 图的广度优先遍历必须使用队列和记录是否访问过的visited哈希表
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // 初始步数为1，题目要求包含起点
        int steps = 1;
        // 开始广度优先遍历
        while (!queue.isEmpty()) {
            // 得到当前队列中的元素个数，因为广度优先的时候每一次要将当前层的所有元素出队
            int currentSize = queue.size();

            for (int i = 0; i < currentSize; i++) {
                // 开始访问队列当前层次的元素
                String currentWord = queue.poll();

                // 如果当前的单词能够通过修改其中某一个字母而得到endWord，则找到了这条路径
                if (findPath(currentWord, endWord, queue, visited, wordSet)) {
                    // 直接返回steps+1
                    return steps + 1;
                }
            }

            // 当前层没有找到，将steps + 1即可
            ++steps;
        }

        // 如果while循环结束还没有得到结果，那么证明无法到达
        return 0;




        /**
         * 方法二：双向广度优先遍历
         *
         * 时间复杂度：O(N * C * 26), N代表字典中元素个数，C代表单词的平均长度，26就是26个字母
         * 空间复杂度：O(N), wordSet, visited等set都用到了额外空间
         */

        // 首先将单词字典放到hashSet中，方便我们通过O(1)的时间查找
        Set<String> wordSet = new HashSet<>(wordList);

        // 边界条件判断,如果字典大小为0或者字典中不包含endWord，直接返回0
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        // 已经访问过的 word 添加到 visited 哈希表里
        Set<String> visited = new HashSet<>();
        // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        // 初始步数为1，题目要求包含起点
        int steps = 1;
        // 开始双向BFS，左右交替扩散的步数之和为所求。什么时候停止呢，当两个集合有交集的时候即可停止
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，这样更快。哈希表小，代表当前层需要扩散的元素少，那么比起扩散元素多的，也就是哈希表大的，肯定是要快一点
            if (beginVisited.size() > endVisited.size()) {
                // 将两个set交换，这样的目的是让我们的处理逻辑一致，我们永远处理的较小的set都是beginVisited
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }

            // 程序走到这里，能够保证我们拿到的beginVisited就是较小的集合，扩散完之后，会将扩散后的集合赋值给beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String currentWord : beginVisited) {
                // 如果当前的单词能够通过修改其中某一个字母而得到endWord，则找到了这条路径
                if (findPath2(currentWord, endVisited, visited, wordSet, nextLevelVisited)) {
                    return steps + 1;
                }
            }

            // 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS
            beginVisited = nextLevelVisited;
            // 当前双向遍历没有找到，将steps + 1即可
            ++steps;
        }

        return 0;
    }

    /**
     * 当前单词经过替换后能否到达endWord
     *
     * @param currentWord      当前的单词
     * @param endVisited       目标集合
     * @param visited          用于存储是否访问过某个单词的哈希表
     * @param wordSet          存储单词字典的哈希表
     * @param nextLevelVisited 当前层扩散之后的集合
     * @return
     */
    private boolean findPath2(String currentWord, Set<String> endVisited, Set<String> visited, Set<String> wordSet, Set<String> nextLevelVisited) {
        // 将字符串转换成字符数组，用于遍历
        char[] currentWordArray = currentWord.toCharArray();

        // 遍历字符数组进行字母替换，看能不能到达endWord
        for (int i = 0; i < currentWordArray.length; i++) {
            // 记录当前字符，之后如果替换当前位置的字符找不到，要还原进行下一个字符的寻找的。（这样可以满足题目要求，只允许更改一个字符）
            char remember = currentWordArray[i];
            // 遍历26个字母对其进行替换
            for (char k = 'a'; k <= 'z'; k++) {
                // 如果遍历到的字母就是原先的字母，直接跳过此次循环
                if (k == remember) {
                    continue;
                }

                // 将当前的字母赋值给字符数组对应的位置得到更改一个字母后的新字符数组
                currentWordArray[i] = k;
                // 将其转化为新的字符串
                String newWord = String.valueOf(currentWordArray);

                // 如果新字符串就等于endWord证明我们已经找到了这条路径直接返回true即可
                if (endVisited.contains(newWord)) {
                    return true;
                }
                // 如果单词表里包含这个词并且我们没有访问过这个词
                if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                    // 将其加入到队列中，并且更新visite哈希表
                    nextLevelVisited.add(newWord);
                    visited.add(newWord);
                }
            }

            // 恢复位置的字母，要进入下一轮循环了
            currentWordArray[i] = remember;
        }

        return false;
    }

    /**
     * 当前单词经过替换后能否到达endWord
     *
     * @param currentWord 当前的单词
     * @param endWord     目标单词
     * @param queue       队列
     * @param visited     用于存储是否访问过某个单词的哈希表
     * @param wordSet     存储单词字典的哈希表
     * @return
     */
    public boolean findPath(String currentWord, String endWord,
                            Queue<String> queue, Set<String> visited, Set<String> wordSet) {
        // 将字符串转换成字符数组，用于遍历
        char[] currentWordArray = currentWord.toCharArray();

        // 遍历字符数组进行字母替换，看能不能到达endWord
        for (int i = 0; i < currentWordArray.length; i++) {
            // 记录当前字符，之后如果替换当前位置的字符找不到，要还原进行下一个字符的寻找的。（这样可以满足题目要求，只允许更改一个字符）
            char remember = currentWordArray[i];
            // 遍历26个字母对其进行替换
            for (char k = 'a'; k <= 'z'; k++) {
                // 如果遍历到的字母就是原先的字母，直接跳过此次循环
                if (k == remember) {
                    continue;
                }

                // 将当前的字母赋值给字符数组对应的位置得到更改一个字母后的新字符数组
                currentWordArray[i] = k;
                // 将其转化为新的字符串
                String newWord = String.valueOf(currentWordArray);

                // 如果新字符串就等于endWord证明我们已经找到了这条路径直接返回true即可
                if (newWord.equals(endWord)) {
                    return true;
                }
                // 如果单词表里包含这个词并且我们没有访问过这个词
                if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                    // 将其加入到队列中，并且更新visite哈希表
                    queue.offer(newWord);
                    visited.add(newWord);
                }
            }

            // 恢复位置的字母，要进入下一轮循环了
            currentWordArray[i] = remember;
        }

        // 程序走到这里还没返回，就证明我们没有找到endWord
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
