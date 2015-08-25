package REST.body; /**
 * Created by sgrushai on 07/07/2015.
 */
import org.json.JSONException;
import org.json.JSONObject;

public class BaseRequisitionBody extends JSONObject {
    JSONObject requisition;


    public BaseRequisitionBody(JSONObject requisitionJson){
        this.requisition =  new JSONObject();

        try {
            this.requisition.put("requisition", requisitionJson);
        } catch (JSONException e) {
            this.requisition=null;
        }
    }

    public JSONObject getRequisition() {
        return requisition;
    }

    public void setRequisition(JSONObject requisition) {
        this.requisition = requisition;
    }



}
