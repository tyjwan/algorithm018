package Algorithm.leetcode.leetcode.editor.cn;
//编写一个程序，通过填充空格来解决数独问题。
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// 提示： 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法 
// 👍 709 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/sudoku-solver/#/description
    // 记录当前行剩余可用数字
    Set<Character>[] rows = new HashSet[9];
    // 记录当前列剩余可用数字
    Set<Character>[] cols = new HashSet[9];
    // 记录当前块剩余可用数字
    Set<Character>[] blocks = new HashSet[9];
    // 定义一个需要填数字的列表，里面装了当前所有空位置的坐标
    List<int[]> empty = new ArrayList<>();
    public void solveSudoku(char[][] board) {
        // 方法一：超时
//        func1(board);

        // 初始化set
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            rows[i].addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));

            cols[i] = new HashSet<>();
            cols[i].addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));

            blocks[i] = new HashSet<>();
            blocks[i].addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
        }

        // 预扫描，这样就可以扫描出哪些位置是可以放数字的以及每个格子对应的还可以放的数字是哪些
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 如果当前格子有数字
                if (board[i][j] != '.') {
                    char value = board[i][j];
                    // 对应的集合里将这个数字去除掉
                    rows[i].remove(value);
                    cols[j].remove(value);
                    blocks[3 * (i / 3) + j / 3].remove(value);
                } else {
                    // 否则当前格子是空的，将其坐标加入到empty列表中
                    empty.add(new int[]{i, j});
                }
            }
        }

        // 预处理完了之后开始递归回溯
        backtrack(0, board);
    }

    /**
     * 回溯
     * @param num 代表当前填的数字个数，初始状态当然是0
     * @return 是否是合理数独
     */
    public boolean backtrack(int num, char[][] board) {
        // 递归出口，就是当前填的数字的个数和empty的size是一样的，就证明所有数字都填完了。
        // 这里注意实际上是填的个数和empty的size-1的时候就填满了，因为下标num从0开始填，但是当第num - 1个
        // 填进去之后如果是合理的才往下走，这个时候num-1变成num，才能证明所有的数字都是符合数独要求的
        if (num == empty.size()) {
            return true;
        }

        // 否则还没填完，要继续去填
        int[] index = empty.get(num);
        int row = index[0];
        int col = index[1];
        int b = (row / 3) * 3 + col / 3;

        // 定义一个set，求几个set的交集也就是当前行，列，块中都还剩下的那些元素才是可以填过来的
        Set<Character> result = new HashSet<>();
        result.addAll(rows[row]);
        result.retainAll(cols[col]);
        result.retainAll(blocks[b]);

        // 遍历set，进行回溯，当前这个放进去看能不能填圆满，不能继续填下一个
        for (char c : result) {
            // 当前元素要填进去的话，就要在对应的行，列，块中删除掉
            rows[row].remove(c);
            cols[col].remove(c);
            blocks[b].remove(c);
            board[row][col] = c;
            // 如果就这样下去是可以满足条件的
            if (backtrack(num + 1, board)) {
                // 直接返回true
                return true;
            }
            // 否则，证明当前这个格子不能填这个元素，要换其他的试试，这个时候要回退当前层状态
            rows[row].add(c);
            cols[col].add(c);
            blocks[b].add(c);
        }

        // 如果for循环完了都没有得到一个true，证明都是不可以的返回false即可
        return false;
    }

    private void func1(char[][] board) {
        // 边界条件
        if (board.length == 0) {
            return;
        }

        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 如果当前棋盘的这个格子没有数字，证明是我们可以放格子的地方
                if (board[i][j] == '.') {
                    // 有数字1-9可以放
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 检查行
            if (i != col && board[row][i] == c) {
                return false;
            }

            // 检查列
            if (i != row && board[i][col] == c) {
                return false;
            }

            // 检查block
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
                && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false;
            }
        }

        return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
