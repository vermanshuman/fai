package fai.common.db;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import it.swdes.database.AbstractConnection;
import it.swdes.database.ConnectionManager;
import it.swdes.database.SqlStatementManager;
import it.swdes.models.AbstractModel;

public abstract class SqlUtilities {
    public static Logger logger = Logger.getLogger(SqlUtilities.class);

    private static String sqlResourceDefaultPath = "assegnare espliciatamente, per mezzo degli appositi metodi, facendo restiture il path alle risorse SQL (es. \"/farmadati/db/resources/sql/\")";

    public static String getSqlResourceDefaultPath() {
        return sqlResourceDefaultPath;
    }

    public static void setSqlResourceDefaultPath(String sqlResourceDefaultPath) {
        SqlUtilities.sqlResourceDefaultPath = sqlResourceDefaultPath;
    }

    private static String getClassName(Class c) {
        return getClassName(c, null);
    }

    private static String getClassName(Class c, String shortDescr) {
        return getClassName(c.getName(), shortDescr);
    }

    private static String getClassName(String className, String shortDescr) {
        shortDescr = shortDescr == null ? "" : " (" + shortDescr + ")";
        //
        int idx = className.lastIndexOf("\u0024");
        if (idx < 0)
            idx = className.lastIndexOf(".");
        //
        className = idx < 0 ? className : className.substring(idx + 1, className.length());
        //
        return className + shortDescr;
    }

    private static String getClassName(String c) {

        return getClassName(c, null);

    }

    public static String format(Calendar c, String format) throws Exception {
        if (c == null)
            return null;
        //
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(c.getTime());
    }

    public static String format(Connection conn) throws Exception {
        return format(null, conn);
    }

    private static String format(String prevConnections, Connection conn) throws Exception {
        final String SD_CONNECTION = AbstractConnection.class.getName() + "\u0024InnerConnection";
        final String TOMCAT_CONNWRAPPER = "org.apache.tomcat.dbcp.dbcp.PoolingDataSource\u0024PoolGuardConnectionWrapper";
        final String TOMCAT_CONN = "org.apache.tomcat.dbcp.dbcp.PoolableConnection";
        if (conn == null)
            return prevConnections;
        //
        String connInstance = getClassName(conn.getClass().getName()) + "@" + Integer.toHexString(conn.hashCode());
        connInstance = prevConnections == null ? connInstance : prevConnections + " --> " + connInstance;
        //
        // se TOMCAT_CONNWRAPPER, estrae la Connection ulteriormente wrappata
        Connection wrappedConnection = null;
        if (conn.getClass().getName().equals(TOMCAT_CONNWRAPPER)) {
            try {
                Field delegateField = conn.getClass().getDeclaredField("delegate");
                delegateField.setAccessible(true);
                wrappedConnection = (Connection) delegateField.get(conn);
                return format(connInstance, wrappedConnection);
            } catch (Exception e) {
                return connInstance += " (" + e.getClass().getName() + ", " + e.getMessage() + ")";
            }
        }
        //
        // se TOMCAT_CONN, estrae l'ennesima Conenction..
        else if (conn.getClass().getName().equals(TOMCAT_CONN)) {
            try {
                Field delegateField = conn.getClass().getSuperclass().getDeclaredField("_conn");
                delegateField.setAccessible(true);
                wrappedConnection = (Connection) delegateField.get(conn);
                return format(connInstance, wrappedConnection);
            } catch (Exception e) {
                return connInstance += " (" + e.getClass().getName() + ", " + e.getMessage() + ")";
            }
        }
        //
        // se SD_CONNECTION, estrae la Connection wrappata
        else if (conn.getClass().getName().equals(SD_CONNECTION)) {
            try {
                Method getConnection = conn.getClass().getMethod("getConnection", null);
                getConnection.setAccessible(true);
                wrappedConnection = ((Connection) getConnection.invoke(conn, null));
                return format(connInstance, wrappedConnection);
            } catch (Exception e) {
                return connInstance += " (" + e.getClass().getName() + ", " + e.getMessage() + ")";
            }
        }
        //
        // tutti gli altri casi
        else {
            return connInstance;
        }
    }

    /**
     * Da usarsi nelle assegnazioni (SET).<br/>
     * Produce in output una stringa del tipo
     * <code>"= to_date('01/01/','dd/MM/yyyy hh24:mi:ss')"</code> oppure
     * <code>"= NULL"</code> a seconda che il parametro sia o meno non
     * <tt>null</tt>
     *
     * @param c Calendar
     * @return String
     */
    public static String calendarToOracleToDateSET(Calendar c) {
        return "= " + calendarToOracleToDate(c);
    }

    /**
     * Da usarsi nelle <i>where condition</i> (WC).<br/>
     * Produce in output una stringa del tipo
     * <code>"= to_date('01/01/','dd/MM/yyyy hh24:mi:ss')"</code> oppure
     * <code>"IS NULL"</code> a seconda che il parametro sia o meno non
     * <tt>null</tt>
     *
     * @param c Calendar
     * @return String
     */
    public static String calendarToOracleToDateWC(Calendar c) {
        return (c != null ? "= " : "IS ") + calendarToOracleToDate(c);
    }

    public static String calendarToOracleToDate(Calendar c) {
        if (c == null)
            return "NULL";
        //
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "to_date('" + sdf.format(c.getTime()) + "','dd/MM/yyyy hh24:mi:ss')";
    }

    public static String calendarToString(Calendar c) {
        String dataStr = null;
        if (c != null) {
            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataStr = data.format(c.getTime());
        }
        return dataStr;
    }

    public static String calendarToStringInMillis(Calendar c) {
        String dataStr = null;
        if (c != null) {
            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
            dataStr = data.format(c.getTime());
        }
        return dataStr;
    }

    public static String calendarToString2(Calendar c) {
        String dataStr = null;
        if (c != null) {
            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dataStr = data.format(c.getTime());
        }
        return dataStr;
    }

    public static String calendarToStringDate(Calendar c) {
        String dataStr = null;
        if (c != null) {
            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
            dataStr = data.format(c.getTime());
        }
        return dataStr;
    }

    public static String calendarToStringHour(Calendar c) {
        String dataStr = null;
        if (c != null) {
            SimpleDateFormat data = new SimpleDateFormat("HH:mm:ss");
            dataStr = data.format(c.getTime());
        }
        return dataStr;
    }

    public static Calendar calendarFirstDayOfMonth(Calendar c) throws Exception {
        Calendar retval = Calendar.getInstance();
        retval.setTime(new SimpleDateFormat("yyyy.MM.dd").parse((new SimpleDateFormat("yyyy.MM.").format(c.getTime())) + "01"));
        return retval;
    }

    public static Calendar calendarLastDayOfMonth(Calendar c) throws Exception {
        Calendar retval = calendarFirstDayOfMonth(c);
        retval.add(Calendar.MONTH, 1);
        retval.add(Calendar.DAY_OF_MONTH, -1);
        return retval;
    }

    public static Calendar getCalendar(Timestamp data) {
        Calendar cal = null;
        if (data != null) {
            cal = Calendar.getInstance();
            cal.setTime(new Date(data.getTime()));
        }
        return cal;
    }

    public static Calendar getCalendar(ResultSet rs, String columnName) throws SQLException {
        return getCalendar(rs.getTimestamp(columnName));
    }

    public static String convertiData(String data, String colonnaDataTabella) {

        // il parametro data contiene una data query by example piena (no "" che
        // significa ALL)
        AbstractModel.QueryByExampleCalendar qbec = new AbstractModel.QueryByExampleCalendar(data);

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

        String whereClauseItem = null;

        // la prima data è sempre valorizzata
        String dt1 = sdf.format(qbec.getCalendar1().getTime());
        if (AbstractModel.QueryByExampleCalendar.BETWEEN.equals(qbec.getOperator())) {
            // la seconda data è valorizzata solo per between
            String dt2 = sdf.format(qbec.getCalendar2().getTime());
            whereClauseItem = " and " + colonnaDataTabella + " between TO_DATE('" + dt1 + "', 'ddMMyyyy') and TO_DATE('" + dt2 + "', 'ddMMyyyy') ";
        } else {
            whereClauseItem = " and " + colonnaDataTabella + " " + qbec.getOperator() + " " + " TO_DATE('" + dt1 + "', 'ddMMyyyy') ";
        }

        return whereClauseItem;
    }

    private static String fillDataAiMinuti(String data) {
        // ddmmyyyyhhmm
        if (data.length() >= 12) {
            return data.substring(0, 12);
        }

        for (int i = data.length(); i < 12; i++) {
            data += "0";
        }
        return data;
    }

    public static Calendar convertAsLocalCalendar(Calendar c) {
        Calendar local = (Calendar) c.clone();
        local.add(Calendar.MILLISECOND, local.get(local.DST_OFFSET));
        local.add(Calendar.MILLISECOND, local.get(local.ZONE_OFFSET));
        return local;
    }

    public static Calendar convertAsGMTCalendar(Calendar c) {
        Calendar gmt = (Calendar) c.clone();
        gmt.add(Calendar.MILLISECOND, -gmt.get(gmt.DST_OFFSET));
        gmt.add(Calendar.MILLISECOND, -gmt.get(gmt.ZONE_OFFSET));
        return gmt;
    }

    public static boolean isSameDay(Calendar c1, Calendar c2) {
        if (c1 == null || c2 == null)
            return false;
        //
        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR))
            return false;
        if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH))
            return false;
        if (c1.get(Calendar.DAY_OF_MONTH) != c2.get(Calendar.DAY_OF_MONTH))
            return false;
        //
        return true;
    }

    public static String getSql(String sqlFileName) throws Exception {
        return getSql(null, sqlFileName, null);
    }

    public static String getSql(String resourcePath, String sqlFileName) throws Exception {
        return getSql(resourcePath, sqlFileName, null);
    }

    public static String getSql(String sqlFileName, Properties parameter) throws Exception {
        return getSql(null, sqlFileName, parameter);
    }

    public static String getSql(String resourcePath, String sqlFileName, Properties parameter) throws Exception {
        String fullpathResourceName = null;
        try {
            fullpathResourceName = resourcePath == null ? getSqlResourceDefaultPath() + sqlFileName : resourcePath + sqlFileName;
            // SqlQueries.logger.debug("Recupero dello statement SQL dalla risorsa \"" + fullpathResourceName + "\"...");
            logger.debug("Risorsa \"" + fullpathResourceName + "\", recupero dello statement SQL...");
            String sql = null;
            if (parameter != null) {
                sql = SqlStatementManager.getSqlStatement(SqlUtilities.class.getResource(fullpathResourceName), parameter);
            } else {
                sql = SqlStatementManager.getSqlStatement(SqlUtilities.class.getResource(fullpathResourceName));
            }
            // SqlQueries.logger.debug("Statement SQL, con parametri valorizzati, corrispondente alla risorsa \"" + fullpathResourceName + "\":"+System.getProperty("line.separator")+sql);
            logger.debug("Risorsa \"" + fullpathResourceName + "\", statement SQL (con parametri valorizzati):" + System.getProperty("line.separator") + sql);

            return sql;
        } catch (Throwable ex) { // sostanzialmente,
            // it.swdes.database.UndeclaredThrowableException
            String error = "Eccezione tentando di caricare lo statement SQL dalla risorsa: \"" + fullpathResourceName + "\"";
            if (parameter != null) {
                List<String> list = new LinkedList<String>();
                Enumeration keys = parameter.propertyNames();
                while (keys.hasMoreElements()) {
                    String key = (String) keys.nextElement();
                    String value = parameter.getProperty(key);
                    list.add(key + "=" + value);
                }
                String[] keyValue = (String[]) list.toArray(new String[0]);
                Arrays.sort(keyValue);
                for (int i = 0; i < keyValue.length; i++) {
                    logger.error(error + "; parametro #" + (i + 1) + "/" + keyValue.length + ": " + keyValue[i]);
                }
            }
            logger.error(error, ex);
            throw ex;
        }

    }

    public static void closeWithNoException(AutoCloseable rs) {
        if (rs == null) {
            return;
        }
        try {
            rs.close();
        } catch (Exception ex) {
        }
    }

    public static void closeWithNoException(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (Exception ex) {
        }
    }

    public static void closeWithNoException(ConnectionManager connManager) {
        if (connManager == null) {
            return;
        }
        try {
            connManager.close();
        } catch (Exception ex) {
        }
    }

    public static void commitWithNoException(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.commit();
        } catch (Exception ex) {
        }
    }

    public static void rollbackWithNoException(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.rollback();
        } catch (Exception ex) {
        }
    }

    /**
     * Restituisce il next val della sequence specificata in input
     *
     * @param sequence
     * @param conn
     * @return
     * @throws SQLException
     */
    private static long getNextVal(String sequence, Connection conn) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT ").append(sequence).append(".NEXTVAL from DUAL");
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sb.toString());
            long nextval = 0;
            if (resultSet.next()) {
                nextval = resultSet.getLong(1);
            } else {
                throw new SQLException("Impossibile ottenere il nextval dalla sequence specificata: " + sequence);
            }
            return nextval;
        } finally {
            closeWithNoException(statement);
            closeWithNoException(resultSet);
        }
    }

    /**
     * Nextval della sequence denominata OID
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static long getOidNextVal(Connection conn) throws SQLException {
        return getNextVal("OID", conn);
    }

    /**
     * Nextval della sequence denominata BOOKING_CODE_SEQ
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static long getBookingCodeNextVal(Connection conn) throws SQLException {
        return getNextVal("BOOKING_CODE_SEQ", conn);
    }

    /**
     * Nextval della sequence denominata BOOKING_CODE_SEQ
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static long getBookingCodeSeqNextVal(Connection conn) throws SQLException {
        return getNextVal("BOOKING_CODE_SEQ", conn);
    }

    /**
     * getAsStringFieldValue
     *
     * @param s
     * @return
     */
    public static String getAsStringFieldValue(String s) {
        if (s == null || "".equals(s.trim()))
            return "NULL";
        //
        return "'" + SqlStatementManager.doubleQuotes(s) + "'";
    }

    public static String getAsStringFieldValueWC(String s) {
        return getAsStringFieldValueWC(s, false);
    }

    public static String getAsStringFieldValueWC(String s, boolean onZeroLengthSetNull) {
        if (s != null && s.equals("")) s = null;
        if (s == null || "".equals(s.trim()))
            return "IS NULL";
        //
        return " = '" + SqlStatementManager.doubleQuotes(s) + "'";
    }


    /**
     * Nextval della sequence denominata COD_TRANS_SEQ
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static long getCodTransNextVal(Connection conn) throws SQLException {
        return getNextVal("COD_TRANS_SEQ", conn);
    }

    public static String getAsIntegerFieldValue(Integer n, int onNullDefaultValue) {
        if (n == null) return "" + onNullDefaultValue;
        return getAsIntegerFieldValue(n);
    }

    public static String getAsIntegerFieldValue(Integer n) {
        return n == null ? "NULL" : "" + n;
    }

    public static String getAsDoubleFieldValue(Double n, double onNullDefaultValue) {
        if (n == null) return "" + onNullDefaultValue;
        return getAsDoubleFieldValue(n);
    }

    public static String getAsDoubleFieldValue(Double n) {
        return n == null ? "NULL" : "" + n;
    }

    public static Integer getInteger(ResultSet rs, String fieldName) throws Exception {
        int value = rs.getInt(fieldName);
        return rs.wasNull() ? null : new Integer(value);
    }

    public static Long getLong(ResultSet rs, String fieldName) throws Exception {
        long value = rs.getLong(fieldName);
        return rs.wasNull() ? null : new Long(value);
    }


    public static Double getDouble(ResultSet rs, String fieldName) throws Exception {
        double value = rs.getDouble(fieldName);
        return rs.wasNull() ? null : new Double(value);
    }


    public static String getAsSqlInList(List list) {
        if (list == null || list.size() == 0)
            return "IS NULL";
        //
        final String SEP = ", ";
        String retval = "";
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            Object t = iterator.next();
            if (t.getClass().getName().equals(String.class.getName())) {
                retval += "'" + t + "'";
            } else if (t.getClass().getName().equals(Integer.class.getName())) {
                retval += "" + t;
            } else {
                throw new IllegalArgumentException("classe non ancora gestita: " + t.getClass().getName());
            }
            retval += SEP;
        }
        retval = "IN (" + retval.substring(0, retval.length() - SEP.length()) + ")";
        return retval;
    }

    public static String getAsSqlInList(Set set) {
        List list = new ArrayList();
        if (set == null) {
            return getAsSqlInList((List) null);
        } else {
            list.addAll(set);
            return getAsSqlInList(list);
        }
    }


    public static String getClobAsString(ResultSet rs, int columnIndex) throws Exception {
        Clob clob = rs.getClob(columnIndex);
        String s = getClobAsString(clob);
        return s;
    }

    public static String getClobAsString(ResultSet rs, String columnLabel) throws Exception {
        Clob clob = rs.getClob(columnLabel);
        String s = getClobAsString(clob);
        return s;
    }


    public static String getClobAsString(Clob clob) throws Exception {
        if (clob == null) return null;
        long len = clob.length();
        if (len == 0) return "";
        //
        StringBuffer sb = new StringBuffer("");
        BufferedReader reader = new BufferedReader(clob.getCharacterStream());
        final int DEFAULT_BLOCK_LEN = 4000;
        long pos = 0;
        do {
            int blocklen = pos + DEFAULT_BLOCK_LEN > len ? (int) (len - pos) : DEFAULT_BLOCK_LEN;
            char[] buffer = new char[blocklen];
            reader.read(buffer, 0, blocklen);
            sb.append(new String(buffer));
            pos += blocklen;
        } while (pos < len);
        return sb.toString();
    }

    public static void setStringAsClob(PreparedStatement pstmt, int idx, String value) throws Exception {
        if (value == null) {
            pstmt.setNull(idx, java.sql.Types.CLOB);
            return;
        }
        //
        Reader reader = new StringReader(value);
        pstmt.setClob(idx, reader);
    }


    /**
     * Rispetto alla normale assegnazione, gestisce il caso di parametro <tt>null</tt>
     *
     * @param pstmt
     * @param index
     * @param s
     * @throws Exception
     */
    public static void setString(PreparedStatement pstmt, int index, String s) throws Exception {
        setString(pstmt, index, s, false);
    }


    /**
     * L'ulteriore parametro boolean, se <tt>true</tt>, consente,
     * rispetto a {@link #setString(PreparedStatement, int, String)},
     * di trattare le {@code String} di lunghezza zero come {@code String} <tt>null</tt>
     *
     * @param pstmt
     * @param index
     * @param s
     * @param onZeroLengthSetNull
     * @throws Exception
     */
    public static void setString(PreparedStatement pstmt, int index, String s, boolean onZeroLengthSetNull) throws Exception {
        if (s != null && s.equals("")) s = null;
        if (s == null)
            pstmt.setNull(index, java.sql.Types.VARCHAR);
        else
            pstmt.setString(index, s);
    }

    /**
     * Rispetto alla normale assegnazione, gestisce il caso di parametro <tt>null</tt>
     *
     * @param pstmt
     * @param index
     * @param c
     * @throws Exception
     */
    public static void setCalendar(PreparedStatement pstmt, int index, Calendar c) throws Exception {
        if (c == null)
            pstmt.setNull(index, java.sql.Types.TIMESTAMP);
        else
            pstmt.setTimestamp(index, new Timestamp(c.getTime().getTime()));
    }

    /**
     * Rispetto alla normale assegnazione, gestisce il caso di parametro <tt>null</tt>
     *
     * @param pstmt
     * @param index
     * @param d
     * @throws Exception
     */
    public static void setTimestamp(PreparedStatement pstmt, int index, java.util.Date d) throws Exception {
        if (d == null)
            pstmt.setNull(index, java.sql.Types.TIMESTAMP);
        else
            pstmt.setTimestamp(index, new Timestamp(d.getTime()));
    }

    /**
     * Rispetto alla normale assegnazione, gestisce il caso di parametro <tt>null</tt>
     *
     * @param pstmt
     * @param index
     * @param d
     * @throws Exception
     */
    public static void setDate(PreparedStatement pstmt, int index, java.util.Date d) throws Exception {
        if (d == null)
            pstmt.setNull(index, java.sql.Types.DATE);
        else
            pstmt.setDate(index, new java.sql.Date(d.getTime()));
    }

    /**
     * Rispetto alla normale assegnazione, gestisce il caso di parametro <tt>null</tt>
     *
     * @param pstmt
     * @param index
     * @param value
     * @throws Exception
     */
    public static void setInteger(PreparedStatement pstmt, int index, Integer value) throws Exception {
        if (value == null)
            pstmt.setNull(index, java.sql.Types.INTEGER);
        else
            pstmt.setInt(index, value);
    }


    /**
     * Rispetto alla normale assegnazione, gestisce il caso di parametro <tt>null</tt>
     *
     * @param pstmt
     * @param index
     * @param value
     * @throws Exception
     */
    public static void setDouble(PreparedStatement pstmt, int index, Double value) throws Exception {
        if (value == null)
            pstmt.setNull(index, java.sql.Types.DOUBLE);
        else
            pstmt.setDouble(index, value);
    }


    /**
     * se <code>value</code>==<code>nullValue</code>, allora al corrispondente
     * campo nel db sar� assegnato NULL.
     *
     * @param index     int
     * @param value     int
     * @param nullValue int
     * @throws Exception
     */
    public static void setInt(PreparedStatement pstmt, int index, int value, int nullValue) throws Exception {
        if (value == nullValue)
            pstmt.setNull(index, java.sql.Types.INTEGER);
        else
            pstmt.setInt(index, value);
    }


    /**
     * Rispetto alla normale assegnazione, gestisce il caso di parametro <tt>null</tt>
     *
     * @param pstmt
     * @param index
     * @param value
     * @throws Exception
     */
    public static void setLong(PreparedStatement pstmt, int index, Long value) throws Exception {
        if (value == null)
            pstmt.setNull(index, java.sql.Types.INTEGER);
        else
            pstmt.setLong(index, value);
    }


    /**
     * se <code>value</code>==<code>nullValue</code>, allora al corrispondente
     * campo nel db sar� assegnato NULL.
     *
     * @param index     long
     * @param value     long
     * @param nullValue long
     * @throws Exception
     */
    public static void setLong(PreparedStatement pstmt, int index, long value, long nullValue) throws Exception {
        if (value == nullValue)
            pstmt.setNull(index, java.sql.Types.INTEGER);
        else
            pstmt.setLong(index, value);
    }


    /**
     * se <code>value</code>==<code>nullValue</code>, allora al corrispondente
     * campo nel db sar� assegnato NULL.
     *
     * @param index     int
     * @param value     double
     * @param nullValue double
     * @throws Exception
     */
    public static void setDouble(PreparedStatement pstmt, int index, double value, double nullValue) throws Exception {
        if (value == nullValue)
            pstmt.setNull(index, java.sql.Types.DOUBLE);
        else
            pstmt.setDouble(index, value);
    }


    public static Boolean asBoolean(String s) {
        if (s == null) return null;
        //
        if ("S".equals(s)) {
            return true;
        } else if ("N".equals(s)) {
            return false;
        } else {
            throw new IllegalStateException("inammissibile, impossibile ricondurre la stringa \"" + s + "\" ad un valore booleano");
        }
    }


}
