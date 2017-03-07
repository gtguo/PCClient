package cn.sursoft;

import java.util.Observable;

/**
 * Created by gtguo on 2/16/2017.
 */

public class AirplaneModeStatus extends Observable {
    private boolean enable;
    private String serial = null;

    public AirplaneModeStatus(String devSerial){this.serial = devSerial;}
    public AirplaneModeStatus(String devSerial,boolean enable){
        this.enable = enable;
        this.serial = devSerial;
    }

    public void setAirPlaneModeEnable(boolean enable){
        if(enable != this.enable){
            this.enable = enable;
            setChanged();
            notifyObservers();
        }
        this.enable = enable;
    }
    public boolean getAirPlaneModeEnable(){
        return this.enable;
    }
    public String getSerial(){return this.serial;}
}
