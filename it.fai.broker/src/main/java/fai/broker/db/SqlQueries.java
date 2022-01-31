package fai.broker.db;

import fai.broker.models.*;
import fai.broker.util.AnagraficaFarmaciMinSanEanCache;
import fai.common.db.SqlUtilities;
import fai.common.models.GenericTaskConfig;
import fai.common.util.CollectionsTool;
import fai.common.util.Timeout;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class SqlQueries {

	static Logger logger = Logger.getLogger(SqlQueries.class);

	static final String SQL_RESOURCE_PATH = "/fai/broker/db/resources/sql/";

	protected static StatusInfo asStatusInfo(ResultSet rs, String columnPrefix) throws Exception {
		if (columnPrefix == null)
			columnPrefix = "";
		columnPrefix = columnPrefix.trim();
		StatusInfo si = StatusInfo.newInstance(ItemStatus.getByOid(rs.getLong(columnPrefix + "OID_STATUS")), null,
				null);
		si.setStatusDescr(SqlUtilities.getClobAsString(rs, columnPrefix + "STATUS_DESCR"));
		si.setStatusTechDescr(SqlUtilities.getClobAsString(rs, columnPrefix + "STATUS_TECH_DESCR"));
		si.setStatusUpdatedTs(SqlUtilities.getCalendar(rs, columnPrefix + "STATUS_UPDATED_TS"));
		return si;
	}

	protected static int setStatusInfo(PreparedStatement stmt, int startingCol, StatusInfo status) throws Exception {
		int col = startingCol - 1;
		// OID_STATUS
		stmt.setLong(++col, status.getStatus().getOid());
		// STATUS_DESCR
		SqlUtilities.setString(stmt, ++col, status.getStatusDescr());
		// STATUS_TECH_DESCR
		SqlUtilities.setString(stmt, ++col, status.getStatusTechDescr());
		// STATUS_UPDATED_TS
		SqlUtilities.setCalendar(stmt, ++col, status.getStatusUpdatedTs());
		//
		return col;
	}

	public static boolean isOrdineInCollectionAlreadyCreated(String uniqueID, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		GenericTaskConfig cfg = null;
		boolean alreadyCreated = false;
		try {
			Properties params = new Properties();
			params.setProperty("UNIQUE_ID", SqlUtilities.getAsStringFieldValueWC(uniqueID));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "countOrdineInCollectionByUID.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			int recordCount = rs.getInt(1);
			if (recordCount == 0) {
				alreadyCreated = false;
			} else if (recordCount == 1) {
				alreadyCreated = true;
			} else {
				throw new IllegalStateException("inammissibile, attesi 0 oppure 1 record; trovati: " + recordCount);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return alreadyCreated;
	}

	public static void insertOrdineInCollection(OrdineInCollection oic, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			long oid = fai.common.db.SqlQueries.getOidNextVal(conn);
			oic.setOid(oid);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertOrdineInCollection.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// OID
			stmt.setLong(++col, oic.getOid());
			//
			SqlUtilities.setString(stmt, ++col, oic.getInputResource());
			// INPUT_RESOURCE_FULL_PATH
			SqlUtilities.setString(stmt, ++col, oic.getInputResourceFullPath());
			// BATCH_ID
			stmt.setLong(++col, oic.getBatchId());
			// OID_STATUS / STATUS_*
			col = setStatusInfo(stmt, ++col, oic.getStatus());
			// UNIQUE_ID
			stmt.setString(++col, oic.getUniqueID());
			// MAGAZZINO_ACRONYM
			SqlUtilities.setString(stmt, ++col, oic.getMagazzinoAcronym());
			stmt.executeUpdate();
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	public static String isUnableToRunStatusError(String tableName, String humanReadableTableName, Connection conn)
			throws Exception {
		return isUnableToRunStatusError(tableName, humanReadableTableName,
				new ItemStatus[] { ItemStatus.VALUE_ERROR, ItemStatus.VALUE_PROCESSING }, conn);
	}

	public static String isUnableToRunStatusError(String tableName, String humanReadableTableName,
												  ItemStatus[] itemStatus, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		Hashtable<Long, Integer> recordsCountByOid = new Hashtable<Long, Integer>();
		String error = null;
		try {
			List<String> itemStatusOids = new ArrayList<String>();
			for (int i = 0; i < itemStatus.length; i++) {
				itemStatusOids.add("" + itemStatus[i].getOid());
			}
			Properties params = new Properties();
			params.setProperty("TABLE_NAME", tableName);
			params.setProperty("FAI_ITEM_STATUS_OID_LIST", CollectionsTool.asCSV(itemStatusOids, ", "));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getUnableToRunStatusErrorRecords.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				recordsCountByOid.put(rs.getLong("OID_STATUS"), rs.getInt("RECORD_COUNT"));
			}
			//
			if (recordsCountByOid.size() != 0) {
				List<String> errors = new ArrayList<String>();
				Integer count = recordsCountByOid.get(ItemStatus.VALUE_ERROR.getOid());
				if (count != null) {
					errors.add(count + " record in errore (" + ItemStatus.VALUE_ERROR.getAcronym() + ")");
				}
				count = recordsCountByOid.get(ItemStatus.VALUE_PROCESSING.getOid());
				if (count != null) {
					errors.add(count + " record dall'elaborazione interrota ("
							+ ItemStatus.VALUE_PROCESSING.getAcronym() + ") per motivi sconosciuti");
				}
				count = recordsCountByOid.get(ItemStatus.VALUE_SUSPENDED.getOid());
				if (count != null) {
					errors.add(count + " record dall'elaborazione sospesa (" + ItemStatus.VALUE_SUSPENDED.getAcronym()
							+ ")");
				}
				count = recordsCountByOid.get(ItemStatus.VALUE_PROCESSED.getOid());
				if (count != null) {
					errors.add(count + " record dall'elaborazione completata ("
							+ ItemStatus.VALUE_PROCESSED.getAcronym() + ")");
				}
				count = recordsCountByOid.get(ItemStatus.VALUE_TO_PROCESS.getOid());
				if (count != null) {
					errors.add(count + " record da processare completata (" + ItemStatus.VALUE_TO_PROCESS.getAcronym()
							+ ")");
				}
				error = "tabella " + humanReadableTableName + " (" + tableName + "), trovati: "
						+ fai.common.util.CollectionsTool.asCSV(errors, ", ");
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return error;
	}

	public static StatusInfo setOrdineInCollectionStatus(long collectionOid, long oidStatusToAssign, String statusDescr,
														 String statusTechDescr, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		return setItemStatus("FAI_ORDINE_IN_COLLECTION", collectionOid, oidStatusToAssign, statusDescr, statusTechDescr,
				conn);
	}

	public static StatusInfo setOrdineInStatus(long ordineInOid, long oidStatusToAssign, String statusDescr,
											   String statusTechDescr, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		return setItemStatus("ORDINE_IN", ordineInOid, oidStatusToAssign, statusDescr, statusTechDescr, conn);
	}

	public static void setApprovvigionamentoFarmacoStatus(List<ApprovvigionamentoFarmaco> list, long oidStatusToAssign,
														  String statusDescr, String statusTechDescr, Connection conn) throws Exception {
		for (ApprovvigionamentoFarmaco af : list) {
			Long oid = af.getOid();
			if (oid == null)
				throw new IllegalArgumentException(
						"inammissibile, OID null per una delle istanze di " + ApprovvigionamentoFarmaco.class.getName()
								+ " della lista di " + list.size() + " elementi in input");
			af.setStatus(
					setApprovvigionamentoFarmacoStatus(oid, oidStatusToAssign, statusDescr, statusTechDescr, conn));
		}

	}

	public static StatusInfo setApprovvigionamentoFarmacoStatus(long approvvigionamentoFarmacoOid,
																long oidStatusToAssign, String statusDescr, String statusTechDescr, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		return setItemStatus("FAI_APPROVVIGIONAMENTO_FARMACO", approvvigionamentoFarmacoOid, oidStatusToAssign,
				statusDescr, statusTechDescr, conn);
	}

	public static StatusInfo setDisponibilitaTempStatus(long disponibilitaTempOid, long oidStatusToAssign,
														String statusDescr, String statusTechDescr, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		return setItemStatus("FAI_DISPONIBILITA_TEMP", disponibilitaTempOid, oidStatusToAssign, statusDescr,
				statusTechDescr, conn);
	}

	public static StatusInfo setItemStatus(String targetTable, Long oid, long oidStatusToAssign, String statusDescr,
										   String statusTechDescr, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		StatusInfo statusInfo = null;
		try {
			Calendar now = Calendar.getInstance();
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setItemStatus.sql");
			sql = sql.replace("?TABLE_NAME?", targetTable);
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, oidStatusToAssign);
			SqlUtilities.setString(stmt, 2, statusDescr);
			SqlUtilities.setStringAsClob(stmt, 3, statusTechDescr);
			SqlUtilities.setCalendar(stmt, 4, now);
			stmt.setLong(5, oid);
			stmt.executeUpdate();
			//
			statusInfo = StatusInfo.newInstance(ItemStatus.getByOid(oidStatusToAssign), statusDescr, statusTechDescr);
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}
		return statusInfo;

	}


	public static void insertOrdineIn(long ordineInCollectionOid, OrdineIn ordine, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			ordine.setOid(fai.common.db.SqlQueries.getOidNextVal(conn));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertOrdineIn.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// OID
			stmt.setLong(++col, ordine.getOid());
			// OID_ORDINEINCOLLECTION
			stmt.setLong(++col, ordineInCollectionOid);
			// ID_VENDITA
			stmt.setLong(++col, ordine.getIdVendita());
			// ID_UTENTE
			stmt.setLong(++col, ordine.getIdUtente() == null ? 0 : ordine.getIdUtente());
			// NOME_COMPLETO_ACQUIRENTE
			SqlUtilities.setString(stmt, ++col, ordine.getNomeCompletoAcquirente());
			// NUMERO_TELEFONO_ACQUIRENTE
			SqlUtilities.setString(stmt, ++col, ordine.getNumeroTelefonoAcquirente());
			// EMAIL_ACQUIRENTE
			SqlUtilities.setString(stmt, ++col, ordine.getEmailAcquirente());
			// INDIRIZZO_ACQUIRENTE_1
			SqlUtilities.setString(stmt, ++col, ordine.getIndirizzoAcquirente1());
			// INDIRIZZO_ACQUIRENTE_2
			SqlUtilities.setString(stmt, ++col, ordine.getIndirizzoAcquirente2());
			// CITTA_ACQUIRENTE
			SqlUtilities.setString(stmt, ++col, ordine.getCittaAcquirente());
			// STATO_ACQUIRENTE
			SqlUtilities.setString(stmt, ++col, ordine.getStatoAcquirente());
			// CAP_ACQUIRENTE
			SqlUtilities.setString(stmt, ++col, ordine.getCapAcquirente());
			// PAESE_ACQUIRENTE
			SqlUtilities.setString(stmt, ++col, ordine.getPaeseAcquirente());
			// QUANTITA
			stmt.setInt(++col, ordine.getQuantita());
			// SPEDIZIONE_E_IMBALLAGGIO
			stmt.setDouble(++col, ordine.getSpedizioneEImballaggio());
			// ASSICURAZIONE
			stmt.setDouble(++col, ordine.getAssicurazione() == null ? 0.0 : ordine.getAssicurazione());
			// TARIFFA_PER_CONTRASSEGNO
			stmt.setDouble(++col, ordine.getTariffaPerContrassegno() == null ? 0.0 : ordine.getTariffaPerContrassegno());
			// PREZZO_TOTALE
			stmt.setDouble(++col, ordine.getPrezzoTotale());
			// METODO_DI_PAGAMENTO
			SqlUtilities.setInteger(stmt, ++col, ordine.getMetodoDiPagamento());
			// DATA_DI_VENDITA
			SqlUtilities.setCalendar(stmt, ++col, ordine.getDataDiVendita());
			// DATA_DEL_MODULO_DI_PAGAMENTO
			SqlUtilities.setCalendar(stmt, ++col, ordine.getDataDelModuloDiPagamento());
			// DATA_PAGAMENTO
			SqlUtilities.setCalendar(stmt, ++col, ordine.getDataPagamento());
			// DATA_SPEDIZIONE
			SqlUtilities.setCalendar(stmt, ++col, ordine.getDataSpedizione());
			// DATA_FATTURA
			SqlUtilities.setCalendar(stmt, ++col, ordine.getDataFattura());
			// NUMERO_FATTURA
			SqlUtilities.setString(stmt, ++col, ordine.getNumeroFattura());
			// FEEDBACK_LASCIATO
			SqlUtilities.setString(stmt, ++col, Boolean.TRUE.equals(ordine.getFeedbackLasciato()) ? "S" : "N");
			// FEEDBACK_RICEVUTO
			SqlUtilities.setString(stmt, ++col, Boolean.TRUE.equals(ordine.getFeedbackRicevuto()) ? "S" : "N");
			// MEMO_PER_USO_PERSONALE
			SqlUtilities.setString(stmt, ++col, ordine.getMemoPerUsoPersonale());
			// CAMPO_PRIVATO
			SqlUtilities.setInteger(stmt, ++col, ordine.getCampoPrivato());
			// ID_TRANSAZIONE_PAYPAL
			SqlUtilities.setString(stmt, ++col, ordine.getIdTransazionePaypal());
			// SERVIZIO_DI_SPEDIZIONE
			SqlUtilities.setInteger(stmt, ++col, ordine.getServizioDiSpedizione());
			// OPZIONE_CONTRASSEGNO
			SqlUtilities.setString(stmt, ++col, Boolean.TRUE.equals(ordine.getOpzioneContrassegno()) ? "S" : "N");
			// ID_TRANSAZIONE
			SqlUtilities.setInteger(stmt, ++col, ordine.getIdTransazione());
			// ID_ORDINE
			SqlUtilities.setInteger(stmt, ++col, ordine.getIdOrdine());
			// ALIQUOTA_IVA_INCLUSA
			SqlUtilities.setInteger(stmt, ++col, ordine.getAliquotaIvaInclusa());
			// DETTAGLI_VARIAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getDettagliVariazione());
			// AZIENDA_ACQUIRENTE
			SqlUtilities.setString(stmt, ++col, ordine.getAziendaAcquirente());
			// NOME_COMPLETO_FATTURAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getNomeCompletoFatturazione());
			// AZIENDA_FATTURAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getAziendaFatturazione());
			// CF_FATTURAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getCfFatturazione());
			// PIVA_FATTURAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getPivaFatturazione());
			// INDIRIZZO_FATTURAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getIndirizzoFatturazione());
			// CITTA_FATTURAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getCittaFatturazione());
			// STATO_FATTURAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getStatoFatturazione());
			// CAP_FATTURAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getCapFatturazione());
			// PAESE_FATTURAZIONE
			SqlUtilities.setString(stmt, ++col, ordine.getPaeseFatturazione());
			// CODICEFE
			SqlUtilities.setString(stmt, ++col, ordine.getCodicefe());
			// PEC
			SqlUtilities.setString(stmt, ++col, ordine.getPec());
			// NOTE
			SqlUtilities.setString(stmt, ++col, ordine.getNote());
			// OID_STATUS / STATUS_*
			col = setStatusInfo(stmt, ++col, ordine.getStatus());
			//
			stmt.executeUpdate();
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	public static void insertOrdineInRigaDettaglio(long ordineInOid, OrdineInRigaDett riga, Connection conn)
			throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		try {
			riga.setOid(fai.common.db.SqlQueries.getOidNextVal(conn));
			Properties params = new Properties();
			params.setProperty("OID", "" + riga.getOid());
			params.setProperty("IS_COUPON", Boolean.TRUE.equals(riga.isCoupon()) ? "'S'" : "'N'");
			params.setProperty("NUMERO_OGGETTO", SqlUtilities.getAsStringFieldValue(riga.getNumeroOggetto()));
			params.setProperty("CODICE_MINSAN", SqlUtilities.getAsStringFieldValue(riga.getCodiceMinSan()));
			params.setProperty("CODICE_EAN", SqlUtilities.getAsStringFieldValue(riga.getCodiceEan()));
			params.setProperty("TITOLO_INSERZIONE", SqlUtilities.getAsStringFieldValue(riga.getTitoloInserzione()));
			params.setProperty("ETICHETTA_PERSONALIZZATA",
					SqlUtilities.getAsStringFieldValue(riga.getEtichettaPersonalizzata()));
			params.setProperty("QUANTITA", "" + riga.getQuantita());
			params.setProperty("PREZZO_FINALE", "" + riga.getPrezzoFinale());
			params.setProperty("ALIQUOTA_IVA_INCLUSA",
					SqlUtilities.getAsIntegerFieldValue(riga.getAliquotaIvaInclusa(), 0));
			params.setProperty("ID_PRODOTTO_UNIVOCO", SqlUtilities.getAsStringFieldValue(riga.getIdProdottoUnivoco()));
			params.setProperty("PRODUCTIDTYPE", SqlUtilities.getAsStringFieldValue(riga.getProductidtype()));
			params.setProperty("PRODUCTIDVALUE_2", SqlUtilities.getAsDoubleFieldValue(riga.getProductidvalue2()));
			params.setProperty("PRODUCTIDVALUE", SqlUtilities.getAsDoubleFieldValue(riga.getProductidvalue2()));
			params.setProperty("DETTAGLI_VARIAZIONE", SqlUtilities.getAsStringFieldValue(riga.getDettagliVariazione()));
			params.setProperty("OID_ORDINEIN", "" + ordineInOid);
			params.setProperty("MAGAZZINO_ACRONYM", SqlUtilities.getAsStringFieldValue(riga.getMagazzinoAcronym()));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertOrdineInRigaDettaglio.sql", params);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	public static AnagraficaFarmaciMinSanEanCache getAnagraficaFarmaciMinSanEanCache(Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		AnagraficaFarmaciMinSanEanCache anagrafica = new AnagraficaFarmaciMinSanEanCache();
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllAnagraficaFarmaci.sql");
			stmt = conn.createStatement();
			logger.info(LOG_PREFIX + "interrogazione ...");
			rs = stmt.executeQuery(sql);
			logger.info(LOG_PREFIX + "caricamento ...");
			int recordCount = 0;
			Timeout timeout = new Timeout(5000, false);
			while (rs.next()) {
				anagrafica.addToCache(rs.getString("COD_MINSAN"), rs.getString("COD_EAN"), rs.getString("DESCR_PROD"),
						rs.getString("DESCR_TIPO_PROD"), rs.getString("DESCR_ESTESA_TIPO_PROD"));
				recordCount++;
				if (timeout.isExpired()) {
					logger.info(LOG_PREFIX + recordCount + " record caricati ...");
				}
			}
			logger.info(LOG_PREFIX + recordCount + " record complessivamente caricati");
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return anagrafica;

	}

	public static Hashtable<Long, Magazzino> getAllMagazzinoAsHashtableByOid(String context, Connection conn)
			throws Exception {
		Hashtable<Long, Magazzino> ht = new Hashtable<Long, Magazzino>();
		List<Magazzino> magazzini = getAllMagazzino(context, conn);
		for (Magazzino magazzino : magazzini) {
			ht.put(magazzino.getOid(), magazzino);
		}
		return ht;
	}

	public static List<Magazzino> getAllMagazzino(String context, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Magazzino> list = new LinkedList<Magazzino>();
		Hashtable<Long, TipoFarmaco> tipiFarmaco = new Hashtable<Long, TipoFarmaco>();
		try {
			Properties params = new Properties();
			params.setProperty("CONTEXT", SqlUtilities.getAsStringFieldValueWC(context));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllMagazzino.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			long oidBak = -1;
			Magazzino m = null;
			while (rs.next()) {
				long oid = rs.getLong("MAGAZZINO_OID");
				if (oid != oidBak) {
					m = new Magazzino();
					m.setOid(oid);
					m.setAcronym(rs.getString("MAGAZZINO_ACRONYM"));
					m.setDescr(rs.getString("MAGAZZINO_DESCR"));
					m.setComune(rs.getString("MAGAZZINO_COMUNE"));
					m.setProvincia(rs.getString("MAGAZZINO_PROVINCIA"));
					m.setRegione(rs.getString("MAGAZZINO_REGIONE"));
					m.setCap(rs.getString("MAGAZZINO_CAP"));
					m.setIndirizzo(rs.getString("MAGAZZINO_INDIRIZZO"));
					m.setSupplierServiceClassName(rs.getString("SUPPLIERSERVICE_CLASS_NAME"));
					m.setOrganizationCode(rs.getString("MAGAZZINO_ORGANIZATION_CODICE"));
					list.add(m);
				}
				//
				long oidTipoFarmaco = rs.getLong("TIPO_FARMACO_OID");
				if (rs.wasNull() == false) {
					TipoFarmaco tf = tipiFarmaco.get(oidTipoFarmaco);
					if (tf == null) {
						tf = new TipoFarmaco();
						tf.setOid(oidTipoFarmaco);
						tf.setAcronym(rs.getString("TIPO_FARMACO_ACRONYM"));
						tf.setDescr(rs.getString("TIPO_FARMACO_DESCR"));
						tipiFarmaco.put(oidTipoFarmaco, tf);
					}
					m.addTipoFarmaco(tf);
				}
				//
				oidBak = oid;
			}
			//
			attachProperties(list, conn);
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

	public static Magazzino getSelectedMagazzino(String context, String magazzinoAcronym, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		//List<Magazzino> list = new LinkedList<Magazzino>();
		Magazzino m = null;
		Hashtable<Long, TipoFarmaco> tipiFarmaco = new Hashtable<Long, TipoFarmaco>();
		try {
			Properties params = new Properties();
			params.setProperty("CONTEXT", SqlUtilities.getAsStringFieldValueWC(context));
			params.setProperty("MAGAZZINOACRONYM", SqlUtilities.getAsStringFieldValueWC(magazzinoAcronym));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getSelectedMagazzino.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			long oidBak = -1;
			while (rs.next()) {
				long oid = rs.getLong("MAGAZZINO_OID");
				if (oid != oidBak) {
					m = new Magazzino();
					m.setOid(oid);
					m.setAcronym(rs.getString("MAGAZZINO_ACRONYM"));
					m.setDescr(rs.getString("MAGAZZINO_DESCR"));
					m.setComune(rs.getString("MAGAZZINO_COMUNE"));
					m.setProvincia(rs.getString("MAGAZZINO_PROVINCIA"));
					m.setRegione(rs.getString("MAGAZZINO_REGIONE"));
					m.setCap(rs.getString("MAGAZZINO_CAP"));
					m.setIndirizzo(rs.getString("MAGAZZINO_INDIRIZZO"));
					m.setSupplierServiceClassName(rs.getString("SUPPLIERSERVICE_CLASS_NAME"));
					m.setOrganizationCode(rs.getString("MAGAZZINO_ORGANIZATION_CODICE"));
					//list.add(m);
				}
				//
				long oidTipoFarmaco = rs.getLong("TIPO_FARMACO_OID");
				if (rs.wasNull() == false) {
					TipoFarmaco tf = tipiFarmaco.get(oidTipoFarmaco);
					if (tf == null) {
						tf = new TipoFarmaco();
						tf.setOid(oidTipoFarmaco);
						tf.setAcronym(rs.getString("TIPO_FARMACO_ACRONYM"));
						tf.setDescr(rs.getString("TIPO_FARMACO_DESCR"));
						tipiFarmaco.put(oidTipoFarmaco, tf);
					}
					m.addTipoFarmaco(tf);
				}
				//
				oidBak = oid;
			}
			//
			//attachProperties(list, conn);
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return m;

	}

	private static void attachProperties(List<Magazzino> magazzini, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		Hashtable<Long, Magazzino> magazzinoByOid = new Hashtable<Long, Magazzino>();
		for (Magazzino m : magazzini) {
			magazzinoByOid.put(m.getOid(), m);
		}
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllMagazzinoProperty.sql");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Property property = new Property();
				property.setOid(rs.getLong("OID"));
				property.setKey(rs.getString("KEY"));
				property.setValue(rs.getString("VALUE"));
				Magazzino magazzino = magazzinoByOid.get(rs.getLong("OID_MAGAZZINO"));
				magazzino.addProperty(property);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
	}

	public static Hashtable<Long, Fornitore> getAllFornitoreAsHashtableByOid(Connection conn) throws Exception {
		Hashtable<Long, Fornitore> ht = new Hashtable<Long, Fornitore>();
		List<Fornitore> fornitori = getAllFornitore(conn);
		for (Fornitore fornitore : fornitori) {
			ht.put(fornitore.getOid(), fornitore);
		}
		return ht;
	}

	public static List<Fornitore> getAllFornitore(Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Fornitore> list = new LinkedList<Fornitore>();
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllFornitore.sql");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			Fornitore f = null;
			long oidBak = -1;
			while (rs.next()) {
				long oid = rs.getLong("OID");
				if (oid != oidBak) {
					f = new Fornitore();
					f.setOid(oid);
					f.setCodice(rs.getString("CODICE"));
					f.setCodiceFarmaclick(rs.getString("CODICE_FARMACLICK"));
					f.setSupplierServiceClassName(rs.getString("SUPPLIERSERVICE_CLASS_NAME"));
					list.add(f);
				}
				oidBak = oid;
				Property property = new Property();
				property.setOid(rs.getLong("PROP_OID"));
				property.setKey(rs.getString("PROP_KEY"));
				property.setValue(rs.getString("PROP_VALUE"));
				f.addProperty(property);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

	public static List<ApprovvigionamentoFarmaco> getAllApprovvigionamentoFarmacoByStatusAndCodice(String codiceMinSan,
																								   String codiceEan, Long[] oidStatus, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		Properties params = new Properties();
		params.setProperty("oidStatusList", "" + fai.common.util.CollectionsTool.asCSV(oidStatus, ","));
		params.setProperty("CODICE_MINSAN", SqlUtilities.getAsStringFieldValueWC(codiceMinSan, true));
		params.setProperty("CODICE_EAN", SqlUtilities.getAsStringFieldValueWC(codiceEan, true));
		String sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllApprovvigionamentoFarmacoByStatusAndCodice.sql",
				params);
		return getAllApprovvigionamentoCommon(sql, conn);
	}

	public static List<ApprovvigionamentoFarmaco> getAllApprovvigionamentoFarmaco(ItemStatus status, Connection conn)
			throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		Properties params = new Properties();
		params.setProperty("OID_STATUS", "" + status.getOid());
		String sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllApprovvigionamentoFarmacoByStatus.sql", params);
		return getAllApprovvigionamentoCommon(sql, conn);
	}

	private static List<ApprovvigionamentoFarmaco> getAllApprovvigionamentoCommon(String sql, Connection conn)
			throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		Statement stmt = null;
		ResultSet rs = null;
		List<ApprovvigionamentoFarmaco> list = new LinkedList<ApprovvigionamentoFarmaco>();
		Hashtable<Long, Magazzino> magazzini = getAllMagazzinoAsHashtableByOid(null, conn);
		Hashtable<Long, Fornitore> fornitori = getAllFornitoreAsHashtableByOid(conn);
		try {
			long approvOidBak = -1;
			ApprovvigionamentoFarmaco currApprov = null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				long approvOid = rs.getLong("OID");
				if (approvOid != approvOidBak) {
					currApprov = new ApprovvigionamentoFarmaco();
					currApprov.setOid(approvOid);
					currApprov.setCodiceMinSan(rs.getString("CODICE_MINSAN"));
					currApprov.setCodiceEan(rs.getString("CODICE_EAN"));
					currApprov.setQuantita(rs.getInt("QUANTITA"));
					currApprov
							.setDisponibilitaRichiestaReq(SqlUtilities.getCalendar(rs, "DISPONIBILITA_RICHIESTA_REQ"));
					currApprov
							.setDisponibilitaRichiestaRes(SqlUtilities.getCalendar(rs, "DISPONIBILITA_RICHIESTA_RES"));
					currApprov.setDisponibilitaConfermataReq(
							SqlUtilities.getCalendar(rs, "DISPONIBILITA_CONFERMATA_REQ"));
					currApprov.setDisponibilitaConfermataRes(
							SqlUtilities.getCalendar(rs, "DISPONIBILITA_CONFERMATA_RES"));
					currApprov.setIdOrdine(rs.getString("ID_ORDINE"));
					currApprov.setIdRicevuta(rs.getString("ID_RICEVUTA"));
					currApprov.setStatus(asStatusInfo(rs, ""));
					currApprov.setPrezzoUnitario(SqlUtilities.getDouble(rs, "PREZZO_UNITARIO"));
					currApprov.setPrezzoTotale(SqlUtilities.getDouble(rs, "PREZZO_TOTALE"));
					currApprov.setAliquotaIva(SqlUtilities.getDouble(rs, "ALIQUOTA_IVA"));
					currApprov.setAliquotaIvaInclusa(SqlUtilities.asBoolean(rs.getString("ALIQUOTA_IVA_INCLUSA")));
					currApprov.setMagazzinoAcronym(rs.getString("MAGAZZINO_ACRONYM"));
					Long oidOrdineOut = SqlUtilities.getLong(rs, "OID_ORDINEOUT");
					if (oidOrdineOut != null) {
						currApprov.setOrdineOut(new OrdineOut());
						currApprov.getOrdineOut().setOid(oidOrdineOut);
					}
					Long oidMagazzino = SqlUtilities.getLong(rs, "OID_MAGAZZINO");
					if (oidMagazzino != null) {
						Magazzino m = magazzini.get(oidMagazzino);
						if (m == null)
							throw new IllegalStateException("inammissibile, nessun " + Magazzino.class.getName()
									+ " per l'OID " + oidMagazzino);
					}
					Long oidFornitore = SqlUtilities.getLong(rs, "OID_FORNITORE");
					if (oidFornitore != null) {
						Fornitore m = fornitori.get(oidFornitore);
						if (m == null)
							throw new IllegalStateException("inammissibile, nessun " + Fornitore.class.getName()
									+ " per l'OID " + oidFornitore);
					}
					list.add(currApprov);
				}
				approvOidBak = approvOid;
				//
				ApprovToRiga a2r = new ApprovToRiga();
				a2r.setOid(rs.getLong("APPROV2RIGA_OID"));
				a2r.setQuantita(rs.getInt("APPROV2RIGA_QUANTITA"));
				OrdineInRigaDett riga = new OrdineInRigaDett();
				riga.setOid(rs.getLong("RIGA_OID"));
				riga.setCodiceMinSan(rs.getString("RIGA_CODICE_MINSAN"));
				riga.setCodiceEan(rs.getString("RIGA_CODICE_EAN"));
				riga.setQuantita(rs.getInt("RIGA_QUANTITA"));
				a2r.setRigaDett(riga);
				//
				// controllo di congruenza
				if (!riga.getCodiceMinSan()
						.equals(currApprov.getCodiceMinSan()) /* AbstractModel --> non possono essere null */) {
					throw new IllegalStateException(
							"inammissibile, il record di Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) identificato dall'OID  "
									+ currApprov.getOid() + " ha un Codice MinSan (" + currApprov.getCodiceMinSan()
									+ ") differente dal Codice MinSan (" + riga.getCodiceMinSan()
									+ ") della Riga dell'Ordine in Ingresso (FAI_ORDINE_IN_RIGA_DETTAGLIO) di OID "
									+ riga.getOid() + " cui si riferisce");
				}
				if (!riga.getCodiceEan()
						.equals(currApprov.getCodiceEan()) /* AbstractModel --> non possono essere null */) {
					throw new IllegalStateException(
							"inammissibile, il record di Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) identificato dall'OID  "
									+ currApprov.getOid() + " ha un Codice EAN (" + currApprov.getCodiceEan()
									+ ") differente dal Codice EAN (" + riga.getCodiceEan()
									+ ") della Riga dell'Ordine in Ingresso (FAI_ORDINE_IN_RIGA_DETTAGLIO) di OID "
									+ riga.getOid() + " cui si riferisce");
				}
				//
				currApprov.addApprovRigaDett(a2r);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

	public static void deleteApprovvigionamentoToRecaulculate(Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		//
		String sql = "DELETE FROM FAI_APPROVVIGIONAMENTO_FARMACO WHERE OID_STATUS = "
				+ ItemStatus.VALUE_PROCESSED.getOid() + " AND DISPONIBILITA_CONFERMATA_REQ IS NULL";
		fai.common.db.SqlQueries.executeUpdate(sql, conn);
		//
		sql = "DELETE FROM FAI_APPROVVIGIONAMENTO_FARMACO WHERE OID_STATUS = " + ItemStatus.VALUE_PROCESSING.getOid()
				+ " AND DISPONIBILITA_CONFERMATA_REQ IS NULL";
		fai.common.db.SqlQueries.executeUpdate(sql, conn);
		//
		sql = "DELETE FROM FAI_APPROVVIGIONAMENTO_FARMACO WHERE OID_STATUS = " + ItemStatus.VALUE_TO_PROCESS.getOid();
		fai.common.db.SqlQueries.executeUpdate(sql, conn);

	}

	public static List<OrdineInRigaDett> getAllOrdineInRigaDettWithoutFabbisogno(Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<OrdineInRigaDett> list = new LinkedList<OrdineInRigaDett>();
		try {
			Properties params = new Properties();
			params.setProperty("ORDINE.OID_STATUS", "" + ItemStatus.VALUE_PROCESSED.getOid());
			params.setProperty("COLLECTION.OID_STATUS", "" + ItemStatus.VALUE_PROCESSED.getOid());
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllOrdineInRigaDettWithoutFabbisogno.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				OrdineInRigaDett riga = new OrdineInRigaDett();
				riga.setOid(rs.getLong("OID"));
				riga.setCodiceMinSan(rs.getString("CODICE_MINSAN"));
				riga.setCodiceEan(rs.getString("CODICE_EAN"));
				riga.setQuantita(rs.getInt("QUANTITA"));
				riga.setMagazzinoAcronym(rs.getString("MAGAZZINO_ACRONYM"));
				list.add(riga);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

	public static void insertAllApprovvigionamentoFarmaco(List<ApprovvigionamentoFarmaco> approvList, Connection conn)
			throws Exception {
		for (ApprovvigionamentoFarmaco approv : approvList) {
			insertApprovvigionamentoFarmaco(approv, conn);
		}
	}

	public static void insertApprovvigionamentoFarmaco(ApprovvigionamentoFarmaco approv, Connection conn)
			throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			if (approv.getOid() == null) {
				approv.setOid(fai.common.db.SqlQueries.getOidNextVal(conn));
			}
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertApprovvigionamentoFarmaco.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// OID
			stmt.setLong(++col, approv.getOid());
			// CODICE_MINSAN
			SqlUtilities.setString(stmt, ++col, approv.getCodiceMinSan(), true);
			// CODICE_EAN
			SqlUtilities.setString(stmt, ++col, approv.getCodiceEan(), true);
			SqlUtilities.setString(stmt, ++col, approv.getMagazzinoAcronym(), true);
			// QUANTITA
			stmt.setInt(++col, approv.getQuantita());
			// OID_STATUS / STATUS_*
			col = setStatusInfo(stmt, ++col, approv.getStatus());
			//
			stmt.executeUpdate();
			//
			ApprovToRiga[] a2r = approv.getAllApprovRigaDett();
			for (int i = 0; i < a2r.length; i++) {
				insertApprovToRiga(approv.getOid(), a2r[i], conn);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	public static void updateApprovvigionamentoFarmaco(ApprovvigionamentoFarmaco approv, boolean updateApprToRigaToo,
													   Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateApprovvigionamentoFarmaco.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// CODICE_MINSAN
			SqlUtilities.setString(stmt, ++col, approv.getCodiceMinSan(), true);
			// CODICE_EAN
			SqlUtilities.setString(stmt, ++col, approv.getCodiceEan(), true);
			// OID_FORNITORE
			SqlUtilities.setLong(stmt, ++col,
					approv.getFornitore() == null ? null : approv.getFornitore().getOid());
			// QUANTITA
			stmt.setInt(++col, approv.getQuantita());
			//DISPONIBILITA_CONFERMATA_REQ
			stmt.setDate(++col, approv.getDisponibilitaConfermataReq() == null ? null : new java.sql.Date(approv.getDisponibilitaConfermataReq().getTimeInMillis()));
			// OID_STATUS / STATUS_*
			col = setStatusInfo(stmt, ++col, approv.getStatus());
			// OID
			stmt.setLong(++col, approv.getOid());
			//
			stmt.executeUpdate();
			//
			if (updateApprToRigaToo) {
				deleteApprovToRigaByApprovOid(approv.getOid(), conn);
				ApprovToRiga[] a2r = approv.getAllApprovRigaDett();
				for (int i = 0; i < a2r.length; i++) {
					insertApprovToRiga(approv.getOid(), a2r[i], conn);
				}
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	public static void deleteApprovToRigaByApprovOid(long approvFarmacoOid, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		try {
			Properties params = new Properties();
			params.setProperty("OID_APPROVFARMACO", "" + approvFarmacoOid);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "deleteApprovFarmacoOrdInRigaByApprovFarmaco.sql", params);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	public static void insertApprovToRiga(long approvFarmacoOid, ApprovToRiga a2r, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		try {
			if (a2r.getOid() == null) {
				long oid = fai.common.db.SqlQueries.getOidNextVal(conn);
				a2r.setOid(oid);
			}
			Properties params = new Properties();
			params.setProperty("OID", "" + a2r.getOid());
			params.setProperty("OID_APPROVFARMACO", "" + approvFarmacoOid);
			params.setProperty("OID_ORDINEINRIGA", "" + a2r.getRigaDett().getOid());
			params.setProperty("QUANTITA", "" + a2r.getQuantita());
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertApprovFarmacoOrdInRiga.sql", params);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	public static void updateApprovToRiga(long updateApprovToRigaOid, int quantita, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = "UPDATE FAI_APPROVVIGFARMACO_ORDINRIGA SET QUANTITA = " + quantita + " WHERE OID = "
				+ updateApprovToRigaOid;
		fai.common.db.SqlQueries.executeUpdate(sql, conn);
	}

	public static void updateApprovToRiga(long updateApprovToRigaOid, long approvFarmacoOidToAttachTo, Connection conn)
			throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = "UPDATE FAI_APPROVVIGFARMACO_ORDINRIGA SET OID_APPROVFARMACO = " + approvFarmacoOidToAttachTo
				+ " WHERE OID = " + updateApprovToRigaOid;
		fai.common.db.SqlQueries.executeUpdate(sql, conn);
	}

	public static List<ApprovvigionamentoFarmaco> setApprovvigionamentoFarmacoToNow(
			List<ApprovvigionamentoFarmaco> afList, int amFieldToAssignTs, Connection conn) throws Exception {
		Calendar now = Calendar.getInstance();
		for (ApprovvigionamentoFarmaco af : afList) {
			setApprovvigionamentoFarmaco(af, amFieldToAssignTs, now, conn);
		}
		return afList;
	}

	protected static ApprovvigionamentoFarmaco setApprovvigionamentoFarmaco(ApprovvigionamentoFarmaco af,
																			int amFieldToAssignTs, Calendar ts, Connection conn) throws Exception {
		Long oid = af.getOid();
		if (oid == null)
			throw new IllegalArgumentException(
					"inammissibile, OID null per l'istanza " + ApprovvigionamentoFarmaco.class.getName());
		//
		int amMagazzinoOrFornitoreIndex = -1;
		Long oidMagazzinoOrFornitore = -1L;
		if (af.getMagazzino() != null && af.getFornitore() == null) {
			amMagazzinoOrFornitoreIndex = ApprovvigionamentoFarmaco.MAGAZZINO;
			oidMagazzinoOrFornitore = af.getMagazzino().getOid();
			if (oidMagazzinoOrFornitore == null)
				throw new IllegalArgumentException(
						"inammissibile, OID null per l'istanza di " + Magazzino.class.getName());
		} else if (af.getMagazzino() == null && af.getFornitore() != null) {
			amMagazzinoOrFornitoreIndex = ApprovvigionamentoFarmaco.FORNITORE;
			oidMagazzinoOrFornitore = af.getFornitore().getOid();
			if (oidMagazzinoOrFornitore == null)
				throw new IllegalArgumentException(
						"inammissibile, OID null per l'istanza di " + Fornitore.class.getName());
		}
		setApprovvigionamentoFarmaco(oid, amMagazzinoOrFornitoreIndex, oidMagazzinoOrFornitore, amFieldToAssignTs, ts,
				conn);
		af.setCalendar(amFieldToAssignTs, ts);
		return af;
	}

	protected static void setApprovvigionamentoFarmaco(long oid, int amMagazzinoOrFornitoreIndex,
													   long oidMagazzinoOrFornitore, int amFieldToAssignTs, Calendar ts, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		try {
			String foreignKeySetExpression = "";
			if (amMagazzinoOrFornitoreIndex != -1 && oidMagazzinoOrFornitore != -1) {
				String foreignKeyColumnName = null;
				if (amMagazzinoOrFornitoreIndex == ApprovvigionamentoFarmaco.MAGAZZINO) {
					foreignKeyColumnName = "OID_MAGAZZINO";
				} else if (amMagazzinoOrFornitoreIndex == ApprovvigionamentoFarmaco.FORNITORE) {
					foreignKeyColumnName = "OID_FORNITORE";
				} else {
					throw new IllegalArgumentException("inammissibile, l'indice " + amMagazzinoOrFornitoreIndex
							+ " non rappresenta uno degli attributi del metodo "
							+ ApprovvigionamentoFarmaco.class.getName()
							+ " riconducibile ad una delle foreign key note");
				}
				foreignKeySetExpression = foreignKeyColumnName + " = " + oidMagazzinoOrFornitore + ", ";
			}
			//
			String disponbilitaColumnName = null;
			if (amFieldToAssignTs == ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_REQ) {
				disponbilitaColumnName = "DISPONIBILITA_RICHIESTA_REQ";
			} else if (amFieldToAssignTs == ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_RES) {
				disponbilitaColumnName = "DISPONIBILITA_RICHIESTA_RES";
			} else if (amFieldToAssignTs == ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_REQ) {
				disponbilitaColumnName = "DISPONIBILITA_CONFERMATA_REQ";
			} else if (amFieldToAssignTs == ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_RES) {
				disponbilitaColumnName = "DISPONIBILITA_CONFERMATA_RES";
			} else {
				throw new IllegalArgumentException("inammissibile, l'indice " + amFieldToAssignTs
						+ " non rappresenta uno degli attributi del metodo " + ApprovvigionamentoFarmaco.class.getName()
						+ " riconducibile dei campi cui assegnare SYSDATE");
			}
			//
			Properties params = new Properties();
			params.setProperty("foreignKeySetExpression", "" + foreignKeySetExpression);
			params.setProperty("disponbilitaColumnName", disponbilitaColumnName);
			params.setProperty("ts", SqlUtilities.calendarToOracleToDateSET(ts));
			params.setProperty("OID", "" + oid);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setApprovvigionamentoFarmacoSysdateTo.sql", params);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	public static StatusInfo setApprovvigionamentoFarmacoQuantitaStauts(long oid, int quantita, long oidStatusToAssign,
																		String statusDescr, String statusTechDescr, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			Calendar now = Calendar.getInstance();
			StatusInfo status = new StatusInfo();
			status.setStatus(ItemStatus.getByOid(oidStatusToAssign));
			status.setStatusDescr(statusDescr);
			status.setStatusTechDescr(statusTechDescr);
			status.setStatusUpdatedTs(now);
			//
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setApprovvigionamentoFarmacoQuantitaStauts.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// QUANTITA = ?
			stmt.setInt(++col, quantita);
			// OID_STATUS = ?
			stmt.setLong(++col, status.getStatus().getOid());
			// STATUS_DESCR = ?
			SqlUtilities.setStringAsClob(stmt, ++col, status.getStatusDescr());
			// STATUS_TECH_DESCR = ?
			SqlUtilities.setStringAsClob(stmt, ++col, status.getStatusTechDescr());
			// STATUS_UPDATED_TS = ?
			SqlUtilities.setCalendar(stmt, ++col, status.getStatusUpdatedTs());
			// OID = ?
			stmt.setLong(++col, oid);
			stmt.executeUpdate();
			//
			return status;
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	public static void deleteApprovvigionamentoFarmacoByOid(long oid, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = "DELETE FROM FAI_APPROVVIGIONAMENTO_FARMACO WHERE OID = " + oid;
		fai.common.db.SqlQueries.executeUpdate(sql, conn);
	}

	public static int countApprovvigionamentoFarmaco(long oid, Integer amMagazzinoOrFornitoreIndex,
													 Long oidMagazzinoOrFornitore, int[] amNotNullField, int[] amNullField, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			if (amNotNullField == null)
				amNotNullField = new int[0];
			if (amNullField == null)
				amNullField = new int[0];
			//
			ModelAttr2TableColConverter conv = new ModelAttr2TableColConverter();
			//
			String fornitoreOrdMagazzinoFk = "";
			if (amMagazzinoOrFornitoreIndex != null && oidMagazzinoOrFornitore != null) {
				fornitoreOrdMagazzinoFk += "AND "
						+ conv.getTableColName(ApprovvigionamentoFarmaco.class, amMagazzinoOrFornitoreIndex) + " = "
						+ oidMagazzinoOrFornitore;
			} else if ((amMagazzinoOrFornitoreIndex != null && oidMagazzinoOrFornitore == null)
					|| (amMagazzinoOrFornitoreIndex == null && oidMagazzinoOrFornitore != null)) {
				throw new IllegalArgumentException(
						"combinazione di parametri non ammessa: l'indice identificativo dell'attributo del Magazzino o del Fornitore ("
								+ amMagazzinoOrFornitoreIndex + ") e l'OID di quest'ultimo (" + oidMagazzinoOrFornitore
								+ ") devono essere entrambi null oppure entrambi non null ");
			}
			//
			String nullOrNotNullWC = "";
			int[][] amField = new int[][] { amNotNullField, amNullField };
			for (int i = 0; i < amField.length; i++) {
				for (int j = 0; j < amField[i].length; j++) {
					nullOrNotNullWC += "AND " + conv.getTableColName(ApprovvigionamentoFarmaco.class, amField[i][j])
							+ " IS " + (i == 0 ? "NOT NULL" : "NULL") + " ";
				}
			}
			//
			Properties params = new Properties();
			params.setProperty("OID", "" + oid);
			params.setProperty("fornitoreOrdMagazzinoFk", "" + fornitoreOrdMagazzinoFk);
			params.setProperty("otherWC", "" + nullOrNotNullWC);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "countApprovvigionamentoFarmaco.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return count;

	}

	public static void deleteAllListiniDisponibilitaTemp(Connection conn) throws Exception {
		fai.common.db.SqlQueries.executeUpdate("DELETE FROM FAI_LISTINI_DISPONIBILITA_TEMP", conn);
	}

	public static int insertListiniDisponibilitaTempByApprovvFarmaco(long oidStatus, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		int recordCount = 0;
		try {
			Properties params = new Properties();
			params.setProperty("OID_STATUS", "" + oidStatus);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertListiniDisponibilitaTempByApprovvFarmaco.sql", params);
			stmt = conn.createStatement();
			recordCount = stmt.executeUpdate(sql);
			logger.info(LOG_PREFIX + recordCount + " record inseriti");
			return recordCount;
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	public static List<Object[]> getAllBestOffersFornitori(Connection conn, Set<Long> selectedFornitori) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		String oidList = "(";
		List<Object[]> list = new LinkedList<Object[]>();
		try {
			for (Long oid : selectedFornitori) {
				oidList += oid + ",";
			}
			if(oidList.length() > 1)
				oidList = oidList.substring(0, oidList.length() - 1);
			else
				oidList += 0;
			oidList += ")";
			Properties params = new Properties();
			params.setProperty("OID_LIST", oidList);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllBestOffersFornitori.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Object[] o = new Object[4];
				int i = 0;
				o[i++] = rs.getLong("OID_FORNITORE");
				o[i++] = rs.getString("TIPO_CODICE");
				o[i++] = rs.getString("CODICE");
				o[i++] = rs.getDouble("PREZZO");
				list.add(o);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

	public static void deleteListiniDisponibilitaTemp(String codiceMinSan, String codiceEan, Long oidFornitore,
													  Connection conn) throws Exception {
		int deleteCalls = 0;
		if (codiceMinSan != null && !"".equals(codiceMinSan.trim())) {
			deleteListiniDisponibilitaTemp(true, codiceMinSan, oidFornitore, conn);
			deleteCalls++;
		}
		if (codiceEan != null && !"".equals(codiceEan.trim())) {
			deleteListiniDisponibilitaTemp(false, codiceEan, oidFornitore, conn);
			deleteCalls++;
		}
		if (deleteCalls == 0) {
			throw new IllegalArgumentException(
					"impossibile cancellare il record FAI_LISTINI_DISPONIBILITA_TEMP: il codice MinSan a l'EAN passati sono entrambi null");
		}

	}

	public static void deleteListiniDisponibilitaTemp(boolean codiceMinSan, String codiceValue, Long oidFornitore,
													  Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		try {
			Properties params = new Properties();
			String codiceColumn = codiceMinSan ? "CODICE_MINSAN" : "CODICE_EAN";
			params.setProperty("codiceWC", codiceColumn + " " + SqlUtilities.getAsStringFieldValueWC(codiceValue));
			params.setProperty("fornitoreWC", oidFornitore == null ? "" : " AND OID_FORNITORE = " + oidFornitore);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "deleteListiniDisponibilitaTemp.sql", params);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	public static int countListiniDisponibilitaTemp(Connection conn) throws Exception {
		return fai.common.db.SqlQueries.executeCount("SELECT COUNT(*) FROM FAI_LISTINI_DISPONIBILITA_TEMP", conn);
	}

	public static int setOrdineInSuspendedIfApprovToProcess(Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		int updatedRecordCount = 0;
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setOrdineInSuspendedIfApprovToProcess.sql");
			stmt = conn.createStatement();
			updatedRecordCount = stmt.executeUpdate(sql);
			return updatedRecordCount;
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	public static List<ApprovvigionamentoFarmaco> getAllOrdineInDataLinkedToSuspendedOnes(boolean fornitoreIsNotNull,
																						  boolean magazzinoIsNotNull, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<ApprovvigionamentoFarmaco> list = new LinkedList<ApprovvigionamentoFarmaco>();
		Hashtable<Long, OrdineIn> ordini = new Hashtable<Long, OrdineIn>();
		try {
			Properties params = new Properties();
			params.setProperty("oidMagazzino", fornitoreIsNotNull ? "IS NOT NULL" : "IS NULL");
			params.setProperty("oidFornitore", magazzinoIsNotNull ? "IS NOT NULL" : "IS NULL");
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllOrdineInDataLinkedToSuspendedOnes.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ApprovvigionamentoFarmaco approv = null;
			while (rs.next()) {
				long oidOrdine = rs.getLong("ordine_oid");
				OrdineIn ordine = ordini.get(oidOrdine);
				if (ordine == null) {
					ordine = new OrdineIn();
					ordine.setOid(oidOrdine);
					ordine.setStatus(asStatusInfo(rs, "ordine_"));
					ordini.put(oidOrdine, ordine);
				}
				//
				OrdineInRigaDett rigaDett = new OrdineInRigaDett();
				rigaDett.setOid(rs.getLong("riga_oid"));
				rigaDett.setCodiceMinSan(rs.getString("riga_CODICE_MINSAN"));
				rigaDett.setCodiceEan(rs.getString("riga_CODICE_EAN"));
				rigaDett.setQuantita(rs.getInt("riga_QUANTITA"));
				ordine.addRigaDett(rigaDett);
				//
				ApprovToRiga a2r = new ApprovToRiga();
				a2r.setOid(rs.getLong("a2r_OID"));
				a2r.setQuantita(rs.getInt("a2r_QUANTITA"));
				a2r.setRigaDett(rigaDett);
				//
				Long oidApprov = rs.getLong("approv_OID");
				if (approv == null || !approv.getOid().equals(oidApprov)) {
					approv = new ApprovvigionamentoFarmaco();
					approv.setOid(oidApprov);
					approv.setCodiceMinSan(rs.getString("approv_CODICE_MINSAN"));
					approv.setCodiceEan(rs.getString("approv_CODICE_EAN"));
					approv.setQuantita(rs.getInt("approv_QUANTITA"));
					approv.setStatus(asStatusInfo(rs, "approv_"));
					list.add(approv);
				}
				approv.addApprovRigaDett(a2r);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

	/**
	 * ATTENZIONE! <br/>
	 * Solo i campi connessi con la "richiesta di disponibilitï¿½" dell'istanza
	 * {@link DisponibilitaTemp} saranno effettivamente presi in considerazione
	 *
	 * @param dispTemp
	 * @param conn
	 * @throws Exception
	 */
	public static void storeRequestDisponibilitaTemp(DisponibilitaTemp dispTemp, Connection conn) throws Exception {
		insertDisponibilitaTemp(dispTemp, conn);
		insertDisponibilitaTempReq(dispTemp.getOid(), dispTemp.getAllReq(), conn);
	}

	private static void insertDisponibilitaTemp(DisponibilitaTemp dispTemp, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			if ((dispTemp.getMagazzino() == null || dispTemp.getMagazzino().getOid() == null)
					&& (dispTemp.getFornitore() == null || dispTemp.getFornitore().getOid() == null)) {
				throw new IllegalArgumentException("inammissibile, l'istanza " + DisponibilitaTemp.class.getName()
						+ " ricevuta in input deve specificare o il Magazzino o il Fornitore (comprensivo di OID); ricevuto, rispettivamente,\r\n "
						+ (dispTemp.getMagazzino() == null ? null : dispTemp.getMagazzino().toXml()) + "\r\n e \r\n"
						+ (dispTemp.getFornitore() == null ? null : dispTemp.getFornitore().toXml()));
			}
			if (dispTemp.getOid() == null) {
				dispTemp.setOid(fai.common.db.SqlQueries.getOidNextVal(conn));
			}
			if (dispTemp.getRequestTs() == null) {
				dispTemp.setRequestTs(Calendar.getInstance());
			}
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertDisponibilitaTemp.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// OID
			stmt.setLong(++col, dispTemp.getOid());
			// OID_FORNITORE
			SqlUtilities.setLong(stmt, ++col,
					dispTemp.getFornitore() == null ? null : dispTemp.getFornitore().getOid());
			// OID_MAGAZZINO
			SqlUtilities.setLong(stmt, ++col,
					dispTemp.getMagazzino() == null ? null : dispTemp.getMagazzino().getOid());
			// REQUEST_TS
			SqlUtilities.setCalendar(stmt, ++col, dispTemp.getRequestTs());
			// REQUEST_RAW_DATA
			SqlUtilities.setStringAsClob(stmt, ++col, dispTemp.getRequestRawData());
			// OID_STATUS / STATUS_*
			col = setStatusInfo(stmt, ++col, dispTemp.getStatus());
			//
			stmt.executeUpdate();
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	private static void insertDisponibilitaTempReq(long parentOid, DisponibilitaReqTemp[] req, Connection conn)
			throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		try {
			for (int i = 0; i < req.length; i++) {
				if (req[i].getOid() == null) {
					req[i].setOid(fai.common.db.SqlQueries.getOidNextVal(conn));
				}
				Properties params = new Properties();
				params.setProperty("OID", "" + req[i].getOid());
				params.setProperty("OID_DISPONIBILITATEMP", "" + parentOid);
				params.setProperty("CODICE_MINSAN", SqlUtilities.getAsStringFieldValue(req[i].getCodiceMinSan()));
				params.setProperty("CODICE_EAN", SqlUtilities.getAsStringFieldValue(req[i].getCodiceEan()));
				params.setProperty("QUANTITA_RICHIESTA", "" + req[i].getQuantitaRichiesta());
				sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertDisponibilitaTempReq.sql", params);
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
				stmt = null;
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	/**
	 * ATTENZIONE! <br/>
	 * Solo i campi connessi con la "risposta" alla "richiesta di disponibilitï¿½"
	 * dell'istanza {@link DisponibilitaTemp} saranno presi in considerazione
	 *
	 *
	 * @param dispTemp
	 * @param conn
	 * @throws Exception
	 */
	public static void storeResponseDisponibilitaTemp(DisponibilitaTemp dispTemp, Connection conn) throws Exception {
		updateDisponibilitaTempResponse(dispTemp, conn);
		DisponibilitaReqTemp[] req = dispTemp.getAllReq();
		for (int i = 0; i < req.length; i++) {
			DisponibilitaResTemp[] resp = req[i].getAllResp();
			insertDisponibilitaTempRes(req[i].getOid(), resp, conn);
		}
	}

	private static void updateDisponibilitaTempResponse(DisponibilitaTemp dispTemp, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateDisponibilitaTemp_response.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// ,RESPONSE_TS = ?
			SqlUtilities.setCalendar(stmt, ++col, dispTemp.getResponseTs());
			// ,RESPONSE_RAW_DATA = ?
			SqlUtilities.setStringAsClob(stmt, ++col, dispTemp.getResponseRawData());
			// OID_STATUS / STATUS_*
			col = setStatusInfo(stmt, ++col, dispTemp.getStatus());
			// OID = ?
			stmt.setLong(++col, dispTemp.getOid());
			//
			stmt.executeUpdate();
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	private static void insertDisponibilitaTempRes(long parentOid, DisponibilitaResTemp[] res, Connection conn)
			throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		try {
			for (int i = 0; i < res.length; i++) {
				if (res[i].getOid() == null) {
					res[i].setOid(fai.common.db.SqlQueries.getOidNextVal(conn));
				}
				Properties params = new Properties();
				params.setProperty("OID", "" + res[i].getOid());
				params.setProperty("OID_DISPONIBILITAREQTEMP", "" + parentOid);
				params.setProperty("QUANTITA_DISPONIBILE", "" + res[i].getQuantitaDisponibile());
				sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertDisponibilitaTempRes.sql", params);
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
				stmt = null;
			}

		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

	}

	public static void deleteAllDisponibilitaTemp(Connection conn) throws Exception {
		fai.common.db.SqlQueries.executeUpdate("DELETE FROM FAI_DISPONIBILITA_RES_TEMP", conn);
	}

	public static List<FornitoreCalendar> getAllFornitoreCalendarByDateOfWeek(Connection conn, int dateOfWeek) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<FornitoreCalendar> list = new LinkedList<FornitoreCalendar>();
		try {
			Properties params = new Properties();
			params.setProperty("DATE_OF_WEEK", "" + dateOfWeek);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllFornitoreCalendarByDateOfWeek.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			FornitoreCalendar f = null;
			while (rs.next()) {
				f = new FornitoreCalendar();
				f.setOid(rs.getLong("OID"));
				f.setOidFornitore(rs.getLong("OID_FORNITORE"));
				f.setDateOfWeek(rs.getInt("DATE_OF_WEEK"));
				f.setHourStart(SqlUtilities.getCalendar(rs, "HOUR_START"));
				f.setHourEnd(SqlUtilities.getCalendar(rs, "HOUR_END"));
				list.add(f);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

	public static List<UploadTaskProperty> findUploadTaskProperty(String key,Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		String sql;
		Statement stmt = null;
		ResultSet rs = null;
		List<UploadTaskProperty> list = new ArrayList<>();
		try {
			String wc = "";
			if (key != null) {
				wc = "WHERE 1=1";
				if (key != null) wc += " AND KEY = "+SqlUtilities.getAsStringFieldValue(key);
			}
			Properties params = new Properties();
			params.setProperty("whereCondition", wc);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "findUploadTaskConfig.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UploadTaskProperty uploadTaskProperty = new UploadTaskProperty();
				uploadTaskProperty.setOid(rs.getLong("OID"));
				uploadTaskProperty.setKey(rs.getString("KEY"));
				uploadTaskProperty.setValue(rs.getString("VALUE"));
				list.add(uploadTaskProperty);
			}
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}

		return list;
	}


	public static void insertUploadTaskConfig(UploadTaskConfig uploadTaskConfig, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			long oid = fai.common.db.SqlQueries.getOidNextVal(conn);
			uploadTaskConfig.setOid(oid);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertUploadTask.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// OID
			stmt.setLong(++col, uploadTaskConfig.getOid());
			// DESCR
			SqlUtilities.setString(stmt, ++col, uploadTaskConfig.getDescr());
			// CSV_FILE_NAME
			SqlUtilities.setString(stmt, ++col, uploadTaskConfig.getCsvFileName());
			// MAGAZZINO_ACRONYM
			SqlUtilities.setString(stmt, ++col, uploadTaskConfig.getMagazzinoAcronym());
			// OID_STATUS / STATUS_*
			col = setUploadCSVStatusInfo(stmt, ++col, uploadTaskConfig.getStatus());
			// CREATION_TS
			SqlUtilities.setCalendar(stmt, ++col, uploadTaskConfig.getCreationTs());
			// RUN_START_TS
			SqlUtilities.setCalendar(stmt, ++col, uploadTaskConfig.getRunStartTs());
			// RUN_END_TS
			SqlUtilities.setCalendar(stmt, ++col, uploadTaskConfig.getRunEndTs());
			// RUN_DONE
			SqlUtilities.setString(stmt, ++col, uploadTaskConfig.getRunDone() != null
					&& uploadTaskConfig.getRunDone() ? "S" : "N");
			//RUN_DESCR
			SqlUtilities.setString(stmt, ++col, uploadTaskConfig.getRunDescr());
			stmt.executeUpdate();
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	public static List<UploadTaskConfig> getAllUploadTask(Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<UploadTaskConfig> list = new LinkedList<>();
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllUploadTask.sql");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				UploadTaskConfig uploadTaskConfig = new UploadTaskConfig();
				uploadTaskConfig.setOid(rs.getLong("TASK_OID"));
				uploadTaskConfig.setDescr(rs.getString("TASK_DESCRIPTION"));
				uploadTaskConfig.setCsvFileName(rs.getString("TASK_CSV_FILE_NAME"));
				uploadTaskConfig.setMagazzinoAcronym(rs.getString("TASK_MAGAZZINO_ACRONYM"));
				uploadTaskConfig.setStatus(asUploadStatusInfo(rs, ""));
				uploadTaskConfig.setCreationTs(SqlUtilities.getCalendar(rs, "TASK_CREATION_TS"));
				list.add(uploadTaskConfig);
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

	public static UploadTaskConfig findUploadTask(Long taskOID,Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		String sql;
		Statement stmt = null;
		ResultSet rs = null;
		UploadTaskConfig uploadTaskConfig = null;
		try {
			String wc = "";
			if (taskOID != null) {
				wc = "WHERE OID=" + taskOID;
			}
			Properties params = new Properties();
			params.setProperty("whereCondition", wc);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "findUploadTask.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				uploadTaskConfig = new UploadTaskConfig();
				uploadTaskConfig.setOid(rs.getLong("OID"));
				uploadTaskConfig.setDescr(rs.getString("DESCR"));
				uploadTaskConfig.setCsvFileName(rs.getString("CSV_FILE_NAME"));
				uploadTaskConfig.setMagazzinoAcronym(rs.getString("MAGAZZINO_ACRONYM"));
				uploadTaskConfig.setStatus(asUploadStatusInfo(rs, ""));
				uploadTaskConfig.setCreationTs(SqlUtilities.getCalendar(rs, "CREATION_TS"));
				uploadTaskConfig.setCreationTs(SqlUtilities.getCalendar(rs, "RUN_START_TS"));
				uploadTaskConfig.setCreationTs(SqlUtilities.getCalendar(rs, "RUN_END_TS"));
				uploadTaskConfig.setRunDone(SqlUtilities.asBoolean(rs.getString("RUN_DONE")));
				uploadTaskConfig.setRunDescr(rs.getString("RUN_DESCR"));
			}
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}

		return uploadTaskConfig;
	}

	public static void setUploadTaskNewSession(long oidConfig, Connection conn) throws Exception {
		final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		//
		String sql = null;
		Statement stmt = null;
		try {
			Properties params = new Properties();
			params.setProperty("RUN_START_TS", SqlUtilities.calendarToOracleToDate(Calendar.getInstance()));
			params.setProperty("OID", ""+oidConfig);
			params.setProperty("OID_STATUS", ""+ UploadCSVStatus.VALUE_PROCESSING.getOid());
			params.setProperty("STATUS_DESCR", ""+ SqlUtilities.getAsStringFieldValue(UploadCSVStatus.VALUE_PROCESSING.getDescr()));
			params.setProperty("STATUS_TECH_DESCR", SqlUtilities.getAsStringFieldValue(UploadCSVStatus.VALUE_PROCESSING.getDescr()));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setUploadTaskNewSession.sql", params);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	public static void setUploadTaskSessionCompleted(long oidConfig, boolean success, long oidStatus,
													 String statusDescr, String runDescr, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setUploadedTaskSessionCompleted.sql");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, success ? "S" : "N");
			SqlUtilities.setStringAsClob(stmt, 2, runDescr);
			stmt.setLong(3, oidStatus);
			stmt.setString(4, statusDescr);
			stmt.setString(5, "");
			stmt.setLong(6, oidConfig);
			stmt.executeUpdate();
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}

	}

	protected static int setUploadCSVStatusInfo(PreparedStatement stmt, int startingCol, UploadStatusInfo uploadStatus) throws Exception {
		int col = startingCol - 1;
		// OID_STATUS
		stmt.setLong(++col, uploadStatus.getStatus().getOid());
		// STATUS_DESCR
		SqlUtilities.setString(stmt, ++col, uploadStatus.getStatusDescr());
		// STATUS_TECH_DESCR
		SqlUtilities.setString(stmt, ++col, uploadStatus.getStatusTechDescr());
		// STATUS_UPDATED_TS
		SqlUtilities.setCalendar(stmt, ++col, uploadStatus.getStatusUpdatedTs());
		//
		return col;
	}

	public static List<OrdineInCollection> findOrdineInCollectionByInputResource(String inputResource, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		String sql;
		Statement stmt = null;
		ResultSet rs = null;
		List<OrdineInCollection> list = new ArrayList<>();
		try {
			String wc = "WHERE INPUT_RESOURCE=" + SqlUtilities.getAsStringFieldValue(inputResource);
			Properties params = new Properties();
			params.setProperty("whereCondition", wc);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getOrdineInCollectionByCondition.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				OrdineInCollection ordineInCollection = new OrdineInCollection();
				ordineInCollection.setOid(rs.getLong("OID"));
				ordineInCollection.setUniqueID(rs.getString("UNIQUE_ID"));
				list.add(ordineInCollection);
			}
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

	public static int countOrdineInByCollectionID(Long oidOrdineincollection, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		int totalOrders;
		try {
			Properties params = new Properties();
			params.setProperty("OID_ORDINEINCOLLECTION", "" + oidOrdineincollection);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "countOrdineInByCollectionID.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			totalOrders = rs.getInt(1);

		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return totalOrders;
	}
	public static void setUploadTaskStatus(long taskOID, long oidStatusToAssign, String statusDescr,
														 String statusTechDescr, Connection conn) throws Exception {
		final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		//
		String sql = null;
		Statement stmt = null;
		try {
			Properties params = new Properties();
			params.setProperty("RUN_START_TS", SqlUtilities.calendarToOracleToDate(Calendar.getInstance()));
			params.setProperty("OID", ""+taskOID);
			params.setProperty("OID_STATUS", ""+ oidStatusToAssign);
			params.setProperty("STATUS_DESCR", ""+ SqlUtilities.getAsStringFieldValue(statusDescr));
			params.setProperty("STATUS_TECH_DESCR", SqlUtilities.getAsStringFieldValue(statusTechDescr));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setUploadTaskStatus.sql", params);

			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	public static OrdineOut insertOrdineOut(OrdineOut ordineOut, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			ordineOut.setOid(fai.common.db.SqlQueries.getOidNextVal(conn));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertOrdineOut.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// OID
			stmt.setLong(++col, ordineOut.getOid());
			// ID_ORDINE
			stmt.setString(++col, ordineOut.getIdOrdine());
			// ID_VENDITA
			stmt.setString(++col, ordineOut.getIdRicevuta());
			// OID_FORNITORE
			stmt.setLong(++col, ordineOut.getFornitore().getOid());
			// OID_STATUS
			stmt.setLong(++col, ordineOut.getStatus().getOid());
			// CREATION_TS
			SqlUtilities.setCalendar(stmt, ++col, Calendar.getInstance());
			stmt.executeUpdate();
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}

		return ordineOut;
	}

	public static void setUploadTaskOrderStatus(long taskOID, String orderStatus, Connection conn) throws Exception {
		final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		//
		String sql = null;
		Statement stmt = null;
		try {
			Properties params = new Properties();
			params.setProperty("OID", ""+taskOID);
			params.setProperty("ORDER_STATUS", orderStatus);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setUploadTaskOrderStatus.sql", params);

			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	protected static UploadStatusInfo asUploadStatusInfo(ResultSet rs, String columnPrefix) throws Exception {
		if (columnPrefix == null)
			columnPrefix = "";
		columnPrefix = columnPrefix.trim();
		UploadStatusInfo usi = UploadStatusInfo.newInstance(UploadCSVStatus.getByOid(rs.getLong(columnPrefix + "OID_STATUS")), null,
				null);
		usi.setStatusDescr(rs.getString(columnPrefix + "STATUS_DESCR"));
		usi.setStatusTechDescr(rs.getString(columnPrefix + "STATUS_TECH_DESCR"));
		usi.setStatusUpdatedTs(SqlUtilities.getCalendar(rs, columnPrefix + "STATUS_UPDATED_TS"));
		return usi;
	}

	public static List<ApprovvigionamentoFarmaco> getAllApprovvigionamentoFarmacoByFornitore(
			ItemStatus status, Fornitore fornitore, Connection conn)
			throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		Properties params = new Properties();
		params.setProperty("OID_STATUS", SqlUtilities.getAsIntegerFieldValue(status.getOid().intValue()));
		params.setProperty("FORNITORE_ID", SqlUtilities.getAsIntegerFieldValue(fornitore.getOid().intValue()));
		String sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getApprovvigionamentoFarmacoByFornitore.sql", params);
		//return getAllApprovvigionamentoCommon(sql, conn);
		
		Statement statement = null;
		ResultSet resultSet = null;
		List<ApprovvigionamentoFarmaco> list = new LinkedList<ApprovvigionamentoFarmaco>();
		try {
			long approvOidBak = -1;
			ApprovvigionamentoFarmaco currApprov = null;
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				long approvOid = resultSet.getLong("OID");
				if (approvOid != approvOidBak) {
					currApprov = new ApprovvigionamentoFarmaco();
					currApprov.setOid(approvOid);
					currApprov.setFornitore(fornitore);
					currApprov.setCodiceMinSan(resultSet.getString("CODICE_MINSAN"));
					currApprov.setCodiceEan(resultSet.getString("CODICE_EAN"));
					currApprov.setQuantita(resultSet.getInt("QUANTITA"));
					currApprov
							.setDisponibilitaRichiestaReq(SqlUtilities.getCalendar(resultSet, "DISPONIBILITA_RICHIESTA_REQ"));
					currApprov
							.setDisponibilitaRichiestaRes(SqlUtilities.getCalendar(resultSet, "DISPONIBILITA_RICHIESTA_RES"));
					currApprov.setDisponibilitaConfermataReq(
							SqlUtilities.getCalendar(resultSet, "DISPONIBILITA_CONFERMATA_REQ"));
					currApprov.setDisponibilitaConfermataRes(
							SqlUtilities.getCalendar(resultSet, "DISPONIBILITA_CONFERMATA_RES"));
					currApprov.setIdOrdine(resultSet.getString("ID_ORDINE"));
					currApprov.setIdRicevuta(resultSet.getString("ID_RICEVUTA"));
					currApprov.setStatus(asStatusInfo(resultSet, ""));
					currApprov.setPrezzoUnitario(SqlUtilities.getDouble(resultSet, "PREZZO_UNITARIO"));
					currApprov.setPrezzoTotale(SqlUtilities.getDouble(resultSet, "PREZZO_TOTALE"));
					currApprov.setAliquotaIva(SqlUtilities.getDouble(resultSet, "ALIQUOTA_IVA"));
					currApprov.setAliquotaIvaInclusa(SqlUtilities.asBoolean(resultSet.getString("ALIQUOTA_IVA_INCLUSA")));
					currApprov.setMagazzinoAcronym(resultSet.getString("MAGAZZINO_ACRONYM"));
					Long oidOrdineOut = SqlUtilities.getLong(resultSet, "OID_ORDINEOUT");
					if (oidOrdineOut != null) {
						currApprov.setOrdineOut(new OrdineOut());
						currApprov.getOrdineOut().setOid(oidOrdineOut);
					}
					list.add(currApprov);
				}
				approvOidBak = approvOid;
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(statement);
			SqlUtilities.closeWithNoException(resultSet);
		}
		return list;
	}

	public static void deleteApprovvigionamentoWithQuantitaZero(Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = "DELETE FROM FAI_APPROVVIGIONAMENTO_FARMACO WHERE (QUANTITA = 0 "
				+" AND OID_MAGAZZINO IS NOT NULL) OR (QUANTITA = 0 AND OID_FORNITORE IS NOT NULL)";
		fai.common.db.SqlQueries.executeUpdate(sql, conn);
	}

	public static void updateApprovvigionamentoFarmacoOrdine(ApprovvigionamentoFarmaco approv,  Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateApprovvigionamentoFarmacoOrineOut.sql");
			stmt = conn.prepareStatement(sql);
			int col = 0;
			// ID_ORDINE
			SqlUtilities.setLong(stmt, ++col,
					approv.getOrdineOut() == null ? null : approv.getOrdineOut().getOid());
			// QUANTITA
			stmt.setInt(++col, approv.getQuantita());
			// OID STatus
			stmt.setLong(++col, approv.getStatus().getStatus().getOid());
			//DISPONIBILITA_CONFERMATA_REQ
			stmt.setDate(++col, approv.getDisponibilitaConfermataReq() == null ? null : new java.sql.Date(approv.getDisponibilitaConfermataReq().getTimeInMillis()));
			// OID
			stmt.setLong(++col, approv.getOid());
			//
			stmt.executeUpdate();
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
		}
	}

	public static ApprovvigionamentoFarmaco checkMissingApprovvigionamentoFarmaco(String codiceMinSan, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		ApprovvigionamentoFarmaco approv = null;
		String sql;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String wc = "WHERE OID_MAGAZZINO IS NULL AND OID_FORNITORE IS NULL";
			if (codiceMinSan != null) wc += " AND CODICE_MINSAN = "+SqlUtilities.getAsStringFieldValue(codiceMinSan);
			Properties params = new Properties();
			params.setProperty("whereCondition", wc);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "checkMissingApprovvigionamentoFarmaco.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				approv = new ApprovvigionamentoFarmaco();
				approv.setOid( rs.getLong("OID"));
				approv.setQuantita(rs.getInt("QUANTITA"));
				approv.setStatus(asStatusInfo(rs, ""));
			}
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return approv;
	}

	public static List<OrdineIn> getAllOrdine(String whereCondition, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		String sql;
		Statement stmt = null;
		ResultSet rs = null;
		List<OrdineIn> list = new ArrayList<>();
		try {
			String wc = "";

			if(StringUtils.isNotBlank(whereCondition))
				wc = whereCondition;
			else
				wc = "WHERE 1=1";
//			if(collectionOID != null && collectionOID > 0){
//				wc = "WHERE ordine.OID_ORDINEINCOLLECTION=" + collectionOID;
//			}else {
//				wc = "WHERE 1=1";
//			}
			Properties params = new Properties();
			params.setProperty("whereCondition", wc);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllOrdine.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				OrdineIn ordine = new OrdineIn();
				ordine.setOid(rs.getLong("OID"));
				ordine.setStatus(asStatusInfo(rs, ""));
				ordine.setIdVendita(rs.getLong("ID_VENDITA"));
				ordine.setNomeCompletoAcquirente(rs.getString("NOME_COMPLETO_ACQUIRENTE"));
				ordine.setEmailAcquirente(rs.getString("EMAIL_ACQUIRENTE"));
				ordine.setPrezzoTotale(rs.getDouble("PREZZO_TOTALE"));
				ordine.setQuantita(rs.getInt("QUANTITA"));
				//ordine.setOIdCollection(rs.getLong("OID_ORDINEINCOLLECTION"));
				ordine.setFulfilled(SqlUtilities.asBoolean(rs.getString("FULFILLED")));
				ordine.setOrderCount(rs.getInt("ORDER_COUNT"));
				list.add(ordine);
			}
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;
	}


	public static Integer countOrdineInDataLinkedToMissingOnes(Long oidOrdine, boolean fornitoreIsNotNull,
															   boolean magazzinoIsNotNull, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		Integer missingCount = 0;
		try {
			Properties params = new Properties();
			params.setProperty("oidMagazzino", fornitoreIsNotNull ? "IS NOT NULL" : "IS NULL");
			params.setProperty("oidFornitore", magazzinoIsNotNull ? "IS NOT NULL" : "IS NULL");
			params.setProperty("oidOrdine", "" + oidOrdine);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "countOrdineInDataLinkedToMissingOnes.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				missingCount = rs.getInt("RECORDS_COUNT");
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return missingCount;

	}

	public static List<ApprovvigionamentoFarmaco> getAllProdottiByOrder(Long oidOrdineIn, String orderBy, Connection conn)
			throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = "";
		Properties params = new Properties();
		if(oidOrdineIn != null)
			params.setProperty("WHERECONDITION", "WHERE OID_ORDINEIN=" + oidOrdineIn);
		else
			params.setProperty("WHERECONDITION", "WHERE 1=1");

		if(StringUtils.isNotBlank(orderBy)){
			params.setProperty("ORDERBY", orderBy);
		}else {
			params.setProperty("ORDERBY", "ORDER BY APPROV.OID, RIGA.OID");
		}

	 	sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllProdottiByOrder.sql", params);
		Statement stmt = null;
		ResultSet rs = null;
		List<ApprovvigionamentoFarmaco> list = new LinkedList<>();
		Hashtable<Long, Magazzino> magazzini = getAllMagazzinoAsHashtableByOid(null, conn);
		Hashtable<Long, Fornitore> fornitori = getAllFornitoreAsHashtableByOid(conn);
		List<Long> approvtoRigaOids = new ArrayList<>();
		try {
			long approvOidBak = -1;
			ApprovvigionamentoFarmaco currApprov = null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				long approvOid = rs.getLong("OID");
				if (approvOid != approvOidBak) {
					currApprov = new ApprovvigionamentoFarmaco();
					currApprov.setOid(approvOid);
					currApprov.setCodiceMinSan(rs.getString("CODICE_MINSAN"));
					currApprov.setCodiceEan(rs.getString("CODICE_EAN"));
					currApprov.setQuantita(rs.getInt("QUANTITA"));
					currApprov
							.setDisponibilitaRichiestaReq(SqlUtilities.getCalendar(rs, "DISPONIBILITA_RICHIESTA_REQ"));
					currApprov
							.setDisponibilitaRichiestaRes(SqlUtilities.getCalendar(rs, "DISPONIBILITA_RICHIESTA_RES"));
					currApprov.setDisponibilitaConfermataReq(
							SqlUtilities.getCalendar(rs, "DISPONIBILITA_CONFERMATA_REQ"));
					currApprov.setDisponibilitaConfermataRes(
							SqlUtilities.getCalendar(rs, "DISPONIBILITA_CONFERMATA_RES"));
					currApprov.setIdOrdine(rs.getString("RIGA_OID_ORDINEIN"));
					currApprov.setIdRicevuta(rs.getString("ID_RICEVUTA"));
					currApprov.setStatus(asStatusInfo(rs, ""));
					currApprov.setPrezzoUnitario(SqlUtilities.getDouble(rs, "PREZZO_UNITARIO"));
					currApprov.setPrezzoTotale(SqlUtilities.getDouble(rs, "PREZZO_TOTALE"));
					currApprov.setAliquotaIva(SqlUtilities.getDouble(rs, "ALIQUOTA_IVA"));
					currApprov.setAliquotaIvaInclusa(SqlUtilities.asBoolean(rs.getString("ALIQUOTA_IVA_INCLUSA")));
					currApprov.setMagazzinoAcronym(rs.getString("MAGAZZINO_ACRONYM"));
					currApprov.setIdVendita(rs.getLong("ID_VENDITA"));
					currApprov.setTotaleQuantita(rs.getInt("ORDINE_QUANTITA"));
					Long oidOrdineOut = SqlUtilities.getLong(rs, "OID_ORDINEOUT");
					if (oidOrdineOut != null) {
						currApprov.setOrdineOut(new OrdineOut());
						currApprov.getOrdineOut().setOid(oidOrdineOut);
					}
					Long oidMagazzino = SqlUtilities.getLong(rs, "OID_MAGAZZINO");
					if (oidMagazzino != null) {
						Magazzino m = magazzini.get(oidMagazzino);
						if (m == null)
							throw new IllegalStateException("inammissibile, nessun " + Magazzino.class.getName()
									+ " per l'OID " + oidMagazzino);
						currApprov.setMagazzino(m);
					}
					Long oidFornitore = SqlUtilities.getLong(rs, "OID_FORNITORE");
					if (oidFornitore != null) {
						Fornitore m = fornitori.get(oidFornitore);
						if (m == null)
							throw new IllegalStateException("inammissibile, nessun " + Fornitore.class.getName()
									+ " per l'OID " + oidFornitore);

						currApprov.setFornitore(m);
					}
					list.add(currApprov);
				}
				approvOidBak = approvOid;
				//
				ApprovToRiga a2r = new ApprovToRiga();
				a2r.setOid(rs.getLong("APPROV2RIGA_OID"));
				a2r.setQuantita(rs.getInt("APPROV2RIGA_QUANTITA"));
				OrdineInRigaDett riga = new OrdineInRigaDett();
				riga.setOid(rs.getLong("RIGA_OID"));
				riga.setCodiceMinSan(rs.getString("RIGA_CODICE_MINSAN"));
				riga.setCodiceEan(rs.getString("RIGA_CODICE_EAN"));
				riga.setQuantita(rs.getInt("RIGA_QUANTITA"));
				a2r.setRigaDett(riga);
				//
				// controllo di congruenza
				if (!riga.getCodiceMinSan()
						.equals(currApprov.getCodiceMinSan()) /* AbstractModel --> non possono essere null */) {
					throw new IllegalStateException(
							"inammissibile, il record di Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) identificato dall'OID  "
									+ currApprov.getOid() + " ha un Codice MinSan (" + currApprov.getCodiceMinSan()
									+ ") differente dal Codice MinSan (" + riga.getCodiceMinSan()
									+ ") della Riga dell'Ordine in Ingresso (FAI_ORDINE_IN_RIGA_DETTAGLIO) di OID "
									+ riga.getOid() + " cui si riferisce");
				}
				if (!riga.getCodiceEan()
						.equals(currApprov.getCodiceEan()) /* AbstractModel --> non possono essere null */) {
					throw new IllegalStateException(
							"inammissibile, il record di Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) identificato dall'OID  "
									+ currApprov.getOid() + " ha un Codice EAN (" + currApprov.getCodiceEan()
									+ ") differente dal Codice EAN (" + riga.getCodiceEan()
									+ ") della Riga dell'Ordine in Ingresso (FAI_ORDINE_IN_RIGA_DETTAGLIO) di OID "
									+ riga.getOid() + " cui si riferisce");
				}
				//
				if(!approvtoRigaOids.contains(a2r.getOid())){
					currApprov.addApprovRigaDett(a2r);
					approvtoRigaOids.add(a2r.getOid());
				}
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;
	}

	public static ApprovvigionamentoFarmaco getOrdineInDataLinkedToMissingOnes(Long oidOrdine, Connection conn) throws Exception {
		final String METH_NAME = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ApprovvigionamentoFarmaco approv = null;
		try {
			Properties params = new Properties();
			params.setProperty("oidOrdine", "" + oidOrdine);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getOrdineInDataLinkedToMissingOnes.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				approv = new ApprovvigionamentoFarmaco();
				approv.setOid(rs.getLong("OID"));
				approv.setQuantita(rs.getInt("QUANTITA"));
			}
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage()
					+ "ï¿½ nell'esecuzione del metodo " + METH_NAME
					+ (sql != null
					? "; sql:" + System.getProperty("line.separator") + sql
					+ System.getProperty("line.separator")
					: "");
			logger.error(msg, th);
			throw new Exception(msg, th);
		} finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return approv;
	}

	public static List<OrdineInCollection> findOrdineInCollectionByOrder(Long  idOrdine, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		String sql;
		Statement stmt = null;
		ResultSet rs = null;
		List<OrdineInCollection> list = new ArrayList<>();
		try {
			String wc = "WHERE ORDINE.OID=" + idOrdine;
			Properties params = new Properties();
			params.setProperty("whereCondition", wc);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getOrdineInCollectionByOrder.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				OrdineInCollection ordineInCollection = new OrdineInCollection();
				ordineInCollection.setOid(rs.getLong("OID"));
				ordineInCollection.setInputResource(rs.getString("INPUT_RESOURCE"));
				list.add(ordineInCollection);
			}
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", ï¿½" + th.getMessage() + "ï¿½ nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}
	
	public static List<fai.imp.farmaclick.models.Fornitore> findAllFornitoreByCondition(String whereCondition, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<fai.imp.farmaclick.models.Fornitore> list = new LinkedList<>();
		try {
			Properties params = new Properties();
			params.setProperty("whereCondition", whereCondition);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "findAllFornitoreByCondition.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				fai.imp.farmaclick.models.Fornitore f = new fai.imp.farmaclick.models.Fornitore();
				f.setOid(rs.getLong("OID"));
				f.setCodice(rs.getString("CODICE"));
				f.setCodiceSitoLogistico(rs.getString("CODICE_SITO_LOGISTICO"));
				f.setDescrizioneBreve(rs.getString("DESCRIZIONE_BREVE"));
				f.setLastCsvFilename(rs.getString("LAST_CSV_FILENAME"));
				f.setLastCsvZipped("S".equals(rs.getString("LAST_CSV_ZIPPED")));
				f.setLastCsvDownloadUrl(rs.getString("LAST_CSV_DOWNLOAD_URL"));
				f.setLastCsvDownloadAtTs(SqlUtilities.getCalendar(rs, "LAST_CSV_DOWNLOAD_AT_TS"));
				f.setLastCsvConfirmUrl(rs.getString("LAST_CSV_CONFIRM_URL"));
				f.setLastCsvConfirmedAtTs(SqlUtilities.getCalendar(rs, "LAST_CSV_CONFIRMED_AT_TS"));
				f.setNoLongerExistsTs(SqlUtilities.getCalendar(rs, "NO_LONGER_EXISTS_TS"));
				f.setAllDataStoredTs(SqlUtilities.getCalendar(rs, "ALL_DATA_STORED_TS"));
				f.setOidConfig(rs.getLong("OID_CONFIG"));
				list.add(f);
			}
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}


	public static List<fai.imp.farmaclick.models.Fornitore> findAllFornitoreByCondition(String whereCondition, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<fai.imp.farmaclick.models.Fornitore> list = new LinkedList<>();
		try {
			Properties params = new Properties();
			params.setProperty("whereCondition", whereCondition);
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "findAllFornitoreByCondition.sql", params);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				fai.imp.farmaclick.models.Fornitore f = new fai.imp.farmaclick.models.Fornitore();
				f.setOid(rs.getLong("OID"));
				f.setCodice(rs.getString("CODICE"));
				f.setCodiceSitoLogistico(rs.getString("CODICE_SITO_LOGISTICO"));
				f.setDescrizioneBreve(rs.getString("DESCRIZIONE_BREVE"));
				f.setLastCsvFilename(rs.getString("LAST_CSV_FILENAME"));
				f.setLastCsvZipped("S".equals(rs.getString("LAST_CSV_ZIPPED")));
				f.setLastCsvDownloadUrl(rs.getString("LAST_CSV_DOWNLOAD_URL"));
				f.setLastCsvDownloadAtTs(SqlUtilities.getCalendar(rs, "LAST_CSV_DOWNLOAD_AT_TS"));
				f.setLastCsvConfirmUrl(rs.getString("LAST_CSV_CONFIRM_URL"));
				f.setLastCsvConfirmedAtTs(SqlUtilities.getCalendar(rs, "LAST_CSV_CONFIRMED_AT_TS"));
				f.setNoLongerExistsTs(SqlUtilities.getCalendar(rs, "NO_LONGER_EXISTS_TS"));
				f.setAllDataStoredTs(SqlUtilities.getCalendar(rs, "ALL_DATA_STORED_TS"));
				f.setOidConfig(rs.getLong("OID_CONFIG"));
				list.add(f);
			}
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", Â«" + th.getMessage() + "Â» nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			SqlUtilities.closeWithNoException(stmt);
			SqlUtilities.closeWithNoException(rs);
		}
		return list;

	}

}
