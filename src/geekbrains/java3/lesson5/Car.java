package geekbrains.java3.lesson5;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private int number;
    private long result;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public int getNumber() { return number; }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.number = CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            Main.cdlStart.countDown();
            Main.cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            result = race.getStages().get(i).go(this);
        }
        Main.cdlWin.countDown();
        if(Main.cdlWin.getCount()==0) win();
        System.out.printf("%s (%d) закончил гонку за %f сек\n",this.name, this.speed, (result-Main.startTime)/1e9);
        Main.cdlEnd.countDown();
    }

    public void win(){
        if(Main.winLock.tryLock()){
            System.out.println(this.name + " выиграл");
        }
    }
}

