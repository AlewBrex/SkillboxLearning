package array_max_value;

public class ArrayMaxValue
{
    public static int getMaxValue(int[] values)
    {
        int maxValue = 0;

        if (values != null && values.length > 0)
        {
            for(int value : values)
            {
                if (value > maxValue)
                {
                    maxValue = value;
                }
                if (value < maxValue)
                {
                    throw new IllegalArgumentException("Элемент массива имеет недопустимое значение!");
                }
            }
        }
        return maxValue;
    }
}