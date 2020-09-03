package quick_sort;

public class QuickSort
{
    public static void sort(int[] array)
    {
        if(array.length <= 1)
        {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int from, int to)
    {
        if(from < to)
        {
            int pivot = partition(array, from, to);
            sort(array, from, pivot - 1);
            sort(array, pivot + 1, to);
        }
    }

    private static int partition(int[] array, int from, int to)
    {
        int pivot = 0;
        for (int i = from; i <= to; i++)
        {
            if (array[i] < array[to])
            {
                int temp = array[i];
                array[i] = array[to];
                array[to] = temp;
                pivot++;
            }
            if (array[i] == array[to])
            {
                int temp = array[to];
                array[to] = array[i];
                array[i] = temp;
            }
        }
        //TODO: moving values around pivot,
        // return new pivot index
        return pivot;
    }
}