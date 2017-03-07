package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import cn.sursoft.AirplaneModeStatus;
import cn.sursoft.BatteryStatus;
import cn.sursoft.ConnectivityStatus;
import cn.sursoft.DevDisplay;
import cn.sursoft.DevDoIdentify;
import cn.sursoft.DevProperty;
import cn.sursoft.DevSdcardStatus;
import cn.sursoft.DevVision;
import cn.sursoft.DevWifiStatus;
import cn.sursoft.PhoneStateStatus;
import cn.sursoft.ServiceStfInstall;
import cn.sursoft.SocketClient;
import cn.sursoft.SursoftToolSrrStatus;

class Watcher<T> implements Observer {
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
public class MyClass {
    private static final String TAG = "example ";
    public static void main(String[] args) throws InterruptedException, IOException {
        SocketClient sc = new SocketClient("0123456789ABCDEF");
        ServiceStfInstall stfInstall = new ServiceStfInstall("0123456789ABCDEF");
        stfInstall.startStfServie();
        Thread.sleep(1000*5);
        sc.socketConnect();
        //minitor batterr status
        BatteryStatus bs = sc.getBatteryStatus();
        Watcher<BatteryStatus> w1 = new Watcher<>(bs);
        w1.update(bs,null);
        //minitor airplaneMode status
        AirplaneModeStatus airplaneModeStatus = sc.getAirplaneModeStatus();
        Watcher<AirplaneModeStatus> w2 = new Watcher<>(airplaneModeStatus);
        w2.update(airplaneModeStatus,null);
        //minitor connectivity status
        ConnectivityStatus connectivityStatus = sc.getConnectivityStatus();
        Watcher<ConnectivityStatus> w3 = new Watcher<>(connectivityStatus);
        w3.update(connectivityStatus,null);
        //minitor phoneState Status
        PhoneStateStatus phoneStateStatus = sc.getPhoneStateStatus();
        Watcher<PhoneStateStatus> w4 = new Watcher<>(phoneStateStatus);
        w4.update(phoneStateStatus,null);
        //minitor sursoftToolSrr stauts
        SursoftToolSrrStatus sursoftToolSrrStatus = sc.getSursoftToolSrrStatus();
        Watcher<SursoftToolSrrStatus> w5 = new Watcher<>(sursoftToolSrrStatus);
        w5.update(sursoftToolSrrStatus,null);
        //get dev identify and product model
        DevDoIdentify devDoIdentify = sc.getDevDoIdentify();
        System.out.println(TAG + "DevDoIdentify "+ devDoIdentify.getDevIdentify());
        System.out.println(TAG + "Product Model: "+ devDoIdentify.getProductModel());
        //get dev Property imei,imsi,phonenumber,etc.
        DevProperty devProperty = sc.getDevProperty();
        HashMap<String,String> hs = devProperty.getDevPropertiesHashMap();
        for(String key : hs.keySet()){
            System.out.println(TAG + "DevProperty "+key + ":" + hs.get(key));
        }
        //get Android version
        DevVision devVision = sc.getDevVision();
        System.out.println(TAG+"dev vision:"+devVision.getDevVision());
        //get display info
        DevDisplay devDisplay = sc.getDevDisplay();
        System.out.println(TAG + "getDevDisplay "+"Density:"+devDisplay.getScreenDensity()
                +" Rotation:"+devDisplay.getScreenRotation()
                +" Fps:"+devDisplay.getScreenFps()
                +" Height:"+devDisplay.getScreenHeight()
                +" Width:"+devDisplay.getScreenWidth()
                +" Xdpi:"+devDisplay.getScreenXdpi()
                +" Ydpi:"+devDisplay.getScreenYdpi());
        //get wifi status:open or closed
        DevWifiStatus devWifiStatus = sc.getDevWifiStatus();
        System.out.println(TAG+"WIFI status:"+devWifiStatus.getWifiStatus());
        //get sdcard status:mounted or not
        DevSdcardStatus devSdcardStatus = sc.getSdcardStatus();
        System.out.println(TAG+"sdcard status:"+devSdcardStatus.getSdcardStatus());

    }
}
