package getTransactions;

import java.util.HashMap;


public class getResponse {
	
	
	public String getResponseData(String targeturl, String org , String username, String pwd, String apiurl,String aggregation, String startdate, String enddate) {
		
	final String output;
	
	try {
	

		
		boolean debug = true;
		
		//if (debug) {
			System.out.println("Login:: username..." + username);
			System.out.println("Login:: pwd..." + pwd);
			System.out.println("Login:: org..." + org);
			System.out.println("Login:: targeturl..." + targeturl);
		//}
		
		
		if ( (username != null && !username.trim().equals("")) &&
			 (pwd != null && !pwd.trim().equals("")) &&
			 (org != null && !org.trim().equals("")) &&
			 (targeturl != null && !targeturl.trim().equals(""))
			) {
					

			// process the login request
			
			HashMap retHash = SampleUtil.login (username, pwd, org, targeturl, debug);
			
			if ( ((Integer) retHash.get("HTTP_STATUS")).intValue() != 200) {

				System.out.println("Login:: User Auth Fail for user = " + username);				
	
			
			} else {
				
				System.out.println("Login:: User Auth Success for user = " + username);

				String bearerToken = SampleUtil.getParamFromJSON((String) retHash.get("RESP_TEXT"), "tkn", debug);
				

				
				if (bearerToken == null || bearerToken.equals("")) {
					
					System.out.println("Login:: MAA Compatibility issue - Searching for 'accessToken' instead of 'tkn'");
					bearerToken = SampleUtil.getParamFromJSON((String) retHash.get("RESP_TEXT"), "accessToken", debug);					
				}
				
				
				String finalurl = targeturl + apiurl + "?aggregation=" + aggregation + "&end_date=" + enddate + "&start_date=" + startdate;
				
				System.out.println("REQUEST URL : "+ finalurl);

				
				HashMap retHash2 = SampleUtil.getMaaData (apiurl,finalurl, bearerToken, true);
				
				
			}
			
		} else {
			
			System.out.println("Login:: Within else - throw error");
			

		} // if-else
	

	} catch (Exception ex) {
		
		ex.printStackTrace();


	} //try-catch
	return "Done. See file getJSON.txt for results.";
	//return output;
	
} 
}

