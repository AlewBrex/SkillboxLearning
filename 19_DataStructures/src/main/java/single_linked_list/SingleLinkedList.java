package single_linked_list;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedList
{
    private ListItem top;

    public void push(ListItem item)
    {
        if(top != null)
        {
            item.setNext(top);
        }
        top = item;
    }

    public ListItem pop()
    {
        ListItem item = top;
        if(top != null)
        {
            top = top.getNext();
            item.setNext(null);
        }
        return item;
    }

    public void removeTop()
    {
        if(top != null)
        {
            top = top.getNext();
        }
    }

    public void removeLast()
    {
        if (top != null)
        {
            ListItem penultimate = top;
            ListItem last = penultimate.getNext();
            while (last != null)
            {
                penultimate = penultimate.getNext();
                last = last.getNext();

                if (last.getNext() == null)
                {
                    penultimate.setNext(null);
                    last = null;
                }
            }
        }
    }
    public List<String> stringList()
    {
        List<String> strings = new ArrayList<>();
        ListItem item = top;
        while (item != null)
        {
            strings.add(item.getData());
            item = item.getNext();
        }
        return strings;
    }
}