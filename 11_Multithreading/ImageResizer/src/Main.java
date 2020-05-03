import java.io.File;

public class Main
{
    private static int newWidth = 240;

    public static void main(String[] args)
    {
        String srcFolder = "C:/Users/aleks/Desktop/FirstDirectory";
        String dstFolder = "C:/Users/aleks/Desktop/SecondDirectory";

        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        int processor = Runtime.getRuntime().availableProcessors();
        boolean more = files.length > processor;
        int oneToEight = files.length / processor;
        int onePlus = oneToEight + 1;
        int mdl = files.length % processor;

        if (more) {
            for (int g = 0; g < processor; g++) {
                if (g < mdl) {
                    File[] files1 = new File[onePlus];
                    System.arraycopy(files,(onePlus * g), files1, 0, files1.length);
                    ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder,start);
                    new Thread(resizer).start();
                }
                else {
                    File[] files1 = new File[oneToEight];
                    System.arraycopy(files,(((oneToEight - 1)*mdl) + (oneToEight * g)), files1, 0, files1.length);
                    ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder,start);
                    new Thread(resizer).start();
                }
            }
        }
        else {
            for (int f = 0; f < files.length ; f++) {
                File[] files1 = new File[1];
                System.arraycopy(files, f, files1, 0, files1.length);
                ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder,start);
                new Thread(resizer).start();
            }
        }
    }
}