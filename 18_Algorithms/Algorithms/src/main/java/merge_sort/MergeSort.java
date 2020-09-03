package merge_sort;

public class MergeSort
{
    public static void mergeSort(int[] array)
    {
        int n = array.length;
        if(n < 2)
        {
            return;
        }
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++)
        {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++)
        {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] left, int[] right)
    {
        int l = 0;
        int r = 0;
        int a = 0;

        while (l < left.length && r < right.length)
        {
            if (left[l] < right[r])
            {
                array[a++] = left[l];
            }
            else
            {
                array[a++] = right[r++];
            }
        }
        //TODO
    }
}
