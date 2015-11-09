import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class FloorFinder {
    public FloorFinder(double threshold1, double threshold2, int stepSize) {
        this.threshold1 = threshold1;
        this.threshold2 = threshold2;
        this.stepSize = stepSize;
    }

    private double threshold1, threshold2;
    private int stepSize;

    public Mat findFoloor(Mat frame) {
        Mat copiedFrame = frame.clone();
        Mat grayFrame = new Mat();
        Mat threshold = new Mat();
        Imgproc.cvtColor(copiedFrame, grayFrame, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(grayFrame, threshold, 0, 255, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);

        // noise removal
        Mat kernel = Mat.ones(3,3, CvType.CV_8U);

        Mat opening = new Mat();
        Imgproc.morphologyEx(threshold, opening, Imgproc.MORPH_OPEN, kernel);

        // sure background area
        Mat sure_bg = new Mat(frame.rows(), frame.rows(), CvType.CV_8U);
        Imgproc.dilate(opening, sure_bg, kernel);

        // Finding sure foreground area

        Mat distTransform = new Mat();
        Imgproc.distanceTransform(opening, distTransform, Imgproc.DIST_L2, 5);
        Mat sureFg = new Mat(frame.rows(), frame.rows(), CvType.CV_8U);
        double distTransforMax = Core.minMaxLoc(distTransform).maxVal;
        Imgproc.threshold(distTransform, sureFg, 0.6 * distTransforMax, 255, 0 );

        //finding unknown region
        sureFg.convertTo(sureFg, CvType.CV_8U);
        Mat unknown = new Mat(sureFg.rows(), sureFg.cols(), CvType.CV_8U);
        Core.subtract(sure_bg, sureFg, unknown);

        return unknown;

    }
}
