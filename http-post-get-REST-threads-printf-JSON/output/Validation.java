package output;

import REST.RestRequisition;
import data.RequisitionData;

import java.util.Date;
import java.util.Properties;


public class Validation {


	public static String VALIDATION_MSG_RequisitionStatus="Unexpected Status for Requisition ID: ";
	public static String VALIDATION_MSG_RequisitionPercentage="Unexpected Requisition Percentage Completed";
	public static String VALIDATION_MSG_ROLE="Unexpected Role";
	public static String VALIDATION_MSG_PEOPLE_FIRSTNAME="Unexpected People First Name";
	public static String VALIDATION_MSG_PEOPLE_LASTNAME="Unexpected People Last Name";
	public static String VALIDATION_MSG_PEOPLE_LOGINNAME="Unexpected People Login Name";
	public static String VALIDATION_MSG_CPOCloudInfrastructure="Unexpected CPOCloudInfrastructure Data";
	public static String VALIDATION_MSG_OU_ORGNAME="Unexpected Organization Name";
	public static String VALIDATION_MSG_OU_UNITTYPE="Unexpected Unit Type";
	public static String VALIDATION_MSG_OU_STATUS="Unexpected Org Unit Status";
	public static String VALIDATION_STEP_HEADER="Unexpected Step header: ";
	public static int VALIDATE_REQUISITION_STATUS_MAX_ATTEMPTS=4;
	public static int VALIDATE_REQUISITION_STATUS_WAITING_INTERVAL=15000;	
	
	//Login to be use to do verification with nsapi
	//initialize by tests classes
	public static String adminLogin = null;
	public static String adminPassword = null;
	public static Properties envProps = null;


	/**
	 * 
	 */
	public Validation() {}
	
	
	public static String getAdminLogin() {
		return adminLogin;
	}

	public static void setAdminLogin(String adminLogin) {
		Validation.adminLogin = adminLogin;
	}

	public static String getAdminPassword() {
		return adminPassword;
	}

	public static void setAdminPassword(String adminPassword) {
		Validation.adminPassword = adminPassword;
	}
	
	public static Properties getEnvProps() {
		return envProps;
	}


	public static void setEnvProps(Properties props) {
		Validation.envProps = props;
	}





	private static RequisitionData getRequisitionData(String requisitionID){
		RestRequisition rr = new RestRequisition(adminLogin, adminPassword, envProps);
		return rr.getRequisionById(requisitionID);
	}


    public static String validateRequisitionStatus(int maxAttempts, int waitFirstAttempt,int waitAdditionalAttempt, String requisitionID, String expectedStatus) throws Exception{

        Thread.sleep(waitFirstAttempt);
        RequisitionData reqData = getRequisitionData(requisitionID);
        String status = reqData.getStatus();
        int attempt = 0;
        while ((status.compareTo(expectedStatus)!=0) && (attempt < maxAttempts)){
            Thread.sleep(waitAdditionalAttempt);
            reqData = getRequisitionData(requisitionID);
            status = reqData.getStatus();
            attempt++;
            System.out.println(reqData.getRequisitionId()+"   -   "+reqData.getStatus()+"   -   "+new Date());



        }

        System.out.println(reqData.getRequisitionId()+"   -   "+reqData.getStatus()+"   -   "+new Date());
        return status;
    }


}
