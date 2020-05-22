import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class BankTest extends TestCase
{
    Bank bank;
    ArrayList<Thread> threads;
    ConcurrentHashMap<String, Account> accounts;
    ArrayList<String> stringList;
    ArrayList<Account> accountArrayList;

    @Override
    protected void setUp() throws Exception
    {
        bank = new Bank();
        accounts = new ConcurrentHashMap<>();

        stringList = new ArrayList<>();
        for (int j = 1; j <= 10; j++) {
            stringList.add(Integer.toString(j));
        }

        accountArrayList = new ArrayList<>();
        for (int l = 0; l < 10; l++) {
            accountArrayList.add(new Account(888000L));
        }

        for (int k = 0; k < 10; k++) {
            accounts.put(stringList.get(k),accountArrayList.get(k));
        }

        bank.setAccounts(accounts);
    }

    public void testTransfer()
    {
        long expected = 0;
        for (Account account : accountArrayList) {
            expected += account.getMoney();
        }
        threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            threads.add(new Thread(()->
                    bank.transfer(
                            stringList.get(new Random().nextInt(stringList.size() - 1)),
                            stringList.get(new Random().nextInt(stringList.size() - 1)),
                            (long) (new Random().nextDouble() * 10000))));
        }
        threads.forEach(Thread::start);
        threads.forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        long actual = 0;
        for(String s : stringList)
        {
            actual += bank.getBalance(s);
        }
        assertEquals(expected,actual);
    }

    @Override
    protected void tearDown() throws Exception
    {

    }
}