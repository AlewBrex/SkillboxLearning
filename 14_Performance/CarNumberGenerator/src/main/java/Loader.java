import java.io.PrintWriter;

public class Loader
{
    private static final long start = System.currentTimeMillis();
    private static final char[] letters = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х',};
    private static PrintWriter writer;
    private static StringBuffer numberStr;


    public void getCarNumber() throws Exception
    {
        for (int regionCode = 1; regionCode < 100; regionCode++)
        {
            writer = new PrintWriter("res/nmbrs_" + regionCode + ".txt");
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
        }
        writer.flush();
        writer.close();
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static synchronized String padNumber(int number, int numberLength)
    {
        numberStr = new StringBuffer(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for(int i = 0; i < padSize; i++)
        {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
    }
}