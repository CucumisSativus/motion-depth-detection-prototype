
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/*
 * Detects faces in an image, draws boxes around them, and writes the results
 * to "faceDetection.png".
 */
public class Main {
    OpenCVWindow cameraWindow = null;
    OpenCVWindow trackerWindow = null;
    OpenCVWindow floorFinderWindow = null;
    ObjectTracker tracker;
    SliderWindow sliderWindow;
    FloorFinder floorFinder;

    Main(){
        cameraWindow = new OpenCVWindow("Camera window");
//        trackerWindow = new OpenCVWindow("Tracker window");
        floorFinderWindow = new OpenCVWindow("Floor finder window");
        tracker = new ObjectTracker(20, 60, 50, 50, 200, 255);
        sliderWindow = new SliderWindow();
        floorFinder = new FloorFinder(50, 100, 5);
        
//        trackerWindow.setLocation(cameraWindow.getLocation().x + cameraWindow.getWidth(), cameraWindow.getLocation().y);
//        sliderWindow.setLocation(trackerWindow.getLocation().x + trackerWindow.getWidth(), trackerWindow.getLocation().y);
    }
    public static void main(String [] args) {
        Main main = new Main();
        main.run(args.length == 1 ? args[0] : null);
    }

    public void run(String filePath){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera;
        if(filePath != null){
            camera = new VideoCapture(filePath);
        }
        else{
            camera = new VideoCapture(0);
        }
        if(!camera.isOpened()){
            System.out.println("Error");
        }
        while(true){
            Mat frame = new Mat();
            camera.read(frame);
            if(!frame.empty()) {
                cameraWindow.showImage(frame);
//                trackerWindow.showImage(tracker.trackedObjectImage(frame));
                floorFinderWindow.showImage(floorFinder.findFoloor(frame));
                tracker.setParams(sliderWindow.getSlidersValues());
            }
        }

    }
}

