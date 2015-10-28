
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.opencv.core.Mat;


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

