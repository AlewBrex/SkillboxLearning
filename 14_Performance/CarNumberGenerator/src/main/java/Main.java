public class Main
{
    public static void main(String[] args)
    {
        Loader loader = new Loader();
        new Thread(() -> loader.getCarNumber(1, 100,1)).start();
        new Thread(() -> loader.getCarNumber(1,100,1)).start();
    }
}