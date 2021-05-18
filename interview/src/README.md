# 慕课网面试直通车
## 6.3 如何加载.class文件
javap命令查看字节码
JVM内存模型
- Class Loader 根据特定格式，加载class文件到内存
- Execution Engine  
  对命令进行解析
- Native Interface  
  融合不同开发语言的原生库为Java所用
- Native Libraries
---
## 6.4 什么是反射
JAVA 反射是在运行状态中，对于任意一个类，都可以知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；
这种动态调用对象的功能成为Java语言的反射机制.  
举例:ReflectSample
```
Class rc = Class.forName("pers.xiaoan.interview.javabasic.reflect.Robot");
Robot r = (Robot) rc.newInstance();
System.out.println("Class name is " + rc.getName());

Method getHello = rc.getDeclaredMethod("throwHello", String.class);
getHello.setAccessible(true);
Object str = getHello.invoke(r, "Bob");
System.out.println(str);
```
- 获取方法
> - Method method = getMethod,getDeclaredMethod   
> - setAccessible(true)
> - Object obj = method.invoke:传入参数，获取私有方法返回值

---
## 6.5 谈谈ClassLoader
> 负责将二进制
- 种类
> - BootStrapClassLoader: C++编写
> - ExtClassLoader: Java编写,加载扩展库 javax.*
> - AppClassLoader: 加载程序所在目录
> - 自定义ClassLoader: Java编写，  
> 关键函数 findClass, defineClass
---
## 6.6 ClassLoader的双亲委派机制

---

## 6.7 loadClass和forName的区别
- loadClass  
类的加载过程:
>

- Class.forName得到的是已经初始化后的class
---
## 6.8,6.9,6.10 Java内存模型之线程独占部分
线程私有
1. 程序计数器  
 - 当前线程所执行的字节码
2. Java虚拟机栈
 - Java方法执行的内存模型
 - 包含多个栈帧


---
## 7.1 垃圾回收之标记算法
1. 引用计数算法
- 通过判断对象的引用数量来决定对象是否可以被回收
- 每个对象实例都有一个引用计数器，被引用则+1，完成引用则-1
- 任何引用计数为0的对象实例都可以被当作垃圾收集
优点：执行效率高，程序执行行受影响较小
缺点：无法检测循环引用的状况，导致内存泄漏
2. 可达性分析算法
- 可以作为GC ROOT的对象
  - 虚拟机栈中引用的对象
  - 方法区中的常量引用对象
  - 方法区中的类静态属性引用的对象
  - 本地方法栈中JNI(Native方法)的引用对象
  - 活跃线程的引用对象

## 7.2 垃圾回收之回收算法
- 清除算法(Mark and Sweep:碎片化)
  - Mark 
  - Sweep
- 复制算法
  - 分为对象面和空闲面
  - 
  优点
    - 解决碎片化问题
    - 顺序分配内存，简单高效
    - 适用于对象存活率较低
- 标记-整理算法(Com)  

- 分代收集算法(Generational Collector)
  - Minor GC
  - Full GC
年轻代 1/3 堆栈空间
  - Eden区 8
  - 两个Survivor区 1 1

对象如何晋级到老年代
  - 经历一定Minor 次数依然存活的对象
  - Survivor区
常用的调优参数
  - XX:SurvivorRatio: Eden和Survivor的比值，默认8:1
  - XX:NewRatio: 老年代和年轻代的内存大小的比例
  - XX:MaxTenuringThrehold: 对象从年轻代晋升到老生代经过GC次数的最大阈值

老年代 2/3 堆栈空间
  存放生存周期较长的对象
  - Full GC和Major GC

触发Full GC的条件
  - 老年代空间不足
  - 永久代空间不足（jdk8前）
  - CMS GC时出现promotion failed,concurrent mode failure
  - Minor GC晋升到老年代的平均大小大于老年代的剩余空间
  - System.gc()
  - 使用RMI来进行RPC或管理的JDK应用
  
## 7.3 JAVA垃圾回收算法之新生代垃圾收集器
SafePoint
  - 分析过程中对象引用关系不会发生变化的点
  - 产生SafePoint
JVM的运行模式
  - Server 启动慢，速度快
  - Client 
年轻代中常见垃圾收集器
  - Serial收集器(-XX:+UseSerialGC,复制算法)
    - 单线程收集
  - ParNew收集器(-XX:+UseParNewGC,复制算法)
    - 多线程收集
  - Parallel Scavenge收集器(-XX:UseParallelGC,复制算法)
    - 更关注系统吞吐量
## 7.4 Java垃圾回收之老年代垃圾收集器
  - 
  - ParallelOld GC收集器(-XX:+UseParallelOldGC,)~~~~~~~~~~~~~~~~~~~~
  - CMS收集器(-XX:UseConcMarkSweepGC, 标记-清除算法)
    - 初始标记: stop-the-world
    - 并发标记:
    - 并发预清理: 
  - Garbage First收集器(-XX:+UseG1GC,复制+标记-整理算法)
    - 并发和并行
    - 分代收集
    - 空间整合
    -    

# 8 Java多线程与并发
## 8.1 进程和线程的区别
1. 由来   
    - 串行
    - 批处理
    - 进程
        > 独占内存空间
        > 资源分配的基本单位
    - 线程 
        > 每个线程负责一个子任务,共享进程的内存资源、
        > cpu调度的最小单位‘
        
线程和进程的区别
    1. 进程
        - 
        -
        -
    2. 线程
        -
        -
        -

## 8.2 线程start和run方法的区别

## 8.3 Thread和Runnable的关系
1. thread 实现runnable接口实现run的多线程特性
因为java单一继承，所以推荐使用实现runnable接口来创建线程

## 8.4 如何实现处理线程的返回值
1. 如何给run()方法传值
2. 如何实现处理线程的返回值
    - 主线程等待法
    - join
    - Callable接口实现：通过FutureTask Or 线程池获取

## 8.5 线程的状态
    1. 新建 new
    2. 运行 Runnable: Running 和 Ready        
    3. 无限期等待 Waiting 
        - Object.wait 方法
        - Thread.join 方法
        - LockSupport.park
    4. 限期等待
        - Thread.sleep
        - Object.wait 方法 有参数
        - Thread.join 方法 有参数
    5. 阻塞 Blocked
        - 等待获取排他锁
    6. 结束 Terminated 已终止的线程状态
    
## 8.6 sleep 和wait区别
1. sleep
    - Thread类下的方法
    - 可以在任何地方使用 
    - 只会让出CPU,不会导致锁行为的改变
2. wait
    - Object下的方法
    - 
## 8.7 notify和notifyall区别
1. 锁池
2. 等待池
- notify 随机获取一个 
- notifyall 获取所有等待池中的

## 8.8 yield函数
> 给出一个暗示，愿意让出cpu使用权
1. 对锁没有影响，不会让出当前线程占用的锁

## 8.9 如何中断线程interrupt函数
- 已被抛弃的方法
    1. stop方法 
    2. 
- 目前使用的方法
    1. interrupt, 通知线程该中断了。
## 8.10 前述方法及线程状态总结
## 8.11 如何有效谈薪

# 9 Java多线程与并发-原理
## 9.1 synchronized
- 互斥锁的特性
    1. 互斥性 操作的原子性
    2. 可见性 

> 锁的是对象，不是代码

获取对象锁和获取类锁
1. 获取对象锁的两种方法
































