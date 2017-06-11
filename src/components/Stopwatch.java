package components;

import javax.swing.JTextField;

import clockBuddy.GUI;

public class Stopwatch implements Runnable {
	public boolean cancelled;

	JTextField stopWatchTextField;

	int inValue;

	int oldInValue;
	
	boolean hasRun = false;
	
	public Stopwatch() {
	}

	public Stopwatch(JTextField stopwatchLabel, int oldInValue) {
		this.stopWatchTextField = stopwatchLabel;
	this.oldInValue = oldInValue;
	}

	public void run() {
		long start = System.currentTimeMillis();
		if(!hasRun){
		start -= oldInValue;
			hasRun = true;
		}
		stopWatchTextField.setEditable(false);
		while (!cancelled) {
			inValue = (int) (System.currentTimeMillis() - start);
			stopWatchTextField.setText(TimeParser.parse(inValue));
		}
		stopWatchTextField.setEditable(true);
		if (!cancelled) {
			GUI.startStopStopwatchButton.setText("Start");
		} else {
			cancelled = false;
		}
	}

	public int cancelStopwatch() {
		cancelled = true;
	hasRun = false;
		return inValue;		
	}
}
