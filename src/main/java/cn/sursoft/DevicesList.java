package cn.sursoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by gtguo on 2/23/2017.
 */

public class DevicesList extends Observable{
    private final static String TAG = "DevicesList ";
    private HashMap<String, String> devMap = new HashMap<>();
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

    public HashMap getDevListID(){
        //getDevicesList();
        if(!devMap.isEmpty()){
            return devMap;
        }else{
            System.out.println(TAG+"There is no device connected USB. Please check it!");
            return null;
        }
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
}
