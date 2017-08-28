package geekbrains.java3.lesson5;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public long go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep((long)(((double)length / (double)c.getSpeed())*1000));
            System.out.printf("%s закончил этап: %s за %.4f сек\n", c.getName(), description, ((double)length / (double)c.getSpeed()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return System.nanoTime();
        }
    }
}

