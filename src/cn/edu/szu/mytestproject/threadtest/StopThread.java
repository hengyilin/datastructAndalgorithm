package cn.edu.szu.mytestproject.threadtest;

/**
 * Author : hengyilin
 * Date   : 16-10-1
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class StopThread {
    static class MyThread implements Runnable {
        public volatile boolean stop = false;

        private void dosomething() throws InterruptedException {
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 1000) {
                System.out.println("All things have bean ready");
            }
            Thread.currentThread().join();
        }

        @Override
        public void run() {
            try {
                dosomething();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread safely exit");
            }

        }
    }

    public static void main(String[] arhs) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        System.out.println("System is ready to start thread");
        thread.start();
        Thread.sleep(300);
        System.out.println("System is ready to stop thread");
        thread.interrupt();
//        myThread.stop = true;
    }
}
