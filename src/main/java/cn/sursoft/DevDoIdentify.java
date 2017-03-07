package cn.sursoft;

import java.net.Socket;


/**
 * Created by gtguo on 2/18/2017.
 */

public class DevDoIdentify {
    private String devSerial = null;
    private Socket socket;
    private boolean devIdentify = false;
    private String productModel = null;

    public DevDoIdentify(String devSerial,Socket socket){
        this.devSerial = devSerial;
        this.socket = socket;
    }

    public String getProductModel(){return this.productModel;}
    public void setProductModel(String model){this.productModel=model;}
    public String getSerial(){
        return this.devSerial;
    }

    public boolean getDevIdentify(){
        return this.devIdentify;
    }
    public void setDevIdentify(boolean b){
        System.out.println("Dev:"+getSerial()+" connect identify Success:"+b);
        this.devIdentify = b;
    }

    public Wire.Envelope getDoIdentifyEnvelope(){
        Wire.DoIdentifyRequest.Builder identifyBuilder = Wire.DoIdentifyRequest.newBuilder();
        identifyBuilder.setSerial(getSerial());
        Wire.DoIdentifyRequest doIdentifyRequest = identifyBuilder.build();

        Wire.Envelope.Builder envelopBuilder = Wire.Envelope.newBuilder();
        envelopBuilder.setType(Wire.MessageType.DO_IDENTIFY);
        envelopBuilder.setMessage(doIdentifyRequest.toByteString());
        Wire.Envelope envelope = envelopBuilder.build();
        return envelope;
    }
}
