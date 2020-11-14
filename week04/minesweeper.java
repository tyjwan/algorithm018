package Algorithm.leetcode.leetcode.editor.cn;
//让我们一起来玩扫雷游戏！
//
// 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）
//地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。 
//
// 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板： 
//
// 
// 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。 
// 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。 
// 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。 
// 如果在此次点击中，若无更多方块可被揭露，则返回面板。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 
//
//[['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'M', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 示例 2： 
//
// 输入: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'X', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 
//
// 注意： 
//
// 
// 输入矩阵的宽和高的范围为 [1,50]。 
// 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。 
// 输入面板不会是游戏结束的状态（即有地雷已被挖出）。 
// 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 194 👎 0


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class minesweeper {
    // 题目链接：https://leetcode-cn.com/problems/minesweeper/description/

    // 因为题目涉及到8个方向，所以分别定义长度为8的x,y坐标数组，表示8个方向(上下左右，左上，右上，左下，右下）
    int[] directX = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] directY = {1, -1, 0, 0, -1, -1, 1, 1};

    public char[][] updateBoard(char[][] board, int[] click) {
        /**
         * 方法一：深度优先遍历
         *
         * 时间复杂度：O(MN), M，N为面板的长和宽。因为每个面板数据只有可能被访问一次
         * 空间复杂度：O(MN), M，N为面板的长和宽。递归的调用深度最大程度就是M * N
         */

        // 拿出点击序列
        int row = click[0], col = click[1];

        // 如果当前点击的是雷，那么把它改成X，游戏结束
        if (board[row][col] == 'M') {
            // 规则1
            board[row][col] = 'X';
        } else {
            dfs(row, col, board);
        }

        return board;


        /**
         * 方法二：广度优先搜索
         *
         * 时间复杂度：O(MN), M，N为面板的长和宽。因为每个面板数据只有可能被访问一次
         * 空间复杂度：O(MN), M，N为面板的长和宽。递归的调用深度最大程度就是M * N
         */

        // 拿出点击序列
        int row = click[0], col = click[1];
        // 如果当前点击的是雷，那么把它改成X，游戏结束
        if (board[row][col] == 'M') {
            // 规则 1
            board[row][col] = 'X';
        } else{
            bfs(row, col, board);
        }
        return board;
    }


    /**
     * 深度优先搜索
     *
     * @param row   当前搜索的行
     * @param col   当前搜索的列
     * @param board 当前的搜索面板
     */
    public void dfs(int row, int col, char[][] board) {
        // 定义地雷的数量
        int countLandmine = 0;
        // 8 个方向，遍历8次寻找
        for (int i = 0; i < 8; i++) {
            // 得到新的坐标
            int nrow = row + directY[i];
            int ncol = col + directX[i];

            // 如果新坐标不在我们的面板范围内，此次循环跳过
            if (nrow < 0 || nrow >= board.length || ncol < 0 || ncol >= board[0].length) {
                continue;
            }

            // 如果新的行新的列所在面板位置是雷，那么将雷的计数器加一
            if (board[nrow][ncol] == 'M') {
                countLandmine++;
            }
        }

        // 规则3
        // 如果雷计数器大于0，证明与当前空方块（一定是空方块，否则不可能进入深度递归中）周围的确有雷，更新面板为当前空方块周围的雷的数量
        if (countLandmine > 0) {
            board[row][col] = (char) (countLandmine + '0');
        } else { // 否则，证明当前空方块附近没有雷，我们需要将面板上对应位置改为B，然后递归的揭露和其相邻的未挖出的方块
            // 规则2
            board[row][col] = 'B';
            // 利用8个方向去遍历
            for (int i = 0; i < 8; i++) {
                // 得到新的坐标
                int nrow = row + directY[i];
                int ncol = col + directX[i];

                // 首先如果行数列数任何一个不符合面板要求，此次循环直接continue即可。
                // 其次，如果得到的新坐标所在面板数据不为E，我们也跳过，因为规则2说的是所有和其相邻的未挖出方块被递归揭露。那么未挖出包含M和E，这里为什么只判断E呢，因为M在前面已经被判断并且计算过了
                // 也就是说，有M，程序走不到这里来，实际上这里的规则3所要表达的就是E这样的未挖出空方块
                if (nrow < 0 || nrow >= board.length || ncol < 0 || ncol >= board[0].length || board[nrow][ncol] != 'E') {
                    continue;
                }

                // 符合要求的坐标，我们就递归揭露
                dfs(nrow, ncol, board);
            }
        }
    }


    /**
     * 广度优先搜索
     * @param row   当前搜索的行
     * @param col   当前搜索的列
     * @param board 当前的搜索面板
     */
    public void bfs(int row, int col, char[][] board) {
        // 广度优先搜索需要用队列进行模拟
        Queue<int[]> queue = new LinkedList<int[]>();
        // 用布尔数组记录哪些元素是被访问过的
        boolean[][] vis = new boolean[board.length][board[0].length];
        // 将点击坐标放到队列中
        queue.offer(new int[]{row, col});
        vis[row][col] = true;
        // 当队列不为空时循环
        while (!queue.isEmpty()) {
            // 出队
            int[] pos = queue.poll();
            // 定义地雷的数量
            int countLandmine = 0;
            int x = pos[0], y = pos[1];

            // 8 个方向，遍历8次寻找
            for (int i = 0; i < 8; ++i) {
                // 得到新坐标
                int nx = x + directY[i];
                int ny = y + directX[i];

                // 如果新坐标不在我们的面板范围内，此次循环跳过
                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) {
                    continue;
                }
                // 如果新的行新的列所在面板位置是雷，那么将雷的计数器加一
                if (board[nx][ny] == 'M') {
                    ++countLandmine;
                }
            }

            // 规则3
            // 如果雷计数器大于0，证明与当前空方块（一定是空方块，否则不可能进入广度搜索中）周围的确有雷，更新面板为当前空方块周围的雷的数量
            if (countLandmine > 0) {
                // 规则 3
                board[x][y] = (char) (countLandmine + '0');
            } else { // 否则，证明当前空方块附近没有雷，我们需要将面板上对应位置改为B，然后将符合面板的坐标加入队列中，揭露和其相邻的未挖出的方块
                // 规则 2
                board[x][y] = 'B';
                for (int i = 0; i < 8; ++i) {
                    int nx = x + directY[i];
                    int ny = y + directX[i];

                    // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                    // 首先如果行数列数任何一个不符合面板要求，此次循环直接continue即可。
                    // 其次，如果得到的新坐标所在面板数据不为E，我们也跳过，因为规则2说的是所有和其相邻的未挖出方块被递归揭露。那么未挖出包含M和E，这里为什么只判断E呢，因为M在前面已经被判断并且计算过了
                    // 也就是说，有M，程序走不到这里来，实际上这里的规则3所要表达的就是E这样的未挖出空方块
                    if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] != 'E' || vis[nx][ny]) {
                        continue;
                    }

                    // 符合面板要求和题目要求的坐标加入到队列中
                    queue.offer(new int[]{nx, ny});
                    vis[nx][ny] = true;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
