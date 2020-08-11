import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler
{
    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static HashMap<Voter,Integer> voterCounts;
    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();

    public XMLHandler()
    {
        voteStationWorkTimes = new HashMap<>();
        voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        try
        {
            if (qName.equals("voter"))
            {
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDay);
            }
            else if (qName.equals("visit") && voter != null)
            {
                Date time = visitDateFormat.parse(attributes.getValue("time"));
                int station = Integer.parseInt(attributes.getValue("station"));

                WorkTime workTime = voteStationWorkTimes.get(station);
                if(workTime == null)
                {
                    workTime = new WorkTime();
                    voteStationWorkTimes.put(station, workTime);
                }
                workTime.addVisitTime(time.getTime());

                int count = voterCounts.getOrDefault(voter,0);
                voterCounts.put( voter, count + 1);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if (qName.equals("voter"))
        {
            voter = null;
        }
    }

    public void printDuplicatedVoters()
    {
        for(Voter voter : voterCounts.keySet())
        {
            int count = voterCounts.get(voter);
            if (count > 1)
            {
                System.out.println(voter.toString() + " - " + count);
            }
        }
    }

    public void printVotingStationWorkTimes()
    {
        System.out.println("Voting station work times: ");
        for(Integer votingStation : voteStationWorkTimes.keySet())
        {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println(votingStation + " - " + workTime);
        }
    }
}