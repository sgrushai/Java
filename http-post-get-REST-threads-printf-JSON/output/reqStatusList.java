package output;

import data.RequisitionDetails;

import java.util.*;

/**
 * Created by sgrushai on 16/07/2015.
 */
public class reqStatusList {
    public static List<RequisitionDetails> rd = Collections.synchronizedList(new ArrayList<RequisitionDetails>());
    public static Map<String, ArrayList<RequisitionDetails>> reqStatuses = new HashMap<String, ArrayList<RequisitionDetails>>();


    public static void outputReqList(int maxAttempts, int waitFirstAttempt, int waitAdditionalAttempt) throws Exception {
        Thread.sleep(waitFirstAttempt);

        int attempt = 0;
        while (attempt < maxAttempts) {
            Thread.sleep(waitAdditionalAttempt);

            for (RequisitionDetails l:rd){
                if(reqStatuses.containsKey(l.getReqId())){
                    reqStatuses.get(l.getReqId()).add(new RequisitionDetails(l.getReqStatus(), new Date(), false));
                } else {
                    reqStatuses.put(l.getReqId(), new ArrayList<RequisitionDetails>());
                }

                ///
                for(Map.Entry<String, ArrayList<RequisitionDetails>> rds:reqStatuses.entrySet()){

                    for(int i=0;i<rds.getValue().size();i++){

                        if(!rds.getValue().get(i).isDisplayed()){
                            if((i>0&&(!rds.getValue().get(i).getDate().equals(rds.getValue().get(i-1).getDate()))) || (i<(rds.getValue().size()-1)&& (!rds.getValue().get(i).getDate().equals(rds.getValue().get(i+1).getDate())))) {

                                    System.out.println(rds.getKey() + "   " + rds.getValue().get(i).getReqStatus() + "   " + rds.getValue().get(i).getDate() + "   " + rds.getValue().get(i).isDisplayed());
                                    rds.getValue().get(i).setDisplayed(true);
                            }
                        }

                    }



                }

                ///
            }
            attempt++;
        }


    }

}