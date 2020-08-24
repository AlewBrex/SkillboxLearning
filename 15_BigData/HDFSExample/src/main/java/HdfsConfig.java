import org.apache.hadoop.conf.Configuration;

public class HdfsConfig
{
    private static Configuration confg;

    public static void config()
    {
        confg = new Configuration();
        confg.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");
    }

    public static Configuration getConfig() {
        return confg;
    }
}