package components;

import java.io.File;
import java.util.Calendar;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Alarm implements Runnable {

	public Alarm(int endHour, int endMinute, String amPm) {
		this.endHour = endHour;
		this.endMinute = endMinute;
		if ((amPm.equals("PM") && this.endHour != 12)
				|| (amPm.equals("AM") && this.endHour == 12)) {
			this.endHour += 12;
		}
	}

	int endHour;
	int endMinute;

	int startHour;
	int startMinute;

	int hours;
	int minutes;

	public void remSleep(int days, int hours, int minutes) {
		for (int m = 0; m < days; m++) {
			for (int n = 0; n < 24; n++) {
				for (int o = 0; o < 60; o++) {
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						continue;
					}
				}
			}
		}
		for (int i = 0; i < hours; i++) {
			for (int j = 0; j < 60; j++) {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					continue;
				}
			}
		}
		for (int k = 0; k < minutes; k++) {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	@Override
	public void run() {
		startHour = Clock.calendar.get(Calendar.HOUR_OF_DAY);
		startMinute = Clock.calendar.get(Calendar.MINUTE);
		System.out.println(endHour + ":" + endMinute);
		System.out.println(startHour + ":" + startMinute);
		hours = endHour - startHour;
		minutes = endMinute - startMinute;
		if (minutes < 0) {
			hours--;
			minutes = 60 + minutes;
		}
		if (hours < 0) {
			hours += 24;
		}
		System.out.println(hours + ":" + minutes);
		if (this.minutes + this.hours * 60 > 3) {
			this.minutes -= 3;
			remSleep(0, hours, minutes);
		}

		while (true) {
			if (endMinute == Clock.calendar.get(Calendar.MINUTE)
					&& endHour == Clock.calendar.get(Calendar.HOUR_OF_DAY)) {
				break;
			}
		}

		beep();
	}

	public void beep() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("Beep.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			System.out.println("FAIL!!");
			e.printStackTrace();
		}
	}

}