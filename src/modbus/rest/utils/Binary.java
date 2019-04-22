package modbus.rest.utils;

import org.apache.commons.lang3.StringUtils;

public class Binary {
	private static int BIN_LENGTH = 16;

	public static String byteArrayToBin(byte[] value) {
		StringBuffer binaryValue = new StringBuffer();
		for (byte i : value) {
			String byteInBinary = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
			binaryValue.append(byteInBinary);
		}
		
		return StringUtils.leftPad(binaryValue.toString(), BIN_LENGTH, '0');
	}

	public static String intToBin(int value) {
		String binaryValue = Integer.toBinaryString(value);
		return StringUtils.leftPad(binaryValue, BIN_LENGTH, '0');
	}

	public static int binToInt(String value) {
		return Integer.parseInt(value, 2);
	}

	public static String switchBit(String value, int bIndex) throws Exception {
		if (bIndex < 0 && bIndex >= BIN_LENGTH) {
			throw new Exception("Invalid bit index");
		}
		StringBuilder sBuilder = new StringBuilder(value);
		char newValue = '0';
		if (sBuilder.charAt(bIndex) == '0') {
			newValue = '1';
		}
		sBuilder.setCharAt(sBuilder.length() - 1 - bIndex, newValue);
		return sBuilder.toString();
	}

	public static String setBitValue(String value, int bIndex, char bValue) throws Exception {
		if (bIndex < 0 && bIndex >= BIN_LENGTH) {
			throw new Exception("Invalid bit index");
		}
		StringBuilder sBuilder = new StringBuilder(value);
		sBuilder.setCharAt(sBuilder.length() - 1 - bIndex, bValue);
		return sBuilder.toString();
	}

	public static boolean getBitValueAsBoolean(String value, int bitIndex) throws Exception {
		if (bitIndex < 0 && bitIndex >= BIN_LENGTH) {
			throw new Exception("Invalid bit index");
		}

		return value.charAt(value.length() - 1 - bitIndex) == '0' ? false : true;
	}

	public static int getBitValueAsInt(String value, int bitIndex) throws Exception {
		if (bitIndex < 0 && bitIndex >= BIN_LENGTH) {
			throw new Exception("Invalid bit index");
		}

		return value.charAt(value.length() - 1 - bitIndex) == '0' ? 0 : 1;
	}

	public static char getBitValueAsString(String value, int bitIndex) throws Exception {
		if (bitIndex < 0 && bitIndex >= BIN_LENGTH) {
			throw new Exception("Invalid bit index");
		}

		return value.charAt(value.length() - 1 - bitIndex);
	}
}
