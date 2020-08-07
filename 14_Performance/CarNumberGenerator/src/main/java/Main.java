public class Main
{
    private static final int processor = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args)
    {
        for (int i = 0; i < processor; i++)
        {
            Loader loader = new Loader();
            new Thread(()->
            {
                try {
                    loader.getCarNumber();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}