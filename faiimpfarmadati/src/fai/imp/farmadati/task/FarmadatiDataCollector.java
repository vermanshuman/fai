package fai.imp.farmadati.task;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.datacontract.schemas._2004._07.FDIWebServices.GetDataSet_Output;
import org.datacontract.schemas._2004._07.FDIWebServices.GetEnabledDataSet_Output;
import org.datacontract.schemas._2004._07.FDIWebServices.GetSchemaDataSet_Output;
import org.datacontract.schemas._2004._07.FDIWebServices.SetDati;

import fai.common.db.SqlUtilities;
import fai.common.util.Filesystem;
import fai.common.util.XmlUtil;
import fai.imp.base.bean.ProcessedOrderBean;
import fai.imp.base.bean.ProductAvailibilityBean;
import fai.imp.base.bean.ProductBean;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.base.task.AbstractDataCollector;
import fai.imp.farmadati.db.FarmadatiDDL;
import fai.imp.farmadati.db.FarmadatiTable;
import fai.imp.farmadati.db.SqlQueries;
import fai.imp.farmadati.ws.FarmadatiSoapWS;
import fai.imp.farmadati.ws.FarmadatiSoapWSMethFormatter;
import it.swdes.decrypt.Decryptor;

public class FarmadatiDataCollector extends AbstractDataCollector {


	static Logger logger = Logger.getLogger(FarmadatiDataCollector.class);

	private GetEnabledDataSet_Output enabledDataSet = null;
	private GetSchemaDataSet_Output currSchemaDataSet = null;
	private GetDataSet_Output currDataSet = null;
	private FarmadatiSoapWS ws = null;
	private FarmadatiDDL farmadatiDDL = null;
	private boolean debugMode = false; 


	public FarmadatiDataCollector(FaiImportConfig config, Connection conn) {
		super(config, conn);
	}


	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}



	@Override
	protected String doCollectData_executeAll() throws Exception {
		String error = null;
		FarmadatiTable table = null;
		//
		try {
			logger.info("recupero dati GetEnabledDataSet ...");
			error = loadGetEnabledDataSet();
			if (error != null) return error;
			//
			Set<String> tablesToIgnore = SqlQueries.searchAllFarmadatiTableToIgnore(conn);
			//
			logger.info("recupero dati GetSchemaDataSet ...");
			SetDati [] setDati = enabledDataSet.getSetDatiAbilitati();
			for (int i = 0; i < setDati.length; i++) {
				for (int j = 0; j < 2; j++) {
					if (isDebugMode() == true) {
						logger.warn("**** ATTENZIONE! MODALITA' DEBUG ATTIVA!! QUESTO MESSAGGIO NON DEVE COMPARIRE NEI LOG DI PRODUZIONE!!! ***");
					}
					boolean schemaVar = j == 1;
					String key = setDati[i].getKey();
					logger.info(FarmadatiSoapWSMethFormatter.formatGetSchemaDataSet(key, schemaVar)+ ", "+setDati[i].getDescription()+" ...");
					//
					if (tablesToIgnore.contains(key)) {
						logger.info(key+" contrassegnata come da ignorare; nessuna ulteriore elaborazione avrà per oggetto questa tabella");
						continue;
					}
					//
					error = loadSchemaDataSet(key, schemaVar);
					if (error != null) return error;
					//
					if (schemaVar == true) continue;
					//
					farmadatiDDL.createTable(setDati[i], currSchemaDataSet);
					//
					logger.info("recupero dati GetDataSet ...");
					//
					logger.info(FarmadatiSoapWSMethFormatter.formatGetDataSet(key, FarmadatiSoapWS.GetDataSetModalita.DATA_AGG, 1)+" ...");
					error = loadGetDataSet(key, FarmadatiSoapWS.GetDataSetModalita.DATA_AGG, 1);
					if (error != null) return error;
					//
					logger.info(FarmadatiSoapWSMethFormatter.formatGetDataSet(key, FarmadatiSoapWS.GetDataSetModalita.COUNT, 1)+" ...");
					error = loadGetDataSet(key, FarmadatiSoapWS.GetDataSetModalita.COUNT, 1);
					if (error != null) return error;
					//
					logger.info(key+" "+currDataSet.getOutputValue()+" record attesi da inserire ...");
					int recordCount = Integer.parseInt(currDataSet.getOutputValue());
					//
					if (SqlQueries.getFarmadatiDdlAllDataLoadedTs(key, conn) == null) {
						table = new FarmadatiTable(setDati[i], currSchemaDataSet , conn);
						table.setUseBatchInsert(false);
						table.deleteAllRecords();
						conn.commit();
						//
						table.insertRecordsPrepare();
						int page = 0;
						boolean thereAreMoreRecords = false;
						do {
							page++;
							logger.info(FarmadatiSoapWSMethFormatter.formatGetDataSet(key, FarmadatiSoapWS.GetDataSetModalita.GETRECORDS, page)+" ...");
							error = loadGetDataSet(key, FarmadatiSoapWS.GetDataSetModalita.GETRECORDS, page);
							if (error != null) return error;
							//
							if ("EMPTY".equals(currDataSet.getOutputValue())) {
								thereAreMoreRecords = false;
							}
							else {
								InputStream zippedInputStream = new ByteArrayInputStream(currDataSet.getByteListFile());
								InputStream unzippedInputStream = Filesystem.getFirstEntryAsInputStream(zippedInputStream);
								FarmadatiContentHandler handler = new FarmadatiContentHandler(table);
								if (isDebugMode()) {
									handler.setIgnoreInsertAferMillis(15000L);
								}
								XmlUtil.doParse(unzippedInputStream, handler);
								unzippedInputStream.close();
								zippedInputStream.close();
							}
						} while (thereAreMoreRecords);
						table.insertRecordsCompleted();
						if (recordCount != table.getRecordCount()) {
							if (isDebugMode() == false) {
								String msg = key+" "+currDataSet.getOutputValue()+" record attesi da inserire; inseriti: "+table.getRecordCount();
								logger.error(msg);
								logger.error(key+" rollback ...");
								SqlUtilities.rollbackWithNoException(conn);
								logger.error(key+" rollback completata");
								throw new IllegalStateException(msg);
							}
						}
						SqlQueries.updateFarmadatiDdlAllDataLoadedTs(key, conn);
						logger.info(key+" commit ...");
						SqlUtilities.commitWithNoException(conn);
						logger.info(key+" commit completata");
					}
					else {
						logger.info(key+", record già inseriti ...");
					}
					//

				}

			}
			return error;
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "»";
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			if (table != null) table.insertRecordsCompleted();
		}
	}


	private String loadGetEnabledDataSet() throws Exception {
		String serviceID = FarmadatiSoapWSMethFormatter.formatGetEnabledDataSet();
		enabledDataSet = SqlQueries.searchGetEnabledDataSet(conn);
		if (enabledDataSet != null) { 
			logger.info(serviceID+" recuperato dalla banca dati");
			if ("OK".equals(enabledDataSet.getCodEsito()) == false) throw new IllegalStateException("inammissibile, codice esito "+enabledDataSet.getCodEsito()+" NON ammesso in questo punto");
			return null;
		}
		//
		logger.info("recupero dati "+serviceID+", invocazione WebService ...");
		enabledDataSet = ws.getEnabledDataSet();
		SqlQueries.insertGetEnabledDataSet(enabledDataSet, conn);
		conn.commit();
		if ("OK".equals(enabledDataSet.getCodEsito()) == false) {
			return (serviceID+" non riuscita ("+enabledDataSet.getCodEsito()+"), causa \""+enabledDataSet.getDescEsito()+"\"");
		}
		//
		return null;
	}

	private String loadSchemaDataSet(String key, boolean schemaVar) throws Exception {
		String serviceID = FarmadatiSoapWSMethFormatter.formatGetSchemaDataSet(key, schemaVar);
		currSchemaDataSet = SqlQueries.searchGetSchemaDataSet(key, schemaVar, conn);
		if (currSchemaDataSet != null) { 
			logger.info(serviceID+" recuperato dalla banca dati");
			if ("OK".equals(currSchemaDataSet.getCodEsito()) == false) throw new IllegalStateException("inammissibile, codice esito "+currSchemaDataSet.getCodEsito()+" NON ammesso in questo punto");
			return null;
		}
		//
		logger.info("recupero dati "+serviceID+", invocazione WebService ...");
		currSchemaDataSet = ws.getSchemaDataSet(key, schemaVar);
		SqlQueries.insertGetSchemaDataSet(currSchemaDataSet, key, schemaVar, conn);
		conn.commit();
		if ("OK".equals(currSchemaDataSet.getCodEsito()) == false) {
			return (serviceID+"non riuscita ("+currSchemaDataSet.getCodEsito()+"), causa \""+currSchemaDataSet.getDescEsito()+"\"");
		}
		//
		return null;
	}

	private String loadGetDataSet(String key, FarmadatiSoapWS.GetDataSetModalita modalita, int pag) throws Exception {
		String serviceID = FarmadatiSoapWSMethFormatter.formatGetDataSet(key, modalita, pag);
		currDataSet = SqlQueries.searchGetDataSet(key, modalita, pag, conn);
		if (currDataSet != null) { 
			logger.info(serviceID+" recuperato dalla banca dati");
			if ("OK".equals(currDataSet.getCodEsito()) == false) throw new IllegalStateException("inammissibile, codice esito "+currDataSet.getCodEsito()+" NON ammesso in questo punto");
			return null;
		}
		//
		logger.info("recupero dati "+serviceID+", invocazione WebService ...");
		currDataSet = ws.getDataSet(key, modalita, pag);
		SqlQueries.insertGetDataSet(currDataSet, key, modalita, pag, conn);
		conn.commit();
		if ("OK".equals(currDataSet.getCodEsito()) == false) {
			return (serviceID+"non riuscita ("+currDataSet.getCodEsito()+"), causa \""+currDataSet.getDescEsito()+"\"");
		}
		//
		return null;
	}




	@Override
	protected void doCollectData_prepare_specificSetup() throws Exception {
		//
		farmadatiDDL = new FarmadatiDDL(conn);
		//
		logger.info("creazione proxy Web Service ...");
		String login = config.getServiceLogin();
		if (login != null && login.trim().equals("")) login = null;
		String pass = config.getServicePass();
		if (pass != null) {
			if (pass.trim().equals("")) { 
				pass = null;
			}
			else {
				if (Boolean.TRUE.equals(config.isServicePassEncr())) {
					logger.info("decrittazione password del WebService ...");
					pass = Decryptor.decrypt(pass);
				}
			}
		}    
		ws =new FarmadatiSoapWS(login, pass);
		ws.setWsUrl(config.getServiceQueryUrl());
	}


	@Override
	protected void doCollectData_prepare_resumePrevSession() throws Exception {
		SqlQueries.deleteAllFailedFarmadatiWS(conn);
	}


	@Override
	protected void doCollectData_prepare_startNewSession() throws Exception {
		SqlQueries.deleteAllFarmadatiWS(conn);
		SqlQueries.setAllFarmadatiDdlAsNoLongerExists(conn);
	}

	@Override
	protected List<ProductBean> doCollectData_getAvailability(List<ProductBean> products) throws Exception {
		return null;
	}


	@Override
	protected void do_prepare_specificSetup() throws Exception {

	}


	@Override
	protected void doCollectData_getDDTList(Date dataInzio, Date dataFine) throws Exception {
	}


	@Override
	protected List<ProcessedOrderBean>  do_OrderProducts(List<ProductBean> productOrderRequests) throws Exception {
		return null;
		
	}
}
