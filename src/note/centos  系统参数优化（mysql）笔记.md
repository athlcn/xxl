##### centos  系统参数优化（mysql）

------



- 网络参数配置

  配置文件/etc/sysctl.conf

  net.core.somaxconn=65536 #每个监听端口允许的最大的监听队列长度，这个值应该适当增大。

  net.core.netdev_max_backlog=65536 #当网络端口接收数据包的速率比内核处理数据速度快时，允许发送到对列中的最大包数。

  net.ipv4.tcp_max_syn_backlog=65536 #还未获得链接的请求可保持在队列中的数量，当超过这个数目是，链接请求就可能被抛弃。

  net.ipv4.tcp_fin_timeout=10 #tcp链接等待时间，调整这个参数目的是为了加快TCP链接回收的速度。

  net.ipv4.tcp_tw_reuse=1 #加快TCP链接回收的速度。

  net.ipv4.tcp_tw_recycle=1 #加快TCP链接回收的速度

  以下四个参数设置tcp链接接收和发送数据时缓冲区大小的最大值和和默认值，应该将其调整的更大一些。

  net.core.wmem_default=87380

  net.core.wmem_max=16777216

  net.core.rmem_default=87380

  net.core.rmem_max=16777216

  以下三个参数用于减少时效的TCP链接所占用的资源，提升资源回收效率，改的较小一些。

  net.ipv4.tcp_keepalive_time=100 #代表TCP发送keepalive探测消息的时间间隔

  net.ipv4.tcp_keepalive_intvl=10 #代表当TCp发送keepalive探测消息未获得确认是重发探测消息的时间间隔

  net.ipv4.tcp_keepalive_probes=3 #代表TCP链接失效之前，最多发送多少keepalive探测消息

- 内存相关参数

  修改/etc/sysctl.conf

  kernel.shmmax=2147483648用于定义单个共享内存段的最大值，这个参数应该设置的足够大，以便共享内存段能容纳下整个InnoDB缓冲池的大小。建议设置成物理内存的一半。

  vm.swappiness=0 表示除非虚拟内存使用完了，否则不要是要swap分区。

  配置sysctl.conf 立即生效  sudo /sbin/sysctl -p 

- 特定资源限制的配置

  修改配置文件/etc/security/limits.conf

  主要的就是对打开文件数量的配置，在MySQL的InnoDB存储引擎中，每一张表就对应着2-3个文件，对于允许打开文件的数量适当的增大，可以是MySQL的数据库性能更好。

  打开文件数的配置

  *soft nofile 65535

  *hard nofile 65535

  *表示对所有用户生效。soft 代表是当前系统生效的设置，hard 代表系统中所能设定的最大值，nofile 表示所限制的资源是打开文件的数量

- 修改磁盘调度策略

  磁盘I/O，Linux提供了cfq, deadline和noop三种调度策略

  cfq: 这个名字是Complete Fairness Queueing的缩写，它是一个复杂的调度策略，按进程创建多个队列，试图保持对多个进程的公平（这就没考虑读操作和写操作的不同耗时）

  deadline: 这个策略比较简单，只分了读和写两个队列（这显然会加速读取量比较大的系统），叫这个名字是内核为每个I/O操作都给出了一个超时时间

  noop: 这个策略最简单，只有单个队列，只有一些简单合并操作

  最后期限算法(Deadline)除了维护了一个拥有合并和排序功能的请求队列外，额外维护了两个队列，分别是读请求队列和写请求队列，他们都是带有超时的请求队列，当新来一个IO请求时，会被同时插入普通队列和读写队列，然后I/O调度器正常处理普通队列中的请求。当调度器发现读写请求队列中的请求超时的时候，会优先处理这些请求，保证尽可能不产生饥饿请求。对于MYSQL来说，建议设置为Deadline，对MYSQL来说是很好的调度算法。
  查看当前系统支持的磁盘IO调度算法

  dmesg | grep -i scheduler

  more/sys/block/sda/queue/scheduler

  一般默认情况下，CentOS系统的磁盘调度策略是完全公平队列模式（cfq），这种调度策略对于数据库服务器来说不太适合，而应该使用deadline调度策略。通过以下命令可以修改调度策略：

  echo deadline>/sys/block/devname/queue/scheduler









 













