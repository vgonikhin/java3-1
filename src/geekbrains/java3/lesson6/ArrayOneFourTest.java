package geekbrains.java3.lesson6;

import org.junit.Assert;
import org.junit.Test;

public class ArrayOneFourTest {
    @Test
    public void testAOF1() {
        int[] src = {4,1,1,1,4,1,4,4};
        Assert.assertTrue(ArrayOneFour.isOneFour(src));
    }
    @Test
    public void testAOF2() {
        int[] src = {1,2,3,1,2,3,4};
        Assert.assertFalse(ArrayOneFour.isOneFour(src));
    }
    @Test
    public void testAOF3() {
        int[] src = {1};
        Assert.assertFalse(ArrayOneFour.isOneFour(src));
    }
    @Test
    public void testAOF4() {
        int[] src = {};
        Assert.assertFalse(ArrayOneFour.isOneFour(src));
    }
    @Test
    public void testAOF5() {
        int[] src = {4,1};
        Assert.assertTrue(ArrayOneFour.isOneFour(src));
    }
}
