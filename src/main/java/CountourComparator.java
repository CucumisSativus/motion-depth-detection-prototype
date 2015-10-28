import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

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

    static Mat substract(Mat firstFrame, Mat secondFrame){
       Mat mask = new Mat(firstFrame.rows(), firstFrame.cols(), CvType.CV_8U);

    }
}
