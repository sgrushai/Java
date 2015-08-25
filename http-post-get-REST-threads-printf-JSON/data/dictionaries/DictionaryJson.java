package data.dictionaries; /**
 * 
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DictionaryJson {
	protected JSONObject dictionary;
	protected JSONObject data;

	DictionaryJson(String name){
		this.dictionary =  new JSONObject();
		this.data = new JSONObject(new HashMap<String, String>());
		try {
			this.dictionary.put("name", name);
			this.dictionary.put("data", data);
		} catch (JSONException e) {
			this.dictionary=null;
			this.data=null;			
		}		
	}


	public JSONObject getDictionary() {
		return dictionary;
	}


	public void setDictionary(JSONObject dictionary) {
		this.dictionary = dictionary;
	}


	public JSONObject getData() {
		return data;
	}


	public void setData(JSONObject data) {
		this.data = data;
	}

	protected void addData(String key, String value) throws JSONException{
		this.data.put(key, value);
	}

}