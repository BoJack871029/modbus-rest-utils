package modbus.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "bitValue" })

public class ModbusRegister {
	private int slave = -1;
	private int register = -1;
	private int value = -1;
	private int bit = -1;
//	private String name = "";

	public static int getFromString(String register) {
		int registerNumber = -1;

		if (!register.isEmpty()) {
			String[] tmp = register.split(":");

			if (tmp.length > 0) {
				registerNumber = Integer.parseInt(tmp[0]);
			}
		}

		return registerNumber;
	}

	public static ModbusRegister fromString(String value) throws Exception {
		if (value == null) {
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

	public boolean getBitValue() {
		int bitValue = this.getValue() >> this.getBit() & 1;
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

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

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
//		this.name = name;
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
		return "{slave:" + this.slave + ", reg:" + this.register + ", bit:" + this.bit + ", value:" + this.value + "} ";
	}

}