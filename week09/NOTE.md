学习笔记

**算法训练营结束了，我在此再次感谢覃超老师和助教老师的辛勤付出。因为有你们，我才有机会跟着厉害的人学习。因为有你们，让我内心备受鼓舞，在无数个崩溃的夜晚坚持了下来。因为有你们，作为一个菜鸟的我才真正开始走上了自己逆袭的道路。我真心的说一句：谢谢。**



**接下来是我算法训练营学习的一些心得。**





### 一. 写代码学会写注释

**以下是合并区间这道题目的相关代码：**

```java
for (int i = 0; i < intervals.length; i++) {
    // 得到每个区间的第一个元素
    int first = intervals[i][0];
    // 得到每个区间的第二个元素
    int second = intervals[i][1];
    // 如果合并的列表长度为0或者合并列表的最后一个区间的右区间比当前区间的左区间小
    // 证明他们不可能有交集，直接在结果集中增加一个区间即可。如（3,5），（6,7）
    if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < first) {
        merged.add(new int[]{first, second});
    } else {
        // 否则，就要合并区间，如（3,5），（4,6）或者（3，7）（4,5）
        merged.get(merged.size() - 1)[1] = Math.max(second, merged.get(merged.size() - 1)[1]);
    }
}
```



**以下是最长公共子序列这道题目的相关代码：**

```java
for (int i = 1; i < length1; i++) {
    for (int j = 1; j < length2; j++) {
        // 1.
        // 如果当前两个字符串目前为止最后一个字符相等，那么我只需要知道他们两个的子序列的公共长度，再加1即可
        // 比如 ****A 和 %%%%%A,A和A相等了，那么只需要看****和%%%%的最长公共子序列是多少然后加上1即可
        // 2.
        // 否则，假如现在是****A，%%%%B，就要看****A和%%%%的公共子序列长度与****和%%%%B的最大值是多少
        if (t1[i - 1] == t2[j - 1]) {
            dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
    }
}
```



### 二. N皇后II 位运算讲解

**1. 先贴上整体代码：**

```java
// 边界条件
if (n < 1) {
	return 0;
}

dfs(n, 0, 0, 0, 0);
return count;
```

```java
public void dfs(int n, int row, int lie, int pie, int na) {
    // 递归终止条件, 每一次能够走到最后一行都对应N皇后的一种解法
    if (row >= n) {
        count+=1;
        return;
    }

    int bits = (~(lie | pie | na)) & ((1 << n) - 1);              // (1)

    // 循环开始判断
    while (bits != 0) {
        // 取到最低的那一位
        int p = bits & -bits;                                     // (2)
        // 既然决定将p这个位置放上皇后，那bits中自然要去掉他
        bits = bits & (bits - 1);                                 // (3)

        // 到这里位置都是在处理当前层逻辑

        dfs(n, row + 1, lie | p, (pie | p) << 1, (na | p) >> 1);  // (4)

        // 不需要回退lie, pie, na的状态，因为我是去与，并没有真正改掉他们的值。
    }
}
```



**2.代码注释中（1）处代码讲解：**

```
N皇后，我们就可以用二进制的N位来表示。如8皇后，0 0 0 1 0 0 1 0 表示当前层从左往右第4个格子和第7个格子是可以放皇后的，其他格子放进去都会有冲突。
所以，每一层进来，我们都要求出当前层这个二进制数来知道当前层是有哪些位子已经被占有了，哪些位子可以放皇后。

代码中   lie,  pie, na我们知道分别代表了当前层同一列，右斜上， 左斜上。有哪些位子是不能放皇后的， 那么 （lie | pie | na) 就可以得到当前层哪些位子是不能放皇后的。下面举的例子我们都以8皇后为例。

如：若 (lie | pie | na) = 000000000000000000000000 10101001
前面一大串0是因为int类型是32位的，所以对于8皇后而言，前面的24位都是无用的。上面的情况易知当前层第1个，第3个，第5个，第8个格子都不可以放皇后。

所以我们对其取反：
则:(~(lie | pie | na)) = 111111111111111111111111 01010110, 这样最低的8位，为1的地方就是当前层可以放皇后的位置。但是这个操作做了之后，前面的24位也变成了1。而这些1是我们不需要的。所以要想办法将其去掉。

(1 << n) = 000000000000000000000001 00000000 (还是以8皇后为例，这里的n就是8)
```

```
那不难得出：
((1 << n) - 1) = 000000000000000000000000 11111111。
(~(lie | pie | na)) = 111111111111111111111111 01010110

(~(lie | pie | na)) & ((1 << n) - 1) = 000000000000000000000000 01010110 = bits

这个时候算出来的bits, 二进制位为1的位就真正代表可以放皇后了。
```



**3.代码注释中（2）处代码讲解：**

```
上面代码我们得到了哪些位置可以放皇后。再来回顾下那个二进制串：

000000000000000000000000 01010110 = bits

代码（2）这个操作就是位运算操作，可以得到二进制中最低位的那个1
bits & -bits = 000000000000000000000000 00000010 = p = 2(十进制)
```



**4.代码注释中 （3）处代码讲解：**

```
000000000000000000000000 01010110 = bits

// 既然决定将p这个位置放上皇后，那bits中自然要去掉他，这样能够保证回溯回来的时候这个位子也不能再用了
 bits = bits & (bits - 1) = 000000000000000000000000 01010100
```



**5.代码注释中（4）处代码讲解：**

```
dfs(n, row + 1, col | p, (pie | p) << 1, (na | p) >> 1);

col | p就是告诉下一层，有哪些列是被占着的。
pie | p就是告诉下一层，有些pie的位置是被占着的。但是我们这个是二进制位注意，pie的位子下面一层是感知不到的。举个例子：
假如当前层取到的p的位子是00001000，第五个格子放了皇后了，那么对于pie而言，下一层不能访问的是第4个格子，也就是00010000这样的表示：
00001000
00010000
所以 (pie | p) 要左移一位

na正好相反：
00001000
00000100
所以 (na | p) 要右移一位
```



### **三. N皇后I 位运算讲解**

```java
// 定义结果集
List<List<String>> res = new ArrayList<>();

public List<List<String>> solveNQueens(int n) {
    // 边界条件
    if (n < 1) {
        return res;
    }

    // 申请一个char棋盘
    char[][] chess = new char[n][n];
    // 初始化棋盘
    for (char[] everyRow : chess) {
        Arrays.fill(everyRow, '.');
    }

    // 开始回溯
    dfs2(n, 0, 0, 0, 0, chess);
    return res;
}
```



```java
public void dfs2(int n, int row, int lie, int pie, int na, char[][] chess) {
    if (row >= n) {
        res.add(transfer(chess)); // 此处transfer只是把二维的char变成List<String>
        return;
    }

    // 得到当前层的bits
    int bits = (~(lie | pie | na)) & ((1 << n) - 1);

    int count = 0;
    while (bits != 0) {
        int p = bits & -bits;   
        
        int index = (int) (Math.log(p) / Math.log(2));  // （1）根据当前p求出1在二进制中的位置
       
        bits = bits & (bits - 1);
        
        chess[row][n - index - 1] = 'Q';

        dfs2(n, row + 1, lie | p, (pie | p) << 1, (na | p) >> 1, chess);
        
        chess[row][n - index - 1] = '.';
    }
}
```

**1.代码注释中（1）位置的代码讲解**：

```java
我们每一层都要在当前行的某一列放一个皇后，所以我们要根据p这个数字求出他对应的二进制中1所在的位置，1的位置就是代表当前行皇后的列。举个例子：
00000010， 这个二进制数的十进制代表2。
实际上就是 1 * 2^1 = 2; 也就是1的位置是从右往左第1位（下标从0开始）。
    
而我们要求的实际上就是根据十进制的2求这个1 （指数），也就是对应二进制中1所在位置。这个就是用到了取对数
指数 = log2 (P) = log2 (2) = 1

所以我们根据p就可以求出指数，也就是二进制中1的位子，也就是可以放皇后的下标（列）
index = Math.log(p) / Math.log(2) = log2 (P)
log2 (P) = loge (p) / loge (2);

刚刚也说到，对00000010这个数取对数得到的1。这个是从右往左的位置，但我们的列数下标是要从左往右的
col = n - index - 1;
col = 8 - 1 -1 = 6; 
这样符合我们的预期
00000010 本就代表第7列,也就是数组中下标为6的地方可以放皇后
```

