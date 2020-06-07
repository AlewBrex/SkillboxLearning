import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.*;

public class Lenta
{
    private String url;
    private TreeSet<Lenta> lentas = new TreeSet<>(Comparator.comparing(o -> o.url));

    public Lenta(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setString(String url) {
        this.url = url;
    }

    public Set<String> getUrls()
    {
        Set<String> urls = new HashSet<>();
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements link = doc.select("a");
            link.forEach(e -> {
                String href = e.absUrl("href");
                if (href.contains(url)) {
                    urls.add(href);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return urls;
    }

    public TreeSet<Lenta> getLentas() {
        return lentas;
    }
    public void setLentas(TreeSet<Lenta> lentas) {
        this.lentas = lentas;
    }
}