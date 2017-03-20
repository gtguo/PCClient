package cn.sursoft;

import java.io.File;

/**
 * Created by gtguo on 3/17/2017.
 */

public interface ExecTestCaseHandler {
    public void execTestCaseScript(String devSerial,String scriptPathName);
    public void generalTestReportLogFile();
    public void setExecTestStatus(String status);
    public void createTestReport();
    public void createTestLog();
    public File getTestReport();
    public File getTestLog();
}
