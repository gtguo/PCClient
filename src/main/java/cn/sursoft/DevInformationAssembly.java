package cn.sursoft;

import java.net.Socket;

/**
 * Created by gtguo on 3/1/2017.
 */

public class DevInformationAssembly {
    public static final String TAG = "Sursoft DevInformationAssembly ";
    private DevProperty devProperty = null;
    private DevDoIdentify devDoIdentify = null;
    private DevVision devVision = null;
    private DevDisplay devDisplay = null;
    private DevSdcardStatus devSdcardStatus = null;
    private DevWifiStatus devWifiStatus = null;
    private String devSerial = null;
    private Socket socket;

    public DevInformationAssembly(String devSerial,Socket socket){
        this.devSerial = devSerial;
        this.socket = socket;
        devProperty = new DevProperty(devSerial,socket);
        devDoIdentify = new DevDoIdentify(devSerial,socket);
        devVision = new DevVision(devSerial,socket);
        devDisplay = new DevDisplay(devSerial,socket);
        devSdcardStatus = new DevSdcardStatus(devSerial,socket);
        devWifiStatus = new DevWifiStatus(devSerial,socket);
    }

    public String getSerial(){
        return this.devSerial;
    }

    public Socket getSocket(){
        return this.socket;
    }

    public DevProperty getDevProperty(){
        return this.devProperty;
    }

    public DevDoIdentify getDevDoIdentify(){
        return this.devDoIdentify;
    }

    public DevVision getDevVision(){
        return this.devVision;
    }

    public DevDisplay getDevDisplay(){
        return this.devDisplay;
    }

    public DevSdcardStatus getDevSdcardStatus(){
        return this.devSdcardStatus;
    }

    public DevWifiStatus getDevWifiStatus(){
        return this.devWifiStatus;
    }
}
