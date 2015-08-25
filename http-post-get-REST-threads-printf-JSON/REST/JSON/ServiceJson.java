package REST.JSON; /**
 * Created by sgrushai on 07/07/2015.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ServiceJson {

    protected JSONObject service;
    protected JSONArray dictionaries;

    protected ServiceJson(String name, int quantity, String version){

        this.service =  new JSONObject();
        this.dictionaries = new JSONArray();
        try {
            this.service.put("name", name);
            this.service.put("quantity", quantity);
            this.service.put("version", version);
            this.service.put("dictionaries", dictionaries);
        } catch (JSONException e) {
            this.service=null;
            this.dictionaries=null;
        }
    }


    public JSONObject getService() {
        return service;
    }

    public void setService(JSONObject service) {
        this.service = service;
    }

    public JSONArray getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(JSONArray dictionaries) {
        this.dictionaries = dictionaries;
    }

    protected void addDictionary(JSONObject dictionary) throws JSONException{
        this.dictionaries.put(dictionary);
    }



}
