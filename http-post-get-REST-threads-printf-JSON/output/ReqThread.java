package output;

import REST.RestRequisition;

import java.util.Properties;

/**
 * Created by sgrushai on 15/07/2015.
 */
public class ReqThread implements Runnable {
    private Thread t;
    private String rl;
    private Properties properties;

    public ReqThread(String rl, Properties properties) {
        this.rl = rl;
        this.properties = properties;
    }

    public void run() {
        //System.out.println("running thread "+rl);
        Validation.setEnvProps(properties);
        Validation.setAdminLogin(properties.getProperty("testLogin"));
        Validation.setAdminPassword(properties.getProperty("testPassword"));
        try {
            Validation.validateRequisitionStatus(5, 300000, 180000, rl, RestRequisition.STATUS_CLOSED);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void start() {
        //System.out.println("starting thread "+ rl);
        t = new Thread (this, rl);
        t.start ();
    }
}
