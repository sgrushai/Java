package REST.body; /**
 * Created by sgrushai on 07/07/2015.
 */

import REST.JSON.ServiceJson;
import data.dictionaries.ConfirmButton;
import data.dictionaries.DecomVirtualMachine;
import org.json.JSONException;

public class DeleteVMBody extends ServiceJson {
    static final String serviceName = "Decommission Virtual Machine";

    public DeleteVMBody(String vmName, String platformElementID, String VMType, int serviceQuantity, String serviceVersion, String confirm){
        super(serviceName, serviceQuantity, serviceVersion);
        try {
            this.setDictionaries(vmName, platformElementID, VMType, confirm);
        } catch (JSONException e) {
            this.service=null;
            this.dictionaries=null;
        }
    }


    private void setDictionaries(String vmName, String platformElementID, String VMType, String confirm) throws JSONException{

        this.addDictionary(new DecomVirtualMachine(vmName, platformElementID, VMType).getDictionary());
        this.addDictionary(new ConfirmButton(confirm).getDictionary());

    }

}
