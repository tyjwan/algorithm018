package Algorithm.leetcode.leetcode.editor.cn;
//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例： 
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// Related Topics 回溯算法 
// 👍 685 👎 0


import scala.Array;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/n-queens/

    // 皇后列的set
    Set<Integer> col = new HashSet<>();
    // 皇后右斜上的set
    Set<Integer> pie = new HashSet<>();
    // 皇后左斜上的set
    Set<Integer> na = new HashSet<>();
    // 定义结果集
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        // 申请一个char棋盘
        char[][] chess= new char[n][n];
        // 初始化棋盘
        for (char[] everyRow : chess) {
            // 初始状态棋盘每个地方都是.
            Arrays.fill(everyRow, '.');
        }

        // 开始回溯遍历
        // 从第一行开始，如果符合条件则往下一层走（逻辑上即为下一行）
        for (int i = 0; i < n; i++) {
            // 第一层怎么放都是符合要求的，所以不用判断
            chess[0][i] = 'Q';
            // 将其加入到各个set集合中
            col.add(i);
            pie.add(0 + i);
            na.add(0 - i);

            dfs(chess, 1);

            // 将状态回退
            na.remove(0 - i);
            pie.remove(0 + i);
            col.remove(i);
            chess[0][i] = '.';
        }

        return res;
    }

    public void dfs(char[][] chess, int row) {
        // 递归出口，也就是到达最后一行还有效的时候
        // 实际上是row在n-1的时候就到最后一层了，因为下标从0开始
        // 但是要最后一层也是满足条件的才行，所以最后一层满足条件进入的下一层
        // 就是第n层，也就是row == n的时候，代表我们找到了一组解
        if (row == chess.length) {
            // 将当前chess加入到结果集中
            res.add(transfer(chess));
            return;
        }

        // 如果没到最后一层，处理当前层逻辑，判断是否能够走到下一层
        // 当前层（当前行）的每一个字符进行遍历
        for (int i = 0; i < chess.length; i++) {
            // 如果是有效的,也就是当前列的set，撇的set，呐的set都没有对应的元素
            if (!col.contains(i) && !pie.contains(row + i) && !na.contains(row - i)) {
                // 将当前行，当前列的字符设置成皇后
                chess[row][i] = 'Q';
                // 更新col,pie,na这三个set
                col.add(i);
                pie.add(row + i);
                na.add(row - i);

                // 递归遍历到下一层
                dfs(chess, row + 1);

                // 当前行的当前列递归回溯完了之后，要将状态回溯
                na.remove(row - i);
                pie.remove(row + i);
                col.remove(i);
                chess[row][i] = '.';
            }
        }

        // 这一层遍历完了，return到上一层，这里也不用我们手动去return，程序执行完自己就回到上一层了。
    }

    public List<String> transfer(char[][] chess) {
        List<String> cString = new ArrayList<>();
        for (char[] every : chess) {
            cString.add(new String(every));
        }
        return cString;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
