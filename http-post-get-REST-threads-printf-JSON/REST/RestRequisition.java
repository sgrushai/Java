package REST;

import REST.base.RestBase;
import data.RequisitionData;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Properties;



public class RestRequisition extends RestBase {
	public static String ORDERED_FOR_MYSELF = "Ordered for Myself";
	public static String ORDERED_FOR_OTHERS = "Ordered for Others";
	public static String ORDERED_FOR_MY_UNIT = "Ordered for my unit";

	public static String STATUS_ONGOING = "Ongoing";
	public static String STATUS_PREPEARATION = "Preparation";
	public static String STATUS_ORDERED = "Ordered";
	public static String STATUS_CLOSED = "Closed";
	public static String STATUS_CANCELLED = "Cancelled";
	public static String STATUS_REJECTED = "Rejected";
	public static String STATUS_ALL = "All";

	public static String SORT_REQUISITIONID = "requisitionId";
	public static String SORT_STATUS = "status";
	public static String SORT_CUSTOMER_NAME = "customerName";
	public static String SORT_OWNER_NAME = "ownerName";
	public static String SORT_STARTED_DATE = "startedDate";
	public static String SORT_SUBMIT_DATE = "submitDate";
	public static String SORT_SERVICE_NAME = "serviceName";
	String baseURL = "/RequestCenter/nsapi/transaction/requisitions";
	
	public RestRequisition(String userName, String password, Properties props) {
		super(userName, password, props);
		path = "/RequestCenter/nsapi/transaction/requisitions";
	}
	
	/* ***********************************************************************
	 * Post Requisitions for the Logged In User
	 * http://172.21.38.31/RequestCenter/nsapi/transaction/requisitions
	 * ***********************************************************************
	 */
	public HttpResponse postRequisitions(String body) {
		path = baseURL;
		HttpResponse response=null;
		response = post(body, this.userName, this.password);
		return response;
	}

	/* ***********************************************************************
	 * Get Requisitions for the Logged In User
	 * http://172.21.38.31/RequestCenter/nsapi/transaction/requisitions
	 * ***********************************************************************
	 */
	public ArrayList<RequisitionData> getRequisitions() {
		path = baseURL;
		get();
		return getReqHelper();
	}

	public ArrayList<RequisitionData> getAllRequisitions() {
		path = baseURL;
		return getAllReqHelper();
	}

	public boolean getAllRequisitionsExectError(int code, String errorMessage) {
		get();
		return responseCode == code && getErrorMessage().equals(errorMessage);
	}
	
	/* ***********************************************************************
	 * Get Requisition By Status and ViewName
	 * http://172.21.38.31/RequestCenter/nsapi
	 * /transaction/requisitions/ViewName={View Name}|AND|Status={Status}
	 * ***********************************************************************
	 */
	public ArrayList<RequisitionData> getByRequisitionAndViewName(String viewName, String status) {
		path = baseURL + "/ViewName=" + viewName + "|AND|Status=" + status;
		get();
		return getReqHelper();
	}

	public ArrayList<RequisitionData> getByRequisitionAndViewName(String viewName,String status,String sortBy,String sortDir) {
		path = baseURL + "/ViewName=" + viewName + "|AND|Status=" + status;
		this.addParameter(new BasicNameValuePair(RestBase.SORT_BY,sortBy));
		this.addParameter(new BasicNameValuePair(RestBase.SORT_DIR,sortDir));
		System.out.println("Path:"  + path);
		get();
		return getReqHelper();
	}
	
	public ArrayList<RequisitionData> getByAllRequisitionAndViewName(String status, String viewName) {
		path = baseURL + "/ViewName=" + viewName + "|AND|Status=" + status;
		return getAllReqHelper();
	}

	public boolean getByRequisitionAndViewName(String status, String viewName, int code, String errorMessage) {
		path = baseURL + "/ViewName=" + viewName + "|AND|Status=" + status;
		get();
		return getErrorMessage().equals(errorMessage) && code == responseCode;
	}

	/* ***********************************************************************
	 * Get Requisition By ID
	 * http://172.21.38.31/RequestCenter/nsapi/directory/organizationalunits
	 * ***********************************************************************
	 */
	public RequisitionData getRequisionById(String id) {
		path = baseURL + "/id/" + id;
		get();
		return new RequisitionData(getArrayListByXPath("//requisition").get(0));
	}

	public boolean getRequisionByIdExpectError(String id, int code, String errorMessage) {
		path = baseURL + "/id/" + id;
		get();
		return responseCode == code && getErrorMessage().equals(errorMessage);
	}
	
	/* ********************* End Standard Methods ********************************/
	private ArrayList<RequisitionData> getReqHelper() {
		ArrayList<RequisitionData> retData = new ArrayList<RequisitionData>();
		ArrayList<Node> nodes = getArrayListByXPath("//requisition");
		for (Node current : nodes) {
			retData.add(new RequisitionData(current));
		}
		return retData;
	}
	
	private ArrayList<RequisitionData> getAllReqHelper(){
		ArrayList<RequisitionData> retData = new ArrayList<RequisitionData>();
		ArrayList<Document> docs = this.getAll(path);
		for (Document currentDoc : docs) {
			ArrayList<Node> nodes = getArrayListByXPath(currentDoc, "//requisition");
			for (Node current : nodes) {
				retData.add(new RequisitionData(current));
			}
		}
		return retData;
	}
	
	private ArrayList<RequisitionData> get(boolean all) {
		ArrayList<RequisitionData> retData = new ArrayList<RequisitionData>();
		if (!all) {
			// Then we are getting only the first list.
			get();
			return getReqHelper();
		} else {
			getMultiple();
			BasicNameValuePair sr;
			int start = 1;
			// Walking through to see if there is a start row that has already
			// been set.
			for (NameValuePair current : arguments) {
				if (current.getName().equals(RestBase.START_ROW)) {
					sr = (BasicNameValuePair) current;
					start = Integer.parseInt(sr.getValue());
				}
			}

			for (startRow = start; startRow < totalCount; startRow += recordSize) {
				sr = new BasicNameValuePair(RestBase.START_ROW, startRow + "");
				arguments.add(sr);
				get();
				ArrayList<Node> nodes = getArrayListByXPath("//requisition");
				for (Node current : nodes) {
					retData.add(new RequisitionData(current));
				}
				// Removing the start row so that we can add a new one in the
				// next iteration.
				arguments.remove(sr);
			}
			return retData;
		}

	}

	public boolean getRequisionList(String viewName, String status, ArrayList<String> expected) {
		return reqListHelper(expected, false);
	}
	
	private boolean reqListHelper(ArrayList<String> expected, boolean orderMatters){
		ArrayList<RequisitionData> data = get(false);
		if (data.size() != expected.size()) return false;
		for (int i = 0; i < expected.size(); i++) {
			RequisitionData curData = data.get(i);
			String reqId = curData.getRequisitionId();
			if (orderMatters){
				String curExp = expected.get(i);
				if (!reqId.equals(curExp)) {
					System.out.println("Requisition id " + reqId + " not found in the order expected.");
					return false;
				}
			} else {
				if (expected.contains(reqId)) {
					System.out.println("Requisition id " + reqId + " not found in expected but found in results.");
					return false;
				}
			}
		}
		return true;
	}

	public boolean getRequisionListWithOrder(String viewName, String status, ArrayList<String> expected) {
		return reqListHelper(expected, true);
	}
}
