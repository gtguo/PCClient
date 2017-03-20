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
import java.util.GregorianCalendar;
import java.util.Observable;

public class ExecStabilityTestCase extends ExecTestCase{
    public final static String TAG = "Sursoft ExecStabilityTestCase ";

    public ExecStabilityTestCase(String userId, String taskName, GregorianCalendar calendar, String serial){
        super(userId,taskName,calendar,serial);
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
    @Override
    public void execTestCaseScript(String devSerial,String scriptPathName,int type){
        //BufferedReader inputStream = null;
        //PythonInterpreter.initialize(null,null,devSerial[]);
        //PythonInterpreter pythonInterpreter = new PythonInterpreter();
        //pythonInterpreter.exec(scriptPathName);
        //pythonInterpreter.
       // try {
        //    Process process = Runtime.getRuntime().exec("Python "+devSerial+ scriptPathName);
       //     inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //    readLine(inputStream);
       // }catch (IOException e){
       //     e.printStackTrace();
       // }
        switch (type){
            case 0://filepath
                break;
            case 1://URl
                break;
            default:
                //nothing
        }
        System.out.println(TAG +"execTestCaseScript");
    }

    //handle error event
    public void errEventHandler(){
        System.out.println(TAG +"errEventHandler");
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




    @Override
    public void createTestReport(){
        System.out.println(TAG +"createTestReport");
    }

    @Override
    public void createTestLog(){
        System.out.println(TAG +"createTestLog");
    }

    @Override
    public File getTestReport(){
        System.out.println(TAG +"getTestReport");
        return new File("");
    }

    @Override
    public File getTestLog(){
        System.out.println(TAG +"getTestLog");
        return new File("");
    }
}
