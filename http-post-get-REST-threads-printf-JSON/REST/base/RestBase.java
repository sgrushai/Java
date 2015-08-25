package REST.base;

import REST.body.HttpDeleteWithBody;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
//import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This is going to be the base class for testing the Rest APIs. They have a
 * similar structure to REX.
 */
public abstract class RestBase {
	protected String host;
	protected String url;
	protected Document document = null;
	protected ArrayList<NameValuePair> arguments;
	protected HttpGet method;
	protected HttpPost post;
	protected HttpClient client = HttpClientBuilder.create().build();
	protected String name;

	protected boolean loggedIn = false;

	protected String baseTag;
	protected String nodeName;
	protected String idTag;
	protected int responseCode;
	protected String userName;
	protected String password;
	protected String errorMessage = "";
	protected Properties props;
	//protected Logger log;
	/* Sorting */
	protected String path;

	/*
	 * These 3 will be set when calling a getMultiple, and can be used in child
	 * methods.
	 */
	protected int recordSize = 0;
	protected int totalCount = 0;
	protected int startRow = 0;
	protected int port = 8080;

	public static final String ASCENDING = "asc";
	public static final String DESCENDING = "desc";

	/* These are arguments for pagination */
	public static final String START_ROW = "startRow";
	public static final String RECORD_SIZE = "recordSize";	
	//adding total count
	public static final String TOTAL_COUNT = "totalCount";	
	
	/* These are arguments for sorting */
	public static final String SORT_BY = "sortBy";
	public static final String SORT_DIR = "sortDir";

	public static final String RESPONSE_TYPE_XML = "xml";
	public static final String RESPONSE_TYPE_JSON = "json";

	public static final String ERROR_NOT_FOUND = "Requested resource could not be found.";
	public static final String ERROR_NO_AUTH = "User does not have proper authentication.";
	public static final String ERROR_INVALID_RESPONSE = "Internal Error: Invalid parameter values specified or unexpected error.";
	
	public RestBase(String userName, String password, Properties props) {

        this.props = props;
		this.userName = userName;
		this.password = password;
		host = this.props.getProperty("host");
		String p = this.props.getProperty("port");
		if (p != null)
		{
			port = Integer.parseInt(this.props.getProperty("port"));
		}
		arguments = new ArrayList<NameValuePair>();
		try {
			method = new HttpGet("http://" + host);
			//log.debug("UserName - "+userName+"||Password - "+password);
		} catch (Exception e) {System.out.println(e);}
		//log = Logger.getLogger(REST.base.RestBase.class);

    }

	public void addParameter(NameValuePair newParam) {
		arguments.add(newParam);
	}

	public void resetParameters() {
		arguments = new ArrayList<NameValuePair>();
	}

	/**
	 * This will make a call to the server based on the path and the arguments,
	 * it will set the document field.
	 */
	protected void get() {
		if (!this.loggedIn) {
			login();
		}
		try {
			port = Integer.parseInt(props.getProperty("port"));
			String queryString = URLEncodedUtils.format(arguments, "UTF-8");
			//System.out.println("GETURL-" + "http://" + host + path + "?" + queryString);
            URI uri = new URI("http", null, host, port, path, queryString, null);
			//log.debug("GET URI PATH-" + uri.toURL());
			method = new HttpGet(uri);
			HttpResponse response = client.execute(method);

			//log.debug("SYSTEM RESPONSE-" + response.getStatusLine());
			responseCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dbuilder = builderFactory.newDocumentBuilder();
				document = dbuilder.parse(instream);
			}
			recordSize = getIntByXpath("//@recordSize");
			startRow = getIntByXpath("//@startRow");
			totalCount = getIntByXpath("//@totalCount");
		} catch (Exception e) {
			//log.fatal("Exception thrown : " + e.getMessage());
			e.printStackTrace();
		}
		host = props.getProperty("host");
	}

	/**
	 * This will make a call to the server based on the path and the arguments,
	 * it will set the document field, but it will use a post method.
	 * 
	 * This is used in Person for update, and Task for review,done,reject,and
	 * accept.
	 */
	protected void post() {
		if (!this.loggedIn) {
			login();
		}
		try {
			//HttpParams param = method.getParams();
			URI uri = new URI("http", null, host, port, path, "", null);
			//log.debug("GET URI PATH in POST-"+uri.toURL());
			post = new HttpPost(uri);
			HttpResponse response = client.execute(post);
			responseCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dbuilder = builderFactory.newDocumentBuilder();
				document = dbuilder.parse(instream);
			}
		} catch (Exception e) {
			//log.fatal("Exception thrown : " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This will make a post call based on the path, it will use the headers to
	 * pass the user name and password fields for
	 * 
	 */
	protected void post(String body) {
		System.out.println("http://" + this.host + ":" + port + path);
		HttpPost method = new HttpPost("http://" + this.host + ":" + port + path);
		try {
			method.setEntity(new StringEntity(body));
			method.addHeader("username", "admin");
			method.addHeader("password", "admin");
			method.addHeader("Content-Type", "application/xml");
			HttpResponse response = client.execute(method);
			
			HttpEntity entity = response.getEntity();
			document = null;
			if (entity != null) {
				InputStream instream = entity.getContent();
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dbuilder = builderFactory.newDocumentBuilder();
				document = dbuilder.parse(instream);
				System.out.println(instream.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected HttpResponse post(String body, String username, String password) {
		System.out.println("http://" + this.host + ":" + port + path);
		HttpPost method = new HttpPost("http://" + this.host + ":" + port + path);
		HttpResponse response=null;
		try {
			method.setEntity(new StringEntity(body));
			method.addHeader("username", username);
			method.addHeader("password", password);
			method.addHeader("Content-Type", "application/json");
			response = client.execute(method);
			responseCode = response.getStatusLine().getStatusCode();

			@SuppressWarnings("unused")
			HttpEntity entity = response.getEntity();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}


	protected void put(String body) {
		System.out.println("http://" + this.host + ":" + port + path);
		HttpPut method = new HttpPut("http://" + this.host + ":" + port + path);
		try {
			method.setEntity(new StringEntity(body));
			method.addHeader("username", "admin");
			method.addHeader("password", "admin");
			method.addHeader("Content-Type", "application/xml");
			HttpResponse response = client.execute(method);
			HttpEntity entity = response.getEntity();
			document = null;
			if (entity != null) {
				InputStream instream = entity.getContent();
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dbuilder = builderFactory.newDocumentBuilder();
				document = dbuilder.parse(instream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void delete(String body) {
		HttpDeleteWithBody method = new HttpDeleteWithBody("http://" + this.host + ":" + port + path);
		try {
			method.setEntity(new StringEntity(body));
			method.addHeader("username", "admin");
			method.addHeader("password", "admin");
			method.addHeader("Content-Type", "application/xml");
			HttpResponse response = client.execute(method);
			HttpEntity entity = response.getEntity();
			document = null;
			if (entity != null) {
				InputStream instream = entity.getContent();
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dbuilder = builderFactory.newDocumentBuilder();
				document = dbuilder.parse(instream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is similar to get but it will set the startRow and total count and
	 * recordSize variables
	 */
	protected void getMultiple() {
		get();
		recordSize = getIntByXpath("//@recordSize");
		startRow = getIntByXpath("//@startRow");
		totalCount = getIntByXpath("//@totalCount");
	}

	/**
	 * This will validate recordSize, and start row parameters
	 * 
	 * @return
	 */
	protected boolean validateSize() {

		for (NameValuePair current : arguments) {
			if (current.getName().equals(START_ROW)) {
				if (Integer.parseInt(current.getValue()) != startRow)
					return false;
			}
			/*
			 * if the number of records in the system is less than the
			 * recordsSize argument the record size will be given
			 */
			if (current.getName().equals(RECORD_SIZE)) {

				// If there are less records, than recordSize then we will still
				// return
				// true.
				if (Integer.parseInt(current.getValue()) < recordSize)
					return false;
			}	
			
			if (current.getName().equals(TOTAL_COUNT)) {
				
				//If there are less records, than recordSize then we will still return 
				// true.
				if (Integer.parseInt(current.getValue()) > totalCount)
					return false;
			}	
		}
		return true;
	}

	/**
	 * This will login to the server, and then set the loggedIn field to true so
	 * that further calls will not have to login.
	 */
	protected void login(String login, String password) {
		try {
			HttpPost method = new HttpPost("http://" + host
					+ "/RequestCenter/login.signon");

			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("NSA_LOGIN_NAME", login));
			formparams.add(new BasicNameValuePair("NSA_PASSWORD", password));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
					"UTF-8");
			method.setEntity(entity);
			//log.debug("URLLOGIN -" + method.getURI());
			HttpResponse response = client.execute(method);
			HttpEntity resEntity = response.getEntity();
			resEntity.getContent().close();
			this.loggedIn = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void login() {
		try {
			host = host.split(":")[0];
			HttpPost method = new HttpPost("http://" + host + ":" + port + "/RequestCenter/login.signon");
			//log.debug("http://" + host + "/RequestCenter/login.signon");
			//log.debug(userName);
			//log.debug(password);
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("NSA_LOGIN_NAME", userName));
			formparams.add(new BasicNameValuePair("NSA_PASSWORD", password));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			method.setEntity(entity);
			HttpResponse response = client.execute(method);
			HttpEntity resEntity = response.getEntity();
			resEntity.getContent().close();
			this.loggedIn = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpGet getMethod() {
		return method;
	}

	public void setMethod(HttpGet method) {
		this.method = method;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getErrorMessage() {
		return this.getStringByXpath("//nsapi-error-response");
	}

	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * This will make a document into a string, this is for debugging purposes
	 * 
	 //* @param transmission
	 * @return
	 */
	protected String documentToString() {
		try {
			DOMSource domSource = new DOMSource(document);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * This will return a nodelist for the given xpath.
	 * 
	 * @param path
	 //* @param doc
	 * @return
	 */
	protected NodeList getNodeListByXPath(String path) {
		return getNodeListByXPath(this.document, path);
	}
	
	protected NodeList getNodeListByXPath(Document doc, String path) {
		try {
			return getNodeHelper(doc, path);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Similar but this takes the node list and puts it into an array list
	 * 
	 * @param path
	 //* @param doc
	 * @return
	 */
	protected ArrayList<Node> getArrayListByXPath(String path) {
		return getArrayListByXPath(this.document, path);
	}

	/**
	 * Similar but this takes the node list and puts it into an array list
	 *
	 * @param path
	 * @param doc
	 * @return
	 */
	protected ArrayList<Node> getArrayListByXPath(Document doc, String path) {
		ArrayList<Node> retList = new ArrayList<Node>();
		NodeList list = getNodeListByXPath(doc, path);
		for (int i = 0; i < list.getLength(); i++) {
			retList.add(list.item(i));
		}
		return retList;
	}
	
	private NodeList getNodeHelper(Document doc, String path) throws XPathExpressionException{
		return (NodeList) getHelper(doc, path, XPathConstants.NODESET);
	}
	
	private Object getHelper(Document doc, String path, QName type) throws XPathExpressionException{
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile(path);
		return expr.evaluate(doc, type);
	}
	
	private String getStringHelper(Document doc, String path) throws XPathExpressionException{
		return (String) getHelper(doc, path, XPathConstants.STRING);
	}

	protected Node getNodeByXPath(String path) {
		try {
			NodeList list = getNodeHelper(this.document, path);
			return list.item(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This will get a string for a given xpath
	 * 
	 * @param path
	 //* @param doc
	 * @return
	 */
	protected String getStringByXpath(String path) {
		try {
			return (String) getStringHelper(this.document, path);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This will get a string for a given xpath
	 */
	protected int getIntByXpath(String path) {
		try {
			String result = (String) getStringHelper(this.document, path);
			//log.debug("Should be a number : " + result);
			return Integer.parseInt(result);
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean getByIdExpectError(String query, String expectedError, int code) {
		this.path = path + "/id/" + query;
		get();
		return getErrorMessage().equals(expectedError) && this.responseCode == code;
	}

	public boolean getByIdExpectError(int id, String expectedError, int code) {
		this.path = path + "/id/" + id;
		get();
		return getErrorMessage().equals(expectedError) && this.responseCode == code;
	}

	public boolean getByNameExpectError(String query, String expectedError, int code) {
		this.path = path + "/name/" + query;
		get();
		return getErrorMessage().equals(expectedError) && this.responseCode == code;
	}

	public boolean getListExpectError(String expectedError, int code) {
		get();
		return getErrorMessage().equals(expectedError) && this.responseCode == code;
	}

	/*
	 * If the arguments contains startrow already, then we will only ever get
	 * the first page.
	 */
	protected boolean argumentIncludeStartRow() {
		for (NameValuePair current : arguments) {
			if (current.getName().equals(RestBase.START_ROW)) return true;
		}
		return false;
	}

	protected ArrayList<Document> getAll(String url) {
		ArrayList<Document> documents = new ArrayList<Document>();
		this.path = url;
		getMultiple();
		documents.add(document);
		int start = (recordSize + 1);
		// Walking through to see if there is a start row that has already been
		// set.
		for (int i = arguments.size() - 1; i > 0; i--) {
			NameValuePair current = arguments.get(i);
			if (current.getName().equals(RestBase.START_ROW)) {
				start = Integer.parseInt(current.getValue());
				arguments.remove(current);
			}
		}
		BasicNameValuePair sr;
		for (startRow = start; startRow < totalCount; startRow += (recordSize + 1)) {
			sr = new BasicNameValuePair(RestBase.START_ROW, startRow + "");
			arguments.add(sr);
			get();
			documents.add(document);
			arguments.remove(sr);
		}
		return documents;
	}
}
