package geekbrains.java3.lesson3;

public class MFU {
    Object printLock = new Object();
    Object scanLock = new Object();

    int pcnt = 1;
    int scnt = 1;

    public static void main(String[] args) {
        MFU mfu = new MFU();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    if (((int)(Math.random())) == 1) {
                        mfu.print();
                    } else mfu.scan();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    if (((int)(1 + Math.random())) == 1) {
                        mfu.print();
                    } else mfu.scan();
                }
            }
        });

        t1.start();
        t2.start();


    }

    public void print(){
        synchronized (printLock) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("printing page " + (pcnt++));
        }
    }

    public void scan(){
        synchronized (scanLock) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("scanning page " + (scnt++));
        }
    }
}
