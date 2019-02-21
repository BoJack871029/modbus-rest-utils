package modbus.rest.utils;

public class OraMinutiUtils {
	public static String toString(int time) {

		int hour = time / 60;

		int minutes = time - (hour * 60);

		return hour + ":" + minutes;
	}

	public static int fromString(String time) throws Exception {

		if (time.isEmpty()) {
			throw new Exception("Il valore non puo' essere nullo");
		}

		if (!time.contains(":")) {
			throw new Exception("Formato ora non valido");
		}

		String[] values = time.split(":");

		if (values.length < 2) {
			throw new Exception("Formato ora non valido");
		}

		int hour = Integer.parseInt(values[0]);
		int minutes = Integer.parseInt(values[1]);

		return (hour * 60) + minutes;
	}
}
