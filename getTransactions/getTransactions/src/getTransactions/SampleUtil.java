package getTransactions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import org.apache.tomcat.util.codec.binary.Base64;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.simple.parser.JSONParser;
//import com.sun.jersey.core.util.Base64;



import org.json.simple.*;
import org.json.simple.parser.JSONParser;


public class SampleUtil {
	
	public static HashMap login (String username, String pwd, String org, String targeturl, boolean debug) {
		
		// System.out.println("IN AdminLogin.login");
		
		String headerOrg = "Basic " + Base64.encodeBase64String(org.getBytes());
		targeturl = targeturl + "/ess/security/v1/token";
		String params = "username=" + username + "&password=" + pwd + "&grant_type=PASSWORD";
		
		//if (debug) {
			System.out.println("SampleUtil:: targeturl..." + targeturl);
			System.out.println("SampleUtil:: params..." + params);
			System.out.println("SampleUtil:: headerOrg..." + headerOrg);
		//}
		
		int httpStatus = 0;
		String respText = "";
		HashMap hash = new HashMap();
		
		Client client = new Client();
		WebResource wr = client.resource(targeturl);
	
		ClientResponse response = wr.accept("application/json")
									.header("Content-type", "application/x-www-form-urlencoded")
									.header("Authorization", headerOrg)
									.post(ClientResponse.class, params);
		
		httpStatus = response.getStatus();
		respText = response.getEntity(String.class);
		hash.put("HTTP_STATUS", new Integer(httpStatus));
		hash.put("RESP_TEXT", respText);
		
		//if (debug) {
			System.out.println("SampleUtil:: httpStatus..." + httpStatus);
			System.out.println("SampleUtil:: response.getEntity..." + respText);
		//}
		
		return hash;

	} // login
	

	public static HashMap getMaaData (String apiurl,String targeturl, String bearerToken, boolean debug) throws Exception {		
	
		HashMap hash = new HashMap();
		
		// define dynamic file name for outputs
  	  String[] parts = apiurl.split("/");
   	  String type = parts[parts.length-1];
   	  System.out.println("CSV output: "+type);
		
		bearerToken = "{\"tkn\":\"" + bearerToken + "\",\"all\":\"true\"}";
		String headerAz = "Bearer " + Base64.encodeBase64String(bearerToken.getBytes());
		
		//if (debug) {
			System.out.println("SampleUtil:: bearerToken..." + bearerToken);
			System.out.println("SampleUtil:: targeturl..." + targeturl);
			System.out.println("SampleUtil:: headerAz..." + headerAz);
		//}
		
		Client client = new Client();
		WebResource wr = client.resource(targeturl);
	
		ClientResponse response = wr.accept("application/json")
									.header("Authorization", headerAz)
									.get(ClientResponse.class);	

		int httpStatus = response.getStatus();
		String respText = response.getEntity(String.class);

		//if (debug) {
        	String csvFileName = type+"JSON.txt";
        	FileWriter csvWriter = new FileWriter(csvFileName);
        	
			System.out.println("SampleUtil:: httpStatus..." + httpStatus);
			System.out.println("SampleUtil:: Profile info from MAA server");
			System.out.println(respText);
			 csvWriter.append(respText);
			    csvWriter.flush();
			    csvWriter.close();
			    
		//}
			    
			    // create csv file according to report type
			    
				JSONParser parser = new JSONParser();		    
				
			
	        	  Object obj = parser.parse(new FileReader(type+"JSON.txt"));
	        	  
	        	  String jsonData = obj.toString();
	        	  
	        	 // JSONObject jObject = new JSONObject(jsonData);
	        	  //String input = jObject.getString("averages");
	        	  
	        	  // since the JSON coming out of MAA is erratic, parse output as string and write to a csv file
	        	  jsonData = jsonData.replaceAll("],", "\n").replaceAll("\\[|\\]", "").replaceAll("\\{|\\}", "").replace("\"", "").replaceAll("_", " ");


	        	  
	        	  File file = new File(type+".csv");
	        	  
	      		// if file doesn't exists, then create it
	  			if (!file.exists()) {
	  				file.createNewFile();
	  			}

	  			FileWriter fw = new FileWriter(file.getAbsoluteFile());
	  			BufferedWriter bw = new BufferedWriter(fw);  			
	  			 			
	  			bw.write(jsonData);
	  			bw.close();

		
		hash.put("HTTP_STATUS", new Integer(httpStatus));
		hash.put("RESP_TEXT", respText);

		return  hash;
		
	} // getMaaData
	
	
	public static String getParamFromJSON(String strJSON, String paramName, boolean debug) {
				
		String value = "";
		
		try {		
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(strJSON);
	        
	        value = (String) jsonObject.get(paramName);
	        
	        if (debug)
	        	System.out.println("SampleUtil:: param..." + paramName + "...value..." + value);
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
        
        return value;
		
	} // getParamFromJSON
	
} // AdminLogin