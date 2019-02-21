package test.modbus.test.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import modbus.rest.utils.Binary;

public class BinaryTest {
	@Test
	public void binToIntTest() {
		Assert.assertEquals(Binary.binToInt("1111111111111111"), 65535);
	}

	@Test
	public void switchBitTest() throws Exception {
		String bitValue = "1111111111111111";

		Assert.assertEquals(Binary.switchBit(bitValue, 0), "1111111111111110");
		Assert.assertEquals(Binary.switchBit(bitValue, 15), "0111111111111111");
		Assert.assertEquals(Binary.switchBit(bitValue, 5), "1111111111011111");
		
		Assert.assertThrows(Exception.class, () -> {
			Binary.switchBit("1111111111111111", -1);
		});

		Assert.assertThrows(Exception.class, () -> {
			Binary.switchBit("1111111111111111", 16);
		});

	}


	@Test
	public void setBitValueTest() throws Exception {
		String bitValue = "1111111111111111";

		Assert.assertEquals(Binary.setBitValue(bitValue, 0, '0'), "1111111111111110");
		Assert.assertEquals(Binary.setBitValue(bitValue, 15, '0'), "0111111111111111");
		Assert.assertEquals(Binary.setBitValue(bitValue, 5, '0'), "1111111111011111");
		
		Assert.assertThrows(Exception.class, () -> {
			Binary.setBitValue("1111111111111111", -1, '1');
		});

		Assert.assertThrows(Exception.class, () -> {
			Binary.setBitValue("1111111111111111", 16, '1');
		});

	}



	@Test
	public void getBitValueAsBooleanTest() throws Exception {
		String bitValue = "0111111111011111";

		Assert.assertEquals(Binary.getBitValueAsBoolean(bitValue, 0), true);
		Assert.assertEquals(Binary.getBitValueAsBoolean(bitValue, 15), false);
		Assert.assertEquals(Binary.getBitValueAsBoolean(bitValue, 5), false);
		
		Assert.assertThrows(Exception.class, () -> {
			Binary.getBitValueAsBoolean(bitValue, 16);
		});

		Assert.assertThrows(Exception.class, () -> {
			Binary.getBitValueAsBoolean(bitValue, -1);
		});
	}

	@Test
	public void getBitValueAsIntTest() throws Exception {
		String bitValue = "0111111111011111";

		Assert.assertEquals(Binary.getBitValueAsInt(bitValue, 0), 1);
		Assert.assertEquals(Binary.getBitValueAsInt(bitValue, 15), 0);
		Assert.assertEquals(Binary.getBitValueAsInt(bitValue, 5), 0);
		
		Assert.assertThrows(Exception.class, () -> {
			Binary.getBitValueAsInt(bitValue, -1);
		});
		
		Assert.assertThrows(Exception.class, () -> {
			Binary.getBitValueAsInt(bitValue, 16);
		});
	}

	@Test
	public void getBitValueAsStringTest() throws Exception {
		String bitValue = "0111111111011111";

		Assert.assertEquals(Binary.getBitValueAsString(bitValue, 0), '1');
		Assert.assertEquals(Binary.getBitValueAsString(bitValue, 15), '0');
		Assert.assertEquals(Binary.getBitValueAsString(bitValue, 5), '0');
		
		Assert.assertThrows(Exception.class, () -> {
			Binary.getBitValueAsString(bitValue, -1);
		});
		
		Assert.assertThrows(Exception.class, () -> {
			Binary.getBitValueAsString(bitValue, 16);
		});
		
	}

}
