###### HashMap

HashMap内部实现的hash算法

```java
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```

- 先判断key是否为null，如果是返回0
- 先通过key的hashCode()方法获取hash值，该方法每个类对象都拥有
- 将hash值无符号右移16位，然后与hash值进行异或运算得到最终的hash值

 put

```java
 final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
     //1)
     //如果table为null或者length为0，就进行扩容，并获取长度n = tab.length 
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
     //2) 
     //先通过i = (n - 1) & hash 计算key在数组中的索引，其中n是数组的长度，hash是key的哈希值，
     //获取数组中指定索引处的链表中第一个结点Node，如果该结点为null，说明该桶中没有元素，
     //那就直接新建结点，然后转向7)
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            //3)
            //p.hash == hash：2)获取的结点中的key的hash值与参数key的hash值是否相等
            //(k = p.key) == key： 通过 == 判断结点p的key和参数key是否相等
            //(key != null && key.equals(k))：通过equals方法判断两个key是否相等
            //如果三个条件判断返回true，即表示结点p的key和参数key相等
            //（这个相等是通过hash值，== ，及equals相结合判断的），就将结点p赋值给结点e，
            //然后进入6)；如果返回false，进入4)
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            //4)
            //判断取出来的p是不是TreeNode类型，即是不是红黑树；如果是红黑树，就直接插入键值对
            //并将返回结果赋值给e（TreeNode间接继承Node）；否则进入5)
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                //5)
                //死循环中不断的从结点p中取出下一个结点，对这个结点有两个判断
                //1.直到最后一个结点取出来，然后通过参数new出一个新结点并链接到结点p的
                //下一个结点中，接下来判断binCount >= TREEIFY_THRESHOLD - 1，
                //即链表中结点数量如果大于等于8，将链表转为红黑树，最后跳出循环
                //三个判断条件，意思就是判断当前结点的key与参数key是否相等，如果相等，
                //说明你put了一个相同key的键值对，那就退出循环
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            //6)
            //判断结点e是否为null，e的值由前面三步进行赋值；如果不为null，
            //说明在链表或者树中找到了一个结点，其key与参数key相同，那就将参数value
            //替换结点中原value，最后返回原value值结束该方法
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
     //7)
     //走到这一步了说明是new了一个新的结点并插入到数组指定索引（i = (n - 1) & hash）的链表中
     //这里会将结构更改计数器+1，如果数组大小超过了扩容阈值就进行扩容
     //最后通过afterNodeInsertion进行回调
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```

get

```java
   public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
```

get node

```java
final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    //1)
    //数组是否为null，数组长度是否大于0，根据参数计算出的下标并获取的链表中第一个结点first是否为null
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            //2)
            //判断结点first的key和参数key是否相等，相等就返回结点first
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            //3)
            //走到这里说明链表中第一个结点不符合，那就继续往下寻找下一个结点
            //如果下一个结点e不为null继续
            if ((e = first.next) != null) {
                //如果结点e是树，那就通过getTreeNode返回该结点
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                //不断遍历该链表中的下一个结点，并判断key是否相等，如果相等就返回结点e，并结束该循环
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```

小结：

初始容量和负载因子：

初始容量为什么是16而不是12或者10等更小的值，是因为越小出现数据碰撞的几率会越大，不符合均匀分布的原则，同时也是考虑到空间使用效率没有取更大的值

- 负载因子越大：意味着哈希表被装填的越满，越容易导致冲突，性能也就越低；对于一个使用拉链法的链表（比如HashMap）此时查找一个元素的时间复杂度是O(1+n)，n是链表长度，虽然空间利用充分，但是查询效率就变低
- 负载因子越小：意味着哈希表中的数据越松散，需要开辟更多的空间，虽然查询效率高了，但是造成空间浪费；并且哈希表进行扩容的频率会变高，重哈希过程中所有元素的位置可能变化，影响性能

所以系统给出0.75是一个空间和时间上的综合考虑，如果没有特殊需求自己不用修改

红黑树和链表转化：

在理想的随机hashCode下，容器中节点的分布遵循泊松分布。TREEIFY_THRESHOLD 值定为8，因为在正常情况下出现这种现象的几率小到忽略不计。一旦出现，几乎可以认为是哈希函数设计有问题导致的，总是出现相同的hash值，也就是说正常情况下红黑树和链表的转换很难发生。

问题：

- 快速存取实现原理及保证key的唯一性
- HashMap容量为什么总是2的幂次方

jdk7相关

 indexFor return h & (length-1);

16 ：0001 0000

15： 0000 1111

   h:   0101 0101	随机数

&

​         0000 0101	//取值范围低4位0000-1111，十进制0-15

