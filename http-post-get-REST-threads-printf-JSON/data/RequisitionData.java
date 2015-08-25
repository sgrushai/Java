package data;
import REST.base.RestDataBase;
import org.w3c.dom.*;

public class RequisitionData extends RestDataBase {
	String actualDuration;
	String customerId;
	String customerName;
	String expectedCost;
	String expectedDuration;
	String organizationalUnitId;
	String organizationalUnitName;
	String ownerId;
	String ownerName;
	String requisitionId;
	String requisitionURL;
	String requisitionURLOnly;
	String serviceId;
	String serviceName;
	String startedDate;
	String startedDateRaw;
	String status;
	String statusId;
	String tenantId;
	String userId;
	String percentageCompleted;

	public RequisitionData(Node node) {
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node current = children.item(i);
			if (current.getNodeName().equals("actualDuration"))
				actualDuration = current.getTextContent();
			if (current.getNodeName().equals("customerId"))
				customerId = current.getTextContent();
			if (current.getNodeName().equals("customerName"))
				customerName = current.getTextContent();
			if (current.getNodeName().equals("expectedCost"))
				expectedCost = current.getTextContent();
			if (current.getNodeName().equals("expectedDuration"))
				expectedDuration = current.getTextContent();
			if (current.getNodeName().equals("organizationalUnitId"))
				organizationalUnitId = current.getTextContent();
			if (current.getNodeName().equals("organizationalUnitName"))
				organizationalUnitName = current.getTextContent();
			if (current.getNodeName().equals("ownerId"))
				ownerId = current.getTextContent();
			if (current.getNodeName().equals("ownerName"))
				ownerName = current.getTextContent();
			if (current.getNodeName().equals("requisitionId"))
				requisitionId = current.getTextContent();
			if (current.getNodeName().equals("requisitionURL"))
				requisitionURL = current.getTextContent();
			if (current.getNodeName().equals("requisitionURLOnly"))
				requisitionURLOnly = current.getTextContent();
			if (current.getNodeName().equals("serviceId"))
				serviceId = current.getTextContent();
			if (current.getNodeName().equals("serviceName"))
				serviceName = current.getTextContent();
			if (current.getNodeName().equals("startedDate"))
				startedDate = current.getTextContent();
			if (current.getNodeName().equals("startedDateRaw"))
				startedDateRaw = current.getTextContent();
			if (current.getNodeName().equals("status"))
				status = current.getTextContent();
			if (current.getNodeName().equals("statusId"))
				statusId = current.getTextContent();
			if (current.getNodeName().equals("tenantId"))
				tenantId = current.getTextContent();
			if (current.getNodeName().equals("userId"))
				userId = current.getTextContent();
			if (current.getNodeName().equals("percentageCompleted"))
				userId = current.getTextContent();
		}
	}

	public RequisitionData(Document dataDoc) {
		actualDuration = getStringByXpath("//actualDuration", dataDoc);
		customerId = getStringByXpath("//customerId", dataDoc);
		customerName = getStringByXpath("//customerName", dataDoc);
		expectedCost = getStringByXpath("//expectedCost", dataDoc);
		expectedDuration = getStringByXpath("//expectedDuration", dataDoc);
		organizationalUnitId = getStringByXpath("//organizationalUnitId",
				dataDoc);
		organizationalUnitName = getStringByXpath("//organizationalUnitName",
				dataDoc);
		ownerId = getStringByXpath("//ownerId", dataDoc);
		ownerName = getStringByXpath("//ownerName", dataDoc);
		requisitionId = getStringByXpath("//requisitionId", dataDoc);
		requisitionURL = getStringByXpath("//requisitionURL", dataDoc);
		requisitionURLOnly = getStringByXpath("//requisitionURLOnly", dataDoc);
		serviceId = getStringByXpath("//serviceId", dataDoc);
		serviceName = getStringByXpath("//serviceName", dataDoc);
		startedDate = getStringByXpath("//startedDate", dataDoc);
		startedDateRaw = getStringByXpath("//startedDateRaw", dataDoc);
		status = getStringByXpath("//status", dataDoc);
		statusId = getStringByXpath("//statusId", dataDoc);
		tenantId = getStringByXpath("//tenantId", dataDoc);
		userId = getStringByXpath("//userId", dataDoc);
		percentageCompleted = getStringByXpath("//percentageCompleted", dataDoc);
	}

	public String getActualDuration() {
		return actualDuration;
	}

	public void setActualDuration(String actualDuration) {
		this.actualDuration = actualDuration;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getExpectedCost() {
		return expectedCost;
	}

	public void setExpectedCost(String expectedCost) {
		this.expectedCost = expectedCost;
	}

	public String getExpectedDuration() {
		return expectedDuration;
	}

	public void setExpectedDuration(String expectedDuration) {
		this.expectedDuration = expectedDuration;
	}

	public String getOrganizationalUnitId() {
		return organizationalUnitId;
	}

	public void setOrganizationalUnitId(String organizationalUnitId) {
		this.organizationalUnitId = organizationalUnitId;
	}

	public String getOrganizationalUnitName() {
		return organizationalUnitName;
	}

	public void setOrganizationalUnitName(String organizationalUnitName) {
		this.organizationalUnitName = organizationalUnitName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getRequisitionId() {
		return requisitionId;
	}

	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}

	public String getRequisitionURL() {
		return requisitionURL;
	}

	public void setRequisitionURL(String requisitionURL) {
		this.requisitionURL = requisitionURL;
	}

	public String getRequisitionURLOnly() {
		return requisitionURLOnly;
	}

	public void setRequisitionURLOnly(String requisitionURLOnly) {
		this.requisitionURLOnly = requisitionURLOnly;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(String startedDate) {
		this.startedDate = startedDate;
	}

	public String getStartedDateRaw() {
		return startedDateRaw;
	}

	public void setStartedDateRaw(String startedDateRaw) {
		this.startedDateRaw = startedDateRaw;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPercentageCompleted() {
		return percentageCompleted;
	}

	public void setPercentageCompleted(String percentageCompleted) {
		this.percentageCompleted = percentageCompleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((actualDuration == null) ? 0 : actualDuration.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result
				+ ((expectedCost == null) ? 0 : expectedCost.hashCode());
		result = prime
				* result
				+ ((expectedDuration == null) ? 0 : expectedDuration.hashCode());
		result = prime
				* result
				+ ((organizationalUnitId == null) ? 0 : organizationalUnitId
						.hashCode());
		result = prime
				* result
				+ ((organizationalUnitName == null) ? 0
						: organizationalUnitName.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result
				+ ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result
				+ ((requisitionId == null) ? 0 : requisitionId.hashCode());
		result = prime * result
				+ ((requisitionURL == null) ? 0 : requisitionURL.hashCode());
		result = prime
				* result
				+ ((requisitionURLOnly == null) ? 0 : requisitionURLOnly
						.hashCode());
		result = prime * result
				+ ((serviceId == null) ? 0 : serviceId.hashCode());
		result = prime * result
				+ ((serviceName == null) ? 0 : serviceName.hashCode());
		result = prime * result
				+ ((startedDate == null) ? 0 : startedDate.hashCode());
		result = prime * result
				+ ((startedDateRaw == null) ? 0 : startedDateRaw.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((statusId == null) ? 0 : statusId.hashCode());
		result = prime * result
				+ ((tenantId == null) ? 0 : tenantId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequisitionData other = (RequisitionData) obj;
		if (actualDuration == null) {
			if (other.actualDuration != null)
				return false;
		} else if (!actualDuration.equals(other.actualDuration))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (expectedCost == null) {
			if (other.expectedCost != null)
				return false;
		} else if (!expectedCost.equals(other.expectedCost))
			return false;
		if (expectedDuration == null) {
			if (other.expectedDuration != null)
				return false;
		} else if (!expectedDuration.equals(other.expectedDuration))
			return false;
		if (organizationalUnitId == null) {
			if (other.organizationalUnitId != null)
				return false;
		} else if (!organizationalUnitId.equals(other.organizationalUnitId))
			return false;
		if (organizationalUnitName == null) {
			if (other.organizationalUnitName != null)
				return false;
		} else if (!organizationalUnitName.equals(other.organizationalUnitName))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (requisitionId == null) {
			if (other.requisitionId != null)
				return false;
		} else if (!requisitionId.equals(other.requisitionId))
			return false;
		if (requisitionURL == null) {
			if (other.requisitionURL != null)
				return false;
		} else if (!requisitionURL.equals(other.requisitionURL))
			return false;
		if (requisitionURLOnly == null) {
			if (other.requisitionURLOnly != null)
				return false;
		} else if (!requisitionURLOnly.equals(other.requisitionURLOnly))
			return false;
		if (serviceId == null) {
			if (other.serviceId != null)
				return false;
		} else if (!serviceId.equals(other.serviceId))
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		if (startedDate == null) {
			if (other.startedDate != null)
				return false;
		} else if (!startedDate.equals(other.startedDate))
			return false;
		if (startedDateRaw == null) {
			if (other.startedDateRaw != null)
				return false;
		} else if (!startedDateRaw.equals(other.startedDateRaw))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		if (tenantId == null) {
			if (other.tenantId != null)
				return false;
		} else if (!tenantId.equals(other.tenantId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "data.RequisitionData [actualDuration=" + actualDuration
				+ ", customerId=" + customerId + ", customerName="
				+ customerName + ", expectedCost=" + expectedCost
				+ ", expectedDuration=" + expectedDuration
				+ ", organizationalUnitId=" + organizationalUnitId
				+ ", organizationalUnitName=" + organizationalUnitName
				+ ", ownerId=" + ownerId + ", ownerName=" + ownerName
				+ ", requisitionId=" + requisitionId + ", requisitionURL="
				+ requisitionURL + ", requisitionURLOnly=" + requisitionURLOnly
				+ ", serviceId=" + serviceId + ", serviceName=" + serviceName
				+ ", startedDate=" + startedDate + ", startedDateRaw="
				+ startedDateRaw + ", status=" + status + ", statusId="
				+ statusId + ", tenantId=" + tenantId + ", userId=" + userId
				+ "]";
	}
	
	
}
