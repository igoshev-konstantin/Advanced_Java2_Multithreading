
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(2);  //Закинем в pool 2 потока, которые будем использовать
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Run(i)); //накидаем заданий для потоков
        }

        executorService.shutdown();     //прекратим ввод заданий для потоков
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

class Run implements Runnable {
    private int id;

    public Run(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("id:" + id);
    }
}