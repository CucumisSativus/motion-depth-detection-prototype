
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/*
 * Detects faces in an image, draws boxes around them, and writes the results
 * to "faceDetection.png".
 */
public class Main {
    OpenCVWindow camera_window = null;
    OpenCVWindow trackerWindow = null;
    ObjectTracker tracker;
    SliderWindow sliderWindow;
    Main(){
        camera_window = new OpenCVWindow("Camera window");
        trackerWindow = new OpenCVWindow("Tracker window");
        tracker = new ObjectTracker(20, 60, 50, 50, 200, 255);
        sliderWindow = SliderWindow.createAndShowGUI();
    }
    public static void main(String [] args) {
        Main main = new Main();
        main.run();
    }

    public void run(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        if(!camera.isOpened()){
            System.out.println("Error");
        }
        while(true){
            Mat frame = new Mat();
            camera.read(frame);
            if(!frame.empty()) {
                camera_window.showImage(frame);
                trackerWindow.showImage(tracker.trackedObjectImage(frame));
                tracker.setHueStart(sliderWindow.getHueStart());
                tracker.setHueStop(sliderWindow.getHueStop());
                tracker.setSaturationStart(sliderWindow.getSaturationStart());
                tracker.setSaturationStop(sliderWindow.getSaturationStop());
                tracker.setValueStart(sliderWindow.getValueStart());
                tracker.setValueStop(sliderWindow.getValueStop());
                //System.out.println(sliderWindow.getHueStart());
            }
        }

    }
}

