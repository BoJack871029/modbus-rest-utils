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
		
		ModbusRegister reg= new ModbusRegister();
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
		
		ModbusRegister reg= new ModbusRegister();
		reg.setBit(0);
		reg.setValue(63840);
		
		Assert.assertEquals(reg.getBitValue(), false);
		
		reg.setBit(15);
		Assert.assertEquals(reg.getBitValue(), true);

		Assert.assertEquals(reg.getBitValue(0), false);
		Assert.assertEquals(reg.getBitValue(15), true);
	}
	
}
