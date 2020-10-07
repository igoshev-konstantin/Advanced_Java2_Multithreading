import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PatternProducerConsumer {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                produce();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                consumer();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    private static void produce()  {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(50);
                queue.put(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void consumer() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(100);
                if(random.nextInt(10)==3) {
                    System.out.println("number: " + queue.take() + "size: "+queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
