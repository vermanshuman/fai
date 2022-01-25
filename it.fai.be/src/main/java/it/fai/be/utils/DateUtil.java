package it.fai.be.utils;

import it.fai.be.constant.ValueConstant;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static String timePattern = "HH:mm";
    private static String datePatternWithMinutes = "dd/MM/yyyy HH:mm";
    public static final Locale defaultLocale = Locale.ITALY;

    public static Date fromString(String str, String format) {
        if (StringUtils.isNotBlank(str)) {
            DateFormat dateFormat = createDateFormat(format, null);
            try {
                return dateFormat.parse(str);
            } catch (ParseException e2) {

            }
        }
        return null;
    }

    public static String toFormattedString(Date value, String format, Locale local) {
        if (value == null) return "";
        DateFormat df = createDateFormat(format, local);
        return df.format(value);
    }

    public static Date getCurrentDay(Date scheduledate) {
        Calendar scheduleCal = Calendar.getInstance();
        scheduleCal.setTime(scheduledate);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(Calendar.HOUR_OF_DAY, scheduleCal.get(Calendar.HOUR_OF_DAY));
        currentCalendar.set(Calendar.MINUTE, scheduleCal.get(Calendar.MINUTE));
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);
        return currentCalendar.getTime();
    }

    private static DateFormat createDateFormat(String pattern, Locale locale) {
        return new SimpleDateFormat(pattern, locale == null ? defaultLocale : locale);
    }

    public static boolean between(Date compareDate, Date startDate, Date endDate) {
        return (compareDate.after(startDate) || compareDate.getTime() == startDate.getTime())
                && (compareDate.before(endDate) || compareDate.getTime() == endDate.getTime());
    }


    /**
     * It returns the current timestamp as a string.
     */
    public static String currentDateForFilename() {
        String pattern = "dd MM yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date()).replaceAll("\\s", ValueConstant.DASH).replace(":", ValueConstant.DASH);
    }

    public static Date getNow() {
        return new Date();
    }

    public static String getTimePattern() {
        return timePattern;
    }

    public static String getDatePatternWithMinutes() {
        return datePatternWithMinutes;
    }
}
