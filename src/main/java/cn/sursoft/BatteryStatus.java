package cn.sursoft;

import java.util.HashMap;
import java.util.Observable;

/**
 * Created by gtguo on 2/15/2017.
 */
class Battery extends Observable{
    private final String TAG =" Sursoft Battery ";
    private String status ;
    private String health ;
    private String source ;
    private int level ;
    private int scale ;
    private double temperature ;
    private double voltage ;
    private String serial;

    public Battery(String devSerial){
        System.out.println(TAG+devSerial+" init Battery Status");
        this.serial = devSerial;
        HashMap<String,String> batteryStatus = new AdbCommand().getBatteryStatus(devSerial);
        if(batteryStatus == null){
            System.out.println(TAG+devSerial+" initBatteryStatus failed! ");
            return;
        }
        setStatus(batteryStatus.get("status"));
        setHealth(batteryStatus.get("health"));
        //System.out.println(batteryStatus.get("status"));
        if(batteryStatus.get("USBpowered").equals("true")){
            setSource("USB");
        }else if(batteryStatus.get("ACpowered").equals("true")){
            setSource("AC");
        }
        int levelTmp = Integer.valueOf(batteryStatus.get("level"));
        setLevel(levelTmp);
        int scaleTmp = Integer.valueOf(batteryStatus.get("scale"));
        setScale(scaleTmp);
        double Temp = Double.valueOf(batteryStatus.get("temperature"));
        setTemperature(Temp/10.0);
        double Voltage = Double.valueOf(batteryStatus.get("voltage"));
        setVoltage(Voltage/1000.0);

    }

    public String getBatteryStatus(){
        return this.status;
    }
    public void setStatus(String status){this.status = status;}

    public String getBatteryHealth(){
        return this.health;
    }
    public void setHealth(String health){this.health = health;}

    public String getBatterySource(){
        return this.source;
    }
    public void setSource(String source){this.source = source;}

    public int getBatteryLevel(){
        return this.level;
    }
    public void setLevel(int level){this.level = level;}

    public int getBatteryScale(){
        return this.scale;
    }
    public void setScale(int scale){this.scale = scale;}

    public double getBatteryTemperature(){
        return this.temperature;
    }
    public void setTemperature(double temperature){this.temperature = temperature;}

    public double getBatteryVoltage(){
        return this.voltage;
    }
    public void setVoltage(double voltage){this.voltage = voltage;}

    public void setBatteryField(String status,String health,String sourc,
                                int level,int scale,double temperature,double voltage){
        boolean changedFlag = false;
        if(level != this.level
                || temperature != this.temperature
                || voltage != this.voltage
                || scale != this.scale
                || !status.equals(this.status)
                || !health.equals(this.health)
                || !sourc.equals(this.source)){
            changedFlag = true;
            System.out.println(TAG+serial+" Battery Status has changed! ");
        }
        setStatus(status);
        setHealth(health);
        setSource(sourc);
        setLevel(level);
        setScale(scale);
        setTemperature(temperature);
        setVoltage(voltage);
        if(changedFlag){
            batteryChangedNotifyObservers();
        }
    }

    private void batteryChangedNotifyObservers(){
        setChanged();
        notifyObservers();
    }
}

public class BatteryStatus extends Battery{
    private final String TAG =" Sursoft BatteryStatus ";
    private String devSerial;
    //Construct
    public BatteryStatus(String devSerial){
        super(devSerial);
        this.devSerial = devSerial;
    }

    public  String getSerial(){return this.devSerial;}
    public void setDevSerial(String devSerial){this.devSerial = devSerial;}

}
