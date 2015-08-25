package output;

/**
 * Created by sgrushai on 15/07/2015.
 */
public class OutputReqDetailsThread implements Runnable {
    private Thread t;

    public OutputReqDetailsThread() {

    }

    public void run() {
        try {
            reqStatusList.outputReqList(7, 300000 + 500, 180000 + 500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void start() {
        //System.out.println("starting thread "+ rl);
        t = new Thread (this);
        t.start ();
    }
}
