package geekbrains.java3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Box<Orange> boxOra = new Box();
        boxOra.fruitArray.add(new Orange());
        boxOra.fruitArray.add(new Orange());

        Box<Apple> boxApp = new Box();
        boxApp.fruitArray.add(new Apple());
        boxApp.fruitArray.add(new Apple());
        boxApp.fruitArray.add(new Apple());

        System.out.println(boxApp.compare(boxOra)?"Коробки равны":"Коробки не равны");//сравниваем

        Box<Orange> boxOraDst = new Box();
        boxOraDst.fruitArray.add(new Orange());
        System.out.println("Src: " + boxOra.getWeight() + "; Dst: " + boxOraDst.getWeight());//выводим веса до перекладывания
        mergeBoxes(boxOra,boxOraDst);//перекладываем
        System.out.println("Src: " + boxOra.getWeight() + "; Dst: " + boxOraDst.getWeight());//выводим веса после перекладывания

        Integer[] intArr = new Integer[] {1,2,3,4,5};
        System.out.println(Arrays.toString(intArr));//выводим элементы до перестановки
        switchElements(intArr, 1,4);//переставляем элементы
        System.out.println(Arrays.toString(intArr));//выводим элементы после перестановки

        ArrayList<Integer> alDst = toArrayList(intArr);//преобразуем в ArrayList
        System.out.println(Arrays.toString(alDst.toArray()));
    }

    public static <T extends Fruit> void mergeBoxes(Box<T> boxSrc, Box<T> boxDst){
        boxDst.fruitArray.addAll(boxSrc.fruitArray);
        boxSrc.fruitArray.removeAll(boxSrc.fruitArray);
    }

    public static <T> void switchElements(T[] arr, int index1, int index2) {
        if (index1 == index2) {
            System.out.println("Одинаковые индексы");
            return;
        }
        if (index1 >= 0 && index1 < arr.length && index2 >= 0 && index2 < arr.length) {
            T obj = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = obj;
        } else System.out.println("Некорректные аргументы");
    }

    public static <T> ArrayList toArrayList(T[] arr){
        ArrayList<T> resArrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            resArrayList.add(arr[i]);
        }
        return resArrayList;
    }
}