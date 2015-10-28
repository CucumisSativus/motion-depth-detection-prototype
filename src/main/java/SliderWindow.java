import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * do not remove commented code, slider labels will show current values in next commit
 */
public class SliderWindow extends JFrame implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	
	private JSlider hueStartSlider, hueStopSlider, satStartSlider, satStopSlider, valueStartSlider, valStopSlider;
	//private JLabel hueStartLabel, hueStopLabel, satStartLabel, satStopLabel, valStartLabel, valStopLabel;
	private JPanel mainPanel;
	
	public SliderWindow() {
		this(0,100,20,60,100,255);
	}	
	
	public SliderWindow(int hueStart, int hueStop, int satStart, int satStop, int valStart, int valStop) {
		super("Sliders");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		hueStartSlider = new JSlider(JSlider.HORIZONTAL, 0, 180, hueStart);
		hueStopSlider = new JSlider(JSlider.HORIZONTAL, 0, 180, hueStop);
		satStartSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, satStart);
		satStopSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, satStop);
		valueStartSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, valStart);
		valStopSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, valStop);
		
		/*hueStartLabel = new JLabel("hue start: " + hueStart);
		hueStopLabel = new JLabel("hue stop: " + hueStop);
		satStartLabel = new JLabel("saturation start: " + satStart);
		satStopLabel = new JLabel("saturation stop: " + satStop);
		valStartLabel = new JLabel("value start: " + valStart);
		valStopLabel = new JLabel("value stop: " + valStop);*/

		JSlider[] sliders = {hueStartSlider, hueStopSlider, satStartSlider, satStopSlider, valueStartSlider, valStopSlider};
		String[] sliderLabels = {"hue start", "hue stop", "saturation start", "saturation stop", "value start", "value stop"};
		//JLabel[] labels = {hueStartLabel, hueStopLabel, satStartLabel, satStopLabel, valStartLabel, valStopLabel};

		for (int i=0; i<sliders.length; i++) {
			int max = sliders[i].getMaximum();
		    sliders[i].setMajorTickSpacing(max/2);
		    sliders[i].setMinorTickSpacing(max/10/2);
		    sliders[i].setPaintTicks(true);
		    sliders[i].setPaintLabels(true);
		    sliders[i].setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		    Font font = new Font("Sans", Font.PLAIN, 10);
		    sliders[i].setFont(font);
		    
		    //sliders[i].addChangeListener(this);
		    
		    mainPanel.add(new JLabel(sliderLabels[i], JLabel.CENTER));
		    //mainPanel.add(labels[i], JLabel.CENTER);
		    mainPanel.add(sliders[i]);
		}
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(mainPanel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocation(100, 200);
        this.setVisible(true);
	}
	
	public int[] getSlidersValues() {
		JSlider[] sliders = {hueStartSlider, hueStopSlider, satStartSlider, satStopSlider, valueStartSlider, valStopSlider};
		int[] v = new int[sliders.length];
		for (int i=0; i<v.length; i++) {
			v[i] = sliders[i].getValue();
		}
		return v;
	}
	
	public int getHueStart() {
		return hueStartSlider.getValue();
	}
	public int getHueStop() {
		return hueStopSlider.getValue();
	}
	public int getSaturationStart() {
		return satStartSlider.getValue();
	}
	public int getSaturationStop() {
		return satStopSlider.getValue();
	}
	public int getValueStart() {
		return valueStartSlider.getValue();
	}
	public int getValueStop() {
		return valStopSlider.getValue();
	}

	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
