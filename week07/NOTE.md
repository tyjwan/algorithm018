学习笔记

### 一. 字典树模板代码

```java
class TrieNode {
    private TrieNode[] trieNode;

    private final int len = 26;

    private boolean isEnd = false;

    public TrieNode() {
        trieNode = new TrieNode[len];
    }

    public boolean containsKey(char ch) {
        if (trieNode[ch - 'a'] == null) {
            return false;
        }
        return true;
    }

    public void put(char ch, TrieNode node) {
        trieNode[ch - 'a'] = node;
    }

    public TrieNode get(char ch) {
        return trieNode[ch - 'a'];
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean getEnd() {
        return isEnd;
    }
}
```

```java
class Trie {
    public TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    private void insertWord(String word) {
        TrieNode node = root;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            char currentWord = wordArray[i];
            if (!node.containsKey(currentWord)) {
                node.put(currentWord, new TrieNode());
            }
            node = node.get(currentWord);
        }
        node.setEnd();
    }

    private boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    private boolean startsWith(String word) {
        TrieNode node = searchPrefix(word);
        return node != null;
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            char currentWord = wordArray[i];
            if (node.containsKey(currentWord)) {
                node = node.get(currentWord);
            } else {
                return null;
            }
        }

        return node;
    }
}
```



### 二. 并查集代码模板

```java
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
```

这两个周真的感觉自己收获特别多，做了很多题目，的的确确发现过遍数之后对于一些算法，题目的理解不知道清晰了多少倍，而且这两周都是一些高阶的算法和数据结构，这种成长的喜悦是其他任何事情都无可替代的，谢谢超哥和助教老师一直以来的帮忙和辛劳。真的谢谢。