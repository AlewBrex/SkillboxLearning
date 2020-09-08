import junit.framework.TestCase;
import single_linked_list.ListItem;
import single_linked_list.SingleLinkedList;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedListTest extends TestCase
{
    private SingleLinkedList singleLinkedList;
    private ArrayList<String> arrayList;
    @Override
    protected void setUp() throws Exception
    {
        singleLinkedList = new SingleLinkedList();
        singleLinkedList.push(new ListItem("1"));
        singleLinkedList.push(new ListItem("2"));
        singleLinkedList.push(new ListItem("3"));

        arrayList = new ArrayList<>();
        arrayList.add("3");
        arrayList.add("2");
    }

    public void testRemoveLast()
    {
        List<String> expected = arrayList;
        singleLinkedList.removeLast();
        List<String> actual = singleLinkedList.stringList();
        assertEquals(expected,actual);
    }

    @Override
    protected void tearDown() throws Exception
    {

    }
}
