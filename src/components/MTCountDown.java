package components;

import javax.swing.JTextField;

import clockBuddy.GUI;

public class MTCountDown implements Runnable {
	long time;
	long inValue;
	String parsedValue;
	private JTextField countDownTextField;
	public boolean cancelled = false;
	public boolean isCaptured = false;

	public MTCountDown(long time, JTextField countDownTextField) {

		this.countDownTextField = countDownTextField;
		this.time = time;
	}

	public MTCountDown() {
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		countDownTextField.setEditable(false);
		while ((time - (System.currentTimeMillis() - start) >= 0) && !cancelled) {
			inValue = (time - (System.currentTimeMillis() - start));
			GUI.time = inValue;
			countDownTextField.setText(TimeParser.parse(inValue));
		}
		countDownTextField.setEditable(true);
		if (!cancelled) {
			GUI.startStopMTCDButton.setText("Start");
		} else {
			cancelled = false;
		}
	}

	public void cancelCountDown() {
		GUI.time = inValue;
		cancelled = true;

	}

}
