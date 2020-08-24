import java.io.IOException;
import java.net.URISyntaxException;

public class Main
{
    private static String userNamePath = "hdfs://7bc3a2b125ba:8020/test/file.txt";

    public static void main(String[] args) throws IOException, URISyntaxException
    {
        FileAccess fileAccess = new FileAccess(userNamePath);
        fileAccess.create("/test/file1.txt");
    }
}