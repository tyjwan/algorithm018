package Algorithm.leetcode.leetcode.editor.cn;
//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 472 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Trie {
    // 题目链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        // 拿到树的根节点
        TrieNode node = root;
        // 将字符串转化为字符数组，方便遍历
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char currentChar = wordArray[i];
            // 如果当前node节点也就是数组中没有这个字符，那么我们加入tries树即可
            if (! node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }

            // 因为是插入，无论找得到还是找不到，都要往下面一层去。
            // 当前层找得到这个字符，往下面层去看后面的字符是否能插入进去
            // 当前层找不到这个字符，将它加入到当前层，再下探到下一层插入后面的字符
            node = node.get(currentChar);
        }
        // 插入完了，最后要将node的是否是最后一层的标志位 置为true
        node.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        // 拿到树的根节点
        TrieNode node = root;
        // 将字符串转化为字符数组，方便遍历
        char[] wordArray = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char currentWord = wordArray[i];
            // 如果当前层包含所对应的单词
            if (node.containsKey(currentWord)) {
                // 则下探到下一层,也就是获取当前字符的儿子节点
                node = node.get(currentWord);
            } else {
                // 否则当前层不包含对应的字符，那么后面的也不用看了，当前tries树里不存在这个单词。
                return false;
            }
        }

        return node.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        // 拿到树的根节点
        TrieNode node = root;
        // 将字符串转化为字符数组，方便遍历
        char[] prefixArray = prefix.toCharArray();

        for (int i = 0; i < prefixArray.length; i++) {
            char currentWord = prefixArray[i];
            // 如果当前层有这个字符
            if (node.containsKey(currentWord)) {
                // 下探到下一层
                node = node.get(currentWord);
            } else {
                // 否则，只要有一个在某一层找不到，他都不是前缀
                return false;
            }
        }

        return true;
    }
}

class TrieNode {
    // 当前节点的所有儿子
    private TrieNode[] children;
    // 26个字母，所以当前场景下，children数组的最大长度为26
    private final int R = 26;

    private boolean isEnd;

    public TrieNode() {
        children = new TrieNode[R];
    }

    // 当前层是否包含字符
    public boolean containsKey(char ch) {
        return children[ch - 'a'] != null;
    }

    // 得到下一层的所有儿子节点
    public TrieNode get(char ch) {
        return children[ch - 'a'];
    }

    // 插入操作
    public void put(char ch, TrieNode node) {
        children[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
