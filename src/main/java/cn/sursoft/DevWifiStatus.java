package cn.sursoft;

import java.net.Socket;

/**
 * Created by gtguo on 2/18/2017.
 */

public class DevWifiStatus {
    public static final String TAG = "Sursoft DevSdcardStatus ";
    private String devSerial = null;
    private Socket socket;
    private boolean wifiStatus = false;

    public DevWifiStatus(String devSerial,Socket socket){
        this.devSerial = devSerial;
        this.socket = socket;
    }

    public boolean getWifiStatus(){
        return this.wifiStatus;
    }
    public String getSerial(){return this.devSerial;}
    public void setWifiStatus(boolean status){this.wifiStatus = status;}

    public Wire.Envelope getWifiStatusEnvelope(){
        Wire.GetWifiStatusRequest.Builder getWifiStatus = Wire.GetWifiStatusRequest.newBuilder();
        Wire.GetWifiStatusRequest getWifiStatusRequest = getWifiStatus.build();

        Wire.Envelope.Builder envelopBuilder = Wire.Envelope.newBuilder();
        envelopBuilder.setType(Wire.MessageType.GET_WIFI_STATUS);
        envelopBuilder.setMessage(getWifiStatusRequest.toByteString());
        Wire.Envelope envelope = envelopBuilder.build();
        return envelope;
    }
}
