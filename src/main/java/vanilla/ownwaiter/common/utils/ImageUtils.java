package vanilla.ownwaiter.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class ImageUtils {

    public static void resizeImage(File file, int width, int height) throws IOException {
        Image originalImage = ImageIO.read(file);
        Image resizeImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(resizeImage, 0,0, width, height, null);
        graphics2D.dispose();

        ImageIO.write(outputImage, FileUtils.getFileExtension(file), file);
    }
}
