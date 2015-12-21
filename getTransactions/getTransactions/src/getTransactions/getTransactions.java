package getTransactions;

/*Utility to extract CA MAA data using REST web services.
 * kopja02@ca.com, 2015. all rights reserved
 * 
 */

public class getTransactions  {
	
	public static void main(String[] args) {
		
		
		try {
			/*
			String targeturl = "https://mdo.mobility.ca.com";
			//String targeturl = "";
			String org = "maademo2";
			String username = "renja01";
			String pwd = "CAdemo123$";
			//String apitype = "Apps Registered";
			String apiurl = "/mdo/v1/crashes/crash_summary";
			String aggregation = "month";
			String startdate = "2015-01-01";
			String enddate = "2015-09-30";
			
			
	
		String targeturl = "https://mdo-dev.emm.ca.com";
		//String targeturl = "";
		String org = "mdo-org";
		String username = "mdoadmin";
		String pwd = "mdoadmin";
		String apiurl = "/mdo/v1/usage/geo";
		String aggregation = "month";
		String startdate = "2015-01-01";
		String enddate = "2015-05-31";
		*/
		
		
		String targeturl = args[0];
		String org = args[1];
		String username = args[2];
		String pwd = args[3];		
		String apiurl = args[4];
		String aggregation = args[5];
		String startdate = args[6];
		String enddate = args[7];
		
		
				/*Options for aggregation: "hour","day", "week", "month", "year"
				 * Options for apiurl, apitype: 
				 * "/mdo/v1/apps", "Apps Registered"
				 * "/mdo/v1/apps/alerted", "Apps Alerted"
				 * "/mdo/v1/performance/apps", "Performance by Apps"
				 * "/mdo/v1/performance/urls", "Performance by URLs"
				 * "/mdo/v1/performance/carrier", "Performance by Carrier"
				 * "/mdo/v1/performance/devices", "Performance by Device"
				 * "/mdo/v1/performance/errorcodes", "Performance by Errors"
				 * "/mdo/v1/performance/apps_summary", "App Summary"
				 * "/mdo/v1/crashes/crash_summary", "Crash Summary"
				 * "/mdo/v1/crashes/crash_summary_by_apps", "Crash Summary by Apps"
				 * "/mdo/v1/usage/users" , "Usage by Users"
				 * "/mdo/v1/usage/active_users" , "Usage by Active Users"
				 * "/mdo/v1/usage/sessions" , "Usage by Sessions"
				 * "/mdo/v1/usage/geo", "Usage by Geo"
				 * "/mdo/v1/usage/retention", "Usage by retention"
				 * "/mdo/v1/sessions" , "Flagged Sessions"
				 * "/mdo/v1/sessions/list", "Session List"
				 * "/mdo/v1/problems/heatmap", "Geo Heatmap of problems"
				 * "/mdo/v1/sessions/heatmap", "Geo Heatmap of sessions"
				 * /mdo/v1/profiles", "Profiles"
				 */
	

		
		for(int i = 0;i<args.length;i++) {
		
			//System.out.println(args.length);
		
		// Check how many arguments were passed in
	    if ((args[i] == null) || (args[i].equals("")))
	    {
		     System.out.print("Argument "+i+ " is empty\n");      
		
        			 System.exit(0);
	    }     

		}			
      			

		System.out.println("Login and get JSON data: ");		
		getResponse start = new getResponse();
		String output = start.getResponseData(targeturl,org,username,pwd,apiurl,aggregation,startdate,enddate);
		System.out.println("JSON RESPONSE: " + output);	               
				
	
		} catch (Exception ex) {
			
			System.out.println("Error: Check arguments. \n");
			System.out.println(" MAA_WS Command line help. kopja02@ca.com. CA Engineering Services 2015\n");
			
			
			System.out.println(" USAGE: java -jar maa_ws.jar \"targeturl\" \"org\" \"username\" \"pwd\" \"apiurl\" \"aggregation\" \"startdate\" \"enddate\"\n"
					+ "Options for aggregation: hour, day, week, month, year\n"
					+ "\nEXAMPLE: \n"
					+ "targeturl: \"https://mdo-dev.emm.ca.com\" \n"
					+ "org: \"mdo_org\" (TENANT, COHORT)\n"
					+ "username: \"mdoadmin\" \n"
					+ "password: \"mdoadmin\" \n"
					+ "apiurl: \"/mdo/v1/usage/geo\" \n"
					+ "aggregation: \"month\" \n"
					+ "startdate: \"2015-01-01\" THIS FORMAT ONLY \n"
					+ "enddate: \"2015-05-31\" \n"
					+ "\n\n"
					
					+ "Supported MAA API URLS: \n"
					+ "/mdo/v1/apps \n"
					 + "/mdo/v1/apps/alerted  \n"
					 + "/mdo/v1/performance/apps  \n"
					 + "/mdo/v1/performance/urls \n"
					 + "/mdo/v1/performance/carrier \n"
					 + "/mdo/v1/performance/devices  \n"
					 + "/mdo/v1/performance/errorcodes  \n"
					 + "/mdo/v1/performance/apps_summary  \n"
					 + "/mdo/v1/crashes/crash_summary \n"
					 + "/mdo/v1/crashes/crash_summary_by_apps \n"
					 + "/mdo/v1/usage/users  \n"
					 + "/mdo/v1/usage/active_users \n"
					 + "/mdo/v1/usage/sessions  \n"
					 + "/mdo/v1/usage/geo  \n"
					 + "/mdo/v1/usage/retention  \n"
					 + "/mdo/v1/sessions  \n"
					 + "/mdo/v1/sessions/list  \n"
					 + "/mdo/v1/sessions/heatmap  \n"
					 + "/mdo/v1/problems/heatmap  \n"
					 + "/mdo/v1/profiles \n\n");
			
			
			System.out.println("Error stack: \n");	
			ex.printStackTrace();
			}
}
	}


