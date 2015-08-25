package data.dictionaries; /**
 * Created by sgrushai on 07/07/2015.
 */
import org.json.JSONException;

public class DecomVirtualMachine extends DictionaryJson {

    public DecomVirtualMachine(String vmName, String platformElementID, String VMType){
        super("data.dictionaries.DecomVirtualMachine");
        try {
            this.addData("Name", vmName);
            this.addData("PlatformElementID", platformElementID);
            this.addData("VMType", VMType);
        } catch (JSONException e) {
            this.dictionary = null;
            this.data = null;
        }
    }

}
