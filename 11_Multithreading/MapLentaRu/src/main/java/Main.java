import java.io.FileWriter;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    private static int countTab = -1;

    public static void main(String[] args)
    {
        String url = "https://lenta.ru/";
        Lenta root = new Lenta(url);
        Lenta lenta = new ForkJoinPool().invoke(new LinkLenta(root));
        getStr(lenta);
    }

    public static void getStr(Lenta link)
    {
        try(FileWriter writer = new FileWriter("date/map.txt", false))
        {
            countTab++;
            for (Lenta s : link.getLentas())
            {
                if (!s.getLentas().isEmpty())
                {
                    System.out.println(plusTab(s.getUrl(),countTab));
                    writer.write(plusTab(s.getUrl(),countTab) + "\n");
                    getStr(s);
                }
                else
                {
                    System.out.println(plusTab(s.getUrl(),countTab));
                   writer.write(plusTab(s.getUrl(),countTab) + "\n");
                }
            }
            countTab--;
            writer.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String plusTab(String tab, int count)
    {
        if (count >= 1)
        {
            String oneTab = "\t";
            if (count == 1)
            {
                return oneTab.concat(tab);
            }
            return plusTab(oneTab.concat(tab),count - 1);
        }
        else
        {
            return tab;
        }
    }
}