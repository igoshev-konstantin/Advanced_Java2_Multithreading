public class MySynchronized1 {
//synchronized это
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        MySynchronized1 m1 = new MySynchronized1();
        m1.startThread();

    }

    public void startThread() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }

    public void increment() {
        synchronized (this){
            counter++;
        }
    }
}

