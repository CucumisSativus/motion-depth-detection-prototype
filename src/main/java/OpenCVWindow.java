
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

class ImageImplement extends JPanel {

    private Image img;


    public ImageImplement(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}

public class OpenCVWindow extends JFrame{

    ImageImplement panel = null;
    public OpenCVWindow(){
        prepareWindow();
    }
    public OpenCVWindow(String title)
    {
        super(title);
        prepareWindow();
    }

    private void prepareWindow() {
        panel = new ImageImplement(new ImageIcon("1.png").getImage());
        add(panel);
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void showImage(Mat m)
    {
        try
        {
            Image img = MatToImageConverter.convert_and_flip(m);
            remove(panel);
            panel = new ImageImplement(img);
            add(panel);
            revalidate();
            repaint();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}

