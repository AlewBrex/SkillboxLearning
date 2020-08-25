import java.io.IOException;
import java.net.URISyntaxException;

public class Main
{
    private static String userNamePath = "hdfs://e56ca2fd4c9e:8020/test/file.txt";

    public static void main(String[] args) throws IOException, URISyntaxException
    {
        FileAccess fileAccess = new FileAccess(userNamePath);
    }
}