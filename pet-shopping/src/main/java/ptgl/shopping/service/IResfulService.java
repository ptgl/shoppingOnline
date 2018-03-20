package ptgl.shopping.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IResfulService {
	
	public int callPostES(String url, Object json) throws IOException;
	
	public Object callGetES(String urlES) throws IOException;
	
	public Object getAllES(String urlES) throws IOException ;
	
	public Object convertJson2Object(String json, Class className) throws JsonParseException, JsonMappingException, IOException ;

	public String convertObject2Json(Object obj) throws JsonParseException, JsonMappingException, IOException ;
	
	
}
