import java.io.IOException;
import java.net.URISyntaxException;

public class Main
{
    private static String userNamePath = "hdfs://e56ca2fd4c9e:8020";

    public static void main(String[] args) throws IOException, URISyntaxException
    {
        HdfsConfig.config();
        FileAccess fileAccess = new FileAccess(userNamePath);
        fileAccess.delete("/test/fileWord.txt");
        fileAccess.create("/test/wordTest");
        RndWrd.startGen();
    }
}