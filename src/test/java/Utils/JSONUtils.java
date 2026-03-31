package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONUtils {

	public static JSONObject readJSONFileReturnJSON(File f) throws FileNotFoundException
	{
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);

		return data;	
	}
	
	/*
	 * public static String readJSONFileReturnString(File f) throws
	 * FileNotFoundException { FileReader fr=new FileReader(f); JSONTokener jt=new
	 * JSONTokener(fr); JSONObject data=new JSONObject(jt); String
	 * putJsonString=data.toString();
	 * 
	 * return putJsonString; }
	 */
	
	public static JSONObject putValuesinPostAPICreateJson(JSONObject inputputPostcreatePayload,String name, String email,String password,String age)
	{
		
		inputputPostcreatePayload.put("name", name);
		inputputPostcreatePayload.put("email", email);
		inputputPostcreatePayload.put("password", password);
		inputputPostcreatePayload.put("age", age);
		return inputputPostcreatePayload;
			
	}
	public static JSONObject putValuesinPostAPILoginJson(JSONObject inputputPostloginPayload,String email, String password)
	{
		
		inputputPostloginPayload.put("email", email);
		inputputPostloginPayload.put("password", password);
		return inputputPostloginPayload;
			
	}
}
