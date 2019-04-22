package test.modbus.test.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import modbus.rest.models.MCPRegister;
import modbus.rest.models.MCPRegisterType;

public class MCPRegisterTest {

	@Test
	public void RegisterIsVirtuale() throws Exception {

		MCPRegister register = MCPRegister.fromString("V751");

		Assert.assertEquals(register.getRegisterNumber(), 1199);
		Assert.assertEquals(register.getBit(), 14);
		
		MCPRegister registerEmpty = MCPRegister.fromString("N");

		Assert.assertEquals(registerEmpty.getType(),MCPRegisterType.kNULL);


	}
	
	@Test
	public void RegisterIsRegister() throws Exception {
		MCPRegister register = MCPRegister.fromString("R22");
		Assert.assertEquals(register.getRegisterNumber(), 3094);		
	}

}
