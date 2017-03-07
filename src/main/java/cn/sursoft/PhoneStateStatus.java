package cn.sursoft;

import java.util.Observable;

/**
 * Created by gtguo on 2/16/2017.
 */


public class PhoneStateStatus extends Observable{
    private String state;
    private boolean manual;
    private String operator;
    private String serial=null;

    public PhoneStateStatus(String devSerial){this.serial = devSerial;}
    public PhoneStateStatus(String devSerial,String state,boolean manual,String operator){
        this.serial = devSerial;
        this.state = state;
        this.manual = manual;
        this.operator = operator;
    }
    public String getPhoneState(){
        return this.state;
    }
    public void setState(String state){this.state = state;}

    public boolean getPhoneManual(){
        return this.manual;
    }
    public void setManual(boolean manual){this.manual = manual;}

    public String getPhoneOperator(){
        return this.operator;
    }
    public void setOperator(String operator){this.operator = operator;}

    public void setPhoneStateStatusField(String state,boolean manual, String operator){
        boolean changedFlag = false;
        if(manual != this.manual
                || !state.equals(this.state)
                || !operator.equals(this.operator)){
            changedFlag = true;
            System.out.println("PhoneStateStatus Status has changed! ");
        }
        setState(state);
        setManual(manual);
        setOperator(operator);
        if(changedFlag == true){
            setChanged();
            notifyObservers();
        }
    }


    public String getSerial(){return this.serial;}
}
