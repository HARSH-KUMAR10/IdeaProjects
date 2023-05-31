package erp;

import erp.modules.Attendance;
import erp.modules.Leaves;

import java.util.Date;

public class Modules implements Attendance, Leaves
{
    /* Modules - Variables and Methods */
    /* Assigned Modules */

    String[] assignedModules = null;

    public void setAssignedModules(String[] assignedModules)
    {
        this.assignedModules = assignedModules;
    }

    public String[] getAssignedModules()
    {
        return assignedModules;
    }

    /* Attendance Module */
    Date attendanceDate = null;

    String inTime = null;

    String outTime = null;

    String totalTime = null;

    @Override
    public void setDate(Date date)
    {
        this.attendanceDate = date;
    }

    public void setInTime(String inTime)
    {
        this.inTime = inTime;
    }

    @Override
    public void setOutTime(String outTime)
    {
        this.outTime = outTime;
    }

    @Override
    public void setTotalTime()
    {
        this.totalTime = totalTime;
    }

    @Override
    public String getDateString()
    {
        return attendanceDate.toString();
    }

    @Override
    public String getInTime()
    {
        return inTime;
    }

    @Override
    public String getOutTime()
    {
        return outTime;
    }

    @Override
    public String getTotalTime()
    {
        return totalTime;
    }


    /* Leave Module */
    String fromDate = null;

    String toDate = null;

    String reason = null;

    boolean status = false;

    @Override
    public void setFromDate(String fromDate)
    {
        this.fromDate = fromDate;
    }

    @Override
    public void setToDate(String toDate)
    {
        this.toDate = toDate;
    }

    @Override
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    @Override
    public void setStatus(boolean status)
    {
        this.status = status;
    }

    @Override
    public String getFromDate()
    {
        return fromDate;
    }

    @Override
    public String getToDate()
    {
        return toDate;
    }

    @Override
    public boolean getStatus()
    {
        return status;
    }

    @Override
    public String getReason()
    {
        return reason;
    }
}
