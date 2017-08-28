package geekbrains.java3.lesson5;

public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract long go(Car c);

}
