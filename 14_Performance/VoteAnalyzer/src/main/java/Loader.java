import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Loader
{
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception
    {
        long firstMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();
        String fileName = "res/data-1572M.xml";

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();

//        parser.parse(new File(fileName), handler);
//        handler.printDuplicatedVoters();
//        DBConnection.executeMultiInsert();

        Thread thread1 = new Thread(() ->
        {
            try {
                parser.parse(new File(fileName), handler);
                handler.printDuplicatedVoters();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread1.start();
//
        Thread thread2 = new Thread(() -> {
            try {
                DBConnection.executeMultiInsert();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
//
//        thread1.join();
//        thread2.join();

        System.out.println("Parsing duration: " + (System.currentTimeMillis() - start) + " ms");
        long secondMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - firstMem;
        System.out.println(secondMem);

//        long firstMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

//        long start = System.currentTimeMillis();
//        parseFile(fileName);
//        System.out.println("Parsing duration: " + (System.currentTimeMillis() - start) + " ms");
//        DBConnection.printVoterCounts();

        //Printing results
//        System.out.println("Voting station work times: ");
//        for(Integer votingStation : voteStationWorkTimes.keySet())
//        {
//            WorkTime workTime = voteStationWorkTimes.get(votingStation);
//            System.out.println("\t" + votingStation + " - " + workTime);
//        }
//
//        System.out.println("Duplicated voters: ");
//        for(Voter voter : voterCounts.keySet())
//        {
//            Integer count = voterCounts.get(voter);
//            if(count > 1) {
//                System.out.println("\t" + voter + " - " + count);
//            }
//        }
//        long secondMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - firstMem;
//        System.out.println(secondMem);
    }

    private static void parseFile(String fileName) throws Exception
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
//        fixWorkTimes(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception
    {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for(int i = 0; i < votersCount; i++)
        {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            String birthDay = attributes.getNamedItem("birthDay").getNodeValue();

            DBConnection.countVoter(name, birthDay);
            if (i % 1000 == 0 || (i == votersCount - 1))
            {
                DBConnection.executeMultiInsert();
            }
//            Voter voter = new Voter(name, birthDay);
//            Integer count = voterCounts.get(voter);
//            voterCounts.put(voter, count == null ? 1 : count + 1);
        }
//        DBConnection.executeMultiInsert();
    }

    private static void fixWorkTimes(Document doc) throws Exception
    {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for(int i = 0; i < visitCount; i++)
        {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            int station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if(workTime == null)
            {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}