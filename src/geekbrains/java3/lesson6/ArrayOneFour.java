package geekbrains.java3.lesson6;

public class ArrayOneFour {
    public static boolean isOneFour(int[] ai){
        if(ai.length<2)
            return false;
        int c = ai.length;
        if(hasFour(ai)&&hasOne(ai))
        for (int i = 0; i < ai.length; i++) {
            if(ai[i]==1||ai[i]==4)c--;
        }
        return c==0;
    }

    public static boolean hasFour(int[] ai){
        for (int i = ai.length; i > 0; i--) {
            if(ai[i-1]==4)return true;
        }
        return false;
    }
    public static boolean hasOne(int[] ai){
        for (int i = ai.length; i > 0; i--) {
            if(ai[i-1]==1)return true;
        }
        return false;
    }
}
