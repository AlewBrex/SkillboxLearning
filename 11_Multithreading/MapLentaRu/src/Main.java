import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    public static void main(String[] args)
    {
//        String lenta = "https://lenta.ru";
//        TreeSet<String> strings = new TreeSet<>();
//        try
//        {
//            Document doc  = Jsoup.connect(lenta).get();
//            Elements link = doc.select("a");
//            link.forEach(e -> {
//                String href = e.absUrl("href");
//                if (href.contains("https://lenta.ru/")) {
//                    strings.add(href);
//                }
//            });
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        strings.forEach(System.out::println);
        Lenta root = null;

        String lenta = new ForkJoinPool().invoke(new LinkLenta(root));
        System.out.println(lenta);
    }
}