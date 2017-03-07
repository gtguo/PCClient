package cn.sursoft;


/**
 * Created by gtguo on 3/1/2017.
 */
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;

public class GetTestReportAndLog {
    public final static String TAG ="GetTestReportAndLog ";
    private String userId;
    private String taskName;
    private GregorianCalendar calendar;

    public GetTestReportAndLog(){}
    public GetTestReportAndLog(String userId,String taskName,GregorianCalendar calendar){
        this.userId = userId;
        this.taskName = taskName;
        this.calendar = calendar;
    }

    public File getTestReport(){
        return new File("");
    }

    public File getTestLog(){
        return new File("");
    }

    public void createTestReport(){
        File file = null;
        String osName = System.getProperties().getProperty("os.name");
        if(osName.indexOf("Windows") >=0){
            //windows System
            file = new File("d:\\Report\\"+getUserId()+"\\"+getTaskName()
                    +"\\"+calendar.get(GregorianCalendar.YEAR)
                    +calendar.get(GregorianCalendar.MONTH)
                    +calendar.get(GregorianCalendar.DAY_OF_MONTH)
                    +calendar.get(GregorianCalendar.HOUR)
                    +calendar.get(GregorianCalendar.MINUTE));
            file.mkdirs();
            System.out.println(TAG+file.getAbsolutePath());
            File f = new File(file.getAbsolutePath()+"\\"+"report.txt");
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //filePath = f.toString()+"../../../apk/";
            //System.out.println(TAG + filePath);
        }
    }

    public void createTestLog(){
        File file = new File("Log");
    }
    public String getUserId(){return this.userId;}
    public String getTaskName(){return this.taskName;}
    public GregorianCalendar getGregorianCalendar(){return this.calendar;}
}
