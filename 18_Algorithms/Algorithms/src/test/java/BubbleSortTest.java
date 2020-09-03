import bubble_sort.BubbleSort;
import junit.framework.TestCase;

import java.util.Arrays;

public class BubbleSortTest  extends TestCase
{
    int[] antRing = {54, 96, 106, 187, 854, 11, 10, 765, 45, 65, 44, 88};
    int[] sortedRing = {10, 11, 44, 45, 54, 65, 88, 96, 106, 187, 765, 854};
    @Override
    protected void setUp() throws Exception
    {

    }
    public void testBubbleSort()
    {
        BubbleSort.sort(antRing);
        assertTrue(Arrays.equals(sortedRing, sortedRing));
    }

    @Override
    protected void tearDown() throws Exception
    {

    }
}