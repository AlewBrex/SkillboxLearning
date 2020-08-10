import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.RecursiveAction;

public class Loader extends RecursiveAction
{
    private static final long start = System.currentTimeMillis();
    private static final char[] letters = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х',};
    private  static int regionCode = 0;
    private static final int countRegionCode = 100;

    public Loader()
    {
        regionCode++;
        try {
            getCarNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void compute()
    {
        if (regionCode <= countRegionCode)
        {
            Loader loader = new Loader();
            loader.fork();
        }
    }

    public void getCarNumber() throws FileNotFoundException
    {
        PrintWriter writer = new PrintWriter("res/nmbrs_" + regionCode + ".txt");
        StringBuffer buffer = new StringBuffer();

        for (int number = 1; number < 1000; number++)
        {
            for (char firstLetter : letters)
            {
                for (char secondLetter : letters)
                {
                    for (char thirdLetter : letters)
                    {
                        buffer.append(firstLetter)
                            .append(padNumber(number, 3))
                                .append(secondLetter)
                                .append(thirdLetter)
                                .append(padNumber(regionCode, 2))
                                .append("\n");
                    }
                }
            }
        }
        writer.write(buffer.toString());
        writer.flush();
        writer.close();
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static synchronized String padNumber(int number, int numberLength)
    {
        StringBuffer numberStr = new StringBuffer(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for(int i = 0; i < padSize; i++)
        {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
    }
}