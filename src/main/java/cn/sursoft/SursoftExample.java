package cn.sursoft;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.ArrayList;
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
    private static HashMap<String, String> devMap = null;
    private static ArrayList<SocketClient> scArrayList = new ArrayList<>();
    private static ArrayList<ServiceStfInstall> serviceStfInstalls = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        devMap = AdbCommand.getDevID();
        System.out.println(TAG+devMap.size());
        int i = 0;
        for(String key : devMap.keySet()) {
            System.out.println(key +" i= "+i+ " status " + devMap.get(key));
            SocketClient  sc = new SocketClient(key);
            ServiceStfInstall si = new ServiceStfInstall(key);
            scArrayList.add(i,sc);
            serviceStfInstalls.add(i,si);
            si.startStfServie();
            sc.socketConnect();
            i++;
        }

        SocketClient sc = scArrayList.get(0);
        //ServiceStfInstall stfInstall = serviceStfInstalls.get(0);
       // stfInstall.startStfServie();
        //Thread.sleep(1000*5);
       // sc.socketConnect();

        SocketClient sc1 = scArrayList.get(1);
       // ServiceStfInstall stfInstall1 = serviceStfInstalls.get(1);
       // stfInstall1.startStfServie();
        //Thread.sleep(1000*5);
       // sc1.socketConnect();

        //minitor batterr status
        BatteryStatus bs = sc.getBatteryStatus();
        Watcher<BatteryStatus> w1 = new Watcher<>(bs);
        w1.update(bs,null);
        //System.out.println(TAG+w1.getClassWatcher().getBatteryHealth());
        //minitor airplaneMode status
        AirplaneModeStatus airplaneModeStatus = sc.getAirplaneModeStatus();
        Watcher<AirplaneModeStatus> w2 = new Watcher<>(airplaneModeStatus);
        w2.update(airplaneModeStatus,null);
        //System.out.println(TAG+w2.getClassWatcher().getAirPlaneModeEnable());
        //minitor connectivity status
        ConnectivityStatus connectivityStatus = sc.getConnectivityStatus();
        Watcher<ConnectivityStatus> w3 = new Watcher<>(connectivityStatus);
        w3.update(connectivityStatus,null);
        //System.out.println(TAG+w3.getClassWatcher().getConnected());
        //minitor phoneState Status
        PhoneStateStatus phoneStateStatus = sc.getPhoneStateStatus();
        Watcher<PhoneStateStatus> w4 = new Watcher<>(phoneStateStatus);
        w4.update(phoneStateStatus,null);
        //System.out.println(TAG+w4.getClassWatcher().getPhoneState());
        //minitor sursoftToolSrr stauts
        SursoftToolSrrStatus sursoftToolSrrStatus = sc.getSursoftToolSrrStatus();
        Watcher<SursoftToolSrrStatus> w5 = new Watcher<>(sursoftToolSrrStatus);
        w5.update(sursoftToolSrrStatus,null);
        //System.out.println(TAG+w5.getClassWatcher().getSursoftToolSrrStatus());
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

        ExecTestFactory execTestFactory = new ExecTestFactory("gtguo", "stability",  new GregorianCalendar(), sc.getDevSerial());
        ExecTestCaseHandler ec = execTestFactory.getExecTestCaseHandler(TestTpyeEnum.SRR);
        ec.execTestCaseScript(sc.getDevSerial(),"",0);
        ec.getTestLog();
        ec.getTestReport();

        ExecTestFactory execTestFactory1 = new ExecTestFactory("gtguo", "stability",  new GregorianCalendar(), sc1.getDevSerial());
        ExecTestCaseHandler ec1 = execTestFactory1.getExecTestCaseHandler(TestTpyeEnum.STABILITY);
        ec.execTestCaseScript(sc.getDevSerial(),"",0);
        ec.getTestLog();
        ec.getTestReport();

    }
}
