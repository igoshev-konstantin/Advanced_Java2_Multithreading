public class MyThreadVolatile implements Runnable {

    private volatile static boolean running = true;//Если не пометить volatile, то
    //возможно возникновение когерентности кешей -значения параметров попадает в кеш
    //ядра и не изменяется в случае изменения значения. То есть без volatile в ядро папло значение true в кеш, а на другом
    //ядре изменилось значение в итоге данные из осн. памяти и данные в кеше одного из ядер - разные.
    //volatile - это отмена кеширования параметра в ядре.

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("hell no");
        }
    }

    public static void shutDown(){
        running = false;
        System.out.println("shutting down");
    }
}
