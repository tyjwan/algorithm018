package Algorithm.leetcode.leetcode.editor.cn;
//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意: 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 所有的目标基因序列必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 示例 1: 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2: 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3: 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
// 👍 63 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/minimum-genetic-mutation/
    public int minMutation(String start, String end, String[] bank) {
        if (bank.length == 0 || !Arrays.asList(bank).contains(end)) {
            return -1;
        }

        // 初始化基因库的set，方便我们后面的比较
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        Queue<String> queue = new LinkedList();
        queue.offer(start);
        // 广度优先必须要设置一个visited来存储哪些节点是我们访问过的，不要重复访问
        Set<String> visited = new HashSet<>();
        visited.add(start);

        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String currentGene = queue.poll();
                if (findPath(currentGene, end, bankSet, visited, queue)) {
                    return step + 1;
                }
            }

            ++step;
        }

        return -1;
    }

    public boolean findPath(String gene, String end, Set bankSet, Set visited, Queue queue) {
        char[] geneArray = gene.toCharArray();

        for (int i = 0; i < geneArray.length; i++) {
            char currentGene = geneArray[i];
            for (char c = 'A'; c <= 'Z'; c++) {
                geneArray[i] = c;
                String newGene = new String(geneArray);

                if (newGene.equals(end)) {
                    return true;
                }

                if (bankSet.contains(newGene) && !visited.contains(newGene)) {
                    queue.offer(newGene);
                    visited.add(newGene);
                }
            }
            geneArray[i] = currentGene;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
