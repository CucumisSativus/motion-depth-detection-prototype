import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SliderWindow extends JPanel implements ActionListener, WindowListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	
	private JSlider hueStartSlider, hueStopSlider, saturationStartSlider, saturationStopSlider, valueStartSlider, valueStopSlider;
	
	public SliderWindow() {
		this(0,100,20,60,100,255);
	}	
	
	public SliderWindow(int hueStart, int hueStop, int satStart, int satStop, int valStart, int valStop) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		hueStartSlider = new JSlider(JSlider.HORIZONTAL, 0, 180, hueStart);
		hueStopSlider = new JSlider(JSlider.HORIZONTAL, 0, 180, hueStop);
		saturationStartSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, satStart);
		saturationStopSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, satStop);
		valueStartSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, valStart);
		valueStopSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, valStop);

		JSlider[] sliders = {hueStartSlider, hueStopSlider, saturationStartSlider, saturationStopSlider, valueStartSlider, valueStopSlider};
		String[] sliderLabels = {"hue start", "hue stop", "saturation start", "saturation stop", "value start", "value stop"};

		for (int i=0; i<sliders.length; i++) {
			//int min = sliders[i].getMinimum();
			int max = sliders[i].getMaximum();
		    sliders[i].setMajorTickSpacing(max/2);
		    sliders[i].setMinorTickSpacing(max/10/2);
		    sliders[i].setPaintTicks(true);
		    sliders[i].setPaintLabels(true);
		    sliders[i].setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		    Font font = new Font("Sans", Font.PLAIN, 10);
		    sliders[i].setFont(font);
		    //sliders[i].addChangeListener(this);
		    
		    add(new JLabel(sliderLabels[i], JLabel.CENTER));
		    add(sliders[i]);
		}
		
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}
	
	public int getHueStart() {
		return hueStartSlider.getValue();
	}
	public int getHueStop() {
		return hueStopSlider.getValue();
	}
	public int getSaturationStart() {
		return saturationStartSlider.getValue();
	}
	public int getSaturationStop() {
		return saturationStopSlider.getValue();
	}
	public int getValueStart() {
		return valueStartSlider.getValue();
	}
	public int getValueStop() {
		return valueStopSlider.getValue();
	}

	public static SliderWindow createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SliderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SliderWindow sw = new SliderWindow();
                 
        //Add content to the window.
        frame.add(sw, BorderLayout.CENTER);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        return sw;
    }

	public static void main(String[] args) {
		/* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //createAndShowGUI();
            }
        });
	}

	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
