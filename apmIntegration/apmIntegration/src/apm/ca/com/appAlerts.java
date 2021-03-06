package apm.ca.com;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
//import org.json.simple.parser.JSONParser;

public class appAlerts {

		
		//"CA MAA report URL: "/mdo/v1/apps/alerted"
	//public static void main(String[] args) {
			public static String getAlerts(String EPAgentURL)	 {	

		//String EPAgentURL = "10.132.64.63:8081";
	    //  other parameters

	    String usage = "";
		String name  = "";
	  

	          try {  
	        	  

	  			// read data from temp file   	  
	        	  // get file created by maa_ws.jar
				 BufferedReader br = null;
					String line = "";				
					FileInputStream fstream = new FileInputStream("alerted.csv");
					DataInputStream in = new DataInputStream(fstream);
					br = new BufferedReader(new InputStreamReader(in, "UTF8"));		
					
					while ((line = br.readLine()) != null ) {
						
						if (line.contains(":")) 
						{
							// do nothing
						} else {
						String[] spec = line.split(",");  
						name  = spec[0];
	                   
	          	  // push the array values 0 and 1 to APM
	              String apiURL = "http://"+EPAgentURL+"/apm/metricFeed";  
	 
	              // httpRequest
	               HttpClient a = HttpClientBuilder.create().build(); 
	               HttpPost ap = new HttpPost(apiURL); 	               
	               ap.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \"StringEvent\" , \"name\" : \"Alerted Mobile Apps\", \"value\":" + name + "\"}] }",

	                         	
	                         //��type�:��StringEvent�,�name�:�Debug�,�value�:�Data�was sent�compressed�	
	                         
	                         ContentType.create("application/json"))); 
	               HttpResponse ar = a.execute(ap);           	               


	             	               
	               System.out.println("Alerted app: " + name+"\n"); 

	               // read and display return message and error
	               BufferedReader rd = new BufferedReader(new InputStreamReader(ar.getEntity().getContent()));  
	               String lineIn = "";  
	               while ((lineIn = rd.readLine()) != null ) {  
	                    System.out.println(lineIn);  
	               }                  
	          
					
					
					}
					 
	              
	          }
					br.close();
	          }

	          catch(Exception e) {  
	               System.out.println(e);  
	          }     
	          
	  		return "Finished pushing apps alerted string event data to EPAgent";
	     }  
	
		}
