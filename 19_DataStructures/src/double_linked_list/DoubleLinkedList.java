package double_linked_list;

public class DoubleLinkedList
{
    private ListItem head;
    private ListItem tail;

    public ListItem getHeadElement()
    {
        return head;
    }

    public ListItem getTailElement()
    {
        return tail;
    }

    public ListItem popHeadElement()
    {
        ListItem item = head;
        if (head != null)
        {
            head.getPrev().setNext(null);
            head = head.getNext();
        }
        return item;
    }

    public ListItem popTailElement()
    {
        ListItem item = tail;
        if (tail != null)
        {
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
        }
        return item;
    }

    public void removeHeadElement()
    {
        if (head != null)
        {
            head.getPrev().setNext(null);
            head = head.getNext();
        }
    }

    public void removeTailElement()
    {
        if (tail != null)
        {
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
        }
    }

    public void addToHead(ListItem item)
    {
        if (head != null)
        {
            head.setPrev(item);
            item.setNext(head);
        }
        head = item;
    }

    public void addToTail(ListItem item)
    {
        if (tail != null)
        {
            tail.setNext(item);
            item.setPrev(tail);
        }
        tail = item;
    }
}