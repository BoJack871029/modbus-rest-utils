package modbus.rest.models;

public class ModbusSettings {	
	private String port;
	private String bound;
	private String parity;
	private String databit;
	private String bitstop;

	
	public String getPort() {
		return port;
	}

	public void setPort(String porta) {
		this.port = porta;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public String getParity() {
		return parity;
	}

	public void setParity(String parity) {
		this.parity = parity;
	}

	public String getDatabit() {
		return databit;
	}

	public void setDatabit(String databit) {
		this.databit = databit;
	}

	public String getBitstop() {
		return bitstop;
	}

	public void setBitstop(String bitstop) {
		this.bitstop = bitstop;
	}

	public ModbusSettings() {
	}

	public ModbusSettings(String porta, String bound, String parity, String databit, String bitstop) {
		super();
		this.port = porta;
		this.bound = bound;
		this.parity = parity;
		this.databit = databit;
		this.bitstop = bitstop;
	}
}
