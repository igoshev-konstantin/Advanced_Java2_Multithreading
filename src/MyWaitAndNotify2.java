import java.util.LinkedList;
import java.util.Queue;

public class MyWaitAndNotify2 {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class ProducerConsumer {
    private Queue<Integer> queue = new LinkedList();
    private final int LIMIT = 5;
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            Thread.sleep(500);
            synchronized (lock) {
                System.out.println("produce: " + queue.size());
                while (queue.size() == LIMIT) {
                    lock.wait();
                }

                queue.offer(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            synchronized (lock) {
                System.out.println("consume: " + queue.size());
                while (queue.size() == 0) {
                    lock.wait();
                }
                if (queue.size()==LIMIT){
                    queue.removeAll(queue);
                }
                queue.poll();
                lock.notify();
            }
        }
    }
}

