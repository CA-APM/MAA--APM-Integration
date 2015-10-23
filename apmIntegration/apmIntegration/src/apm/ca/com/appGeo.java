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

public class appGeo {

		
		//"CA MAA report URL: /mdo/v1/usage/geo"
			
			public static String getUsage(String EPAgentURL, String dataType)	 {	

	    //  other parameters

	    String usage = "";
		String name  = "";
	  

	          try {  
	        	  

	  			// read data from temp file   	  
	        	  // get file created by maa_ws.jar
				 BufferedReader br = null;
					String line = "";				
					FileInputStream fstream = new FileInputStream("geo.csv");
					DataInputStream in = new DataInputStream(fstream);
					br = new BufferedReader(new InputStreamReader(in, "UTF8"));		
					
					while ((line = br.readLine()) != null ) {
						
						if (line.contains(":")) 
						{
							// do nothing
						} else {
						String[] spec = line.split(",");  
						name  = spec[0];

	                   //number = Integer.parseInt(countryData[1]); 
	                   // EPAgent wants string not int
						usage = spec[1].replace(".0", "");

	   
	                  // System.out.println(name+" "+usage);
	                   
	          	  // push the array values 0 and 1 to APM
	              String apiURL = "http://"+EPAgentURL+"/apm/metricFeed";  
	 
	              // httpRequest
	               HttpClient a = HttpClientBuilder.create().build(); 
	               HttpPost ap = new HttpPost(apiURL); 	               
	               ap.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \""+dataType+"\", \"name\" : \"MAA|Country|" + name + ":Usage\", \"value\"=\"" + usage + "\"}] }",

	                         ContentType.create("application/json"))); 
	               HttpResponse ar = a.execute(ap);           	               


	             	               
	               System.out.println("Pushed data for: " + name+"\n"); 

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
	          
	  		return "Finished pushing country data to EPAgent";
	     }  
	
		}