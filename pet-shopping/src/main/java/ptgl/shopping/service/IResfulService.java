package ptgl.shopping.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IResfulService {
	
	 Object callGetES(String urlES) throws IOException;
	
	 Object convertJson2Object(String json, Class className) throws JsonParseException, JsonMappingException, IOException ;

	 String convertObject2Json(Object obj) throws JsonParseException, JsonMappingException, IOException ;
}
