package modbus.rest.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseRestResponse {
	String message;
	String status;
	Object result;
	long timestamp;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public BaseRestResponse() {
		timestamp = System.currentTimeMillis();

	}

	public BaseRestResponse(String message, String status, Object result) {
		super();
		timestamp = System.currentTimeMillis();
		this.message = message;
		this.status = status;
		this.result = result;
	}

	public void setKoMessage(String message) {
		this.status = "KO";
		this.message = message;
	}

	public void setOkMessage(String message) {
		this.status = "OK";
		this.message = message;
	}
	
	public String toJson() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.writeValueAsString(this);
	}
}
