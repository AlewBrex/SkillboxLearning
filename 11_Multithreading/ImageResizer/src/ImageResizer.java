import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable
{
    private File[] files;
    private int newWidth;
    private String dstFolder;
    private  long start;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run()
    {
        int fourWidth = newWidth*4;
        try
        {
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) fourWidth)
                );
                BufferedImage newImage = new BufferedImage(
                        fourWidth, newHeight, BufferedImage.TYPE_INT_BGR
                );
                int widthStep = image.getWidth() / fourWidth;
                int heightStep = image.getHeight() / newHeight;
                for (int x = 0; x < fourWidth; x++)
                {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                int newHeight1 = (int) Math.round(
                        newImage.getHeight() / (newImage.getWidth() / (double) newWidth)
                );
                BufferedImage newImage1 = new BufferedImage(
                        newWidth, newHeight1, BufferedImage.TYPE_INT_BGR
                );
                int widthStep1 = newImage.getWidth() / newWidth;
                int heightStep1 = newImage.getHeight() / newHeight1;
                for (int x = 0; x < newWidth; x++)
                {
                    for (int y = 0; y < newHeight1; y++) {
                        int rgb1 = newImage.getRGB(x * widthStep1, y * heightStep1);
                        newImage1.setRGB(x, y, rgb1);
                    }
                }
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage1, "jpg", newFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + "ms");
    }
}