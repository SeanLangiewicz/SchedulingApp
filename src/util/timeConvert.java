package util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


/**
 * Methods used to convert times.
 */
public class timeConvert
{

    /**
     * Method used to convert provided local date time to UTC.
     * @param ts Local Date Time
     * @return Returns a Local Date Time in UTC time to be inserted into SQl.
     */
    public static LocalDateTime convertToUTC (LocalDateTime ts)
    {


       LocalDateTime returnedTime = null;

       ZoneId localZID = ZoneId.systemDefault();
       ZoneId utcZID = ZoneId.of("UTC");

       ZonedDateTime zDTStart = ts.atZone(localZID);

       ZonedDateTime utcZDT = zDTStart.withZoneSameInstant(utcZID);
       returnedTime = utcZDT.toLocalDateTime();



       return  returnedTime;

    }

    /**
     * Method used to convert provided Local Date Time to the local date time.
     * @param ts Local Date Time used to be converted.
     * @return Returns the converted local date time.
     */
    public static LocalDateTime convertToLocal (LocalDateTime ts)
    {


        LocalDateTime resultTime = null;

   //  ZoneId estZID = ZoneId.of("America/New_York");
        ZoneId utcZID = ZoneId.of("UTC");
     ZoneId localZID = ZoneId.systemDefault();

     ZonedDateTime utcZDT = ts.atZone(utcZID);
     ZonedDateTime localZDT = utcZDT.withZoneSameInstant(localZID);


        resultTime = localZDT.toLocalDateTime();



        return resultTime;
    }


    public static LocalDateTime estToLocal(LocalDateTime ts)
    {
        LocalDateTime resultTime = null;

        ZoneId estZID = ZoneId.of("America/New_York");
        ZoneId localZID = ZoneId.systemDefault();

        ZonedDateTime estZDT = ts.atZone(estZID);
        ZonedDateTime localZDT = estZDT.withZoneSameInstant(localZID);

        resultTime = localZDT.toLocalDateTime();
        return resultTime;
    }


    /**
     * Method used to take the provided local date time to EST.
     * @param ts Provided local date time to be used to be converted.
     * @return Returns local date time convert to EST.
     */
    public static LocalDateTime convertToEST (LocalDateTime ts)
    {


       ZoneId zid = ZoneId.of("Etc/UTC");

        ZonedDateTime zdtStart = ts.atZone(zid);




        ZonedDateTime est = zdtStart.withZoneSameInstant(ZoneId.of("America/New_York"));


        LocalDateTime resultTime = est.toLocalDateTime();




        return resultTime;
    }






}
