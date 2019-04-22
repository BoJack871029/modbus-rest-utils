package test.modbus.test.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import modbus.rest.models.ModbusRegister;

public class ModbusRegisterTest {

	@Test
	public void hasBit() throws Exception {

		Assert.assertEquals(ModbusRegister.hasBit("300:1"), true);
		Assert.assertEquals(ModbusRegister.hasBit("300"), false);
		Assert.assertThrows(Exception.class, () -> {
			ModbusRegister.hasBit("");
		});
	}

	@Test
	public void getRegisterName() throws Exception {

		Assert.assertEquals(ModbusRegister.getRegisterName("300:1"), 300);
		Assert.assertThrows(Exception.class, () -> {
			ModbusRegister.getRegisterName("");
		});
	}

	@Test
	public void getBitIndex() throws Exception {

		Assert.assertEquals(ModbusRegister.getBitIndex("300:1"), 1);
		Assert.assertThrows(Exception.class, () -> {
			ModbusRegister.getBitIndex("");
		});
		Assert.assertThrows(Exception.class, () -> {
			ModbusRegister.getBitIndex("300");
		});
	}

	@Test
	public void fromString() throws Exception {

		ModbusRegister reg = new ModbusRegister();
		reg.setBit(1);
		reg.setRegister(300);

		Assert.assertEquals(ModbusRegister.fromString("300:1").getBit(), reg.getBit());
		Assert.assertEquals(ModbusRegister.fromString("300:1").getRegister(), reg.getRegister());

		Assert.assertThrows(Exception.class, () -> {
			ModbusRegister.fromString("");
		});

	}

	@Test
	public void getBitValue() throws Exception {

		ModbusRegister reg = new ModbusRegister();
		reg.setBit(0);
		reg.setValue(63840);

		Assert.assertEquals(reg.getBitValue(), false);

		reg.setBit(15);
		Assert.assertEquals(reg.getBitValue(), true);

		Assert.assertEquals(reg.getBitValue(0), false);
		Assert.assertEquals(reg.getBitValue(15), true);
	}

	@Test
	public void getFromMCPRegisterRegister() throws Exception {
		ModbusRegister regR22 = ModbusRegister.fromMCPString("R22");

		Assert.assertEquals(regR22.getBit(), -1);

		Assert.assertEquals(regR22.getRegister(), 3094);
	}

	@Test
	public void getFromMCPRegisterVirtual() throws Exception {
		ModbusRegister regV751 = ModbusRegister.fromMCPString("V297");

		Assert.assertEquals(regV751.getBit(), 8);

		Assert.assertEquals(regV751.getRegister(), 1171);
	}

	@Test
	public void getFromMCPRegisterEmpty() throws Exception {
		ModbusRegister regEmpty = ModbusRegister.fromMCPString("N");

		Assert.assertNull(regEmpty);
	}

	@Test
	public void getFromMCPRegisterOutputWithBit() throws Exception {
		ModbusRegister regO25 = ModbusRegister.fromMCPString("O25.1");

		Assert.assertEquals(regO25.getBit(), 0);

		Assert.assertEquals(regO25.getRegister(), 537);

		ModbusRegister regO26 = ModbusRegister.fromMCPString("O26.8");

		Assert.assertEquals(regO26.getBit(), 7);

		Assert.assertEquals(regO26.getRegister(), 538);
	}

	@Test
	public void getFromMCPRegisterInputWithBit() throws Exception {
		ModbusRegister regI44 = ModbusRegister.fromMCPString("I44.8");

		Assert.assertEquals(regI44.getBit(), 7);

		Assert.assertEquals(regI44.getRegister(), 44);

		ModbusRegister regI43 = ModbusRegister.fromMCPString("I43.1");

		Assert.assertEquals(regI43.getBit(), 0);

		Assert.assertEquals(regI43.getRegister(), 43);
	}

	@Test
	public void getFromMCPRegisterInputNoBit() throws Exception {
		ModbusRegister regI44NoBit = ModbusRegister.fromMCPString("I44");

		Assert.assertEquals(regI44NoBit.getBit(), -1);

		Assert.assertEquals(regI44NoBit.getRegister(), 44);
	}
	
	@Test
	public void getFromMCPRegisterAnalogicInput() throws Exception {
		ModbusRegister regI44NoBit = ModbusRegister.fromMCPString("A10");

		Assert.assertEquals(regI44NoBit.getBit(), -1);

		Assert.assertEquals(regI44NoBit.getRegister(), 9);
	}
	
	@Test
	public void getFromMCPRegisterNoConversion() throws Exception {
		//Deve ritornare lo stesso numero di registro
		ModbusRegister regNoConversion = ModbusRegister.fromMCPString("X1920");

		Assert.assertEquals(regNoConversion.getBit(), -1);

		Assert.assertEquals(regNoConversion.getRegister(),1920);
	}
	
}
