package geekbrains.java3.lesson6;

public class ArrayFour {

    public static int[] arrayFour(int[] ai){
        int s = hasFour(ai);
        if(s != 0){
            int[] res=new int[ai.length-s-1];
            for (int i = 0; i < res.length; i++) {
                res[i] = ai[s+1+i];
            }
            return res;
        }else{
            throw new RuntimeException();
        }

    }

    public static int hasFour(int[] ai){
        for (int i = ai.length; i > 0; i--) {
            if(ai[i-1]==4)return (i-1);
        }
        return 0;
    }
}
