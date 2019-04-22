package modbus.rest.utils;

import java.nio.ByteBuffer;

/**
 * Copyright 2010 Firat Salgur
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 */
public class BCD {
	public static int bcdToInt(int num) {
		byte[] bytes = ByteBuffer.allocate(4).putInt(num).array();

		return (int) BCDToDecimal(bytes);
	}

	public static int intToBCD(int num) {

		byte[] bcd = DecimalToBCD(num);

		String binaryBCD = Binary.byteArrayToBin(bcd);

		return Binary.binToInt(binaryBCD);
	}

	public static byte[] DecimalToBCD(long num) {
		int digits = 0;

		long temp = num;
		while (temp != 0) {
			digits++;
			temp /= 10;
		}

		int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;

		byte bcd[] = new byte[byteLen];

		for (int i = 0; i < digits; i++) {
			byte tmp = (byte) (num % 10);

			if (i % 2 == 0) {
				bcd[i / 2] = tmp;
			} else {
				bcd[i / 2] |= (byte) (tmp << 4);
			}

			num /= 10;
		}

		for (int i = 0; i < byteLen / 2; i++) {
			byte tmp = bcd[i];
			bcd[i] = bcd[byteLen - i - 1];
			bcd[byteLen - i - 1] = tmp;
		}

		return bcd;
	}

	public static long BCDToDecimal(byte[] bcd) {
		return Long.valueOf(BCD.BCDtoString(bcd));
	}

	public static String BCDtoString(byte bcd) {
		StringBuffer sb = new StringBuffer();

		byte high = (byte) (bcd & 0xf0);
		high >>>= (byte) 4;
		high = (byte) (high & 0x0f);
		byte low = (byte) (bcd & 0x0f);

		sb.append(high);
		sb.append(low);

		return sb.toString();
	}

	public static String BCDtoString(byte[] bcd) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < bcd.length; i++) {
			sb.append(BCDtoString(bcd[i]));
		}

		return sb.toString();
	}

}