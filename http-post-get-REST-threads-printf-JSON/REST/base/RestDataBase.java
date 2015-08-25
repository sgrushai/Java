package REST.base;//import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RestDataBase {
	Document dataDoc;
	//Logger logger = Logger.getLogger(REST.base.RestDataBase.class);
	String name;
	protected String restNamePath= "";

	
	/**
	 * This will get a string for a given xpath
	 * @param path
	 * @param doc
	 * @return
	 */
	protected String getStringByXpath(String path, Document doc) {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile(path);
			return (String) expr.evaluate(doc, XPathConstants.STRING);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * This will get a Node for a given xpath
	 * @param path
	 * @param doc
	 * @return
	 */
	protected Node getNodeByXpath(String path, Document doc) {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile(path);
			return (Node) expr.evaluate(doc, XPathConstants.NODE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * This will get a List of Nodes for a given xpath
	 * @param path
	 * @param doc
	 * @return
	 */
	protected NodeList getNodeListByXPath(String path, Document doc) {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile(path);
			return (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected ArrayList<Node> getArrayListByXpath(String path,Document doc) { 
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile(path);
			ArrayList<Node> retList = new ArrayList<Node>();
			NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for(int i = 0;i < list.getLength();i++) {retList.add(list.item(i));}
			return retList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This will take the raw string format and parse it to 
	 * @param rawDate
	 * @return
	 */
	protected Date convertRawDateToDate(String rawDate) { 
		SimpleDateFormat rawFormat = new SimpleDateFormat("yyyy-MM-dd");
		String day = rawDate.split("T")[0];
		//String time = rawDate.split("T")[1].split("[.]")[0];
		try { 
		 return rawFormat.parse(day);
		}catch(Exception e) { e.printStackTrace();}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDoc == null) ? 0 : dataDoc.hashCode());
		return result;
	}

	/**
	 * This should not be used, I am putting it here so that the sub classes do not give me a warning.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestDataBase other = (RestDataBase) obj;
		if (dataDoc == null) {
			if (other.dataDoc != null)
				return false;
		} else if (!dataDoc.equals(other.dataDoc))
			return false;
		return true;
	}

}
