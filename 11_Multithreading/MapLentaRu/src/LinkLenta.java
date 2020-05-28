import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class LinkLenta extends RecursiveTask<String>
{
    private Lenta lenta;

    public LinkLenta(Lenta lenta) {
        this.lenta = lenta;
    }

    @Override
    protected String compute()
    {
        String s = lenta.getString();

        List<LinkLenta> taskList = new ArrayList<>();

        for (String link : lenta.getStrings()) {
            LinkLenta task = new LinkLenta(lenta);
            task.fork();
        }
        return null;
    }
}