package geekbrains.java3.lesson6;

public class ArrayCheck {

    public static int[] arrayFour(int[] ai){
        int s = hasNumber(ai,4);
        if(s != -1){
            int[] res=new int[ai.length-s-1];
            for (int i = 0; i < res.length; i++) {
                res[i] = ai[s+1+i];
            }
            return res;
        } else {
            throw new RuntimeException();
        }

    }

    public static boolean isOneFour(int[] ai){
        int[] counter = new int[]{0,2,3,5,6,7,8,9};
        boolean other = false;
        for (int c:counter) if(hasNumber(ai,c)!=-1) other=true;
        return (hasNumber(ai,1)!=-1)&&(hasNumber(ai,4)!=-1)&&(!other);
    }

    public static int hasNumber(int[] ai, int number){
        for (int i = ai.length; i > 0; i--) if(ai[i-1]==number)return (i-1);
        return -1;
    }
}
