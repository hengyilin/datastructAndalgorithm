package cn.edu.szu.mytestproject.threadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Author : hengyilin
 * Date   : 16-10-1
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 线程池测试
 */
public class ThreadExecutorTest {

    public static void main(String[] args) {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 线程的执行体
                System.out.println("通过FutureTask创建的线程");
                return Thread.currentThread().getName() + "通过FutureTask创建的线程";
            }
        });
        new Thread(task).start();
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            executorService2.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "线程被调用啦");

                }
            });
            System.out.println("Main主线程被调用啦");
        }
        List<Future<String>> resultList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<String> submit = executorService.submit(new TaskWithResult(i));
            resultList.add(submit);
        }
        for (Future<String> resultString : resultList) {
            try {
                while (!resultString.isDone()) ; // 等待
                System.out.println(resultString.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                // 启动一次顺序关闭执行以前提交的任务但不再接收新任务
                executorService.shutdown();
            }

        }
    }
}

class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        System.out.println("call()方法被调用" + Thread.currentThread().getName());

        return "call()方法返回id= " + this.id + Thread.currentThread().getName();
    }
}