package Algorithm.leetcode.leetcode.editor.cn;
//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法 
// 👍 289 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
//class TrieNode {
//    // 当前节点的所有儿子
//    private Map<Character, TrieNode> children;
//
//    private boolean isEnd;
//
//    public TrieNode() {
//        children = new HashMap<>();
//    }
//
//    // 当前层是否包含字符
//    public boolean containsKey(char ch) {
//        return children.containsKey(ch);
//    }
//
//    // 得到下一层的所有儿子节点
//    public TrieNode get(char ch) {
//        return children.get(ch);
//    }
//
//    // 插入操作
//    public void put(char ch, TrieNode node) {
//        children.put(ch, node);
//    }
//
//    public void setEnd() {
//        isEnd = true;
//    }
//
//    public boolean isEnd() {
//        return isEnd;
//    }
//}

class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    String word = null;

    public TrieNode() {
    }
}

class Solution {
    // 题目链接：https://leetcode-cn.com/problems/word-search-ii/
    public TrieNode root;
    public List<String> res;
    public int[] directX = {0, 0, -1, 1};
    public int[] directY = {-1, 1, 0, 0};
    char[][] myBoard;

    public List<String> findWords(char[][] board, String[] words) {
        // 思路应该是正确的，但是这个方法超时
//        return func1(board, words);

        // 思路二：
        // 边界条件
        // 时间复杂度：O(M*N*(3^(L-1)). M,N是格子的行数和列数。3是代表每个格子除了刚刚来过的地方还有三个方向可以走，走多久呢？单词有多长就会走多久。所以L是单词的平均长度。
        // 空间复杂度：额外开了一个二维数组。所以空间复杂度是O(M*N)
        return func2(board, words);
    }

    private List<String> func2(char[][] board, String[] words) {
        if (board.length == 0) {
            return res;
        }

        res = new ArrayList<>();
        // 申请一个字典树
        root = new TrieNode();
        // 将每个单词加入到字典树中
        for (String word : words) {
            TrieNode node = root;
            char[] currentWords = word.toCharArray();
            for (char currentWord : currentWords) {
                // 如果当前层不包含这个key
                if (!node.children.containsKey(currentWord)) {
                    // 将其加入到字典树中
                    node.children.put(currentWord, new TrieNode());
                }
                // 无论怎样，我都要往下面一层走
                node = node.children.get(currentWord);
            }
            // 每个字符加入完，最后的这个节点存放整个单词的值
            node.word = word;
        }

        // 得到面板的行和列
        int row = board.length;
        int col = board[0].length;

        myBoard = board;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 申请一个标记数组用于在回溯过程中标记哪些格子是访问过的
                boolean[][] mark = new boolean[row][col];
                // 如果当前root的字典里包括当前遍历到的字符，递归回溯进行寻找单词的工作
                if (root.children.containsKey(board[i][j])) {
                    dfs(i, j, root);
                }
            }
        }

        return res;
    }

    /**
     * 思路二：递归回溯剪枝，trieNode简单
     *
     * @param row  当前遍历的行
     * @param col  当前遍历的列
     * @param node 当前字典树的节点
     */
    private void dfs(int row, int col, TrieNode node) {
        // 首先能够进来dfs的，一定是当前行和列对应的字符在字典树中的，这个是由上层函数以及当前函数后面的判断语句决定的
        // 得到当前递归到的字符
        char currentWord = myBoard[row][col];
        // 得到这一层字符对应的新node
        TrieNode currNode = node.children.get(currentWord);

        // 如果已经找到单词了
        if (currNode.word != null) {
            // 将其加入结果集
            res.add(currNode.word);
            // 将其赋值为空，以防重复加入到结果集，这里也可以用另外一种方式，判断列表中是否存在这个单词，我们就不用去修改这棵字典树了。
            currNode.word = null;
        }

        // 将当前层的格子对应的值修改为#，以免递归重复访问
        myBoard[row][col] = '#';
        // 这上面都是在处理当前层逻辑

        // 循环遍历四个方向（上下左右）进行递归回溯
        for (int i = 0; i < 4; i++) {
            int newRow = row + directX[i];
            int newCol = col + directY[i];

            if (newRow < 0 || newRow >= myBoard.length || newCol < 0 || newCol >= myBoard[0].length) {
                continue;
            }
            if (currNode.children.containsKey(myBoard[newRow][newCol])) {
                dfs(newRow, newCol, currNode);
            }
        }
        // 这里为止是递归调用

        // 这一层结束了，需要将这一层所代表的的格子的值重新赋值回去,这是将当前层状态回退
        myBoard[row][col] = currentWord;

        // 如果已经遍历到最后的叶子节点，将叶子节点删掉，也可以避免之后花多余的时间去遍历他
        // 这里也可以叫剪枝
        if (currNode.children.isEmpty()) {
            node.children.remove(currentWord);
        }
    }

    private List<String> func1(char[][] board, String[] words) {
        // 边界条件判断
        if (board.length == 0) {
            return res;
        }

        // 初始化结果集
        res = new ArrayList<>();

        // 申请一棵前缀树（字典树）
        root = new TrieNode();
        // 遍历单词数组，将所有单词加入到字典树中
        for (int i = 0; i < words.length; i++) {
//            insertWord(words[i]);
        }

        // 得到矩阵面板的行数和列数
        int row = board.length;
        int col = board[0].length;

        // 开始进行回溯
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 定义一个二维数组用于标记哪些地方走过了，走过的地方我们就不用再走了
                boolean[][] mark = new boolean[row][col];
                StringBuilder sb = new StringBuilder();
//                findHelper(mark, board, i, j, res, sb);
            }
        }

        return res;
    }

    /**
     * 回溯函数
     *
     * @param mark 标记数组，用于标记哪些格子已经访问过了
     * @param row  当前格子行数
     * @param col  当前格子列数
     * @param res  当前结果集
     * @param sb   回溯迭代到的当前单词
     */
//    private void findHelper(boolean[][] mark, char[][] board, int row, int col, List<String> res, StringBuilder sb) {
//        // 如果以前访问过这个格子
//        if (mark[row][col]) {
//            // 直接返回
//            return;
//        }
//
//        // 否则，将当前字符加入到当前单词中
//        sb.append(board[row][col]);
//        // 如果字典树中找不到这个前缀，我们再往下走也不可能找得到我们要的单词
//        if (!startsWith(sb.toString())) {
//            // 将sb中刚刚加进来的字符去掉
//            sb.deleteCharAt(sb.length() - 1);
//            // 然后返回
//            return;
//        }
//
//        // 否则证明字典树中是有这个前缀的
//        // 将当前格子标记为true，也就是我们访问过了
//        mark[row][col] = true;
//        // 通过sb得到当前的单词
//        String currentWord = sb.toString();
//
//        // 至此，前面2个if其实都是递归出口
//
//
//        // 下面就是处理当前层逻辑
//        // 如果字典树能够找到这个单词(注：找到单词有两个条件，一个是单词的所有字符在字典树的路径上，二是最后得到的node的isEnd为true）
//        if (search(currentWord)) {
//            // 如果结果集不包含这个结果
//            if (!res.contains(currentWord)) {
//                // 将这个单词加入到结果集中
//                res.add(currentWord);
//            }
//
//            // 为了以防出现dog,dogs两个单词的情况，我们还要往下去遍历。
//        }
//
//        // 这下面就是递归调用进行回溯
//        for (int i = 0; i < 4; i++) {
//            // 得到新的格子的坐标
//            int newRow = row + directX[i];
//            int newCol = col + directY[i];
//
//            // 如果格子坐标是满足要求的
//            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
//                // 则递归进行回溯运算
//                findHelper(mark, board, newRow, newCol, res, sb);
//            }
//        }
//
//        // 下面的操作就是将当前层状态回退
//        sb.deleteCharAt(sb.length() - 1);
//        mark[row][col] = false;
//    }

    /**
     * 往字典树中插入单词
     *
     * @param word 要插入的单词
     */
//    private void insertWord(String word) {
//        TrieNode node = root;
//        char[] wordArray = word.toCharArray();
//        for (int i = 0; i < wordArray.length; i++) {
//            char currentWord = wordArray[i];
//            if (!node.containsKey(currentWord)) {
//                node.put(currentWord, new TrieNode());
//            }
//            node = node.get(currentWord);
//        }
//        node.setEnd();
//    }

//    private boolean search(String word) {
//        TrieNode node = searchPrefix(word);
//        return node != null && node.isEnd();
//    }

//    private boolean startsWith(String word) {
//        TrieNode node = searchPrefix(word);
//        return node != null;
//    }

//    private TrieNode searchPrefix(String word) {
//        TrieNode node = root;
//        char[] wordArray = word.toCharArray();
//        for (int i = 0; i < wordArray.length; i++) {
//            char currentWord = wordArray[i];
//            if (node.containsKey(currentWord)) {
//                node = node.get(currentWord);
//            } else {
//                return null;
//            }
//        }
//
//        return node;
//    }
}

//leetcode submit region end(Prohibit modification and deletion)
