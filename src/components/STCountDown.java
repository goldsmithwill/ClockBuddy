package components;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextField;

public class STCountDown implements Runnable {
	JTextField lapLabel;
	int time;
	private JTextField countDownLabel;
	public boolean cancelled = false;
	
	public STCountDown(JTextField lapLabel, int time,
			JTextField countDownLabel) {
		this.lapLabel = lapLabel;
		this.countDownLabel = countDownLabel;
		this.time = time;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		countDownLabel.setEditable(false);
		while ((time - (System.currentTimeMillis() - start) >= 0) && !cancelled) {
			countDownLabel.setText(""
					+ (time - (System.currentTimeMillis() - start)));

		}
		countDownLabel.setEditable(true);
		cancelled = false;
	}

	public void cancelCountDown() {
		cancelled = true;
		
		
	}

}
