import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

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

    public int getHueStart() {
        return hueStart;
    }

    public void setHueStart(int hueStart) {
        this.hueStart = hueStart;
    }

    public int getSaturationStart() {
        return saturationStart;
    }

    public void setSaturationStart(int saturationStart) {
        this.saturationStart = saturationStart;
    }

    public int getValueStart() {
        return valueStart;
    }

    public void setValueStart(int valueStart) {
        this.valueStart = valueStart;
    }

    public int getHueStop() {
        return hueStop;
    }

    public void setHueStop(int hueStop) {
        this.hueStop = hueStop;
    }

    public int getSaturationStop() {
        return saturationStop;
    }

    public void setSaturationStop(int saturationStop) {
        this.saturationStop = saturationStop;
    }

    public int getValueStop() {
        return valueStop;
    }

    public void setValueStop(int valueStop) {
        this.valueStop = valueStop;
    }

    private int hueStart;
    private int saturationStart;
    private int valueStart;

    private int hueStop;
    private int saturationStop;
    private int valueStop;

    java.util.List<MatOfPoint> last_contours;


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

        frame = findAndDrawContours(morphOutput, frame);

        return frame;
    }
    private Mat findAndDrawContours(Mat maskedImage, Mat frame)
    {
        // init
        java.util.List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();

        // find contours
        Imgproc.findContours(maskedImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
        last_contours = contours;
        // if any contour exist...
        if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
        {
            // for each contour, display it in blue
            for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
            {
                Imgproc.drawContours(frame, contours, idx, new Scalar(250, 250, 0));
            }
        }

        return frame;
    }
}
