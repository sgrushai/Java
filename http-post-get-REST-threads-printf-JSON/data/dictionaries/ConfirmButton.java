package data.dictionaries;
import org.json.JSONException;

/**
 * @author sgrushai
 *
 */
public class ConfirmButton extends DictionaryJson {
	
 public ConfirmButton(String confirm){
	 super("data.dictionaries.ConfirmButton");
	 try {
			this.addData("ConfirmCheckbox", confirm);
		} catch (JSONException e) {
			this.dictionary = null;
			this.data = null;
		} 	 
 }
	
}