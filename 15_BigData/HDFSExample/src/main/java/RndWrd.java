import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RndWrd
{
    private static String userNamePath = "hdfs://e56ca2fd4c9e:8020/test/file.txt";
    private static String landRoverFile = "resourse/JaguarLand.txt";

    public static void startGen() throws URISyntaxException, IOException
    {
        FileSystem hdfS = FileSystem.get(new URI("hdfs://e56ca2fd4c9e:8020"),HdfsConfig.getConfig());
        Path file = new Path(userNamePath);

        OutputStream os = hdfS.create(file);
        BufferedWriter br = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8")
        );

        List<String> lines = Files.readAllLines(Paths.get(landRoverFile));
        for (String strng : lines)
        {
            String first = strng.replaceAll("[^A-z\\-\\:ёЁ 0-9]","");
            br.write(first);
        }

        br.flush();
        br.close();
        hdfS.close();
    }
}