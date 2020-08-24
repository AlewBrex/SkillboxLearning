import org.apache.hadoop.fs.*;
import sun.security.util.IOUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccess
{
    private FileSystem hdfs;
    private FSDataOutputStream os;
    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     * for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) throws URISyntaxException, IOException
    {
        hdfs = FileSystem.get(new URI(rootPath),HdfsConfig.getConfig());
    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws IOException
    {
        os = hdfs.create(new Path(path));
        os.close();
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException
    {
        Path file = new Path(path);
        if (!hdfs.exists(file))
        {
            create(path);
        }
        os = hdfs.append(file);
        os.writeBytes(content);
        os.close();
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException
    {
        StringBuilder strBldr = new StringBuilder();
        Path path1 = new Path(path);
        FSDataInputStream isa = hdfs.open(path1);
        byte[] brstrg = IOUtils.readNBytes(isa,1024);
        while (brstrg.length != 0)
        {
            strBldr.append(Arrays.toString(brstrg));
        }

        return strBldr.toString();
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException
    {
        Path file = new Path(path);
        if (hdfs.exists(file))
        {
            hdfs.delete(file,true);
        }
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws IOException
    {
        Path file = new Path(path);
        if (hdfs.isDirectory(file))
        {
            return true;
        }
        return false;
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws IOException
    {
        Path path1 = new Path(path);
        List<String> stringList = new ArrayList<>();
        FileStatus[] flSts = hdfs.listStatus(path1);
        for (FileStatus fileStatus: flSts)
        {
            if (fileStatus.isDirectory())
            {
                stringList.addAll(list(fileStatus.getPath().toString()));
            }
            else
            {
                stringList.add(fileStatus.getPath().toString());
            }
        }

        return null;
    }
}