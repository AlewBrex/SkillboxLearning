import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;

public class RedisStorage
{
    private RedissonClient redisson;
    private RKeys rKeys;
    private RScoredSortedSet<String> users;
    private final static String KEY = "user";

    private double getTs()
    {
        return new Date().getTime() / 1000;
    }

    public void init()
    {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

        try {
            redisson = Redisson.create(config);
        }
        catch (RedisConnectionException Ex)
        {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Ex.getMessage());
        }
        rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    public void userLast(String str)
    {
        System.out.println("> Пользователь " + str + " оплатил платную услугу");
        users.remove(str);
        users.add(getTs(),str);
    }

    public void addOnlineUsers(int id)
    {
        users.add(getTs(),String.valueOf(id));
    }

    public void printUser()
    {
        for (String usr : users)
        {
            System.out.println("На главной странице показываем пользователя " + usr);
        }

    }
}