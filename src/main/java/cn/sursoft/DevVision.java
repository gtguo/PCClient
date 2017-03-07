package cn.sursoft;

import java.net.Socket;

/**
 * Created by gtguo on 2/18/2017.
 */

public class DevVision {
    public static final String TAG = "Sursoft DevVision ";
    private String devSerial = null;
    private Socket socket;
    private String devVision;

    public DevVision(String devSerial,Socket socket){
        this.devSerial = devSerial;
        this.socket = socket;
    }

    public String getSerial(){
        return this.devSerial;
    }
    public String getDevVision(){return this.devVision;}
    public void setDevSerial(String devVision){this.devVision = devVision;}

    public Wire.Envelope getDevVisionEnvelope(){
        Wire.GetVersionRequest.Builder getVersion = Wire.GetVersionRequest.newBuilder();
        Wire.GetVersionRequest getVersionRequest = getVersion.build();

        Wire.Envelope.Builder envelopBuilder = Wire.Envelope.newBuilder();
        envelopBuilder.setType(Wire.MessageType.GET_VERSION);
        envelopBuilder.setMessage(getVersionRequest.toByteString());
        Wire.Envelope envelope = envelopBuilder.build();
        return envelope;
    }
}
