public class MyWaitAndNotify {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = makeThreadProduce();
        Thread thread2 = makeThreadProduce();
        Thread thread3 = makeThreadProduce();

        Thread thread1c = makeThreadConsume();
        Thread thread2c = makeThreadConsume();
        Thread thread3c = makeThreadConsume();

        thread1.start();
//        thread2.start();
//        thread3.start();
        thread1c.start();
//        thread2c.start();
//        thread3c.start();

        thread1.join();
//        thread2.join();
//        thread3.join();
        thread1c.join();
//        thread2c.join();
//        thread3c.join();

    }

    public static Thread makeThreadProduce() {
        WaitAndNotify wf = new WaitAndNotify();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wf.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    public static Thread makeThreadConsume() {
        WaitAndNotify wf = new WaitAndNotify();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wf.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

}

class WaitAndNotify {
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void produce() throws InterruptedException {
        synchronized (lock1) {      //по умолчанию если просто notify, то лочится на this
            System.out.println("Start produce " + this.toString());
            lock1.wait();       //по умолчанию если просто notify, то лочится на this
            System.out.println("Unlock " + this.toString());    //почему-то не отрабатывает?
        }

        synchronized (lock2) {
            System.out.println("Start produce lock2");
            wait();
            System.out.println("Unlock lock2");
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(2000);
            System.out.println("Consume " + this.toString());
            lock1.notifyAll();
            System.out.println("Preparing to unlocking " + this.toString());
            Thread.sleep(1000);
        }
    }
}
