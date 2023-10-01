import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.apache.kafka.common.utils.Time;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class LearnJava {

    public static void main(String[] args) {

        Timestamp t1 = new Timestamp(Time.SYSTEM.milliseconds());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Timestamp t2 = new Timestamp(Time.SYSTEM.milliseconds());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Timestamp t3 = new Timestamp(Time.SYSTEM.milliseconds());

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);

        SortedMap<Timestamp, String> tm = new TreeMap<>();
        tm.put(t2, "two");
        tm.put(t1, "one");
        tm.put(t3, "three");
        System.out.println(tm.firstKey());
        System.out.println(tm.lastKey());

    }
}
