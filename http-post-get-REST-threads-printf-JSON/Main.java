/**
 * Created by appko on 07/07/2015.
 */
import HTTP.HTTPloginPostGet;
import REST.deleteVM;
import data.vmDetails;
import org.json.simple.JSONObject;
import output.ReqThread;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.*;

/**
 * Created by sgrushai on 07/07/2015.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        if (args.length != 7) {
            System.out.println();
            System.out.println("There should be 7 Input parameters for BulkDeleteVMs App to work");
            System.exit(-1);
        }
        Properties properties = new Properties();

        properties.put("host", args[0]);
        properties.put("port", args[1]);
        properties.put("testLogin", args[2]);
        properties.put("testPassword", args[3]);
        properties.put("billToOU", args[4]);
        properties.put("vmFNFilter1", args[5]);
        properties.put("vmFNFilter2", args[6]);
        properties.put("USER_AGENT", "Mozilla/5.0");
        properties.put("pathToLogin", "/RequestCenter/login.signon");
        properties.put("pathToGet", "/RequestCenter/custom/IAC/jsp/iacview.jsp?si=SiVirtualServer&filter=Status=Active");

            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            HTTPloginPostGet http = new HTTPloginPostGet();
            System.out.println("\nLoggin in to environment: " + properties.getProperty("host"));
            http.sendPost(properties.getProperty("USER_AGENT"), properties.getProperty("testLogin"), properties.getProperty("testPassword"), properties.getProperty("host"), properties.getProperty("pathToLogin"));
            String jsonString = http.sendGet(properties.getProperty("USER_AGENT"), properties.getProperty("host"), properties.getProperty("port"), properties.getProperty("pathToGet"));
            org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            List<JSONObject> list = new ArrayList<JSONObject>();
            list.addAll((ArrayList<JSONObject>) jsonObject.get("items"));
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("List of VMs with Active status: ");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-30s", "Friendly Name");
            System.out.printf("%-30s", "VM Type");
            System.out.printf("%-25s", "Datacenter");
            System.out.printf("%-40s", "Operating System");
            System.out.printf("%-35s", "Organizational Unit Name");
            System.out.printf("%-30s", "VM Full Path");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (JSONObject l : list) {
                System.out.printf("%-30s", l.get("FriendlyName"));
                System.out.printf("%-30s", l.get("VMType"));
                System.out.printf("%-25s", l.get("Datacenter"));
                System.out.printf("%-40s", l.get("GuestOperatingSystem"));
                System.out.printf("%-35s", l.get("organizationalUnitName"));
                System.out.printf("%-30s", l.get("VMFullPath"));
                System.out.println();
            }
            Map map = new HashMap<String, vmDetails>();
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("VMs to be deleted: ");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-30s", "Friendly Name");
            System.out.printf("%-30s", "VM Type");
            System.out.printf("%-25s", "Datacenter");
            System.out.printf("%-40s", "Operating System");
            System.out.printf("%-35s", "Organizational Unit Name");
            System.out.printf("%-30s", "VM Full Path");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).get("FriendlyName").toString().contains(properties.getProperty("vmFNFilter1")) || list.get(i).get("FriendlyName").toString().contains(properties.getProperty("vmFNFilter2"))) {
                    System.out.printf("%-30s", list.get(i).get("FriendlyName"));
                    System.out.printf("%-30s", list.get(i).get("VMType"));
                    System.out.printf("%-25s", list.get(i).get("Datacenter"));
                    System.out.printf("%-40s", list.get(i).get("GuestOperatingSystem"));
                    System.out.printf("%-35s", list.get(i).get("organizationalUnitName"));
                    System.out.printf("%-30s", list.get(i).get("VMFullPath"));
                    System.out.println();

                    map.put((String) list.get(i).get("Name"), new vmDetails((String) list.get(i).get("FriendlyName"), (String) list.get(i).get("PlatformElementID"), (String) list.get(i).get("VMType")));

                }
            }
        List<JSONObject> jso = new ArrayList<JSONObject>();
        System.out.println();
            System.out.println("Would you like to delete all above Virtual Machines (type Y or N)?");
            Scanner sc = new Scanner(System.in);
            String answer = sc.next();
            if (answer.equalsIgnoreCase("y")) {
                List<JSONObject> listResult = new ArrayList<JSONObject>();
            for (Object l : map.entrySet()) {
                deleteVM dvm = new deleteVM();
                String result = dvm.deleteVM(properties.getProperty("testLogin"), properties.getProperty("testPassword"), properties.getProperty("billToOU"), ((Map.Entry<String, vmDetails>) l).getKey(), ((Map.Entry<String, vmDetails>) l).getValue().getPlatformElementID(), ((Map.Entry<String, vmDetails>) l).getValue().getVMType(), properties);
                listResult.add((JSONObject) parser.parse(result));
            }
                for (JSONObject jo : listResult) {
                    System.out.println((JSONObject) parser.parse(jo.get("RequisitionSubmit").toString()));
                    jso.add((JSONObject) parser.parse(jo.get("RequisitionSubmit").toString()));
                }
                List<String> reqList = new ArrayList<String>();
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("List of Requisitions Submitted: ");
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-30s", "Requisition #");
                System.out.printf("%-30s", "Initiator");
                System.out.printf("%-30s", "Customer");
                System.out.printf("%-30s", "Status");
                System.out.printf("%-30s", "Started Date");
                System.out.println();
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (JSONObject jo : jso) {
                    System.out.println();
                    System.out.printf("%-30s", jo.get("id"));
                    reqList.add(jo.get("id").toString());
                    System.out.printf("%-30s", jo.get("initiator"));
                    System.out.printf("%-30s", jo.get("customer"));
                    System.out.printf("%-30s", jo.get("status"));
                    System.out.printf("%-30s", jo.get("startedDate"));
                }

                System.out.println();
            }
        System.out.println();
                System.out.println("Would you like to wait while BulkDeleteVMs App validates submitted requisitions? It may take up to 30 minutes (type Y or N)?");
                Scanner sc2 = new Scanner(System.in);
                String answer2 = sc2.next();
                List<ReqThread> threads = new ArrayList<ReqThread>();
                if (answer2.equalsIgnoreCase("y")) {
                    System.out.println("Please wait for first update of requisitions statuses around 5 minutes. Next updates will be every 3 minutes...");
                    for (JSONObject jo : jso) {
                        threads.add(new ReqThread(jo.get("id").toString(), properties));
                    }
                    for (ReqThread t : threads) {
                        t.start();
                    }
                } else {
                    System.exit(-1);
                }



        }

    }
