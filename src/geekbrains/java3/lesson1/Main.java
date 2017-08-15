package geekbrains.java3.lesson1;

public class Main {

    public static void main(String[] args) {
        Box<Orange> boxOra = new Box();
        boxOra.fruitArray.add(new Orange());
        boxOra.fruitArray.add(new Orange());

        Box<Apple> boxApp = new Box();
        boxApp.fruitArray.add(new Apple());
        boxApp.fruitArray.add(new Apple());
        boxApp.fruitArray.add(new Apple());

        System.out.println(boxApp.compare(boxOra));

        Box<Orange> boxOraDst = new Box();
        boxOraDst.fruitArray.add(new Orange());
        System.out.println("Src: " + boxOra.getWeight() + "; Dst: " + boxOraDst.getWeight());
        mergeBoxes(boxOra,boxOraDst);
        System.out.println("Src: " + boxOra.getWeight() + "; Dst: " + boxOraDst.getWeight());
    }
    public static <T extends Fruit> void mergeBoxes(Box<T> boxSrc, Box<T> boxDst){
        boxDst.fruitArray.addAll(boxSrc.fruitArray);
        boxSrc.fruitArray.removeAll(boxSrc.fruitArray);
    }

/*    public static <T> void switchElements(Class<T> arr, int index1, int index2){
        if (index1==index2){
            System.out.println("No point");
            return;
        }

        if(arr.isArray()){

        }

    }*/
}
