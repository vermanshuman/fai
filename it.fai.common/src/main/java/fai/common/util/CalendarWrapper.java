package fai.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarWrapper {

    private Calendar calendar = null;

    private SimpleDateFormat sdfYYYYMMDD = new SimpleDateFormat("yyyy.MM.dd");


    private CalendarWrapper(Calendar calendar) {
        super();
        this.calendar = calendar;
    }

    public static CalendarWrapper getInstance(Calendar calendar) {
        return calendar == null ? null : new CalendarWrapper(calendar);
    }

    public static CalendarWrapper getInstance(String timestamp, String format) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime((new SimpleDateFormat(format)).parse(timestamp));
            return getInstance(c);
        } catch (ParseException e) {
            throw new IllegalArgumentException("impossibile riconoscere nel testo " + timestamp + " una data/ora nel formato " + format + ": eccezione " + e.getClass().getName() + " �" + e.getMessage() + "�", e);
        }
    }

    public static CalendarWrapper getInstance(CalendarWrapper datePart, CalendarWrapper timePart) {
        return getInstance(datePart.getCalendar(), timePart.getCalendar());
    }

    public static CalendarWrapper getInstance(Calendar datePart, String timePart, String format) {
        return getInstance(datePart, CalendarWrapper.getInstance(timePart, format).getCalendar());
    }

    public static CalendarWrapper getInstance(CalendarWrapper datePart, String timePart, String format) {
        return getInstance(datePart, CalendarWrapper.getInstance(timePart, format));
    }

    public static CalendarWrapper getInstance(Calendar datePart, Calendar timePart) {
        try {
            SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy.MM.dd");
            SimpleDateFormat sdfTime = new SimpleDateFormat("HH.mm.ss");
            //
            Calendar retval = Calendar.getInstance();
            //
            retval.setTime(sdfDateTime.parse(sdfDate.format(datePart.getTime()) + "-" + sdfTime.format(timePart.getTime())));
            //
            return getInstance(retval);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Eccezione " + e.getClass().getName() + " �" + e.getMessage() + "�", e);
        }
    }


    public static CalendarWrapper getNowInstance() {
        return new CalendarWrapper(Calendar.getInstance());
    }

    private CalendarWrapper() {

    }

    public Calendar getCalendar() {
        return calendar;
    }


    public CalendarWrapper setStartOfDay() throws ParseException {
        calendar.setTime(sdfYYYYMMDD.parse(sdfYYYYMMDD.format(calendar.getTime())));
        return this;
    }

    public CalendarWrapper setTime(String timestamp, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        calendar.setTime(sdf.parse(timestamp));
        return this;
    }

    public CalendarWrapper add(int field, int amount) {
        calendar.add(field, amount);
        return this;
    }

    public int minus(CalendarWrapper c, int field) {
        return minus(c.getCalendar(), field);
    }

    public int minus(Calendar c, int field) {
        long millis = calendar.getTimeInMillis() - c.getTimeInMillis();
        long retval = 0;
        switch (field) {
            case Calendar.DAY_OF_MONTH:
                retval = millis / (1000 * 60 * 60 * 24);
                break;
            case Calendar.HOUR_OF_DAY:
                retval = millis / (1000 * 60 * 60);
                break;
            case Calendar.MINUTE:
                retval = millis / (1000 * 60);
                break;
            case Calendar.SECOND:
                retval = millis / 1000;
                break;
            case Calendar.MILLISECOND:
                retval = millis;
                break;
            default:
                throw new IllegalArgumentException("field = " + field + " non gestito");
        }
        return (int) retval;
    }

    public int minusAbs(Calendar c, int field) {
        return Math.abs(minus(c, field));
    }

    public int minusAbs(CalendarWrapper c, int field) {
        return minusAbs(c.getCalendar(), field);
    }

    public boolean compare(String operator, CalendarWrapper c) {
        if (c == null) return false;
        return compare(operator, c.getCalendar());
    }

    public boolean compare(String operator, Calendar c) {
        if (c == null) return false;
        //
        if (">".equals(operator)) {
            return calendar.getTimeInMillis() > c.getTimeInMillis();
        } else if (">=".equals(operator)) {
            return calendar.getTimeInMillis() >= c.getTimeInMillis();
        } else if ("==".equals(operator)) {
            return calendar.getTimeInMillis() == c.getTimeInMillis();
        } else if ("<=".equals(operator)) {
            return calendar.getTimeInMillis() <= c.getTimeInMillis();
        } else if ("<".equals(operator)) {
            return calendar.getTimeInMillis() < c.getTimeInMillis();
        } else {
            throw new IllegalArgumentException("operatore non gestito: " + operator);
        }
    }

    public CalendarWrapper clone() {
        Calendar c = (Calendar) calendar.clone();
        return CalendarWrapper.getInstance(c);
    }

    public String format(String format) {
        return format(calendar, format);
    }

    public static String format(Calendar c, String format) {
        return c == null ? null : (new SimpleDateFormat(format)).format(c.getTime());
    }

    public static String format(CalendarWrapper c, String format) {
        return format(c == null ? null : c.getCalendar(), format);
    }


}
