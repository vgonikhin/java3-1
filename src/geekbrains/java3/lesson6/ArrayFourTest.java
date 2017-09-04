package geekbrains.java3.lesson6;

import org.junit.*;

public class ArrayFourTest {

    @Test
    public void testAF1() {
        int[] res = {1,2,3};
        int[] src = {4,1,2,3,4,1,2,3};
        Assert.assertArrayEquals(res, ArrayFour.arrayFour(src));
    }
    @Test
    public void testAF2() {
        int[] res = {};
        int[] src = {1,2,3,1,2,3,4};
        Assert.assertArrayEquals(res, ArrayFour.arrayFour(src));
    }
    @Test(expected = RuntimeException.class)
    public void testAF3() {
        int[] res = {};
        int[] src = {1,2,3,1,2,3};
        Assert.assertArrayEquals(res, ArrayFour.arrayFour(src));
    }


}
