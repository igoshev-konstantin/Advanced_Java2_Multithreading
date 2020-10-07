public class MainClass {
    public static void main(String[] args) throws Exception {
//        test1();
//        test2();
        test3();
    }

    static void test1() throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println("sleep 1");
        myThread.sleep(1000);
        myThread.start();
        MyThread myThread2 = new MyThread();
        myThread2.start();
    }

    static void test2() throws InterruptedException {
        Thread thread = new Thread(new MyRun());
        thread.start();
    }

    static void test3() throws InterruptedException {
        Thread thread = new Thread(new MyThreadVolatile());
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyThreadVolatile.shutDown();
    }
}
