package data;

import java.util.Date;

/**
 * Created by sgrushai on 16/07/2015.
 */
public class RequisitionDetails {
   private String reqId;
   private String reqStatus;
    private Date date;
    private boolean displayed;

    public RequisitionDetails(String reqStatus, Date date, boolean displayed) {
        this.reqStatus = reqStatus;
        this.date = date;
        this.displayed = displayed;
    }

    public RequisitionDetails(String reqId, String reqStatus) {
        this.reqId = reqId;
        this.reqStatus = reqStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getReqId() {
        return reqId;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    @Override
    public String toString() {
        return "data.RequisitionDetails{" +
                "reqId='" + reqId + '\'' +
                ", reqStatus='" + reqStatus + '\'' +
                ", date=" + date +
                ", displayed=" + displayed +
                '}';
    }
}
