package components;

import java.awt.Color;

import java.awt.Font;
import java.util.Calendar;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import clockBuddy.GUI;

public class Clock extends TimerTask implements Runnable {
	// Implementing run method()
	static Calendar calendar = Calendar.getInstance();
	JLabel dateLabel = new JLabel("", JLabel.CENTER), timeLabel = new JLabel(
			"", JLabel.CENTER), timeZoneLabel = new JLabel("", JLabel.CENTER),
			clockLabel = new JLabel(new ImageIcon("ClockBuddyIcon.png"),
					JLabel.CENTER);

	public void run() {
		GUI.clockPanel.setBackground(Color.WHITE);
		JLabel[] clockLabelArray = { dateLabel, timeLabel, timeZoneLabel,
				clockLabel };
		for (JLabel clockLabel : clockLabelArray) {
			clockLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 55));
		}
		for (JLabel clockLabel : clockLabelArray) {
			GUI.clockPanel.add(clockLabel);
		}
		String dateString = "", timeString = "", timeZoneString = "", yearString = "";
		int day, month, date, year = 0;

		calendar = Calendar.getInstance();

		timeString = calendar.get(Calendar.HOUR) + ":";

		if ((String.valueOf(calendar.get(Calendar.MINUTE))).length() == 1) {
			timeString += 0;
		}
		timeString += calendar.get(Calendar.MINUTE) + ":";

		if ((String.valueOf(calendar.get(Calendar.SECOND))).length() == 1) {
			timeString += 0;
		}
		timeString += calendar.get(Calendar.SECOND) + " ";
		if (calendar.get(Calendar.AM_PM) == 0) {
			timeString += "AM";
		} else {
			timeString += "PM";
		}

		day = calendar.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case Calendar.MONDAY:
			dateString += "Monday, ";
			break;
		case Calendar.TUESDAY:
			dateString += "Tuesday, ";
			break;
		case Calendar.WEDNESDAY:
			dateString += "Wednesday, ";
			break;
		case Calendar.THURSDAY:
			dateString += "Thursday, ";
			break;
		case Calendar.FRIDAY:
			dateString += "Friday, ";
			break;
		case Calendar.SATURDAY:
			dateString += "Saturday, ";
			break;
		case Calendar.SUNDAY:
			dateString += "Sunday, ";
			break;
		}
		month = calendar.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
			dateString += "Jan ";
			break;
		case Calendar.FEBRUARY:
			dateString += "Feb ";
			break;
		case Calendar.MARCH:
			dateString += "Mar ";
			break;
		case Calendar.APRIL:
			dateString += "Apr ";
			break;
		case Calendar.MAY:
			dateString += "May ";
			break;
		case Calendar.JUNE:
			dateString += "June ";
			break;
		case Calendar.JULY:
			dateString += "July ";
			break;
		case Calendar.AUGUST:
			dateString += "Aug ";
			break;
		case Calendar.SEPTEMBER:
			dateString += "Sept ";
			break;
		case Calendar.OCTOBER:
			dateString += "Oct ";
			break;
		case Calendar.NOVEMBER:
			dateString += "Nov ";
			break;
		case Calendar.DECEMBER:
			dateString += "Dec ";
			break;
		}

		date = calendar.get(Calendar.DAY_OF_MONTH);
		dateString += date + ", ";
		timeZoneString = calendar.getTimeZone().getDisplayName();
		year = calendar.get(Calendar.YEAR);
		yearString = String.valueOf(year);
		dateString += yearString;

		dateLabel.setText(dateString);
		timeLabel.setText(timeString);
		timeZoneLabel.setText(timeZoneString);

	}
}