package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree
{
    private static final String WORD_TERMINATION = "$";
    private static final int POSITION_UNDEFINED = -1;
    private String text;
    private Node root;

    public SuffixTree(String text)
    {
        root = new Node("", POSITION_UNDEFINED);
        this.text = text;
        build();
    }

    private void build()
    {
        for (int i = 0; i < text.length(); i++) 
        {
            addSuffix(text.substring(i) + WORD_TERMINATION, i);
        }
    }
    private List<Integer> search(String query)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        List<Node> nodes = getAllNodesInTraversePath(query, root, false);

        if (nodes.size() > 0)
        {
            Node lastNode = nodes.get(nodes.size() - 1);
            if (lastNode != null)
            {
                positions = getPositions(lastNode);
                System.out.println("Search results of \"" + query + "\": " +
                        positions + " matches are marked with ^. Text: " +
                        showMatches(positions));
            }
        }
        return positions;
    }

    private String showMatches(ArrayList<Integer> positions)
    {
        String resultText = text;

        for (int i = text.length() - 1; i > 0; i--)
        {
            if (positions.contains(i))
            {
                resultText = insertString(resultText, "^", i);
            }
        }
        return resultText;
    }

    public String insertString(String originalString, String stringToBeInserted, int index)
    {
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < originalString.length(); i++)
        {
            newString.append(originalString.charAt(i));
            if (i == index)
            {
                newString.append(stringToBeInserted);
            }
        }
        return newString.toString();
    }

    private void addChildNode(Node parentNode, String text, int index)
    {
        parentNode.getNextNodes().add(new Node(text, index));
    }

    private String getLongestCommonPrefix(String str1, String str2)
    {
        int minLength = Math.min(str1.length(), str2.length());

        for (int i = 0; i < minLength; i++)
        {
            if (str1.charAt(i) != str2.charAt(i))
            {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, minLength);
    }

    private void splitNodeToParentAndChild(Node parentNode, String parentNewText, String childNewText)
    {
        Node childNode = new Node(childNewText, parentNode.getPosition());

        if (parentNode.getNextNodes().size() > 0)
        {
            while (parentNode.getNextNodes().size() > 0)
            {
                childNode.getNextNodes()
                        .add(parentNode.getNextNodes().remove(0));
            }
        }
        parentNode.getNextNodes().add(childNode);
        parentNode.setFragment(parentNewText);
        parentNode.setPosition(POSITION_UNDEFINED);
    }

    private List<Node> getAllNodesInTraversePath(String query, Node startNode, boolean isAllowPartialMatch)
    {
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < startNode.getNextNodes().size(); i++)
        {
            Node currentNode = startNode.getNextNodes().get(i);
            String nodeText = currentNode.getFragment();

            if (query.charAt(0) == nodeText.charAt(0))
            {
                if (isAllowPartialMatch && query.length() <= nodeText.length())
                {
                    nodes.add(currentNode);
                    return nodes;
                }

                int minLength = Math.min(nodeText.length(), query.length());

                for (int j = 1; j < minLength; j++)
                {
                    if (query.charAt(j) != nodeText.charAt(j))
                    {
                        if (isAllowPartialMatch)
                        {
                            nodes.add(currentNode);
                        }
                        return nodes;
                    }
                }

                nodes.add(currentNode);

                if (query.length() > minLength)
                {
                    List<Node> nodes2 = getAllNodesInTraversePath(
                            query.substring(minLength),
                            currentNode, isAllowPartialMatch);

                    if (nodes2.size() > 0)
                    {
                        nodes.addAll(nodes2);
                    } else if (!isAllowPartialMatch)
                    {
                        nodes.add(null);
                    }
                }
                return nodes;
            }
        }
        return nodes;
    }

    private void extendNode(Node node, String newFragment, int position)
    {
        String currentFragment = node.getFragment();
        String commonPrefix = getLongestCommonPrefix(currentFragment, newFragment);

        if (!commonPrefix.equals(currentFragment))
        {
            String parentFragment = currentFragment.substring(0, commonPrefix.length());
            String childFragment = currentFragment.substring(commonPrefix.length());
            splitNodeToParentAndChild(node, parentFragment, childFragment);
        }
        String remainingFragment = newFragment.substring(commonPrefix.length());
        addChildNode(node, remainingFragment, position);
    }

    private void addSuffix(String suffix, int position)
    {
        List<Node> nodeList = getAllNodesInTraversePath(
                suffix, root, true);

        if (nodeList.size() == 0)
        {
            addChildNode(root, suffix, position);
        }
        else
        {
            Node lastNode = nodeList.remove(nodeList.size() - 1);
            String newFragment = suffix;

            if (nodeList.size() > 0)
            {
                String existingSuffixUpToLastNode = nodeList.stream()
                        .map(Node::getFragment)
                        .reduce("", String::concat);
                newFragment = newFragment.substring(existingSuffixUpToLastNode.length());
            }
            extendNode(lastNode, newFragment, position);
        }
    }

    private ArrayList<Integer> getPositions(Node node)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        if (node.getFragment().endsWith(WORD_TERMINATION))
        {
            positions.add(node.getPosition());
        }
        for (int i = 0; i < node.getNextNodes().size(); i++)
        {
            positions.addAll(getPositions(node.getNextNodes().get(i)));
        }
        return positions;
    }
}