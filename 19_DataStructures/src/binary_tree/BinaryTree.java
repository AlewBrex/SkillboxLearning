package binary_tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree
{
    private Node root;

    public void addNode(String data)
    {
       if (root != null)
       {
           addTo(root, data);
       }
       else
       {
           root = new Node(data);
       }
    }

    private void addTo(Node node, String string)
    {
        if (string.compareTo(node.getData()) < 0)
        {
            if (node.getLeft() == null)
            {
                node.setLeft(new Node(string));
            }
            else
            {
                addTo(node.getLeft(), string);
            }
        }
        else
        {
            if (node.getRight() == null)
            {
                node.setRight(new Node(string));
            }
            else
            {
                addTo(node.getRight(), string);
            }
        }
    }

    public List<Node> searchNodes(String data)
    {
        List<Node> list = new ArrayList<>();
        Node node = new Node(data);
        containsNodeRecursive(node, root, list);

        return list;
    }

    private void containsNodeRecursive(Node current, Node rootNode, List<Node> list)
    {
        if (current != null)
        {
            if (current.getData().equals(rootNode.getData()))
            {
                list.add(current);
            }
            containsNodeRecursive(current.getLeft(), rootNode, list);
            containsNodeRecursive(current.getRight(), rootNode, list);
        }
    }
}