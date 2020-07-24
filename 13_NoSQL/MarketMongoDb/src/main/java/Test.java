import java.util.Scanner;

public class Test
{
    public static void main(String[] args)
    {
        Storage storage = new Storage();
        storage.init();
        Scanner scanner = new Scanner(System.in);

        for (;;)
        {
            System.out.println("Введите команду: ");
            String input[] = scanner.nextLine().split(" ");

            try
            {
                if (input[0].equalsIgnoreCase("ДОБАВИТЬ_МАГАЗИН"))
                {
                    storage.addMarket(input[1]);
                    System.out.println("Магазин " + input[1] + " успешно добавлен");
                }
                if (input[0].equalsIgnoreCase("ДОБАВИТЬ_ТОВАР"))
                {
                    var fst = input[1];
                    var scnd = Integer.parseInt(input[2]);
                    storage.addGood(fst, scnd);
                    System.out.println("Товар " + fst + " успешно добавлен");
                }
                if (input[0].equalsIgnoreCase("ВЫСТАВИТЬ_ТОВАР"))
                {
                    storage.displayGood(input[1], input[2]);
                    System.out.println("Товар " + input[1] +
                            " успешно добавлен в список товаров магазина " + input[2]);
                }
                if (input[0].equalsIgnoreCase("СТАТИСТИКА_ТОВАРОВ"))
                {
                    storage.statisticGoods();
                }
            }
            catch (Exception ex)
            {
                System.out.println("Команда введена некорректно");
            }
        }
    }
}