/*
Thread is the smallest unit of execution in a program
A single program can have multiple threads which are responsible for different
tasks dependent/independent of each other
A thread shares the same heap memory but has its own execution stack and register values
A thread generally helps in multitasking with in a single program and makes the application feel
more interactive as one thread might be handling heavy duty and the other is handling user response and UI

Merits of multithreading
: Multiple workflow can run concurrently so is best for multitasking within a same process
: Makes the application more responsive as mentioned above
: makes the optimum use of resources(like cpu)
: low overhead than creating entire process for each tasks
: uses same heap memory so context switching is not generally necessary

 */

class First implements Runnable{
    @Override
    public void run(){
        for (int i = ;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+" running for: "+i);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println("Interrupt");
            }
        }
    }
}
class Second implements Runnable{
    @Override
    public void run(){
        for(char ch = 'A';ch<='E';ch++){
            System.out.println(Thread.currentThread().getName()+":"+ch);
            try{
                Thread.sleep(500);
            }catch(InterruptedException ignored){}
        }
    }
}

class Running{
    public static void main(String[] args){
        First f1 = new First();
        Second s1 = new Second();

        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(s1);

        t1.setName("Thread 1");
        t2.setName("Thread 2");

        t1.start();t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ignored) {}
    }
}

