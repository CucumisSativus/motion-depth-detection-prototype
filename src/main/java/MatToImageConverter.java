import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Created by michal on 27.10.15.
 */
public class MatToImageConverter {
    public static Image convert(Mat m) throws IOException {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", m, matOfByte);

        byte[] byteArray = matOfByte.toArray();
        return getImage(byteArray);
    }

    public static Image convert_and_flip(Mat m) throws IOException{
        MatOfByte matOfByte = new MatOfByte();
        Mat flippedMat = new Mat();
        Core.flip(m, flippedMat, 1);
        Imgcodecs.imencode(".jpg", flippedMat, matOfByte);

        byte[] byteArray = matOfByte.toArray();
        return getImage(byteArray);
    }
    private static Image getImage(byte[] byteArray) throws IOException {
        InputStream in = new ByteArrayInputStream(byteArray);
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) readers.next();

        ImageInputStream iis = ImageIO.createImageInputStream((Object) in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();

        return reader.read(0, param);
    }
}
