package test.modbus.test.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import modbus.rest.utils.BCD;

public class BCDTest {
	@Test
	public void intToBCD() {
		int aDecimalValue = 36;
		int aExpectedByteValue = 54;

		int bcd = BCD.intToBCD(aDecimalValue);

		Assert.assertEquals(bcd, aExpectedByteValue);
	}

	@Test
	public void bcdToInt() {
		int aBCDValue = 54;
		int aExpectedByteValue = 36;

		Assert.assertEquals(BCD.bcdToInt(aBCDValue), aExpectedByteValue);
	}

}
