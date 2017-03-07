package cn.sursoft;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by gtguo on 2/17/2017.
 */

public class DevProperty {
    private ArrayList<String> properties = new ArrayList<>();
    private String devSerial = null;
    private Socket socket;
    private HashMap<String,String> devPropertiesHashMap = new HashMap<>();

    public DevProperty(String devSerial,Socket socket){
        this.devSerial = devSerial;
        this.socket = socket;
        properties.add("imei");
        properties.add("imsi");
        properties.add("phoneNumber");
        properties.add("iccid");
        properties.add("operator");
        properties.add("network");
    }

    public ArrayList<String> getDevProperties(){
        return this.properties;
    }

    public String getSerial(){
        return this.devSerial;
    }

    public HashMap getDevPropertiesHashMap(){
        return this.devPropertiesHashMap;
    }
/***********
    public void setDevPropertiesHashMap(HashMap<String,String> hs){
        this.devPropertiesHashMap = hs;
    }
 *********/
    public Wire.Envelope getPropertiesEnvelope(){
        Wire.GetPropertiesRequest.Builder getPropertiesBuilder= Wire.GetPropertiesRequest.newBuilder();
        Iterator<String> iterator = getDevProperties().iterator();
        if(iterator.hasNext()){
            getPropertiesBuilder.addProperties(iterator.next());
        }
        Wire.GetPropertiesRequest request = getPropertiesBuilder.build();

        Wire.Envelope.Builder envelopBuilder1 = Wire.Envelope.newBuilder();
        envelopBuilder1.setType(Wire.MessageType.GET_PROPERTIES);
        envelopBuilder1.setMessage(request.toByteString());
        Wire.Envelope envelope1 = envelopBuilder1.build();
        return envelope1;
    }
}
