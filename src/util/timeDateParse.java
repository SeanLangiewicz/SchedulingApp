package util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Methods within this class are used to get date, get time, get timestamp and to format the local date times
 */
public class timeDateParse {

    /**
     * Method takes in a timestamp as a string and returns a formatted date.
     * @param timestamp takes a time stamp as a string and parses the date and formats it to a specific pattern.
     * @return Returns Local Date as a string in a specific format.
     */
    public static LocalDate getDate(String timestamp)
    {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        String ts = String.format(timestamp,df );


        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(ts.substring(0, 10), dFormatter);

        return localDate;
    }

    /**
     * Method takes in a time stamp as a string and returns a local time.
     * @param timestamp Takes a timestamp as a string to be used to parse a local time
     * @return Returns time from the time stamp parameter.
     */
    public static LocalTime getTime (String timestamp)
    {


        LocalTime localTime = null;


        DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("kk:mm:ss");
         localTime = LocalTime.parse(timestamp.substring(11), tFormatter);

      return localTime;
    }

    /**
     * Method takes a local date time and returns it as a time stamp.
     * @param startTime Local Date Time parameter used to get a time stamp.
     * @return Returns a timestamp
     */
    public static Timestamp getTimeStamp (LocalDateTime startTime)
    {


        Timestamp startsqllts = Timestamp.valueOf(startTime);
        LocalDateTime ldt = startsqllts.toLocalDateTime();

        return startsqllts;
    }

    /**
     * Method takes a local time time and formats it as a local string
     * @param ldt Parameter to be formatted as a string.
     * @return Returns a formatted string.
     */
    public static String ldtFormat (LocalDateTime ldt)
    {
        String sLDT = null;

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");


        sLDT = ldt.format(df);
        return  sLDT;
    }

    /**
     * Method takes in a string and returns it as a local date time.
     * @param sLDT Parameter used to get a local date time from a string.
     * @return Returns a formatted local date time.
     */
    public static LocalDateTime stringToLD (String sLDT)
    {
        LocalDateTime returnedLDT = null;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");

        returnedLDT = LocalDateTime.parse(sLDT,df);

        return returnedLDT;
    }


}
