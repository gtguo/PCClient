package cn.sursoft;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

/**
 * Created by gtguo on 2/22/2017.
 */

public class SursoftExample {
    private final static String TAG =" SursoftExample ";
    public static void main(String[] args) throws InterruptedException {
        SocketClient sc = new SocketClient("0123456789ABCDEF");
        ServiceStfInstall stfInstall = new ServiceStfInstall("0123456789ABCDEF");
        stfInstall.startStfServie();
        //Thread.sleep(1000*5);
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
        DevInformationAssembly devInformationAssembly = sc.getDevInformationAssembly();
        System.out.println(TAG + "DevDoIdentify "+ devInformationAssembly.getDevDoIdentify().getDevIdentify());
        System.out.println(TAG + "Product Model: "+ devInformationAssembly.getDevDoIdentify().getProductModel());
        //get dev Property imei,imsi,phonenumber,etc.
        //DevProperty devProperty = sc.getDevProperty();
        HashMap<String,String> hs = devInformationAssembly.getDevProperty().getDevPropertiesHashMap();
        for(String key : hs.keySet()){
            System.out.println(TAG + "DevProperty "+key + ":" + hs.get(key));
        }
        //get Android version
        //DevVision devVision = sc.getDevVision();
        System.out.println(TAG+"dev vision:"+devInformationAssembly.getDevVision().getDevVision());
        //get display info
        //DevDisplay devDisplay = sc.getDevDisplay();
        System.out.println(TAG + "getDevDisplay "+"Density:"+devInformationAssembly.getDevDisplay().getScreenDensity()
                +" Rotation:"+devInformationAssembly.getDevDisplay().getScreenRotation()
                +" Fps:"+devInformationAssembly.getDevDisplay().getScreenFps()
                +" Height:"+devInformationAssembly.getDevDisplay().getScreenHeight()
                +" Width:"+devInformationAssembly.getDevDisplay().getScreenWidth()
                +" Xdpi:"+devInformationAssembly.getDevDisplay().getScreenXdpi()
                +" Ydpi:"+devInformationAssembly.getDevDisplay().getScreenYdpi());
        //get wifi status:open or closed
        //DevWifiStatus devWifiStatus = sc.getDevWifiStatus();
        System.out.println(TAG+"WIFI status:"+devInformationAssembly.getDevWifiStatus().getWifiStatus());
        //get sdcard status:mounted or not
        //DevSdcardStatus devSdcardStatus = sc.getSdcardStatus();
        System.out.println(TAG+"sdcard status:"+devInformationAssembly.getDevSdcardStatus().getSdcardStatus());

        GetTestReportAndLog getTestReportAndLog = new GetTestReportAndLog("gtguo","Stability",new GregorianCalendar());
        getTestReportAndLog.createTestReport();

        ExecTestCase execTestCase = new ExecTestCase(sc.getDevSerial());
        execTestCase.initTestScriptEnvironment(sc.getDevSerial());

    }
}
