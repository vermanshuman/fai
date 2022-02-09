package it.fai.be.utils;

import it.fai.be.constant.ValueConstant;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private static String timePattern = "HH:mm";
    private static String datePatternWithMinutes = "dd/MM/yyyy HH:mm";
    private static String mySQLDateTimePattern = "yyyy-MM-dd HH:mm:ss";
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

    public static Date getNextDay(Date scheduledate) {
        Calendar scheduleCal = Calendar.getInstance();
        scheduleCal.setTime(scheduledate);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(Calendar.HOUR_OF_DAY, scheduleCal.get(Calendar.HOUR_OF_DAY));
        currentCalendar.set(Calendar.MINUTE, scheduleCal.get(Calendar.MINUTE));
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);
        currentCalendar.add(Calendar.DATE, 1);
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

    public static  String getNextScheduleTime(List<String> items){
        String nextScheduleTime = null;
        Optional<Date> schedule = items.stream()
                .filter(item -> StringUtils.isNotBlank(item))
                .map(item -> DateUtil.getCurrentDay(DateUtil.fromString(item,
                        DateUtil.getTimePattern())))
                .filter(date -> date.equals(DateUtil.getNow()) || date.after(DateUtil.getNow()))
                .findFirst();

        if(schedule.isPresent()){
            nextScheduleTime = DateUtil.toFormattedString(schedule.get(), DateUtil.getDatePatternWithMinutes(), null);
        }else {
            if(items.size() > 0)
                nextScheduleTime = DateUtil.toFormattedString(DateUtil.getNextDay(DateUtil.fromString(items.get(0),
                        DateUtil.getTimePattern())), DateUtil.getDatePatternWithMinutes(), null);

        }
        return nextScheduleTime;
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

    public static String getMySQLDateTimePattern() {
        return mySQLDateTimePattern;
    }
}
