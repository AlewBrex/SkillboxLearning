import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.TreeSet;

public class Lenta
{
    private String string;
    private boolean linkChild;
    private TreeSet<String> strings;

    public String getString()
    {
        return string;
    }

    public void setString(String string)
    {
        this.string = string;
    }

    public boolean isLinkChild()
    {
        return linkChild;
    }

    public void setLinkChild(boolean linkChild)
    {
        this.linkChild = linkChild;
    }

    public TreeSet<String> getStrings()
    {
        strings = new TreeSet<>();
        try
        {
            Document doc  = Jsoup.connect(string).get();
            Elements link = doc.select("a");
            link.forEach(e -> {
                String href = e.absUrl("href");
                if (href.contains(string)) {
                    strings.add(href);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    public void setStrings(TreeSet<String> strings)
    {
        this.strings = strings;
    }
}