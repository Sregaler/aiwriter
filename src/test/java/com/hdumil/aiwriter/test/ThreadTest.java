package com.hdumil.aiwriter.test;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadTest implements Runnable{
    /**
     *   synchronized操作
     */
    // 方法无synchronized时无法使用notify和wait，会报IllegalMonitorStateException，非法监视器状态
    public synchronized void print1() {
        for (int i = 0; i <= 100; i += 2) {
            System.out.println(i);
            this.notify();
            try {
                this.wait();
                Thread.sleep(100);// 防止打印速度过快导致混乱
            } catch (InterruptedException e) {
                // NO
            }
        }
    }

    public synchronized void print2() {
        for (int i = 1; i <= 100; i += 2) {
            System.out.println(i);
            this.notify();
            try {
                this.wait();
                Thread.sleep(100);// 防止打印速度过快导致混乱
            } catch (InterruptedException e) {
                // NO
            }
        }
    }
    public static void demo_1() throws InterruptedException {  // synchronized操作
        final ThreadTest demo1 = new ThreadTest();
        Thread t1 = new Thread(demo1::print1);
        Thread t2 = new Thread(demo1::print2);
        t1.start();
        Thread.sleep(100);
        t2.start();
    }

    /**
     *   CAS操作
     */
//    static AtomicInteger cxsNum = new AtomicInteger(0);  //可以用原子类
    static volatile int cxsNum = 0;  //也可以用volatile保持可见性，用flag保证原子性
    static volatile boolean flag = false; // CAS的标志
    public static void demo_2() {
        Thread t1 = new Thread(() -> {
            for (; 100 > cxsNum; ) {
                if (!flag && (cxsNum % 2 == 0)) {
                    System.out.println(cxsNum++);
//                    try {
//                        Thread.sleep(100);// 防止打印速度过快导致混乱
//                    } catch (InterruptedException e) {
//                        //NO
//                    }
                    flag = true;
                }
            }
        }
        );

        Thread t2 = new Thread(() -> {
            for (; 100 > cxsNum; ) {
                if (flag && (cxsNum % 2 == 1)) {
                    System.out.println(cxsNum++);
//                    try {
//                        Thread.sleep(100);// 防止打印速度过快导致混乱
//                    } catch (InterruptedException e) {
//                        //NO
//                    }
                    flag = false;
                }
            }
        }
        );
        t1.start();
        t2.start();
    }

    //SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));
    @Override
    public void run() {
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" default Formatter = "+formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = " + formatter.get().toPattern());
    }

    /**
     *  ThreadLocal 测试
     */
    public static void demo_3() throws InterruptedException {
        ThreadTest obj = new ThreadTest();
        for(int i=0 ; i<10; i++){
            Thread t = new Thread(obj, ""+i); //创建线程
            Thread.sleep(new Random().nextInt(1000));
            t.start();//启动线程
        }
    }

    /**
     *  ThreadPoolExecutor 测试
     */
    private static final int CORE_POOL_SIZE = 5;  //核心线程数量
    private static final int MAX_POOL_SIZE = 10; //最大线程数量
    private static final int QUEUE_CAPACITY = 100; //队列长度
    private static final Long KEEP_ALIVE_TIME = 1L; //等待队列存活时间
    public static void demo_4() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY), //等待队列
                new ThreadPoolExecutor.CallerRunsPolicy());  //线程工厂，默认饱和策略
        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new ThreadTest();
            executor.execute(worker);//执行Runnable
            //submit()执行任务会获取Future对象，submit方法返回Future, callable的返回需要靠Future.get()
            //submit()运行Runnable类会自动转换成callable执行
//            Callable c = new TestCallable();
//            Future f = executor.submit(worker);
        }
        executor.shutdown();//终止线程池
        while (!executor.isTerminated()) {
            //若关闭后所有任务都已完成，则返回true。注意除非首先调用shutdown或shutdownNow，否则isTerminated永不为true。返回：若关闭后所有任务都已完成，则返回true。
        }
        System.out.println("Finished all threads");
    }
    public static void main(String[] args) throws InterruptedException {
//        demo_1();
        demo_2();
//        demo_3();
    }

}
