package components;

import javax.swing.JTextField;

import clockBuddy.GUI;

public class Capture implements Runnable {

	JTextField captureThreadTextField;

	JTextField countDownTextField;

	MTCountDown mtCountDown;

	long inValue;
	
	public boolean cancelled = false;

	long time;
	
	public Capture() {
	}

	public Capture(JTextField captureThreadTextField,
			JTextField countDownTextField, MTCountDown mtCountDown, long time) {
		this.captureThreadTextField = captureThreadTextField;
		this.countDownTextField = countDownTextField;
		this.mtCountDown = mtCountDown;
	this.time = time;
	}

	@Override
	public void run() {
		mtCountDown.cancelCountDown();
		mtCountDown.isCaptured = true;
		GUI.startStopMTCDButton.setText("Continue");
		long start = System.currentTimeMillis();
		long time = this.time;
		captureThreadTextField.setEditable(false);
		while ((time - (System.currentTimeMillis() - start) >= 0)  && !cancelled) {
			inValue = (time - (System.currentTimeMillis() - start));
			GUI.time = inValue;
			GUI.captureTime = inValue;
			captureThreadTextField.setText(TimeParser.parse(inValue));

		}
		captureThreadTextField.setEditable(true);
		cancelled = false;
	}

	public void cancelCaptureThread() {
		GUI.time = inValue;
		GUI.captureTime = inValue;
		cancelled = true;
		
	}
}
