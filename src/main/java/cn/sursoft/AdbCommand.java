package cn.sursoft;

/**
 * Created by gtguo on 2/13/2017.
 */
//import java.io.BufferedInputStream;
import java.io.BufferedReader;
//import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
//import java.util.Iterator;

public class AdbCommand {
    private static final String TAG = "Sursoft AdbCommand ";
    private HashMap<String, String> devMap = new HashMap<>();
    private HashMap<String, String> batteryStatusMap = new HashMap<>();
   // private BatteryStatus bs = new BatteryStatus();

    private void getDevicesList(){
        BufferedReader inputStream = null;
        try{
            Process process = Runtime.getRuntime().exec("adb devices");
            inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            readLine(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void getDeviceBatteryStatus(String serial){
        BufferedReader inputStream = null;
        Process process = null;
        try{
            if(serial != null){
                process = Runtime.getRuntime().exec("adb -s " + serial +" shell dumpsys battery");
            }else{
                process = Runtime.getRuntime().exec("adb shell dumpsys battery");
            }
            inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            readBatteryStatusLine(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public HashMap getDevID(){
        getDevicesList();
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if(!devMap.isEmpty()){
            return devMap;
        }else{
            System.out.println(TAG+"There is no device connected USB. Please check it!");
            return null;
        }
    }

    public HashMap getBatteryStatus(String devSerial){
        getDeviceBatteryStatus(devSerial);
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if(!batteryStatusMap.isEmpty()){
            return batteryStatusMap;
        }else{
            System.out.println(TAG+"Get battery status err!");
            return null;
        }
    }

    public void execReboot(String serial){
        try{
            if(serial != null){
                Runtime.getRuntime().exec("adb -s " + serial +" reboot");
            }else{
                Runtime.getRuntime().exec("adb reboot");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void execInstallApk(String serial, String apkName){
        try{
            System.out.println(TAG+"adb -s "+ serial + " install " + apkName);
            if(serial != null){
                Runtime.getRuntime().exec("adb -s "+ serial + " install " + apkName);
            }else {
                Runtime.getRuntime().exec("adb install " + apkName);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void execUninstallApk(String serial, String pkgName){
        try{
            System.out.println(TAG+"adb -s "+ serial + " uninstall " + pkgName);
            if(serial != null){
                Runtime.getRuntime().exec("adb -s "+ serial + " uninstall " + pkgName);
            }else{
                Runtime.getRuntime().exec("adb uninstall " + pkgName);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void execShellCommand(String serial,String cmd){
        try{
            System.out.println(TAG+"adb -s "+ serial + " shell " + cmd);
            if(serial != null){
                Runtime.getRuntime().exec("adb -s "+ serial + " shell " + cmd);
            }else{
                Runtime.getRuntime().exec("adb shell " + cmd);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void execPullCommand(String serial,String source,String dest){
        try{
            System.out.println(TAG+"adb -s "+ serial + " pull " + source+" "+dest);
            if(serial != null){
                Runtime.getRuntime().exec("adb -s "+ serial + " pull " + source+" "+dest);
            }else{
                Runtime.getRuntime().exec("adb pull " + source+" "+dest);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void execAdbForward(String serial,int localTcpPort, String remote){
        try{
            System.out.println(TAG+"adb -s "+ serial + " forward tcp:"+ localTcpPort +" "+ remote);
            if(serial != null){
                Runtime.getRuntime().exec("adb -s "+ serial + " forward tcp:"+ localTcpPort +" "+ remote);
            }else{
                Runtime.getRuntime().exec("adb forward tcp:"+ localTcpPort +" "+ remote);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void readInstallOrUninstallApkLine(final BufferedReader br){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String line = null;
                try{
                    while ((line = br.readLine()) != null) {
                        //remove strDel
                        if (line.equals("Success")) {
                            return;
                        }else{
                            continue;
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void readBatteryStatusLine(final BufferedReader br){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String line = null;
                //StringBuffer str = new StringBuffer();
                try{
                    //get adb devices
                    String strDel = "Current Battery Service state:";
                    while ((line = br.readLine()) != null) {
                        //remove strDel
                        if (!line.equals(strDel)) {
                            String[] strs = line.split(":\\s");
                            if (strs.length != 2) {
                                continue;
                            } else {
                               // System.out.println("batterystatus:"+strs[0].toString()+": "+strs[1].toString());
                                batteryStatusMap.put(strs[0].replace(" ","").toString(), strs[1].toString());
                            }
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void readLine(final BufferedReader br){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String line = null;
                //StringBuffer str = new StringBuffer();
                try{
                    //get adb devices
                    {
                        String strDel = "List of devices attached";
                        while ((line = br.readLine()) != null) {
                            //remove strDel
                            if (!line.equals(strDel)) {
                                String[] strs = line.split("\\s");
                                if (strs.length != 2) {
                                    System.out.println(TAG + "adb devices info was invilid! ");
                                    continue;
                                } else {
                                    devMap.put(strs[0].toString(), strs[1].toString());
                                }
                            }
                        }
                    }
/***
 String[] devID = devMap.keySet().toArray(new String[0]);
 //debug info,need to remove on release
 for(String key : devMap.keySet()){
 System.out.println(key + " status " + devMap.get(key));
 }
 System.out.println(devID.length);
 for(int i =0;i< devID.length;i++ ){
 System.out.println(" test " + devID[i]);
 }
 ***/
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //public static void main(String[] args){
    //    AdbCommand adbTest = new AdbCommand();
        //System.out.println(TAG+ adbTest.getDevID().toString());
     //   adbTest.getDeviceBatteryStatus(null);
    //}
}


