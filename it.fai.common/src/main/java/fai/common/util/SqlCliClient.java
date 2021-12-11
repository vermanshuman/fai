package fai.common.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;


public class SqlCliClient {


    static Logger logger = Logger.getLogger(SqlCliClient.class);


    private static SqlCliClient singleton = null;

    private Connection conn;


    public static synchronized SqlCliClient getSingleton(Connection conn) throws Exception {
        if (singleton == null)
            singleton = new SqlCliClient(conn);
        else
            singleton.setConnection(conn);
        return singleton;
    }

    public static synchronized void runSingleton(Connection conn) throws Exception {
        getSingleton(conn).run();
    }


    public SqlCliClient(Connection conn) {
        super();
        this.conn = conn;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    private void manageStatement(String sql) throws Exception {
        manageStatement(sql, null);
    }

    private void manageStatement(String sql, org.apache.log4j.Level toLog) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        sql = sql.trim();
        if (sql.endsWith(";")) {
            sql = sql.substring(0, sql.length() - 1);
            sql = sql.trim();
        }
        //
        if (!sql.toUpperCase().startsWith("SELECT")) {
            System.out.println("only SELECT statements are supported at moment");
            return;
        }
        //
        SimpleDateFormat _TIMESTAMP = new SimpleDateFormat("yy.MM.dd-HH.mm.ss");
        stmt = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ", �" + e.getMessage() + "�");
            stmt.close();
            if (rs != null) rs.close();
            return;
        }
        ResultSetMetaData md = rs.getMetaData();
        List<List<String>> table = new ArrayList<List<String>>();
        List<Integer> colSizes = new ArrayList<Integer>();
        List<String> record = new ArrayList<String>();
        for (int i = 0; i < md.getColumnCount(); i++) {
            String colName = md.getColumnLabel(i + 1);
            record.add(colName);
            colSizes.add(colName.length());
        }
        table.add(record);
        while (rs.next()) {
            record = new ArrayList<String>();
            for (int i = 0; i < md.getColumnCount(); i++) {
                int colType = md.getColumnType(i + 1);
                String value = null;
                switch (colType) {
                    case java.sql.Types.BIGINT:
                        value = "" + rs.getLong(i + 1);
                        if (rs.wasNull()) value = null;
                        break;
                    case java.sql.Types.CHAR:
                    case java.sql.Types.CLOB:
                    case java.sql.Types.VARCHAR:
                        value = rs.getString(i + 1);
                        break;
                    case java.sql.Types.DATE:
                    case java.sql.Types.TIME:
                    case java.sql.Types.TIMESTAMP:
                        // case java.sql.Types.TIMESTAMP_WITH_TIMEZONE:
                        // case java.sql.Types.TIME_WITH_TIMEZONE:
                        java.sql.Timestamp sqlTs = rs.getTimestamp(i + 1);
                        if (sqlTs != null) {
                            java.util.Date javaDate = new java.util.Date(sqlTs.getTime());
                            Calendar calendarTs = Calendar.getInstance();
                            calendarTs.setTime(javaDate);
                            value = _TIMESTAMP.format(calendarTs.getTime());
                            if (value.endsWith("-00.00.00")) {
                                value = value.substring(0, value.length() - "-00.00.00".length());
                            }
                        } else {
                            value = null;
                        }
                        break;
                    case java.sql.Types.DECIMAL:
                    case java.sql.Types.DOUBLE:
                    case java.sql.Types.FLOAT:
                    case java.sql.Types.NUMERIC:
                    case java.sql.Types.REAL:
                        value = "" + rs.getDouble(i + 1);
                        if (rs.wasNull())
                            value = null;
                        else if (value.endsWith(".0"))
                            value = value.substring(0, value.length() - 2);
                        break;
                    case java.sql.Types.INTEGER:
                    case java.sql.Types.TINYINT:
                    case java.sql.Types.SMALLINT:
                        value = "" + rs.getInt(i + 1);
                        if (rs.wasNull()) value = null;
                        break;
                    case java.sql.Types.NULL:
                        value = null;
                        break;
                    case java.sql.Types.BOOLEAN:
                        value = "" + rs.getBoolean(i + 1);
                        if (rs.wasNull()) value = null;
                        break;
                    default:
                        value = "unk.type:" + colType;
                        break;
                }
                if (value != null && value.length() > colSizes.get(i)) colSizes.set(i, value.length());
                record.add(value);
            }
            table.add(record);
        }
        for (List<String> rec : table) {
            String recordAsText = "| ";
            for (int i = 0; i < rec.size(); i++) {
                // s+=String.format("%-"+(fieldLen[i]+2)+"s", record[i]);
                String value = String.format("%-" + (colSizes.get(i)) + "s", rec.get(i));
                recordAsText += value + " | ";
            }
            if (toLog != null)
                logger.log(toLog, recordAsText);
            else
                System.out.println(recordAsText);
        }
        //
        rs.close();
        stmt.close();
    }

    public void run() throws Exception {

        final String CMD_PREFIX = "/";
        final String CMD_EXEC = CMD_PREFIX + "exec (or end line with \";\" character)";
        final String CMD_QUIT = CMD_PREFIX + "quit";
        System.out.println(CMD_EXEC + " to execute");
        System.out.println(CMD_QUIT + " to exit");
        // java.io.Console console = System.console();
        Scanner scanner = new Scanner(System.in);
        boolean takeInLoop = true;
        String sql = "";
        while (takeInLoop) {
            // String line = console.readLine();
            String line = scanner.next();
            if (line.startsWith(CMD_PREFIX)) {
                if (line.startsWith(CMD_QUIT)) {
                    takeInLoop = false;
                } else if (line.startsWith(CMD_EXEC)) {
                    manageStatement(sql);
                    sql = "";
                } else {
                    System.out.println("unknown command");
                }
            } else {
                sql += line + System.lineSeparator();
                if (line.trim().endsWith(";")) {
                    manageStatement(sql);
                    sql = "";
                }
            }
        }

    }

    public void execute(String sql) throws Exception {
        execute(sql, org.apache.log4j.Level.INFO);
    }

    public void execute(String sql, org.apache.log4j.Level toLog) throws Exception {
        if (toLog != null)
            logger.log(toLog, sql);
        else
            System.out.println(sql);
        manageStatement(sql, toLog);
    }


    public static void main(String[] args) throws Exception {


        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.21.245:1521:odb", "crm", "crm");

        SqlCliClient client = new SqlCliClient(conn);
        client.run();


    }

}
