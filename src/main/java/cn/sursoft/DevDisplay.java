package cn.sursoft;

import java.net.Socket;

/**
 * Created by gtguo on 2/18/2017.
 */
class Screen{
    private int width;
    private int height;
    private float xdpi;
    private float ydpi ;
    private float fps ;
    private float density ;
    private int rotation ;

    //SET
    public void setScreenWidth(int w){this.width = w;}
    public void setScreenHeight(int h){this.height = h;}
    public void setScreenXdpi(float xdpi){this.xdpi = xdpi;}
    public void setScreenYdpi(float ydpi){this.ydpi = ydpi;}
    public void setScreenDensity(float density){this.density = density;}
    public void setScreenRotation(int ro){this.rotation = ro;}
    public void setScreenFps(float fps){this.fps = fps;}
    //GET
    public int getScreenWidth(){return this.width;}
    public int getScreenHeight(){return this.height;}
    public float getScreenXdpi(){return this.xdpi ;}
    public float getScreenYdpi(){return this.ydpi;}
    public float getScreenDensity(){return this.density;}
    public int getScreenRotation(){return this.rotation ;}
    public float getScreenFps(){return this.fps ;}
}
public class DevDisplay extends Screen{
    public static final String TAG = "Sursoft DevDisplay ";
    private String devSerial = null;
    private Socket socket;

    public DevDisplay(String devSerial,Socket socket){
        this.devSerial = devSerial;
        this.socket = socket;
    }

    public String getSerial(){
        return this.devSerial;
    }

    public Wire.Envelope getDisplayRequestEnvelope(){
        Wire.GetDisplayRequest.Builder getDisplayBuilder = Wire.GetDisplayRequest.newBuilder();
        getDisplayBuilder.setId(0);
        Wire.GetDisplayRequest getDisplayRequest = getDisplayBuilder.build();

        Wire.Envelope.Builder envelopBuilder = Wire.Envelope.newBuilder();
        envelopBuilder.setType(Wire.MessageType.GET_DISPLAY);
        envelopBuilder.setMessage(getDisplayRequest.toByteString());
        Wire.Envelope envelope = envelopBuilder.build();
        return envelope;
    }
}
