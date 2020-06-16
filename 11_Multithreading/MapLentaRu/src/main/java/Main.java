import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    public static void main(String[] args)
    {
        String path = "date/map.txt";
        String url = "https://lenta.ru/";
        Lenta root = new Lenta(url);
        Lenta lenta = new ForkJoinPool().invoke(new LinkLenta(root));
        try(FileWriter writer = new FileWriter(path,false))
        {
            getStr(lenta,0,writer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void getStr(Lenta link, int countTab, FileWriter writer) throws IOException
    {
        writer.write(plusTab(link.getUrl(),countTab) + "\n");
        for(Lenta s : link.getLentas())
        {
            getStr(s,countTab + 1, writer);
        }
    }

    public static String plusTab(String tab, int count)
    {
        String oneTab = "\t";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < count; i++)
        {
            str.append(oneTab);
        }
        str.append(tab);
        return str.toString();
    }
}