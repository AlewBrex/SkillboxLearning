package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RabinKarpExtended
{
    private String text;
    private TreeMap<Integer, Integer> number2position;
    private int r = 256;
    private int q = 101;


    public RabinKarpExtended(String text)
    {
        this.text = text;
        createIndex(text);
    }

    public List<Integer> search(String query)
    {
        ArrayList<Integer> indices = new ArrayList<>();
        //TODO: implement search alogorithm
        return indices;
    }

    private void createIndex(String strng)
    {
        int rM = 1;
        int pttrnStrng = strng.length();

        for (int i = 1; i < pttrnStrng - 1; i++)
        {
            rM = (r * rM) % q;
        }
        int strngHash = getPatternHash(strng, pttrnStrng);

        for (int k = 0; k < pttrnStrng; k++)
        {
            number2position.put(k, strngHash);
        }
        //TODO: implement text indexing
    }

    private int getPatternHash(String key, int intLength)
    {
        int patternHash = 0;
        for (int j = 0; j < intLength; j++)
        {
            patternHash = ((r * patternHash) + key.codePointAt(j)) % q;
        }
        return patternHash;
    }

    private boolean check(String query, int i)
    {
        for (int a = 0; a < text.length(); a++)
        {
            if (query.charAt(a) != text.charAt(i + a))
            {
                return false;
            }
        }
        return true;
    }
}