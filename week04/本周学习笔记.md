学习笔记第四周

### 一. 总结作业题

####   1. 题目描述

​		使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方



####   2. 思路描述

​		这样一个半有序数组其实是有规律的，抓住了最核心的规律这个问题就不难解决了。我们可以看到，虽然他是半有序的，但是他分别有序的两个区间都是递增（或者递减，看题目要求了）的。而偏偏在中间点也就是无序的边界点，他会变成递减的。也就是7 > 0, 或者 0 < 7, 翻译成代码就是如下所示的两句核心代码：

```java
nums[i] > nums[i + 1];
nums[i] < nums[i - 1];
```

​		所以我们思路再清晰一点就是如下：

​		（1）. 利用二分，每次计算出mid下标

​        （2）. 看nums[mid]  > nums[0]是否成立，如果成立，代表左边这一段是有序的，那么我就要到右边去寻找边界点。如果不成立，代表右边是有序的，那么我就要到左边去寻找边界点。

 	      (3)  . 重复以上操作直到找到为止

​		代码如下所示：

```java
 /**
         * 方法一：二分法
         *
         * 时间复杂度：O(logN), N即为数组元素的个数
         * 空间复杂度：O(1)
         */

        if (nums.length == 1) {
            return nums[0];
        }

        // 定义左边界，有边界，初始条件下当然就为0，和nums.length-1，也就是数组的初始位置和终止位置
        int left = 0, right = nums.length - 1, len = right;

        // 筛选出数组本身有序的情况
        if (nums[right] > nums[left]) {
            // 数组本身有序的情况下，第一个元素即为数组的最小元素
            return nums[left];
        }

        // 当左边界小于有边界时继续循环（这就是二分模板记住即可）
        while (left <= right) {
            // 算出中间节点的下标，这里用变成left + (right - left) / 2是为了防止left+right超出范围
            // 用移位操作是因为计算机对于位运算更友好，更快
            int mid = left + ((right - left) >> 1);

            // 这就是边界点, 边界点就是数组的值不随下标的增大而增大的那两个点
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            // 如果左边是有序的
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
```

​		其中代码里我注释都写的非常详细，唯有一个地方需要单独拿出来讲一下：

```
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
```

​		这里的两句if是**不能更换位置**的。

​        为什么？

​        因为java的除法是**向下取整**的，假如我们的序列现在是如下序列：

​        1	6	5	4	3	2

​        那么最后的mid就会收敛到下标为0的这个位置，如果你后一句if写前面，那就会报数组下标越界的错误。



#### 二. 作业题感想

1. 单词接龙

   这道题目个人认为官方的解法不是很优，因为他每次都是比对单词，这样的话时间复杂度就是O(N^2), 实际上我们只需要将旧单词的每一位从a更换到z，看看在不在set集合里面即可。这样复杂度可以降为O(N * 26)。具体代码如下:

   ```java
   // 遍历字符数组进行字母替换，看能不能到达endWord
           for (int i = 0; i < currentWordArray.length; i++) {
               // 记录当前字符，之后如果替换当前位置的字符找不到，要还原进行下一个字符的寻找的。（这样可以满足题目要求，只允许更改一个字符）
               char remember = currentWordArray[i];
               // 遍历26个字母对其进行替换
               for (char k = 'a'; k <= 'z'; k++) {
                   // 如果遍历到的字母就是原先的字母，直接跳过此次循环
                   if (k == remember) {
                       continue;
                   }
   
                   // 将当前的字母赋值给字符数组对应的位置得到更改一个字母后的新字符数组
                   currentWordArray[i] = k;
                   // 将其转化为新的字符串
                   String newWord = String.valueOf(currentWordArray);
   
                   // 如果新字符串就等于endWord证明我们已经找到了这条路径直接返回true即可
                   if (newWord.equals(endWord)) {
                       return true;
                   }
                   // 如果单词表里包含这个词并且我们没有访问过这个词
                   if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                       // 将其加入到队列中，并且更新visite哈希表
                       queue.offer(newWord);
                       visited.add(newWord);
                   }
               }
   
               // 恢复位置的字母，要进入下一轮循环了
               currentWordArray[i] = remember;
           }
   ```

   