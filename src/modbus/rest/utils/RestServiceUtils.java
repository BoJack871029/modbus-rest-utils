package modbus.rest.utils;

import java.util.regex.Pattern;

import javax.ws.rs.core.Response;
import modbus.rest.models.BaseRestResponse;


public class RestServiceUtils {
	public static Response buildErrorResponse() {
		BaseRestResponse response = new BaseRestResponse();
		response.setKoMessage("Errore durante l'operazione");
		return Response.serverError().entity(response).build();
	}
	
	public static Response buildErrorResponse(String message) {
		BaseRestResponse response = new BaseRestResponse();
		response.setKoMessage(message);
		return Response.serverError().entity(response).build();
	}
	
	public static Response buildSuccessResponse() {
		BaseRestResponse response = new BaseRestResponse();
		response.setOkMessage("Operazione eseguita con successo");		
		return Response.ok().entity(response).build();
	}
	
	public static Response buildSuccessResponse(String message) {
		BaseRestResponse response = new BaseRestResponse();
		response.setOkMessage(message);	
		return Response.ok().entity(response).build();
	}
	
	public static Response buildSuccessResponse(String message, Object result) {
		BaseRestResponse response = new BaseRestResponse();
		response.setOkMessage(message);
		response.setResult(result);
		
		String str=Pattern.compile("\"").matcher(result.toString()).replaceAll("\\\"");		
		
		response.setResultstr(str);
		return Response.ok().entity(response).build();
	}
	
	public static Response buildSuccessResponse(Object result) {
		BaseRestResponse response = new BaseRestResponse();
		response.setOkMessage("Operazione eseguita con successo");
		response.setResult(result);
		
		String str=Pattern.compile("\"").matcher(result.toString()).replaceAll("\\\"");

		response.setResultstr(str);
		return Response.ok().entity(response).build();
	}
}
