package Algorithm.leetcode.leetcode.editor.cn;
//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 426 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/surrounded-regions/
    public void solve(char[][] board) {
        int row = board.length;
        if (board.length == 0) {
            return;
        }
        int col = board[0].length;

        UnionFind unionFind = new UnionFind(row * col + 1);
        int dummyNode = row * col;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    // 把边界上的O和dummyNode合成一个连通区域
                    if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                        unionFind.union(i * col + j, dummyNode);
                    } else {
                        // 这里就将它上面的和左边的O合并成一个连通区域即可，为什么不用上下左右？
                        // 因为一个格子，只有可能由他上面的格子走过来或者他左边的格子走过来，这样可以减少很多无用功
                        if (i > 0 && board[i - 1][j] == 'O') {
                            unionFind.union(i * col + j, (i - 1) * col + j);
                        }
                        if (j > 0 && board[i][j - 1] == 'O') {
                            unionFind.union(i * col + j, i * col + j - 1);
                        }
                        if (i < row - 1 && board[i + 1][j] == 'O') {
                            unionFind.union(i * col + j, (i + 1) * col + j);
                        }
                        if (j < col - 1 && board[i][j + 1] == 'O') {
                            unionFind.union(i * col + j, i * col + j + 1);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    if (unionFind.find(i * col + j) != unionFind.find(dummyNode)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
}

class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int n) {
        int p = n;
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        // 路径压缩
        while (n != parent[n]) {
            int temp = parent[n];
            parent[n] = p;
            n = temp;
        }
        return p;
    }

    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            parent[rootx] = rooty;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
