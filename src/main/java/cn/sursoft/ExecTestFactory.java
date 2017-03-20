package cn.sursoft;

import java.util.GregorianCalendar;

/**
 * Created by gtguo on 3/17/2017.
 */


public class ExecTestFactory {
    private ExecTestCaseHandler execTestCaseHandler;
    private String userId;
    private String taskName;
    private GregorianCalendar calendar;
    private String serial;
    public ExecTestFactory(String userId, String taskName, GregorianCalendar calendar, String serial){
        this.userId = userId;
        this.taskName = taskName;
        this.calendar = calendar;
        this.serial = serial;
    }

    public ExecTestCaseHandler getExecTestCaseHandler(TestTpyeEnum testTpyeEnum){
        switch (testTpyeEnum){
            case SRR:
                return new ExecSrrToolTestCase(userId, taskName, calendar, serial);
            case STABILITY:
                return new ExecStabilityTestCase(userId, taskName, calendar, serial);
            default:
                return null;
        }
    }
}
