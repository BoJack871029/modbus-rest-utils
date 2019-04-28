package modbus.rest.models;

public class MCPRegister {
    static int _offesetRegistro = 3071; //Errore nel manuale
    static int _offsetVirtuale = 1152;  //Errore nel manuale
    static int _offsetUscita = 511;
    public String type;
    public int value;
    public int bit = -1;
    private String registerMCP;

    public String getRegisterMCP() {
	return registerMCP;
    }

    public int getBit() {
	return bit;
    }

    public void setBit(int bit) {
	this.bit = bit;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public int getValue() {
	return value;
    }

    public void setValue(int name) {
	this.value = name;
    }

    public int getRegisterNumber() {
	int register = 0;

	if (this.type.equals(MCPRegisterType.kREGISTRO)) {
	    register = getNumberFromRegister();
	} else if (this.type.equals(MCPRegisterType.kVIRTUALE)) {
	    register = getNumberFromVirtuale();
	} else if (this.type.equals(MCPRegisterType.kINGRESSO)
		|| this.type.equals(MCPRegisterType.kINGRESSOANALOGICO)) {
	    register = getNumberFromIngresso();
	} else if (this.type.equals(MCPRegisterType.kUSCITA)) {
	    register = getNumberFromUscita();
	} else if (this.type.equals(MCPRegisterType.kNOCONVERSION)) {
	    register = this.value;
	}
	return register;
    }

    public static int getBitNumberFromVirtuale(int value) {
	// Formula BIT: (x – 1)%16
	return (value - 1) % 16;
    }

    public int getBitNumberFromInOut() {
	// Formula BIT: (x – 1)%16
	return (this.value - 1) % 16;
    }

    public static MCPRegister fromString(String value) throws Exception {
	MCPRegister reg = new MCPRegister();
	reg.registerMCP = value;

	reg.setType(MCPRegisterType.fromString(value));

	String modbusRegister;

	modbusRegister = value.substring(1, value.length());

	if (reg.getType().equals(MCPRegisterType.kUSCITA)) {
	    parseMCPOutputRegister(reg, modbusRegister);
	}

	if (reg.getType().equals(MCPRegisterType.kINGRESSO)) {
	    parseMCPInputRegister(reg, modbusRegister);
	}

	if (reg.getType().equals(MCPRegisterType.kVIRTUALE)) {
	    reg.setValue(Integer.parseInt(modbusRegister));
	    reg.setBit(MCPRegister.getBitNumberFromVirtuale(reg.getValue()));
	}

	if (reg.getType().equals(MCPRegisterType.kREGISTRO)) {
	    reg.setValue(Integer.parseInt(modbusRegister));
	}

	if (reg.getType().equals(MCPRegisterType.kNULL)) {
	    // Do nothing
	}

	if (reg.getType().equals(MCPRegisterType.kINGRESSOANALOGICO)) {
	    parseMCPAnalogicInputRegister(reg, modbusRegister);
	}

	if (reg.getType().equals(MCPRegisterType.kNOCONVERSION)) {
	    reg.setValue(Integer.parseInt(modbusRegister));
	}

	return reg;
    }

    private static MCPRegister parseMCPOutputRegister(MCPRegister oMCPRegister, String iValue) {
	String register = iValue.substring(0, iValue.indexOf('.'));

	oMCPRegister.setValue(Integer.parseInt(register));

	String bit = iValue.substring(iValue.indexOf('.'), iValue.length());
	bit = bit.substring(1, bit.length());
	oMCPRegister.setBit(Integer.parseInt(bit) - 1);

	return oMCPRegister;
    }

    private static void parseMCPInputRegister(MCPRegister oMCPRegister, String iValue) {
	if (iValue.contains(".")) {
	    oMCPRegister = parseMCPOutputRegister(oMCPRegister, iValue);
	} else {
	    oMCPRegister.setValue(Integer.parseInt(iValue));
	}
    }

    private static void parseMCPAnalogicInputRegister(MCPRegister oMCPRegister, String iValue) {
	// Formula: x. C'e un bag, devo fare x-1
	oMCPRegister.setValue(Integer.parseInt(iValue) - 1);
    }

    private int getNumberFromRegister() {
	return _offesetRegistro + this.getValue();
    }

    private int getNumberFromVirtuale() {
	int register = this.getValue();

	register = (int) (register / 16);

	register = _offsetVirtuale + register;
	return register;
    }

    private int getNumberFromIngresso() {
	// Formula: x
	return this.getValue();
    }

    private int getNumberFromUscita() {
	// Formula: x
	return _offsetUscita + this.getValue();
    }

}
