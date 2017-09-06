import geekbrains.java3.lesson6.ArrayCheck;
import org.junit.Assert;
import org.junit.Test;

public class ArrayOneFourTest {
    @Test
    public void testAOF1() {
        int[] src = {4,1,1,1,4,1,4,4};
        Assert.assertTrue(ArrayCheck.isOneFour(src));
    }
    @Test
    public void testAOF2() {
        int[] src = {1,2,3,1,2,3,4};
        Assert.assertFalse(ArrayCheck.isOneFour(src));
    }
    @Test
    public void testAOF3() {
        int[] src = {1};
        Assert.assertFalse(ArrayCheck.isOneFour(src));
    }
    @Test
    public void testAOF4() {
        int[] src = {};
        Assert.assertFalse(ArrayCheck.isOneFour(src));
    }
    @Test
    public void testAOF5() {
        int[] src = {4,1};
        Assert.assertTrue(ArrayCheck.isOneFour(src));
    }
}
