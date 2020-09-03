package binary_search;

import java.util.ArrayList;

public class BinarySearch
{
    private ArrayList<String> list;

    public BinarySearch(ArrayList<String> list)
    {
        this.list = list;
    }

    public int search(String query)
    {
        return search(query, 0, list.size() - 1);
    }

    private int search(String query, int from, int to)
    {
        int middle = (from + to) / 2;
        int comparison = query.compareTo(list.get(middle));

        if (from < to)
        {
            if (comparison == 0)
            {
                return middle;
            }
            if (comparison > 0)
            {
                return search(query, middle, to);
            }
            return search(query, from, middle);
        }
        return -1;
    }
}