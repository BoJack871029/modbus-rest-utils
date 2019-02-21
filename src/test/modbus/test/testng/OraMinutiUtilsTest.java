package test.modbus.test.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import modbus.rest.utils.OraMinutiUtils;

public class OraMinutiUtilsTest {

	@Test
	public void OraToString() {
		Assert.assertEquals(OraMinutiUtils.toString(300), "5:0");
	}

	@Test
	public void fromString() throws Exception {
		Assert.assertEquals(OraMinutiUtils.fromString("5:0"), 300);

		
		Assert.assertThrows(Exception.class, () -> {
			OraMinutiUtils.fromString("");
		});
		
		Assert.assertThrows(Exception.class, () -> {
			OraMinutiUtils.fromString("5");
		});

		Assert.assertThrows(Exception.class, () -> {
			OraMinutiUtils.fromString("5:");
		});

	}
}
