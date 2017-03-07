package cn.sursoft;
/**
 * Created by gtguo on 2/14/2017.
 */
import java.io.File;

import cn.sursoft.AdbCommand;

public class ServiceStfInstall{
    private static final String TAG = "Sursoft ServiceStfInstall ";
    private static final String cmdStartPgk = "am startservice --user 0 -a jp.co.cyberagent.stf.ACTION_START -n jp.co.cyberagent.stf/.Service";
    private static final String pgk = "jp.co.cyberagent.stf";
    private static final String main = "jp.co.cyberagent.stf.Agent";
    private static final String apkName = "STFService.apk";
    private static final String action = "jp.co.cyberagent.stf.ACTION_START";
    private static final String componet = "jp.co.cyberagent.stf/.Service";
    private static final String installPath = "/data/local/tmp/";

    private String serial;
    private AdbCommand adbCmd = new AdbCommand();

    public ServiceStfInstall(){}
    public ServiceStfInstall(String serial){
        this.serial = serial;
    }

    public String getDevSerialId(){
        return this.serial;
    }

    private void installStfServie(){
        //install stfservice.apk
        if(serial == null){
            System.out.println(TAG+"warning: install maybe failed when serial null ! ");
        }
        File f = new File(this.getClass().getResource("/").getPath());
        String filePath = null;
        String osName = System.getProperties().getProperty("os.name");
        if(osName.indexOf("Windows") >=0){
            //windows System
            filePath = f.getAbsolutePath()+"\\..\\..\\..\\apk\\";
            System.out.println(TAG + filePath);
            //adbCmd.execInstallApk(this.getSerial(),filePath+"..\\..\\..\\apk\\");
        }else{
            filePath = f.toString()+"../../../apk/";
            System.out.println(TAG + filePath);
        }
        adbCmd.execInstallApk(this.getDevSerialId(),filePath+apkName);
    }

    public void startStfServie(){
        //installStfServie();
        //run the stfservice
        /***********
        try{
            Thread.sleep(1000*10);//sleep 10s
        }catch (InterruptedException e){
            e.printStackTrace();
        }
         ********/
        if(serial == null){
            System.out.println(TAG+"warning: start service maybe failed when serial null ! ");
        }
        adbCmd.execShellCommand(this.getDevSerialId(),cmdStartPgk);
        //forwardAdbSocket(1100);
    }

    public void startStfAgent(){

    }

}

