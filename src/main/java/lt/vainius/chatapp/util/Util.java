package lt.vainius.chatapp.util;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class Util {
    public Timestamp subtractOneHourFromCurrentTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long subtracted = timestamp.getTime() - 60 * 60 * 1000;
        return new Timestamp(subtracted);
    }
}
