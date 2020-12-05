package Algorithm.leetcode.leetcode.editor.cn;
//班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C
//的朋友。所谓的朋友圈，是指所有朋友的集合。 
//
// 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你
//必须输出所有学生中的已知的朋友圈总数。 
//
// 
//
// 示例 1： 
//
// 输入：
//[[1,1,0],
// [1,1,0],
// [0,0,1]]
//输出：2 
//解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。
//第2个学生自己在一个朋友圈。所以返回 2 。
// 
//
// 示例 2： 
//
// 输入：
//[[1,1,0],
// [1,1,1],
// [0,1,1]]
//输出：1
//解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 200 
// M[i][i] == 1 
// M[i][j] == M[j][i] 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 379 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class UnionFind {
    int count;
    int[] parent;

    public UnionFind(int n) {
        count = n;
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

        if (rootx == rooty) {
            return;
        }
        parent[rootx] = rooty;
        count--;
    }

    public int getCount() {
        return count;
    }
}

class Solution {
    // 题目链接：https://leetcode-cn.com/problems/friend-circles/
    int rows = 0;
    int cols = 0;
    public int findCircleNum(int[][] M) {
        return func1(M);
    }

    /**
     * 思路一：并查集
     *
     * 时间复杂度：O(N^2)，两层for循环
     * 空间复杂度：O(N),parent数组的大小
     * @param M 朋友圈矩阵
     * @return 朋友圈数量
     */
    private int func1(int[][] M) {
        rows = M.length;
        if (rows == 0) {
            return 0;
        }
        cols = M[0].length;

        // 这里要特别注意，不是row*cols，因为人只有那么多个人。这里并查集里的容量代表的是人的数量
        UnionFind unionFind = new UnionFind(rows);

        int[][] directions = {{1, 0}, {0, 1}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果M[i][j]==1,代表第i个人和第j个人是朋友(并且i!=j，i==j代表他和他自己是朋友，这当然不用union了），那么我们就union，将其加入到朋友圈即可。
                if (M[i][j] == 1 && i != j) {
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.getCount();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
