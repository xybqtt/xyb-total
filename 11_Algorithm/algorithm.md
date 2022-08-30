@[TOC](Spring boot)

# 0 格式
## 0.0 格式要求
html代码后面一定加一行空行；
标题后面需要添加空行；
https://www.cnblogs.com/cndarren/p/14415213.html
视频地址：https://www.bilibili.com/video/BV1o34y197fz?p=13&spm_id_from=pageDriver
每个大标题之间空2行，每个小标题之间空1行
![avatar](pictures/1-1.png)
<span style="color: red;"></span>
<hr style="height: 10px; background: green;"/>



# 1 异或

a ^ b，表示a、b相同返回0，不相同返回1，相当于无进位相加。

符合以下规律：
- a ^ b = b ^ a交换率
  - 从无进位相加角度考虑，最终结果仅与此位上1的个数相关，不与顺序相关，所以满足交换率；
- a ^ a = 0
  - (a ^ a) ^ (a ^ a) ^ ... ^ (a ^ a) = 0 ^ 0 ^ ... ^ 0 = 0，所以任意偶数个a异常结果是0。
- a ^ 0 = a
  - a ^ 0 = a ^ (a ^ a) ^ ... ^ (a ^ a) = a，所以任意奇数个a异或的结果是a。
- a ^ b != 0，则必定在某一位上，2个数的值不等。
  - 如果要等于0，则必定每一位都必须相等，如果至少有一位不为0，则结果必不为0。

- 可以用于交换2个数(前提2个数不能指向同一块内存)：
~~~
a = a ^ b; 此时 a = a ^ b，b = b
b = a ^ b; 此时 b = a ^ b ^ b = a ^ 0 = a，a = a ^ b
a = a ^ b; 此时 a = a ^ b = (a ^ b) ^ a = a ^ a ^ b = 0 ^ b = b。
~~~



# 2 排序
## 2.1 选择排序
## 2.2 冒泡排序
## 2.3 插入排序
## 2.4 归并排序
### 2.4.1 
### 2.4.2 小和问题

数组arr：1、2、5、6、7、4
**小和问题**：在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。

### 2.4.3 逆序对问题

逆序对问题：在一个数组中，如果左边的数 > 右边的数，则这2个数构成一个逆序对，求逆序对的数量。
与小和问题刚好相反。

## 2.5 快速排序
## 2.6 堆排序
## 2.7 计数排序
## 2.8 桶排序
## 2.9 排序算法稳定性及其汇总

排序稳定性：同样值的个体之间，如果不因为排序后改变相对次序，这个排序就是稳定的。

不具备稳定性的排序：选择、快速、堆。
具备稳定性的排序：冒泡、插入、归并、一切桶排序思想下的排序。

目前没有找到时间复杂度O(N*logN)，额外空间复杂度o(1)，又稳定的排序。

|排序算法|时间复杂度|空间复杂度|排序稳定性|
|:--|:--|:--|:--|
|选择|O(N*N)|O(1)|×|
|冒泡|O(N*N)|O(1)|√|
|插入|O(N*N)|O(1)|√|
|归并|O(N*logN)|O(N)|√|
|快排(推荐)|O(N*logN)|O(logN)|×|
|堆|O(N*logN)|O(1)|×|

基于比较的排序，时间复杂度不会低于O(N*logN)。当时间复杂度为O(N*logN)时，空间复杂度低于O(N)时，排序一定不稳定。

排序常见的坑：
- 归并排序的额外空间复杂度可以变成O(1)，但是非常验证，搜索"归并排序 内部缓存法"，但是会丧失稳定性，那和堆排序就没有区别了，所以不实用。
- "原地归并排序"，会让归并的时间复杂度变为O(N*N)。
- 快排可以做到稳定性，但非常难，不需要掌握，"O1 stable sort"。
- 所有的改进都不重要，因为目前没有找到时间复杂度O(N*logN)、空间复杂度O(1)，又稳定的排序。
- 有一道题目，把奇数放数组左边，偶数放数组右边，还要求原始相对次序不变，依靠这几种排序算法无法实现，不符合上一个的结论。

# 3 链表
## 3.1 单链表判断回文

不考虑空间复杂度：
- O(N)、O(N)节点都压栈，再次遍历链表，与弹栈数据比较是否完全一样。
- O(N)、O(N)快慢指针：慢指针走到一半，剩下Node压栈，重复上面的操作，比上面省一半空间。

考虑空间复杂度：
- O(N)、O(1)快慢指针：慢指针走到一半，后半截链表反转后，headNode、tailNode节点遍历对比。

## 3.2 单链表按某值划分为<、=、> 3个区

不考虑空间复杂度，很简单，用3个List分别存入，拼接。
考虑空间复杂度：用3对节点，分别存入这3个区域的head、tail，遍历链表，把数据按要求放到这3对head、tail里面，最后把6个节点连接起来，注意这3个区域可能有空区域情况。

## 3.3 复制含有随机指针节点的链表

即Node除了指向下一个，还有一个rand指针随机指向链表中的另一个元素或null。

不考虑空间复杂度：
- 用Map<原Node, 新Node>，遍历新Node的时候，找到rand(此时与原Node指向同一个原Node)对应的新Node，赋值给新Node.rand。

考虑空间复杂度：
- 看代码吧，原理与用Map差不多。

## 3.4 有环单链表的环起点确定

不考虑空间复杂度：Map。
考虑空间复杂度：快慢指针，当快慢指针第一次相遇时，快指针放到链表头，慢指针进1，然后2个一起走，一次都走一步，下次相遇时就是环起点。

## 3.5 2个单链表相交问题

2个链表如果相交，要么2个都有环，要么都没有环。
无环相交：
- 查看最后一个节点是否是同一个
  - 不是：不相交。
  - 是：2个链表从头开始遍历，长链表先走n个(n = 长链表长度 - 短链表长度)，然后2个链表每走一步进行一次判断2个链表上的Node是否相同，直到碰到相同的Node，就是2链表交点。

有环相交：
- 先获取2链表各自环起点，查看是否是同一个
  - 是：则交点问题就是上面的无环链表相交问题。
  - 不是：遍历l1的环，每个与l2作比较，
    - 如果有一样的，就相交，并且此点是环起点。
    - 没有一样的，就不相交。

# 4 二叉树
## 4.1 二叉树的递归序遍历

~~~
f(node) {
    // line1
    f(node.left);
    // line2
    f(node.right);
    // line3
}
~~~
在每次递归遍历时，有3次操作node的机会，选择在不同的时机打印，可以成功先、中、后序遍历的一种。即使对于叶节点，也会有3次机会。
先序遍历：对每一颗子树，打印顺序为"头、左、右"。
中序遍历：对每一颗子树，打印顺序为"左、头、右"。
后序遍历：对每一颗子树，打印顺序为"左、右、头"。

## 4.2 非递归的3种遍历

任何递归方式都可以改为非递归，递归是系统帮忙压栈，非递归自己压栈。










