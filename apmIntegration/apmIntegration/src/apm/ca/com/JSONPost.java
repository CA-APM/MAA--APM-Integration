//Sample URL: http://10.130.179.82:8080/apm/metricFeed 

package apm.ca.com; 

/*Utility to push CA MAA data into CA APM using REST web services.
 * kopja02@ca.com, 2015. all rights reserved
 * 
 */
  
public class JSONPost {  
	
	public static void main(String[] args) {
		
	  // command line parameters
  
		// EPAgent ip address and port
		 String EPAgentURL = args[0];
		
		// MAA report type
		 String type = args[1];
		 
		 String dataType = args[2];
    
    
   // String EPAgentURL = "10.132.64.63:8081";
   // String type = "appPerformance";
    //String type = "geo";
		 //String type = "crashes";
		   // String type = "alerted";
		// String dataType = "IntCounter";
    
 
System.out.println("Connecting to APM...");

          try {  
        	  
        	  if (type.equals("geo"))
        		  
        	  {        		  
        		  String geo = appGeo.getUsage(EPAgentURL, dataType);
                  System.out.println(geo); 
                        
          } else if (type.equals("appPerformance")) {
        	  
        	  String getPerf = appPerformance.getPerf(EPAgentURL, dataType);
              System.out.println(getPerf); 
        	  
          } else if (type.equals("crashes")) {
        	  
        	  String getCrash = platformCrash.getCrashes(EPAgentURL, dataType);
              System.out.println(getCrash); 
        	  
          }  else if (type.equals("alerted")) {
        	  
        	  String appAlert = appAlerts.getAlerts(EPAgentURL);
        	
              System.out.println(appAlert); 
        	  
          }
          
          else {
        	  
        	  System.out.println("This utility supports data types \"geo\", \"crashes\", \"alerted\" and \"appPerformance\"");
        	  
          }
          }
          catch(Exception e) {  
               System.out.println(e);  
          }                        
     }  

	}
