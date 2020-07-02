import java.text.SimpleDateFormat;
import java.util.Random;

public class RedisTest
{
    private static final int users = 20;
    private static final int sleep = 900;
    private static final int fortuity = 10;

    public static void main(String[] args) throws InterruptedException
    {
        RedisStorage redis = new RedisStorage();
        redis.init();

        for (int i = 1; i <= users; i++) {
            redis.addOnlineUsers(i);
        }

        for (;;)
        {
            for (int i = 1; i <= users; i++)
            {
                redis.printUser(i);
                Thread.sleep(sleep/2);
                if (i % fortuity == 0)
                {
                    int userId = new Random().nextInt(users);
                    System.out.println("> Пользователь " + userId + " оплатил платную услугу");
                    redis.printUser(userId);
                    redis.userLast(userId);
                    Thread.sleep(sleep);
                }
            }
        }
    }
}