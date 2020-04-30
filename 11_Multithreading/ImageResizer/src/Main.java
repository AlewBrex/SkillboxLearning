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

        int oneToEight = files.length / processor;

        for (int i = 0; i < processor; i++) {
            File[] files1 = new File[oneToEight];
            System.arraycopy(files, (oneToEight * i), files1, 0, files1.length);
            new Thread(new ImageResizer(files1, newWidth, dstFolder,start)).start();
        }

//        File[] files1 = new File[quarter];
//        System.arraycopy(files, 0, files1, 0, files1.length);
//        ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder,start);
//        new Thread(resizer1).start();
//
//        File[] files2 = new File[quarter];
//        System.arraycopy(files, quarter, files2, 0, files2.length);
//        ImageResizer resizer2 = new ImageResizer(files2, newWidth, dstFolder,start);
//        new Thread(resizer2).start();
//
//        File[] files3 = new File[quarter];
//        System.arraycopy(files, files.length - (files.length/2), files3, 0, files3.length);
//        ImageResizer resizer3 = new ImageResizer(files3, newWidth, dstFolder,start);
//        new Thread(resizer3).start();
//
//        File[] files4 = new File[quarter];
//        System.arraycopy(files, files.length - quarter, files4, 0, files4.length);
//        ImageResizer resizer4 = new ImageResizer(files4, newWidth, dstFolder,start);
//        new Thread(resizer4).start();
    }
}