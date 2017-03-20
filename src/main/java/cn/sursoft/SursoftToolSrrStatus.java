package cn.sursoft;

import java.util.Observable;

/**
 * Created by gtguo on 2/23/2017.
 */

public class SursoftToolSrrStatus extends Observable{
    private String status = null;
    private String serial = null;

    public SursoftToolSrrStatus(String devSerial){this.serial = devSerial;}

    public void setSursoftToolSrrStatus(String status){
        if(!status.equals(this.status)){
            this.status = status;
            AdbCommand.setDevIdStatus(serial,status);
            setChanged();
            notifyObservers();
        }
        this.status = status;
    }
    public String getSursoftToolSrrStatus(){
        return this.status;
    }
    public String getSerial(){return this.serial;}
}
