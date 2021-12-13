package fai.imp.farmadati.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.net.nntp.Article;
import org.apache.log4j.Logger;

import fai.common.db.SqlUtilities;
import fai.imp.comifar.dto.Art;
import fai.imp.comifar.dto.ListBracket;

public class ComifarTable {

	static Logger logger = Logger.getLogger(ComifarTable.class);
	private Connection conn = null;
	private String client;
	private String tableName = "FAI_COMIFAR_LISTINO";
	private String priceTableName = "FAI_COMIFAR_LISTINO_PRICE";
	private int recordCount = 0;
	private StringBuffer sql = null; 
	private PreparedStatement pstmt = null;
	private boolean useBatchInsert = false;
	private String [] productFields = new String[]{"STATO", "CODICE_CLIENTE", "CODICE_MINSAN", "NOTA_PRODOTTO", 
			"ALIQUOTA_IVA", "SCONTO", "VALID_FROM_TS", "VALID_TO_TS"}; 

	private String [] productPriceFields = new String[]{"OID_COMIFAR_LISTINO", "QUANTITA", "PREZZO_NETTO"}; 

	private static final String COMIFAR_DATE_FORMAT = "dd/MM/yyyy";
	private static final String SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat sdfComifar = new SimpleDateFormat(COMIFAR_DATE_FORMAT);
	private static final SimpleDateFormat sdfSQL = new SimpleDateFormat(SQL_DATE_FORMAT);


	private ComifarTable() {

	}

	public ComifarTable(Connection conn, String client) {
		super();
		this.conn = conn;
		this.client = client;
	}

	public ComifarTable deleteAllRecords(String tableName) throws Exception {
		SqlQueries.executeUpdate("DELETE FROM "+tableName, conn);
		return this;
	}

	public String getTableName() {
		return tableName;
	}

	public ComifarTable insertRecord(Art art) throws Exception {
		insertRecordExecute(art);
		recordCount++;
		return this;
	}

	private void insertRecordExecute(Art art) throws Exception {
		final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
		List<String> debugPurposeFieldsInfo = new ArrayList<String>();
		try {
			for (int i = 0; i < productFields.length; i++) {
				String fieldName = productFields[i];
				String valueAsString = "";

				if(fieldName.equalsIgnoreCase("STATO")){
					valueAsString = art.getStatus();
				}
				else if(fieldName.equalsIgnoreCase("CODICE_CLIENTE"))
					valueAsString = getClient();
				else if(fieldName.equalsIgnoreCase("CODICE_MINSAN"))
					valueAsString = art.getMinsan();
				else if(fieldName.equalsIgnoreCase("NOTA_PRODOTTO"))
					valueAsString = art.getNote();
				else if(fieldName.equalsIgnoreCase("VALID_FROM_TS"))
					valueAsString = art.getValidStartDate();
				else if(fieldName.equalsIgnoreCase("VALID_TO_TS"))
					valueAsString = art.getValidEndDate();

				debugPurposeFieldsInfo.add("#"+(i+1)+"/"+productFields.length+" "+fieldName+"= "+valueAsString);

				if(fieldName.equalsIgnoreCase("VALID_TO_TS") || fieldName.equalsIgnoreCase("VALID_FROM_TS") && valueAsString != null){
					SqlUtilities.setDate(pstmt, i+1, parseComifarDate(valueAsString));
				}else {
					SqlUtilities.setString(pstmt, i+1, valueAsString);
				}
			}
			if (useBatchInsert) {
				pstmt.addBatch();
			}
			else {
				pstmt.executeUpdate();
			}
		}
		catch (Throwable th) {
			logger.error("Insert NON riuscita causa eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "»; segue elenco parametri:");
			for (int i = 0; i < debugPurposeFieldsInfo.size(); i++) {
				logger.error(debugPurposeFieldsInfo.get(i));
			}
			String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}


	}

	public ComifarTable insertRecordsPrepare() throws Exception {
		if (sql == null) {
			StringBuffer fieldNames = new StringBuffer("");
			StringBuffer fieldValues = new StringBuffer("");
			for (int i = 0; i < productFields.length; i++) {
				fieldNames.append(productFields[i]);
				fieldValues.append("?");
				if (i < productFields.length - 1) {
					fieldNames.append(", ");
					fieldValues.append(", ");
				}
			}
			sql = new StringBuffer("INSERT INTO ");
			sql.append(tableName);
			sql.append(" (OID,");
			sql.append(fieldNames.toString());
			sql.append(", UPDATE_TS) VALUES (OID.NEXTVAL,");
			sql.append(fieldValues.toString());
			sql.append(", SYSDATE)");
			logger.info("Statement SQL:" + System.getProperty("line.separator") + sql);
			pstmt = conn.prepareStatement(sql.toString());
		}
		//
		return this;
	}

	public ComifarTable insertPriceRecordsPrepare() throws Exception {
		if (sql == null) {
			StringBuffer fieldNames = new StringBuffer("");
			StringBuffer fieldValues = new StringBuffer("");
			for (int i = 0; i < productPriceFields.length; i++) {
				fieldNames.append(productPriceFields[i]);
				fieldValues.append("?");
				if (i < productPriceFields.length - 1) {
					fieldNames.append(", ");
					fieldValues.append(", ");
				}
			}
			sql = new StringBuffer("INSERT INTO ");
			sql.append(getPriceTableName());
			sql.append(" (OID,");
			sql.append(fieldNames.toString());
			sql.append(", UPDATE_TS) VALUES (OID.NEXTVAL,");
			sql.append(fieldValues.toString());
			sql.append(", SYSDATE)");
			logger.info("Statement SQL:" + System.getProperty("line.separator") + sql);
			pstmt = conn.prepareStatement(sql.toString());
		}
		//
		return this;
	}

	public ComifarTable insertPriceRecords(List<Art> articles) throws Exception {
		insertPriceRecordExecute(articles);
		return this;
	}
	
	private void insertPriceRecordExecute(List<Art> articles) throws Exception {
		final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
		List<String> debugPurposeFieldsInfo = new ArrayList<String>();
		try {
			List<String> minsSans = 
					articles.stream()
				              .map(Art::getMinsan)
				              .collect(Collectors.toList());
			for(String minSan: minsSans ){
				StringBuffer dataSql = new StringBuffer("SELECT OID FROM ");
				dataSql.append(getTableName());
				dataSql.append(" WHERE CODICE_MINSAN = '" + minSan + "'");
				try {
					PreparedStatement datPstmt = conn.prepareStatement(dataSql.toString());
					ResultSet rs = datPstmt.executeQuery();
					Art matchedArticle = null;
					Long productId = null;
					if (rs.next()) {
						productId = rs.getLong("OID");
						matchedArticle = articles
								.stream()
								.filter(a -> a.getMinsan() != null && a.getMinsan().equalsIgnoreCase(minSan))
								.findFirst()
								.orElse(null);
					}
					SqlUtilities.closeWithNoException(datPstmt);
					if(matchedArticle != null){
						List<ListBracket> lbs = matchedArticle.getListBracket();
						if(lbs != null && lbs.size() > 0){
							for(ListBracket lb : lbs){
								for (int i = 0; i < productPriceFields.length; i++) {
									String fieldName = productPriceFields[i];
									String valueAsString = "";
									if(fieldName.equalsIgnoreCase("QUANTITA")){
										valueAsString = lb.getQuantity() != null ? lb.getQuantity().toString(): null;
									}else if(fieldName.equalsIgnoreCase("PREZZO_NETTO")){
										valueAsString = lb.getPrice() != null ? lb.getPrice().toString(): null;
									}else if(fieldName.equalsIgnoreCase("OID_COMIFAR_LISTINO")){
										valueAsString = productId.toString();
									}

									debugPurposeFieldsInfo.add("#"+(i+1)+"/"+productFields.length+" "+fieldName+"= "+valueAsString);

									if(fieldName.equalsIgnoreCase("QUANTITA")){
										Integer value = valueAsString == null ? null : new Integer(valueAsString.trim());
										SqlUtilities.setInteger(pstmt, i+1, value);
									}else if(fieldName.equalsIgnoreCase("OID_COMIFAR_LISTINO")){
										Long value = valueAsString == null ? null : new Long(valueAsString.trim());
										SqlUtilities.setLong(pstmt, i+1, value);
									}else if(fieldName.equalsIgnoreCase("PREZZO_NETTO")){
										if (valueAsString.indexOf(",") >=0 ) {
											Double value = valueAsString == null ? null : new Double(valueAsString.replaceAll("\\,", "\\.").trim());
											SqlUtilities.setDouble(pstmt, i+1, value);
										}
									}
								}
								
								if (useBatchInsert) {
									pstmt.addBatch();
								}
								else {
									pstmt.executeUpdate();
								}
							}
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (Throwable th) {
			logger.error("Insert NON riuscita causa eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "»; segue elenco parametri:");
			for (int i = 0; i < debugPurposeFieldsInfo.size(); i++) {
				logger.error(debugPurposeFieldsInfo.get(i));
			}
			String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}


	}
	private  java.util.Date parseComifarDate(String s) throws Exception {
		if (s == null || (s != null && "".equals(s.trim()))) return null;
		try {
			return sdfSQL.parse(sdfSQL.format(sdfComifar.parse(s)));
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "»; \""+s+"\" non è una data nel formato atteso \""+COMIFAR_DATE_FORMAT+"\"";
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
	}

	public ComifarTable insertRecordsCompleted() throws Exception {
		if (pstmt == null) return this;
		if (useBatchInsert) {
			logger.info("conferma batch insert ("+recordCount+" record) ...");
			pstmt.executeBatch();
			logger.info("conferma batch confermata");
		}
		SqlUtilities.closeWithNoException(pstmt);
		sql = null;
		pstmt = null;
		return this;
	}

	
	public ComifarTable insertPriceRecordsCompleted() throws Exception {
		if (pstmt == null) return this;
		if (useBatchInsert) {
			logger.info("conferma batch insert ("+recordCount+" record) ...");
			pstmt.executeBatch();
			logger.info("conferma batch confermata");
		}
		SqlUtilities.closeWithNoException(pstmt);
		sql = null;
		pstmt = null;
		recordCount = 0;
		return this;
	}


	public int getRecordCount() {
		return recordCount;
	}

	public String getClient() {
		return client;
	}

	public boolean isUseBatchInsert() {
		return useBatchInsert;
	}

	public void setUseBatchInsert(boolean useBatchInsert) {
		this.useBatchInsert = useBatchInsert;
	}

	public String getPriceTableName() {
		return priceTableName;
	}
	
	
}
