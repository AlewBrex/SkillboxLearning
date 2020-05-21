import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;
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
        List<Long> expected = new ArrayList<>();
        for (int i = 1; i <= accountArrayList.size(); i++) {
            expected.add(accountArrayList.get(i).getMoney());
        }
        threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            for (int e = 1; e <= 10; e++) {
                int finalE = e;
                threads.add(new Thread(()->{
                    bank.transfer(stringList.get(finalE),stringList.get(11-finalE),88000L);
                }));
            }
        }
        threads.forEach(Thread::start);
        List<Long> actual = new ArrayList<>();
        for (int i = 1; i <= stringList.size(); i++) {
            String s = stringList.get(i);
            actual.add(bank.getBalance(s));
        }
        assertEquals(expected,actual);
    }

    @Override
    protected void tearDown() throws Exception
    {

    }
}