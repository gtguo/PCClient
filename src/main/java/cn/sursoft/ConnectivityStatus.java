package cn.sursoft;

import java.util.Observable;

/**
 * Created by gtguo on 2/16/2017.
 */

public class ConnectivityStatus extends Observable{
    private boolean connected ;
    private String type ;
    private String subtype ;
    private boolean failover ;
    private boolean roaming ;
    private String serial = null;

    public ConnectivityStatus(String devSerial){this.serial = devSerial;}
    public ConnectivityStatus(String devSerial,boolean connected,
                              String type,String subtype,boolean failover,boolean roaming){
        this.serial = devSerial;
        this.connected = connected;
        this.type = type;
        this.subtype = subtype;
        this.failover = failover;
        this.roaming = roaming;
    }

    public boolean getConnected(){
        return this.connected;
    }
    public void setConnected(boolean connected){this.connected = connected;}

    public String getType(){
        return this.type;
    }
    public void setType(String type){this.type = type;}

    public String getSubtype(){
        return this.subtype;
    }
    public void setSubtype(String subtype){this.subtype = subtype;}

    public boolean getFailover(){
        return this.failover;
    }
    public void setFailover(boolean failover){this.failover = failover;}

    public boolean getRoaming(){
        return this.roaming;
    }
    public void setRoaming(boolean roaming){this.roaming = roaming;}

    public void setConnectivityField(boolean connected,String type,
                                     String subtype,boolean failover,boolean roaming){
        boolean changedFlag = false;
        if(connected != this.connected
                || failover != this.failover
                || roaming != this.roaming
                || !type.equals(this.type)
                || !subtype.equals(this.subtype)){
            changedFlag = true;
            System.out.println("ConnectivityStatus Status has changed! ");
        }
        setConnected(connected);
        setType(type);
        setSubtype(subtype);
        setFailover(failover);
        setRoaming(roaming);
        if(changedFlag == true){
            setChanged();
            notifyObservers();
        }
    }

    public String getSerial(){return this.serial;}
}
