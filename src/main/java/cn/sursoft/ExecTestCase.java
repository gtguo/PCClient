package cn.sursoft;

/**
 * Created by gtguo on 3/1/2017.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

public class ExecTestCase extends Observable{
    public final static String TAG = "Sursoft ExecTestCase ";
    private String devSerial ;
    private String scriptStatus ;

    public ExecTestCase(String devSerial){
        this.devSerial = devSerial;
    }

    public String getDevSerial(){
        return this.devSerial;
    }

    //init run env
    public void initTestScriptEnvironment(String devSerial){
        File file = new File("Config");
        FileOutputStream outputStream = null;
        FileWriter writer = null;
        Boolean status = false;
        if(!file.exists()){
            try {
                status = file.createNewFile();
                System.out.println(TAG +"create file:"+ status);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            writer = new FileWriter(file);
        }catch (IOException e){
            e.printStackTrace();
        }

        if (writer != null) {
            try {
                writer.write("serial:"+devSerial+"\r\n");
                writer.write("serial:"+devSerial+"\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //run script
    public void execTestCaseScript(String devSerial,String scriptPathName){
        BufferedReader inputStream = null;
        //PythonInterpreter.initialize(null,null,devSerial[]);
        //PythonInterpreter pythonInterpreter = new PythonInterpreter();
        //pythonInterpreter.exec(scriptPathName);
        //pythonInterpreter.
        try {
            Process process = Runtime.getRuntime().exec("Python "+devSerial+ scriptPathName);
            inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            readLine(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //general report
    public void generalTestReport(){
        //run script to general report
    }

    //minitor script runtime status
    public void setScriptStatus(String status){
        if(!status.equals(getScriptStatus())){
            this.scriptStatus = status;
            setChanged();
            notifyObservers();
        }
        this.scriptStatus = status;
    }

    public String getScriptStatus(){
        return this.scriptStatus;
    }

    //handle error event
    public void errEventHandler(){

    }

    private void readLine(final BufferedReader br){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String line = null;
                    /**********
                    try{
                     while(){
                     }

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                     *******/
                }
            }).start();
        }

}
