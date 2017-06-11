package clockBuddy;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import components.Alarm;
import components.Capture;
import components.Clock;
import components.MTCountDown;
import components.Stopwatch;

public class GUI implements Runnable {

	JLabel countDownBuddyLabel = new JLabel(new ImageIcon(
			"CountDownBuddyIcon.png"), JLabel.CENTER);

	JLabel stopwatchBuddyLabel = new JLabel(new ImageIcon(
			"StopWatchBuddyIcon.png"), JLabel.CENTER);
	
	JFrame frame = new JFrame();
	Container container = frame.getContentPane();
	JTabbedPane tabbedPane = new JTabbedPane();
	public static JPanel clockPanel = new JPanel();
	JPanel MTCountDownPanel = new JPanel();
	JPanel stopwatchPanel = new JPanel();

	JPanel alarmPanel = new JPanel();

	JComboBox<String> dayComboBox = new JComboBox<String>();
	
	JComboBox<String> hourComboBox = new JComboBox<String>();

	JComboBox<String> tenMinuteComboBox = new JComboBox<String>();

	JComboBox<String> unitMinuteComboBox = new JComboBox<String>();

	JComboBox<String> amPmComboBox = new JComboBox<String>();

	JButton setAlarmButton = new JButton("Set Alarm");

	MTCountDown mtCountDown;
	Capture captureThread;
	Stopwatch stopwatch;

	public static long time;

	int stopwatchTime;

	public static long captureTime;

	public static JButton startStopMTCDButton = new JButton("Start");
	JButton clearButton = new JButton("Clear");
	JButton captureButton = new JButton("Capture");

	JTextField countDownLabel = new JTextField("000000");
	JTextField captureThreadLabel = new JTextField("000000");

	Component[] countDownComponentArray = { countDownLabel, captureThreadLabel,
			startStopMTCDButton, clearButton, captureButton };

	public static JButton startStopStopwatchButton = new JButton("Start");
	JButton clearStopwatchButton = new JButton("Clear");

	JTextField stopwatchLabel = new JTextField("000000");

	public void createGUI() {
		container.setPreferredSize(new Dimension(700, 600));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.addTab("Show Clock", clockPanel);
		tabbedPane.addTab("Show MTCountDown", MTCountDownPanel);
		tabbedPane.addTab("Show StopWatch", stopwatchPanel);
		tabbedPane.addTab("Show Alarm", alarmPanel);

		tabbedPane.setSelectedComponent(alarmPanel);

		Timer t = new Timer();
		t.scheduleAtFixedRate(new Clock(), 0, 1000);

		startStopMTCDButton.addActionListener(new StartStopMTCountDown());

		captureButton.addActionListener(new CaptureMTCD());

		clearButton.addActionListener(new ClearAction());

		createCountdown();
		createStopwatch();
		createAlarm();
		container.add(tabbedPane);
		frame.pack();
		frame.setVisible(true);

	}

	public void createAlarm() {
		JLabel colon = new JLabel(":");
		alarmPanel.setLayout(new FlowLayout());
		alarmPanel.add(hourComboBox);
		alarmPanel.add(colon);
		alarmPanel.add(tenMinuteComboBox);
		alarmPanel.add(unitMinuteComboBox);
		alarmPanel.add(amPmComboBox);
		alarmPanel.add(setAlarmButton);
		setAlarmButton.addActionListener(new SetAlarm());
		String[] numbers = new String[13];

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = String.valueOf(i);
		}
		for (int j = 1; j < numbers.length; j++) {
			hourComboBox.addItem(numbers[j]);
		}
		for (int k = 0; k < 6; k++) {
			tenMinuteComboBox.addItem(numbers[k]);
		}
		for (int m = 0; m < 10; m++) {
			unitMinuteComboBox.addItem(numbers[m]);
		}
		amPmComboBox.addItem("AM");
		amPmComboBox.addItem("PM");

	}

	public void createStopwatch() {
		Component[] stopwatchComponentArray = { startStopStopwatchButton,
				clearStopwatchButton, stopwatchLabel };
		stopwatchPanel.setLayout(new GridLayout(4, 1));
		stopwatchPanel.add(stopwatchLabel);
		stopwatchPanel.add(startStopStopwatchButton);
		startStopStopwatchButton.addActionListener(new StartStopStopwatch());
		stopwatchPanel.add(clearStopwatchButton);
		clearStopwatchButton.addActionListener(new ClearStopwatch());
		stopwatchPanel.add(stopwatchBuddyLabel);
		for (Component stopwatchComponent : stopwatchComponentArray) {
			stopwatchComponent.setFont(new Font(Font.SERIF, Font.PLAIN, 46));
		}
	}

	public void createCountdown() {

		MTCountDownPanel.setLayout(new GridLayout(3, 1));
		MTCountDownPanel.setBackground(Color.WHITE);
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(2, 1));
		labelPanel.add(countDownLabel);
		labelPanel.add(captureThreadLabel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(startStopMTCDButton);
		buttonPanel.add(captureButton);
		buttonPanel.add(clearButton);
		JPanel iconPanel = new JPanel();
		iconPanel.add(countDownBuddyLabel);
		labelPanel.setBackground(Color.WHITE);
		buttonPanel.setBackground(Color.WHITE);
		iconPanel.setBackground(Color.WHITE);

		MTCountDownPanel.add(labelPanel);
		MTCountDownPanel.add(buttonPanel);
		MTCountDownPanel.add(iconPanel);
		for (Component countDownComponent : countDownComponentArray) {
			countDownComponent.setFont(new Font(Font.SERIF, Font.PLAIN, 46));
		}

	}

	@Override
	public void run() {

	}

	class StartStopMTCountDown implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("CDL text: " + countDownLabel.getText());

			if (!(countDownLabel.getText().equals(0))) {
				if (startStopMTCDButton.getText().equals("Start")
						|| startStopMTCDButton.getText().equals("Continue")) {
					startStopMTCDButton.setText("Stop");
					long time;
					if (mtCountDown != null && mtCountDown.isCaptured) {
						while (true) {
							try {
								time = GUI.captureTime;
							} catch (NumberFormatException ev) {
								continue;
							}
							break;
						}
					} else {
						try {
							time = Integer.parseInt(countDownLabel.getText());
						} catch (Exception ev) {
							time = GUI.time;
						}

					}
					mtCountDown = new MTCountDown(time, countDownLabel);
					new Thread(mtCountDown).start();
				} else {
					startStopMTCDButton.setText("Continue");
					mtCountDown.cancelCountDown();
				}
			}
		}
	}

	class CaptureMTCD implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (captureButton.getText().equals("Capture")) {
				captureThread = new Capture(captureThreadLabel, countDownLabel,
						mtCountDown, GUI.time);
				new Thread(captureThread).start();
				captureButton.setText("Stop");
			} else {
				captureThread.cancelCaptureThread();
				captureButton.setText("Capture");
			}
		}

	}

	class ClearAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mtCountDown.cancelCountDown();
			captureThread.cancelCaptureThread();
			countDownLabel.setText("000000");
			captureThreadLabel.setText("000000");
			startStopMTCDButton.setText("Start");
		}

	}

	class ClearStopwatch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			stopwatch.cancelStopwatch();
			startStopStopwatchButton.setText("Start");
			stopwatchLabel.setText("000000");
			stopwatchTime = 0;
		}

	}

	class StartStopStopwatch implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (startStopStopwatchButton.getText().equals("Start")
					|| startStopStopwatchButton.getText().equals("Continue")) {

				startStopStopwatchButton.setText("Stop");
				stopwatch = new Stopwatch(stopwatchLabel, stopwatchTime);
				new Thread(stopwatch).start();
			} else {
				startStopStopwatchButton.setText("Continue");
				stopwatchTime = stopwatch.cancelStopwatch();
			}

		}
	}

	class SetAlarm implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int hours = Integer
					.valueOf((String) hourComboBox.getSelectedItem());
			int minutes = (10)
					* (Integer.valueOf((String) tenMinuteComboBox
							.getSelectedItem()))
					+ (Integer.valueOf((String) unitMinuteComboBox
							.getSelectedItem()));
			System.out.println(minutes);
			String amPm = (String) amPmComboBox.getSelectedItem();
			new Thread(new Alarm(hours, minutes, amPm)).start();
		}
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.createGUI();
	}

}