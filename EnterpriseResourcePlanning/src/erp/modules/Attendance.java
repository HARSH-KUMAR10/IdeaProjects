package erp.modules;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public interface Attendance
{
    void setDate(Date date);

    void setInTime(String s);

    void setOutTime(String s);

    void setTotalTime();

    String getDateString();

    String getInTime();

    String getOutTime();

    String getTotalTime();

    default void saveToFile() throws IOException
    {
        FileWriter fw = new FileWriter("attendance.txt", true);
        fw.write("{date:" + getDateString() + ", inTime:" + getInTime() + ", outTime:" + getOutTime() + ", totalTime:" + getTotalTime() + "}");
        fw.close();
    }
}
