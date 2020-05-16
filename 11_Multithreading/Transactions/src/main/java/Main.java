import java.util.concurrent.ConcurrentHashMap;

public class Main
{
    public static void main(String[] args)
    {
        Bank bank = new Bank();
        ConcurrentHashMap<String, Account> accountsFirst = new ConcurrentHashMap<>();
        Account account1 = new Account(80000);
        Account account2 = new Account(67000);
        String accountOne = "one";
        String accountTwo = "two";
        accountsFirst.put(accountOne,account1);
        accountsFirst.put(accountTwo,account2);
        bank.setAccounts(accountsFirst);
        bank.transfer("one","two",60000);
        try {
            bank.isFraud("one","two",60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(bank.getBalance("two"));
    }
}