import geekbrains.java3.lesson6.ArrayCheck;
import org.junit.*;

public class ArrayOneTest {

    @Test
    public void testAF1() {
        int[] res = {1,2,3};
        int[] src = {4,1,2,3};
        Assert.assertArrayEquals(res, ArrayCheck.arrayFour(src));
    }
    @Test
    public void testAF2() {
        int[] res = {};
        int[] src = {1,2,3,1,2,3,4};
        Assert.assertArrayEquals(res, ArrayCheck.arrayFour(src));
    }
    @Test(expected = RuntimeException.class)
    public void testAF3() {
        int[] res = {};
        int[] src = {1,2,3,1,2,3};
        Assert.assertArrayEquals(res, ArrayCheck.arrayFour(src));
    }
    @Test(expected = RuntimeException.class)
    public void testAF4() {
        int[] res = {};
        int[] src = {};
        Assert.assertArrayEquals(res, ArrayCheck.arrayFour(src));
    }

}
