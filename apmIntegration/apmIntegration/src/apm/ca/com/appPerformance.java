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

public class appPerformance {

		
		//"CA MAA report URL: /mdo/v1/performance/apps_summary"
			
			public static String getPerf(String EPAgentURL, String dataType)	 {	

	    //  other parameters

	    String loadTime = "";
	    String httpRequests = "";
	    String httpErrors = "";
	    String users = "";
	    String latency = "";
	    String dataIn = "";
	    String dataOut = "";
	    String crashes = "";
	    String name  = "";
	  

	          try {  
	        	  

	  			// read data from temp file   	  
	        	  // get file created by maa_ws.jar
				 BufferedReader br = null;
					String line = "";				
					FileInputStream fstream = new FileInputStream("apps_summary.csv");
					DataInputStream in = new DataInputStream(fstream);
					br = new BufferedReader(new InputStreamReader(in, "UTF8"));		
					
					while ((line = br.readLine()) != null) {
						String[] spec = line.split(",");  
						if (line.contains(":")) 
						{
							// do nothing
						} else {
						name  = spec[0];

	                   //number = Integer.parseInt(countryData[1]); 
	                   // EPAgent wants string not int
						httpRequests = spec[1].replace(".0", "");
						httpErrors = spec[2].replace(".0", "");
						users = spec[4].replace(".0", "");
	                   loadTime = spec[5].replace(".0", "");
	                   latency = spec[7].replace(".0", "");
	                   dataIn = spec[8].replace(".0", "");
	                   dataOut = spec[9].replace(".0", "");
	                   crashes = spec[14].replace(".0", "");
	   
	                   System.out.println(name+" "+loadTime);
	                   
	                   // getting rid of decimals for latency - apm wants integers
	                   double adouble = Math.round(Double.parseDouble(latency));	                   
	                   String latency2 = String.valueOf(adouble).replace(".0", "");;

	                   
	          	  // push the array values 0 and 1 to APM
	              String apiURL = "http://"+EPAgentURL+"/apm/metricFeed";  
	 
	              // httpRequest
	               HttpClient a = HttpClientBuilder.create().build(); 
	               HttpPost ap = new HttpPost(apiURL); 	               
	               ap.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \""+dataType+"\", \"name\" : \"MAA|App|" + name + ":http requests\", \"value\"=\"" + httpRequests + "\"}] }",  
	                         ContentType.create("application/json"))); 
	               HttpResponse ar = a.execute(ap);  
	               
		              // httpErrors
	               HttpClient b = HttpClientBuilder.create().build(); 
	               HttpPost bp = new HttpPost(apiURL); 	               
	               bp.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \""+dataType+"\", \"name\" : \"MAA|App|" + name + ":http errors\", \"value\"=\"" + httpErrors + "\"}] }", 
	                         ContentType.create("application/json"))); 
	               HttpResponse brr = b.execute(bp);  
	               
	               // Load
	               HttpClient c = HttpClientBuilder.create().build(); 	               
	               HttpPost cp = new HttpPost(apiURL); 
	               cp.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \""+dataType+"\", \"name\" : \"MAA|App|" + name + ":Load time\", \"value\"=\"" + loadTime + "\"}] }",  
	                         ContentType.create("application/json"))); 	               
	               HttpResponse cr = c.execute(cp);  	 
	               
		              // Active users
	               HttpClient d = HttpClientBuilder.create().build(); 
	               HttpPost dp = new HttpPost(apiURL); 	               
	               dp.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \""+dataType+"\", \"name\" : \"MAA|App|" + name + ":Active users\", \"value\"=\"" + users + "\"}] }",  
	                         ContentType.create("application/json"))); 
	               HttpResponse dr = d.execute(dp);  
	               
		              // Latency
	               HttpClient e = HttpClientBuilder.create().build(); 
	               HttpPost ep = new HttpPost(apiURL); 	               
	               ep.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \""+dataType+"\", \"name\" : \"MAA|App|" + name + ":Latency\", \"value\"=\"" + latency2 + "\"}] }",  
	                         ContentType.create("application/json"))); 
	               HttpResponse er = e.execute(ep);  
	               
		              // data in
	               HttpClient f = HttpClientBuilder.create().build(); 
	               HttpPost fp = new HttpPost(apiURL); 	               
	               fp.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \""+dataType+"\", \"name\" : \"MAA|App|" + name + ":Data in\", \"value\"=\"" + dataIn + "\"}] }",  
	                         ContentType.create("application/json"))); 
	               HttpResponse fr = f.execute(fp);  
	               
		              // data out
	               HttpClient g = HttpClientBuilder.create().build(); 
	               HttpPost gp = new HttpPost(apiURL); 	               
	               gp.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \""+dataType+"\", \"name\" : \"MAA|App|" + name + ":Data out\", \"value\"=\"" + dataOut + "\"}] }",  
	                         ContentType.create("application/json"))); 
	               HttpResponse gr = g.execute(gp);  
	               
		              // crashes
	               HttpClient h = HttpClientBuilder.create().build(); 
	               HttpPost hp = new HttpPost(apiURL); 	               
	               hp.setEntity(new StringEntity(  
	                         "{ \"metrics\" : [{ \"type\" : \""+dataType+"\", \"name\" : \"MAA|App|" + name + ":Crashes\", \"value\"=\"" + crashes + "\"}] }",  
	                         ContentType.create("application/json"))); 
	               HttpResponse hr = h.execute(hp);  
	               


	             	               
	               System.out.println("Pushed data for: " + name+"\n"); 

	               // read and display return message and error
	               BufferedReader rd = new BufferedReader(new InputStreamReader(hp.getEntity().getContent()));  
	               String lineIn = "";  
	               while ((lineIn = rd.readLine()) != null) {  
	                    System.out.println(lineIn);  
	               }                  
	          
					
					
					}
					}
					 br.close();
	              
	          }
	          catch(Exception e) {  
	               System.out.println(e);  
	          }     
	          
	  		return "Finished pusing app performance data to EPAgent";
	     }  
	
		}