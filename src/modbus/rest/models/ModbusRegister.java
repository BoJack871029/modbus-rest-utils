package modbus.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "bitValue" })

public class ModbusRegister {
	private int slave = -1;
	private int register = -1;
	private int value = -1;
	private int bit = -1;

	public static boolean hasBit(String register) throws Exception {
		if (register.isEmpty()) {
			throw new Exception("Register string is empty");
		}
		String[] tmp = register.split(":");

		return tmp.length < 2 ? false : true;
	}

	public static int getRegisterName(String register) throws Exception {

		if (register.isEmpty()) {
			throw new Exception("Register string is empty");
		}

		String[] tmp = register.split(":");

		return Integer.parseInt(tmp[0]);

	}

	public static int getBitIndex(String register) throws Exception {

		if (register.isEmpty()) {
			throw new Exception("Register string is empty");
		}

		String[] tmp = register.split(":");

		if (tmp.length < 1) {
			throw new Exception("Register string doesn't contains bit index");
		}

		return Integer.parseInt(tmp[1]);

	}

	public static ModbusRegister fromString(String value) throws Exception {
		if (value.isEmpty()) {
			throw new Exception("Value is null or empty");
		}

		ModbusRegister register = new ModbusRegister();

		String[] tmp = value.split(":");
		if (tmp.length > 0)
			register.setRegister(Integer.parseInt(tmp[0]));
		if (tmp.length > 1)
			register.setBit(Integer.parseInt(tmp[1]));

		return register;
	}

	public static ModbusRegister fromMCPString(String value) throws Exception {
		if (value.isEmpty()) {
			throw new Exception("Value is null or empty");
		}

		MCPRegister mcpRegister = MCPRegister.fromString(value);

		if (mcpRegister.getType() == MCPRegisterType.kNULL) {
			return null;
		}
		
		ModbusRegister register = new ModbusRegister();

		register.setRegister(mcpRegister.getRegisterNumber());
		register.setBit(mcpRegister.getBit());

		return register;
	}

	public boolean getBitValue() {
		int bitValue = this.getValue() >> this.getBit() & 1;
		if (bitValue == 1) {
			return true;
		}
		return false;
	}

	public boolean getBitValue(int bitIndex) {
		int bitValue = this.getValue() >> bitIndex & 1;
		if (bitValue == 1) {
			return true;
		}
		return false;
	}

	public int getBit() {
		return bit;
	}

	public void setBit(int bit) {
		this.bit = bit;
	}

	public int getSlave() {
		return slave;
	}

	public void setSlave(int slave) {
		this.slave = slave;
	}

	public int getRegister() {
		return register;
	}

	public void setRegister(int register) {
		this.register = register;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ModbusRegister() {

	}

	public ModbusRegister(int slave, int register, int value) {
		super();
		this.slave = slave;
		this.register = register;
		this.value = value;
	}

	public ModbusRegister(int slave, int register, int value, int bit, String name) {
		super();
		this.slave = slave;
		this.register = register;
		this.value = value;
		this.bit = bit;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		ModbusRegister second = (ModbusRegister) obj;

		if (this.slave != second.getSlave()) {
			return false;
		}

		if (this.register != second.getRegister()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "{slave:" + this.slave + ", register:" + this.register + ", bit:" + this.bit + ", value:" + this.value
				+ "} ";
	}

}