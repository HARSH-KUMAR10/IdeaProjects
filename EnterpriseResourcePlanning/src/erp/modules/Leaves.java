package erp.modules;

public interface Leaves
{
    void setFromDate(String from);
    void setToDate(String to);
    void setReason(String reason);
    void setStatus(boolean status);
    String getFromDate();
    String getToDate();
    String getReason();
    boolean getStatus();
}
