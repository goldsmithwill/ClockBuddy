package components;

public class TimeParser {
	static public String parse(long millisecondsInput) {
		long milliseconds;
		try {
			milliseconds = millisecondsInput;
		} catch (NumberFormatException ex) {
			System.err.print("Error: Input too large");
			return Long.toString(millisecondsInput);
		}

		int millis = (int) milliseconds % 1000;
		int seconds = (int) (milliseconds / 1000) % 60;
		int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
		int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
		String timeString = hours + ":" + minutes + ":" + seconds + ":"
				+ millis;

		return timeString;

	}
}
