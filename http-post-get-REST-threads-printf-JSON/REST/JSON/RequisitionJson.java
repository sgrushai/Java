package REST.JSON; /**
 * Created by sgrushai on 07/07/2015.
 */
import REST.JSON.ServiceJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequisitionJson  {
    JSONObject requisition;
    JSONArray services;

    public RequisitionJson(String customerLoginName, String billToOU, ServiceJson service){
        this.requisition =  new JSONObject();
        this.services = new JSONArray();
        try {
            this.requisition.put("customerLoginName", customerLoginName);
            this.requisition.put("billToOU", billToOU);
            this.addService(service.getService());
            this.requisition.put("services", this.services);
        } catch (JSONException e) {
            this.requisition=null;
            this.services=null;
        }
    }

    public JSONObject getRequisition() {
        return requisition;
    }

    public void setRequisition(JSONObject requisition) {
        this.requisition = requisition;
    }

    public JSONArray getServices() {
        return services;
    }

    public void setServices(JSONArray services) {
        this.services = services;
    }

    protected void addService(JSONObject service) throws JSONException{
        this.services.put(service);
    }

}
