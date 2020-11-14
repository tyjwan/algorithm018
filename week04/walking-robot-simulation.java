package Algorithm.leetcode.leetcode.editor.cn;
//机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
//
// 
// -2：向左转 90 度 
// -1：向右转 90 度 
// 1 <= x <= 9：向前移动 x 个单位长度 
// 
//
// 在网格上有一些格子被视为障碍物。 
//
// 第 i 个障碍物位于网格点 (obstacles[i][0], obstacles[i][1]) 
//
// 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。 
//
// 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。 
//
// 
//
// 示例 1： 
//
// 输入: commands = [4,-1,3], obstacles = []
//输出: 25
//解释: 机器人将会到达 (3, 4)
// 
//
// 示例 2： 
//
// 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
//输出: 65
//解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
// 
//
// 
//
// 提示： 
//
// 
// 0 <= commands.length <= 10000 
// 0 <= obstacles.length <= 10000 
// -30000 <= obstacle[i][0] <= 30000 
// -30000 <= obstacle[i][1] <= 30000 
// 答案保证小于 2 ^ 31 
// 
// Related Topics 贪心算法 
// 👍 112 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class walking-robot-simulation {
    // 题目链接：https://leetcode-cn.com/problems/walking-robot-simulation/
    public int robotSim(int[] commands, int[][] obstacles) {
        /**
         * 暴力法
         *
         * 时间复杂度：O(N+K),N是commands的长度，K是obstacles的长度
         * 空间复杂度：O(K), 用以存储障碍物所花掉的set的空间
         */

        // 用两个数组来模拟方向所以初始值如下（题目要求朝着北面）
        // 初始状态directX和directY中的第一个元素拿出来作为方向(0,1)，因为朝北，所以X移动为零，Y的移动为1(每一次不管走多少步，都是一步一步走）
        int[] directX = {0, 1, 0, -1};
        int[] directY = {1, 0, -1, 0};
        // di就是用于知道当前的方向的，向右转90度就是di + 1，向左转90度就是(di + 3) % 4
        int di = 0;
        // 定义最大值
        int maxRes = 0;

        // 用HashSet来存储障碍物，否则只能强行暴力去遍历很浪费时间
        Set<Long> set = new HashSet<Long>();
        // 将障碍物一个一个加入到hashset中
        for (int[] obstacle : obstacles) {
            long ox = (long) obstacle[0] + 30000;
            long oy = (long) obstacle[1] + 30000;
            set.add((ox << 16) + oy);
        }
        // 定义机器人的坐标，初始值为（0,0）
        int x = 0, y = 0;
        for (int command : commands) {
            // 向右转
            if (command == -1) {
                // di + 1之后对应的diretX和directY就是新的方向
                di = (di + 1) % 4;
            } else if (command == -2) {
                di = (di + 3) % 4;
            } else {
                // 每次走一步
                for (int i = 0; i < command; i++) {
                    int newx = x + directX[di];
                    int newy = y + directY[di];

                    // 看看会不会遇到障碍物，如果不会，那么更新最大值;否则，直接break即可。因为她已经被障碍物挡着了，后面的操作也不用再做了。
                    if (! set.contains((((long) newx + 30000) << 16) + ((long) newy + 30000))) {
                        x = newx;
                        y = newy;
                        maxRes = Math.max(maxRes, x * x + y * y);
                    } else {
                        break;
                    }
                }
            }
        }

        return maxRes;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
