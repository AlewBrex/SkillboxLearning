import java.util.HashMap;
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
        Account first;
        Account second;

        if (fromAccountNum.hashCode() < toAccountNum.hashCode())
        {
            first = accountFrom;
            second = accountTo;
        }
        else {
            first = accountTo;
            second = accountFrom;
        }
        synchronized (first)
        {
            synchronized (second)
            {
                if (!accountFrom.isAccountLock() && !accountTo.isAccountLock())
                {
                    if (accountFrom.getMoney() > amount)
                    {
                        accountFrom.setMoney(accountFrom.getMoney() - amount);
                        accountTo.setMoney(accountTo.getMoney() + amount);

                        if (amount > 50000)
                        {
                            try {
                                isFraud = isFraud(fromAccountNum, toAccountNum, amount);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (isFraud)
                            {
                                accountFrom.setAccountLock(true);
                                accountTo.setAccountLock(true);
                            }
                        }
                    }
                    else {
                        System.out.println("Insufficient funds for transaction");
                    }
                }
                else {
                    System.out.println("Accounts are locked");
                }
            }
        }
    }
    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum)
    {
        return accounts.get(accountNum).getMoney();
    }
    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }

    public synchronized void setAccounts(ConcurrentHashMap<String, Account> accounts) {
        this.accounts = accounts;
    }
}