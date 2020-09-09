package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    private ArrayList<Node> nextNodes;
    private int position;
    private String fragment;

    public Node(String fragment, int position)
    {
        this.fragment = fragment;
        nextNodes = new ArrayList<>();
        this.position = position;
    }

    public void setFragment(String fragment)
    {
        this.fragment = fragment;
    }

    public void setNextNodes(ArrayList<Node> nextNodes)
    {
        this.nextNodes = nextNodes;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public String getFragment()
    {
        return fragment;
    }

    public int getPosition()
    {
        return position;
    }

    public List<Node> getNextNodes()
    {
        return nextNodes;
    }
}