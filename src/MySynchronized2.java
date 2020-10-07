import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MySynchronized2 {
    public static void main(String[] args) throws InterruptedException {
        Sync2 s = new Sync2();
        s.main();
    }
}

class Sync2 {
    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();
    Object lock1 = new Object(); //lock для синхронизации. Пока монитор объекта lock1 занят, мы используем монитор объекта lock2
    Object lock2 = new Object();
    Random r = new Random();

    public void main() throws InterruptedException {
        long before = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fillArray();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fillArray2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        long after = System.currentTimeMillis();
        System.out.println("time: " + (after - before));
        System.out.println("list1:" + list1.size());
        System.out.println("list2:" + list2.size());
    }

    public void fillArray() throws InterruptedException {

        synchronized (lock1) {
            for (int i = 0; i < 1000; i++) {
                list1.add(r.nextInt(100));
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fillArray2() throws InterruptedException {

        synchronized (lock2) {
            for (int i = 0; i < 1000; i++) {
                list2.add(r.nextInt(100));
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
