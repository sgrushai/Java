package data;

/**
 * Created by sgrushai on 07/07/2015.
 */
public class vmDetails{
    private String platformElementID;
    private String VMType;
    private String FriendlyName;
    public vmDetails(String friendlyName, String platformElementID, String vMType) {
        super();
        this.platformElementID = platformElementID;
        VMType = vMType;
        FriendlyName = friendlyName;
    }
    public String getPlatformElementID() {
        return platformElementID;
    }
    public void setPlatformElementID(String platformElementID) {
        this.platformElementID = platformElementID;
    }
    public String getVMType() {
        return VMType;
    }
    public void setVMType(String vMType) {
        VMType = vMType;
    }
    public String getFriendlyName() {
        return FriendlyName;
    }
    public void setFriendlyName(String friendlyName) {
        FriendlyName = friendlyName;
    }
    @Override
    public String toString() {
        return "data.vmDetails [platformElementID=" + platformElementID
                + ", VMType=" + VMType + ", FriendlyName=" + FriendlyName + "]";
    }


}