import org.zeromq.ZMQ;

public class ZMQVersionExample
{
    public static void main(String[] args)
    {
        String version = ZMQ.getVersionString();
        int fullVersion = ZMQ.getFullVersion();

        System.out.println(
                String.format(
                        "Version string: %s, Version int: %d", version, fullVersion
                )
        );
    }
}
