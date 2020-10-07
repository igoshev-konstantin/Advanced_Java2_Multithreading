
public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i:"+i+" "+this);
        }
    }

    @Override
    public String toString() {
        return "MyThread{} id:" + getId() + " name:" + getName();
    }
}

class MyRun implements Runnable{

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}
