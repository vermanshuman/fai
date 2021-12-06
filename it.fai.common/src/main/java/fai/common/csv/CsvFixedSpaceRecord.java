package fai.common.csv;

import java.util.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CsvFixedSpaceRecord {

    private String line = null;

    public CsvFixedSpaceRecord(String line) {
        super();
        this.line = line;
    }


    public String getLine() {
        return line;
    }

    public void mandatoryValueCheck(int idxStart, int len, String expected, String fieldName) {
        String value = getString(false, idxStart, len);
        if (value != null) value = "\"" + value + "\"";
        if (expected != null) expected = "\"" + expected + "\"";
        if (value == null && expected != null || value != null && expected == null || !value.equals(expected)) {
            String fieldDescr = fieldName != null ? fieldName : "testo";
            throw new CsvException("indice " + idxStart + " (posizione " + (idxStart + 1) + "), lunghezza " + len + ", atteso " + fieldDescr + " " + expected + "; trovato: " + value);
        }

    }


    public String getString(boolean trim, int idxStart, int len, boolean mandatory, String fieldName) {
        String s = getString(trim, idxStart, len);
        if (mandatory == false) return s;
        //
        if (s == null || "".equals(s.trim())) {
            if (mandatory == false) return null;
            //
            String sDescr = s == null ? "null" : "\"" + s + "\"";
            String fieldDescr = fieldName != null ? "campo " + fieldName : "valore stringa";
            throw new CsvException(fieldDescr + " obbligatorio; trovato: " + sDescr);
        }
        //
        return s;
    }

    public String getString(boolean trim, int idxStart, int len) {
        if (line == null) return null;
        if (idxStart >= line.length()) return null;
        if (idxStart + len >= line.length()) len = line.length() - idxStart;
        if (len <= 0) return null;
        //
        String s = line.substring(idxStart, idxStart + len);
        if (trim) s = s.trim();
        return s;
    }

    public Integer getInteger(int idxStart, int len, boolean mandatory) {
        return getInteger(idxStart, len, mandatory, null);
    }

    public Integer getInteger(int idxStart, int len, boolean mandatory, String fieldName) {
        //
        Integer value = null;
        String s = getString(true, idxStart, len, mandatory, fieldName);
        if (s == null) return null;
        //
        try {
            return new Integer(s);
        } catch (Throwable e) {
            String fieldDescr = fieldName != null ? "il campo " + fieldName : "un valore intero";
            throw new CsvException("impossibile riconoscere " + fieldDescr + " nel testo " + s + " (" + e.getClass().getName() + "�" + e.getMessage() + "�)");
        }
    }


    public Double getDouble(int idxStart, int len, boolean mandatory) {
        return getDouble(idxStart, len, null, mandatory, null);
    }

    public Double getDouble(int idxStart, int len, NumberFormat nf, boolean mandatory) {
        return getDouble(idxStart, len, nf, mandatory, null);
    }

    public Double getDouble(int idxStart, int len, boolean mandatory, String fieldName) {

        return getDouble(idxStart, len, null, mandatory, fieldName);
    }


    public Double getDouble(int idxStart, int len, NumberFormat nf, boolean mandatory, String fieldName) {
        //
        String s = getString(true, idxStart, len, mandatory, fieldName);
        if (s == null) return null;
        //
        try {
            return nf == null ? new Double(s) : nf.parse(s).doubleValue();
        } catch (Throwable e) {
            String fieldDescr = fieldName != null ? "il campo " + fieldName : "un valore double";
            throw new CsvException("impossibile riconoscere " + fieldDescr + " nel testo " + s + " (" + e.getClass().getName() + "�" + e.getMessage() + "�)");
        }
    }


    public Calendar getCalendar(int idxStart, int len, SimpleDateFormat sdf, boolean mandatory) {
        return getCalendar(idxStart, len, sdf, mandatory, null);
    }

    public Calendar getCalendar(int idxStart, int len, SimpleDateFormat sdf, boolean mandatory, String fieldName) {
        //
        String s = getString(true, idxStart, len, mandatory, fieldName);
        if (s == null) return null;
        //
        try {
            Date dt = sdf.parse(s);
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            return c;
        } catch (Throwable e) {
            String fieldDescr = fieldName != null ? "il campo " + fieldName : "un valore data/ora";
            throw new CsvException("impossibile riconoscere " + fieldDescr + " nel testo " + s + " (" + e.getClass().getName() + "�" + e.getMessage() + "�)");
        }
    }


    public Long getLong(int idxStart, int len, boolean mandatory) {
        return getLong(idxStart, len, mandatory, null);
    }

    public Long getLong(int idxStart, int len, boolean mandatory, String fieldName) {
        //
        Integer value = null;
        String s = getString(true, idxStart, len, mandatory, fieldName);
        if (s == null) return null;
        //
        try {
            return new Long(s);
        } catch (Throwable e) {
            String fieldDescr = fieldName != null ? "il campo " + fieldName : "un valore intero";
            throw new CsvException("impossibile riconoscere " + fieldDescr + " nel testo " + s + " (" + e.getClass().getName() + "�" + e.getMessage() + "�)");
        }
    }


}
