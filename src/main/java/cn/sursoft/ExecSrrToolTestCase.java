package cn.sursoft;

import java.io.File;
import java.util.GregorianCalendar;

/**
 * Created by gtguo on 3/17/2017.
 */

public class ExecSrrToolTestCase extends ExecTestCase {

    public final static String TAG = "Sursoft ExecSrrToolTestCase ";
    public ExecSrrToolTestCase(String userId, String taskName, GregorianCalendar calendar, String serial){
        super(userId,taskName,calendar,serial);
    }

    @Override
    public void execTestCaseScript(String devSerial,String scriptPathName,int type){
        System.out.println(TAG +"execTestCaseScript");
        switch (type){
            case 0://filepath
                break;
            case 1://URl
                break;
            default:
                //nothing
        }
        setExecTestStatus("busy");
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
