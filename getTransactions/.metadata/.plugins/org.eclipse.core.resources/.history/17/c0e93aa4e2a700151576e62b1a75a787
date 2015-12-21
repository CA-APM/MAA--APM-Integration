package getTransactions;


import java.util.HashMap;



	public class ApiServlet  {
		
		{

		//	boolean debug = true;
			
			//if (debug)
			//	System.out.println("ApiServlet:: Within ApiServlet POST");
			
			try {
				
				String apitype = "Usage by Geo";
				String apiuri = "/mdo/v1/usage/geo";
				String targeturl = "https://mdo-dev.emm.ca.com";
				String aggregation = "month";
				String startdate = "2015-01-01";
				String enddate = "2015-05-31";
				
				//String bearerToken = (String) session.getAttribute("BEARER_TOKEN");
				String bearerToken = "bd17a7a7-3b0a-4655-9b8e-90d34a00d264";
		
					
					System.out.println("ApiServlet:: apitype..." + apitype);
					System.out.println("ApiServlet:: apiuri..." + apiuri);
					System.out.println("ApiServlet:: targeturl..." + targeturl);
					System.out.println("ApiServlet:: aggregation..." + aggregation);
					System.out.println("ApiServlet:: startdate..." + startdate);
					System.out.println("ApiServlet:: enddate..." + enddate);
		
				
				if (apitype != null && !apitype.trim().equals(""))
					apitype = apitype.trim();
				else
					apitype = "<API Type Not Available>";
		
				if (aggregation == null || aggregation.trim().equals(""))
					aggregation = "day";
		
				if (startdate == null || startdate.trim().equals(""))
					startdate = "2015-01-01";
		
				if (enddate == null || enddate.trim().equals(""))
					enddate = "2015-05-31";
		
				
				if  (targeturl != null && !targeturl.trim().equals(""))
				{
			
					// System.out.println("ApiServlet:: BEFORE data fetch");
					// process the request
					
					String finalurl = targeturl + apiuri + "?aggregation=" + aggregation + "&end_date=" + enddate + "&start_date=" + startdate;

					HashMap retHash = SampleUtil.getMaaData (finalurl, bearerToken, true);
		
					if ( ((Integer) retHash.get("HTTP_STATUS")).intValue() != 200) {
		
						System.out.println("ApiServlet:: MAA data fetch FAILED for '" + apitype + "' API");
						
			
					
					} else {
						
						System.out.println("ApiServlet:: MAA data fetch SUCCESS for '" + apitype + "' API");
		
						String maaData = (String) retHash.get("RESP_TEXT");
						
		
							System.out.println("ApiServlet:: maaData..." + maaData);
						
						if (maaData != null && maaData.trim().equals(""))
							maaData = null;						

										
					} // if-else retHash
				
				} else {
					
					System.out.println("ApiSerlet:: Within else - throw error");
					
				
				} // if-else targeturl
			
			} catch (Exception ex) {
				
				ex.printStackTrace();


			} //try-catch
			
		} // doPost

	}