class RunnableDemo implements Runnable{
    private String fileName;

    public RunnableDemo(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void run(){
        for (int part = 1; part <= 4; part ++){
            System.out.println(Thread.currentThread().getName()+" downloading"+fileName+" part "+part);
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException{
        RunnableDemo r1 = new RunnableDemo("First.txt");
        RunnableDemo r2 = new RunnableDemo("Second.pdf");

        Thread t1 = new Thread(r1,"First name");
        Thread t2 = new Thread(r2,"Second name");

        t1.start();t2.start();

        t1.join();t2.join();
    }
}