import java.util.Random;

import static java.lang.Thread.sleep;

public class RedisTest
{
    private static final int users = 20;
    private static final int sleepTime = 1000;

    public static void main(String[] args) throws InterruptedException
    {
        RedisStorage redis = new RedisStorage();
        redis.init();

        for (int i = 1; i <= users; i++) {
            redis.addOnlineUsers(i);
        }

        new  Thread(() ->
        {
            for (;;)
            {
                redis.printUser();
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new  Thread(() ->
        {
            Random random = new Random();
            for (;;)
            {
                if (random.nextInt(100) >= 90)
                {
                    redis.userLast(String.valueOf(random.nextInt(20) + 1));
                    try {
                        sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}