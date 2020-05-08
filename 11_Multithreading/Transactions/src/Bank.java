import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Bank
{
    private ConcurrentHashMap<String, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }
    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount)
    {
        boolean isFraud = false;

        Account accountFrom = accounts.get(fromAccountNum);
        Account accountTo = accounts.get(toAccountNum);

        accountFrom.setMoney(accountFrom.getMoney() - amount);
        accountTo.setMoney(accountTo.getMoney() + amount);
        if (amount > 50000)
        {
            try {
                wait();
                isFraud = isFraud(fromAccountNum,toAccountNum,amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isFraud)
            {
                accountFrom.setAccountLock(true);
                accountTo.setAccountLock(true);
            }
        }
        notify();
    }
    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum)
    {
        return accounts.get(accountNum).getMoney();
    }
}