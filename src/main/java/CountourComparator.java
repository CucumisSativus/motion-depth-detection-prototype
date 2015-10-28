import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

/**
 * Created by michal on 27.10.15.
 */
public class CountourComparator {
    private int thresholdMin;
    private int thresholdMax;

    public CountourComparator(int thresholdMin, int thresholdMax) {
        this.thresholdMin = thresholdMin;
        this.thresholdMax = thresholdMax;
    }

    static Mat substract(java.util.List<MatOfPoint> contours_old, java.util.List<MatOfPoint> contours_new){
        return new Mat();
    }
}
