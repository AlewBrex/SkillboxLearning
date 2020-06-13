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
        try
        {
            List<LinkLenta> taskList = new ArrayList<>();

            for (String link : lenta.getUrls()) {
                if (allUrls.contains(link)) {
                    continue;
                }
                synchronized (allUrls) {
                    allUrls.add(link);
                }
                LinkLenta task = new LinkLenta(new Lenta(link));
                task.fork();
                taskList.add(task);
            }

            for (LinkLenta linkLenta : taskList) {
                lenta.getLentas().add(linkLenta.join());
            }
        }
         catch (Exception e)
        {
            e.printStackTrace();
        }
        return lenta;
    }
}
//(FileWriter writer = new FileWriter("date/map.txt", false))
//        writer.write(lenta.getUrl() + "\n");
//        writer.write("\t" + link + "\n");
//        writer.flush();