package REST;

import REST.JSON.RequisitionJson;
import REST.body.BaseRequisitionBody;
import REST.body.DeleteVMBody;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.Properties;

/**
 * Created by sgrushai on 07/07/2015.
 */
public class deleteVM {
    RequisitionsPostTest rp = new RequisitionsPostTest();

    public String deleteVM(String testLogin, String testPassword, String billToOU, String vmName, String platformElementID, String VMType, Properties props) throws Exception {

        String body;

        body = new BaseRequisitionBody((new RequisitionJson(testLogin, billToOU, (new DeleteVMBody(vmName, platformElementID, VMType, 1, "0", "Yes")))).getRequisition()).getRequisition().toString();
        System.out.println("requisition POST: "+body);

        HttpResponse response = rp.requisitionPost(testLogin, testPassword, body, props);

        System.out.println("Response Status:v" + response.getStatusLine().getStatusCode());

        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println("Response: " + responseString);
        return responseString;

    }
}
