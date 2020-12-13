### 一. 冒泡排序模板

```java

// 冒泡排序，a表示数组，n表示数组大小
public void bubbleSort(int[] a, int n) {
  if (n <= 1) return;
 
 for (int i = 0; i < n; ++i) {
    // 提前退出冒泡循环的标志位
    boolean flag = false;
    for (int j = 0; j < n - i - 1; ++j) {
      if (a[j] > a[j+1]) { // 交换
        int tmp = a[j];
        a[j] = a[j+1];
        a[j+1] = tmp;
        flag = true;  // 表示有数据交换      
      }
    }
    if (!flag) break;  // 没有数据交换，提前退出
  }
}
```



### 二. 插入排序模板

```java

// 插入排序，a表示数组，n表示数组大小
public void insertionSort(int[] a, int n) {
  if (n <= 1) return;

  for (int i = 1; i < n; ++i) {
    int value = a[i];
    int j = i - 1;
    // 查找插入的位置
    for (; j >= 0; --j) {
      if (a[j] > value) {
        a[j+1] = a[j];  // 数据移动
      } else {
        break;
      }
    }
    a[j+1] = value; // 插入数据
  }
}
```



### 三. 希尔排序模板

```java
private static void shellSort(int[] arr) {
    int len = arr.length;
    if (len == 1) return;

    int step = len / 2;
    while (step >= 1) {
      for (int i = step; i < len; i++) {
        int value = arr[i];
        int j = i - step;
        for (; j >= 0; j -= step) {
          if (value < arr[j]) {
            arr[j+step] = arr[j];
          } else {
            break;
          }
        }
        arr[j+step] = value;
      }

      step = step / 2;
    }
  }
```



### 四. 选择排序模板

```java
// 选择排序，a表示数组，n表示数组大小
    public static void selectionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n - 1; ++i) {
            // 查找最小值
            int minIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            // 交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }
```



学习体会：训练营第8周，无论是老题还是新题，解题的速度都比以前更快了，而且我觉得在这个过程中自己的理解能力，学习能力有了很大的提升。人生最有意义的事情莫过于知道自己的位置，然后想要去什么位置，最后付诸努力。继续加油，向目标进军。