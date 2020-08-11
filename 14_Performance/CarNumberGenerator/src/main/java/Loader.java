import java.io.PrintWriter;
import java.util.concurrent.RecursiveAction;

public class Loader extends RecursiveAction
{
    private static final long start = System.currentTimeMillis();
    private static final char[] letters = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х',};

//    public Loader()
//    {
//        regionCode++;
//        try {
//            getCarNumber();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void compute()
    {
        Loader loader = new Loader();
        loader.fork();
    }

    public void getCarNumber(int regionFirst, int regionSecond, int numberFile)
    {
        try (PrintWriter writer = new PrintWriter("res/nmbrs_" + numberFile + ".txt"))
        {
            for (int regionCode = regionFirst; regionCode < regionSecond; regionCode++)
            {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++)
                {
                    for (char firstLetter : letters)
                    {
                        for (char secondLetter : letters)
                        {
                            for (char thirdLetter : letters)
                            {
                                builder.append(firstLetter)
                                        .append(padNumber(number, 3))
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(padNumber(regionCode, 2))
                                        .append("\n");
                            }
                        }
                    }
                }
                writer.write(builder.toString());
            }
            writer.flush();
            writer.close();
            System.out.println((System.currentTimeMillis() - start) + " ms");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static synchronized String padNumber(int number, int numberLength)
    {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for(int i = 0; i < padSize; i++)
        {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
    }
}