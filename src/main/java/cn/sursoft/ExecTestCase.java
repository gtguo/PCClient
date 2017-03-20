package cn.sursoft;

/**
 * Created by gtguo on 3/17/2017.
 */
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Observable;

public abstract class ExecTestCase extends Observable implements ExecTestCaseHandler {
    public final static String TAG = "ExecTestCase " ;
    private String userId;
    private String taskName;
    private GregorianCalendar calendar;
    private String scriptStatus;
    private String serial;

    public ExecTestCase(String userId,String taskName,GregorianCalendar calendar,String serial){
        this.userId = userId;
        this.taskName = taskName;
        this.calendar = calendar;
        this.serial = serial;
    }
    @Override
    public void setExecTestStatus(String status){
        if(!status.equals(getScriptStatus())){
            this.scriptStatus = status;
            setChanged();
            notifyObservers();
        }
        this.scriptStatus = status;
    }

    @Override
    public void generalTestReportLogFile(){
        File fileReport = null;
        File filelog = null;
        String osName = System.getProperties().getProperty("os.name");
        if(osName.indexOf("Windows") >=0){
            //windows System
            fileReport = new File("d:\\Report\\"+getUserId()+"\\"+getTaskName()
                    +"\\"+calendar.get(GregorianCalendar.YEAR)
                    +calendar.get(GregorianCalendar.MONTH)
                    +calendar.get(GregorianCalendar.DAY_OF_MONTH)
                    +calendar.get(GregorianCalendar.HOUR)
                    +calendar.get(GregorianCalendar.MINUTE));
            fileReport.mkdirs();

            filelog = new File("d:\\Log\\"+getUserId()+"\\"+getTaskName()
                    +"\\"+calendar.get(GregorianCalendar.YEAR)
                    +calendar.get(GregorianCalendar.MONTH)
                    +calendar.get(GregorianCalendar.DAY_OF_MONTH)
                    +calendar.get(GregorianCalendar.HOUR)
                    +calendar.get(GregorianCalendar.MINUTE));
            filelog.mkdirs();

            /**********
            System.out.println(TAG+file.getAbsolutePath());
            File f = new File(file.getAbsolutePath()+"\\"+"report.txt");
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
             ****************/
        }else{
            //unbantu System
            //System.out.println(TAG + filePath);
        }
    }

    public String getUserId(){return this.userId;}
    public String getTaskName(){return this.taskName;}
    public GregorianCalendar getGregorianCalendar(){return this.calendar;}
    public String getScriptStatus(){return this.scriptStatus;}
    public void setSerial(String devSerial){this.serial = devSerial;}
}
