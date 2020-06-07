import java.util.concurrent.ForkJoinPool;

public class Main
{
    public static void main(String[] args)
    {
        String url = "https://lenta.ru/";
        Lenta root = new Lenta(url);
        Lenta lenta = new ForkJoinPool().invoke(new LinkLenta(root));
        System.out.println(lenta);
    }
}