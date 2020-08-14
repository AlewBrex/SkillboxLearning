import java.util.TreeSet;

public class WorkTime
{
    private TreeSet<TimePeriod> periods;

    /**
     * Set of TimePeriod objects
     */
    public WorkTime()
    {
        periods = new TreeSet<>();
    }

    public void addVisitTime(long visitTime)
    {
        long visit = visitTime;
        TimePeriod newPeriod = new TimePeriod(visit, visit);
        for(TimePeriod period : periods)
        {
            if(period.compareTo(newPeriod) == 0)
            {
                period.appendTime(visit);
                return;
            }
        }
        periods.add(new TimePeriod(visit, visit));
    }

    public String toString()
    {
        String line = "";
        for(TimePeriod period : periods)
        {
            if(!line.isEmpty())
            {
                line += ", ";
            }
            line += period;
        }
        return line;
    }
}