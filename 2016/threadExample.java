class Worker implements Runnable{
    private String taskName;

    Worker(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run(){
        for(int i = 1 ; i <= 5 ; i++){
            System.out.println(taskName + "- step "+i+'['+Thread.currentThread().getName()+']');
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted!");
                return;
            }
        }
    }
}
 class MultiThreadDemo{
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new Worker("Downloading..."),"download-thread");
        Thread t2 = new Thread(new Worker("Processing..."),"process thread");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}