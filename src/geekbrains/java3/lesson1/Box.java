package geekbrains.java3.lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    ArrayList<T> fruitArray;

    public Box(){
        this.fruitArray = new ArrayList<>();
    }

    public Box(ArrayList<T> fruitArray) {
        this.fruitArray = fruitArray;
    }

    public float getWeight(){
        float weight = 0f;
        for (int i = 0; i < fruitArray.size(); i++) {
            weight += fruitArray.get(i).weight;
        }
        return weight;
    }

    public boolean compare(Box<? extends Fruit> otherBox){
        return (Math.abs(this.getWeight()- otherBox.getWeight()))<0.0001f;
    }


}
