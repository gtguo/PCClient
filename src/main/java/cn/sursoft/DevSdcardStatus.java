package cn.sursoft;

import java.net.Socket;

/**
 * Created by gtguo on 2/18/2017.
 */

public class DevSdcardStatus {
    public static final String TAG = "Sursoft DevSdcardStatus ";
    private String devSerial = null;
    private Socket socket;
    private boolean mounted = false;

    public DevSdcardStatus(String devSerial,Socket socket){
        this.devSerial = devSerial;
        this.socket = socket;
    }

    public boolean getSdcardStatus(){
        return this.mounted;
    }
    public String getSerial(){return this.devSerial;}
    public void setSdcardStatus(boolean mounted){this.mounted = mounted;}

    public Wire.Envelope getSdcardStatusEnvelope(){
        Wire.GetSdStatusRequest.Builder getSdcardStatus = Wire.GetSdStatusRequest.newBuilder();
        Wire.GetSdStatusRequest getSdStatusRequest = getSdcardStatus.build();

        Wire.Envelope.Builder envelopBuilder = Wire.Envelope.newBuilder();
        envelopBuilder.setType(Wire.MessageType.GET_SD_STATUS);
        envelopBuilder.setMessage(getSdStatusRequest.toByteString());
        Wire.Envelope envelope = envelopBuilder.build();
        return envelope;
    }
}
