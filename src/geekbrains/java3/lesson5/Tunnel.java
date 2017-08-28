package geekbrains.java3.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore sm;
    public Tunnel(int len) {
        this.length = len;
        this.description = "Тоннель " + length + " метров";
        this.sm = new Semaphore(Main.CARS_COUNT/2);
    }
    @Override
    public long go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                sm.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep((long)(((double)length / (double)c.getSpeed())*1000));
                sm.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.printf("%s закончил этап: %s за %.4f сек\n", c.getName(), description, ((double)length / (double)c.getSpeed()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return System.nanoTime();
        }
    }
}
