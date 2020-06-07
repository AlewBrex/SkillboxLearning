import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class LinkLenta extends RecursiveTask<Lenta>
{
    private Lenta lenta;
    private static Set<String> allUrls = new TreeSet<>();

    public LinkLenta(Lenta lenta) {
        this.lenta = lenta;
    }

    @Override
    protected Lenta compute()
    {
        try(FileWriter writer = new FileWriter("date/map.txt", false))
        {
            writer.write(lenta.getUrl() + "\n");

            List<LinkLenta> taskList = new ArrayList<>();

            for (String link : lenta.getUrls()) {
                if (allUrls.contains(link)) {
                    continue;
                }
                synchronized (allUrls) {
                    allUrls.add(link);
                    writer.write("\t" + link + "\n");
                }
                LinkLenta task = new LinkLenta(new Lenta(link));
                task.fork();
                taskList.add(task);
            }

            for (LinkLenta linkLenta : taskList) {
                lenta.getLentas().add(linkLenta.join());
            }
            writer.flush();
        }
         catch (Exception e)
        {
            e.printStackTrace();
        }
        return lenta;
    }
}