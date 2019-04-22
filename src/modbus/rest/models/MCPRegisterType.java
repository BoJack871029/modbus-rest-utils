package modbus.rest.models;

import java.util.Arrays;
import java.util.HashSet;

public class MCPRegisterType {

	public static String kREGISTRO = "R";
	public static String kVIRTUALE = "V";
	public static String kUSCITA = "O";
	public static String kINGRESSO = "I";
	public static String kNULL = "N";
	public static String kINGRESSOANALOGICO = "A";
	public static String kNOCONVERSION = "X";

	private static HashSet<String> acceptedTypes = new HashSet<String>(Arrays.asList(
			new String[] { kREGISTRO, kVIRTUALE, kUSCITA, kINGRESSO, kINGRESSOANALOGICO, kNULL, kNOCONVERSION }));

	;

	public static boolean isValid(String type) {
		if (type.isEmpty()) {
			return false;
		}

		String registerType = type.substring(0, 1);

		if (!acceptedTypes.contains(registerType)) {
			return false;
		}

		return true;
	}

	public static String fromString(String value) throws Exception {
		if (!MCPRegisterType.isValid(value)) {
			throw new Exception("Type del registro non valido: [" + value + "]");
		}

		return value.substring(0, 1);
	}

}
