package REST;

import REST.RestRequisition;
import org.apache.http.HttpResponse;
//import org.apache.log4j.Logger;

import java.util.Properties;

public class RequisitionsPostTest{
    RestRequisition req;
    Properties props;

	


	
	protected HttpResponse requisitionPost(String testLogin, String testPassword, String body, Properties props) throws Exception {
        HttpResponse response=null;
		req = new RestRequisition(testLogin, testPassword, props);
		response = req.postRequisitions(body);
		
		return response;
	}
}
