import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class BankTest extends TestCase
{
    Bank bank;
    ArrayList<Thread> threads;
    ConcurrentHashMap<String, Account> accounts;

    @Override
    protected void setUp() throws Exception
    {
        bank = new Bank();
        accounts = new ConcurrentHashMap<>();

        String accountOne = "one";
        String accountTwo = "two";
        String accounTree = "tree";
        String accountFour = "four";
        String accountFive = "five";
        String accountSix = "six";
        String accountSeven = "seven";
        String accountEight = "eight";
        String accountNine = "nine";
        String accountTen = "ten";

        Account one = new Account(840000L);
        Account two = new Account(760000L);
        Account tree = new Account(830000L);
        Account four = new Account(973000L);
        Account five = new Account(474000L);
        Account six = new Account(267000L);
        Account seven = new Account(633000L);
        Account eight = new Account(176000L);
        Account nine = new Account(999000L);
        Account ten = new Account(598000L);

        accounts.put(accountOne,one);
        accounts.put(accountTwo,two);
        accounts.put(accounTree,tree);
        accounts.put(accountFour,four);
        accounts.put(accountFive,five);
        accounts.put(accountSix,six);
        accounts.put(accountSeven,seven);
        accounts.put(accountEight,eight);
        accounts.put(accountNine,nine);
        accounts.put(accountTen,ten);

        bank.setAccounts(accounts);
    }

    public void testTransfer()
    {
        threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(()->{
                    bank.transfer("one","two",(long) (53000 * (Math.random())));
            }));
            threads.add(new Thread(()->{
                bank.transfer("tree","one",(long) (34000 * (Math.random())));
            }));
            threads.add(new Thread(()->{
                bank.transfer("ten","eight",(long) (1000 * (Math.random())));
            }));
            threads.add(new Thread(()->{
                bank.transfer("two","tree",(long) (33000 * (Math.random())));
            }));
            threads.add(new Thread(()->{
                bank.transfer("five","ten",(long) (5000 * (Math.random())));
            }));
            threads.add(new Thread(()->{
                bank.transfer("four","one",(long) (42000 * (Math.random())));
            }));threads.add(new Thread(()->{
                bank.transfer("seven","nine",(long) (132000 * (Math.random())));
            }));
            threads.add(new Thread(()->{
                bank.transfer("nine","six",(long) (331000 * (Math.random())));
            }));
        }
        threads.forEach(Thread::start);
    }

    @Override
    protected void tearDown() throws Exception
    {

    }
}