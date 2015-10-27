import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.io.IOException;
import java.util.*;

/**
 * Created by michal on 27.10.15.
 */
public class ObjectTracker {


    public ObjectTracker(int hueStart, int saturationStart, int valueStart, int hueStop, int saturationStop, int valueStop) {
        this.hueStart = hueStart;
        this.saturationStart = saturationStart;
        this.valueStart = valueStart;
        this.hueStop = hueStop;
        this.saturationStop = saturationStop;
        this.valueStop = valueStop;
    }

    private int hueStart;
    private int saturationStart;
    private int valueStart;

    private int hueStop;
    private int saturationStop;
    private int valueStop;


    public Mat trackedObjectImage(Mat frame){
        Mat blurredImage = new Mat();
        Mat hsvImage = new Mat();
        Mat mask = new Mat();
        Mat morphOutput = new Mat();


        // remove some noise
        Imgproc.blur(frame, blurredImage, new Size(7, 7));
        // convert the frame to HSV
        Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
        Scalar minValues = new Scalar(this.hueStart, this.saturationStart,
                this.valueStart);
        Scalar maxValues = new Scalar(this.hueStop, this.saturationStop,
                this.valueStop);

        // threshold HSV image to select tennis balls
        Core.inRange(hsvImage, minValues, maxValues, mask);

        // morphological operators
        // dilate with large element, erode with small ones
        Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
        Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));

        Imgproc.erode(mask, morphOutput, erodeElement);
        Imgproc.erode(mask, morphOutput, erodeElement);

        Imgproc.dilate(mask, morphOutput, dilateElement);
        Imgproc.dilate(mask, morphOutput, dilateElement);

        frame = findAndDrawBalls(morphOutput, frame);

        return frame;
    }
    private Mat findAndDrawBalls(Mat maskedImage, Mat frame)
    {
        // init
        java.util.List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();

        // find contours
        Imgproc.findContours(maskedImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

        // if any contour exist...
        if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
        {
            // for each contour, display it in blue
            for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
            {
                Imgproc.drawContours(frame, contours, idx, new Scalar(250, 0, 0));
            }
        }

        return frame;
    }
}
