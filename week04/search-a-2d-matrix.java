package Algorithm.leetcode.leetcode.editor.cn;
//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 13
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：matrix = [], target = 0
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 0 <= m, n <= 100 
// -104 <= matrix[i][j], target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 274 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class search-a-2d-matrix {
    // 题目链接：https://leetcode-cn.com/problems/search-a-2d-matrix/
    public boolean searchMatrix(int[][] matrix, int target) {
        /**
         * 方法一：二分法
         *
         * 时间复杂度：O(log(M*N)), M是行数，N是列数
         * 空间复杂度：O(1), 没有用到额外空间
         */

        // 边界条件判断
        if (matrix.length == 0) {
            return false;
        }

        // 得到二维数组的行数
        int m = matrix.length;
        // 得到二维数组的列数
        int n = matrix[0].length;

        // 定义左边界，有边界, 将整个二维数组拉长，看成一个一维数组，这个问题就非常好解决了。
        // 拉长之后，初始的left就是0，right就是 行数 * 列数 - 1
        int left = 0, right = m * n - 1;

        while (left <= right) {
            // 算出中间节点的下标，这里用变成left + (right - left) / 2是为了防止left+right超出范围
            // 用移位操作是因为计算机对于位运算更友好，更快
            int mid = left + ((right - left) >> 1);
            // 算出mid在二维数组中对应的行号和列号
            int row = mid / n;
            int col = mid % n;

            // 如果当前对应的元素正好等于target，直接返回true
            // 如果大于target，证明我们要到左边进行寻找，right = mid - 1
            // 如果小于target，证明我们要到右边进行寻找，left = mid + 1
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 循环结束还没有返回，那就是没找到
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
