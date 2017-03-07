package cn.sursoft;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by gtguo on 3/1/2017.
 */

public class Watcher<T> implements Observer {
    public Watcher(Observable o){o.addObserver(this);}
    @Override
    public void update(Observable o, Object arg){
        T t = (T)o;
        System.out.println("Watcher: "+o.getClass().toString());
        if(o.getClass().toString().equals("class cn.sursoft.BatteryStatus")){
            BatteryStatus bs = (BatteryStatus)o;
            System.out.println("BatteryWatcher "+bs.getBatteryStatus()
                    +" health:"+bs.getBatteryHealth()
                    +" source:"+bs.getBatterySource()
                    +" level:"+bs.getBatteryLevel()
                    +" scale:"+bs.getBatteryScale()
                    +" temperature:"+bs.getBatteryTemperature()
                    +" voltage:"+bs.getBatteryVoltage());
        }else if(o.getClass().toString().equals("class cn.sursoft.AirplaneModeStatus")){
            AirplaneModeStatus as = (AirplaneModeStatus)o;
            System.out.println("AirplaneModeWatcher "+as.getAirPlaneModeEnable());
        }else if(o.getClass().toString().equals("class cn.sursoft.ConnectivityStatus")){
            ConnectivityStatus cs = (ConnectivityStatus)o;
            System.out.println("ConnectivityStatusWatcher "
                    +" Connected:" +cs.getConnected()
                    +" type:"+cs.getType()
                    +" subType:"+cs.getSubtype()
                    +" Roaming:"+cs.getRoaming()
                    +" failover:"+cs.getFailover());
        }else if(o.getClass().toString().equals("class cn.sursoft.PhoneStateStatus")){
            PhoneStateStatus ps = (PhoneStateStatus)o;
            System.out.println("PhoneStateStatusWatcher "
                    +" state:" +ps.getPhoneState()
                    +" Manual:"+ps.getPhoneManual()
                    +" Operator:"+ps.getPhoneOperator());
        }else{
            System.out.println("Watcher is invalid! ");
        }
    }
}
