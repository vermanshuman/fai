package fai.imp.comifar.task;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;

import fai.common.db.SqlUtilities;
import fai.common.util.XmlUtil;
import fai.imp.base.bean.ProcessedOrderBean;
import fai.imp.base.bean.ProductAvailibilityBean;
import fai.imp.base.bean.ProductOrderRequestBean;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.base.task.AbstractDataCollector;
import fai.imp.comifar.dto.Disponibilita;
import fai.imp.comifar.dto.Head;
import fai.imp.comifar.dto.Listino;
import fai.imp.comifar.dto.MyXML;
import fai.imp.comifar.dto.Order;
import fai.imp.comifar.dto.OrderBody;
import fai.imp.comifar.dto.OrderItem;
import fai.imp.comifar.dto.OrderResponse;
import fai.imp.comifar.dto.OrderResponseBody;
import fai.imp.comifar.dto.ProductData;
import fai.imp.comifar.ws.ComifarSoapWS;
import fai.imp.comifar.ws.ComifarSoapWSMethFormatter;
import fai.imp.farmadati.db.ComifarTable;
import fai.imp.farmadati.db.SqlQueries;
import it.swdes.decrypt.Decryptor;

public class ComifarDataCollector extends AbstractDataCollector{
	static Logger logger = Logger.getLogger(ComifarDataCollector.class);
	private String ddtList = null;
	private String productList = null;
	private ComifarSoapWS ws = null;
	private String client = null;
	private String primeLevelPassword = null;
	private String secondLevelPassword = null;

	public ComifarDataCollector(FaiImportConfig config, Connection conn) {
		super(config, conn);
	}

	private String loadGetDataSet() throws Exception {
		String serviceID = ComifarSoapWSMethFormatter.formatProductList();
		productList = SqlQueries.searchGetDataSet(conn);
		if (productList != null) { 
			logger.info(serviceID+" recuperato dalla banca dati");
			Listino listino = parseComifarListino(productList);
			if (!listino.getInfo().getOutcome().contains("OK.")) throw new IllegalStateException("inammissibile, codice esito "+listino.getInfo().getOutcome()+" NON ammesso in questo punto");
			ws.getProductList(listino.getInfo().getProgressive()-1);
		}

		return null;
	}

	private static Listino parseComifarListino(String productList) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(Listino.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		org.w3c.dom.Document xmlDoc = XmlUtil.asDocument(productList);
		return  (Listino) unmarshaller.unmarshal(xmlDoc);
	}
	
	private static Disponibilita parseComifarDisponibilita(String disponibilitaResponse) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(Disponibilita.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		org.w3c.dom.Document xmlDoc = XmlUtil.asDocument(disponibilitaResponse);
		return  (Disponibilita) unmarshaller.unmarshal(xmlDoc);
	}
	
	private static OrderResponse parseComifarOrdine(String orderResponse) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(OrderResponse.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		org.w3c.dom.Document xmlDoc = XmlUtil.asDocument(orderResponse);
		return  (OrderResponse) unmarshaller.unmarshal(xmlDoc);
	}

	@Override
	protected String doCollectData_executeAll() throws Exception {
		String error = null; 
		try {
			logger.info("recupero dati get list ...");
			error = loadGetDataSet();
			if (error != null) return error;
			String queryType = null;
			if ("A".equals(sessionQueryType)) {
				queryType = "AGG";
			}else {
				queryType = "RESET";
			}
			error = loadListino(queryType);
			if (error != null) return error;
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "»";
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		return null;
	}

	@Override
	protected void doCollectData_prepare_specificSetup() throws Exception {
		String queryType = null;
		if ("A".equals(sessionQueryType)) {
			queryType = "AGG";
		}else {
			ComifarTable table  = new ComifarTable(conn,config.getServiceLogin());
			table.deleteAllRecords("FAI_COMIFAR_LISTINO_PRICE");
			table.deleteAllRecords("FAI_COMIFAR_LISTINO");
			SqlQueries.deleteAllComifarWS(conn);

			conn.commit();
			queryType = "RESET";
		}

		if(queryType.equals("RESET")){
			ws.getProductList();
			ws.setSessionQueryType("AGG");
			sessionQueryType = "A";
		}
	}

	@Override
	protected void doCollectData_prepare_resumePrevSession() throws Exception {
		SqlQueries.deleteAllFailedComifarWS(conn);

	}

	@Override
	protected void doCollectData_prepare_startNewSession() throws Exception {
		SqlQueries.deleteAllComifarWS(conn);
	}

	private String loadGetDDTList() throws Exception {
		String serviceID = ComifarSoapWSMethFormatter.formatGetDDTList();
		logger.info("recupero dati "+serviceID+", invocazione WebService ...");
		ddtList = ws.getDDTList();
		logger.debug("DDTList Resposne : {}" + ddtList);
		if(ddtList != null){
			org.w3c.dom.Document xmlDoc = XmlUtil.asDocument(ddtList);
			XPath xPath = XPathFactory.newInstance().newXPath();

			org.w3c.dom.NodeList nodes = (org.w3c.dom.NodeList)xPath.evaluate("risposta_ddtlist/esito/sigla",
					xmlDoc, XPathConstants.NODESET);

			String status = "";

			if(nodes.getLength() > 0){
				status = nodes.item(0).getTextContent();
			}
			if (!"OK".equalsIgnoreCase(status)) throw new IllegalStateException("inammissibile, codice esito "+status+" NON ammesso in questo punto");


		}
		return "";
	}
	private String loadListino_del(String queryType) throws Exception {
		try {
			Scanner scanner = new Scanner( new File("E:/encoded.txt") );
			String text = scanner.useDelimiter("\\A").next();
			scanner.close(); 
			org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
			if(text != null){
				byte[] decoded = base64.decode(text);
				InputStream zippedInputStream = new ByteArrayInputStream(decoded);
				GZIPInputStream gZIPInputStream = new GZIPInputStream(zippedInputStream);
				StringBuilder textBuilder = new StringBuilder();
				try (Reader reader = new BufferedReader(new InputStreamReader
						(gZIPInputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
					int c = 0;
					while ((c = reader.read()) != -1) {
						textBuilder.append((char) c);
					}
				}
				System.out.println(">>>>>>>>>>> " + textBuilder);
				if(textBuilder != null){
					int recordCount = 0;
					ComifarTable table = new ComifarTable(conn, config.getServiceLogin());
					table.deleteAllRecords("FAI_COMIFAR_LISTINO_PRICE");
					table.deleteAllRecords("FAI_COMIFAR_LISTINO");
					table.setUseBatchInsert(Boolean.TRUE);
					table.insertRecordsPrepare();
					String xmlString = "<comifar>" + textBuilder.toString() + "</comifar>";
					JAXBContext jaxbContext = JAXBContext.newInstance(ProductData.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					ProductData productData = (ProductData) jaxbUnmarshaller.unmarshal(
							new ByteArrayInputStream(xmlString.getBytes()));	
					if(productData != null && productData.getArt() != null && !productData.getArt().isEmpty()){
						recordCount = productData.getArt().size();
						productData
						.getArt()
						.stream()
						.forEach(art -> {
							try {
								table.insertRecord(art);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
					}
					table.insertRecordsCompleted();
					if (recordCount != table.getRecordCount()) {
						String msg = "record attesi da inserire:" + recordCount + ";inseriti: "+table.getRecordCount();
						logger.error(msg);
						logger.error(" rollback ...");
						SqlUtilities.rollbackWithNoException(conn);
						logger.error(" rollback completata");
						throw new IllegalStateException(msg);
					}

					if(productData != null && productData.getArt() != null && !productData.getArt().isEmpty()){
						table.insertPriceRecordsPrepare();
						table.insertPriceRecords(productData.getArt());
						table.insertPriceRecordsCompleted();
					}
					logger.info(" Comifar commit ...");
					SqlUtilities.commitWithNoException(conn);
					logger.info(" Comifar commit completata");
				}
				gZIPInputStream.close();
				zippedInputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	private String loadListino(String queryType) throws Exception {
		String serviceID = ComifarSoapWSMethFormatter.formatProductList();
		logger.info("recupero dati "+serviceID+", invocazione WebService ...");
		ComifarTable table = new ComifarTable(conn, config.getServiceLogin());
		table.setUseBatchInsert(Boolean.TRUE);

		boolean thereAreMoreRecords = true;
		do {
			table.insertRecordsPrepare();
			productList = ws.getProductList();


			logger.info("Listino Resposne : {}" + productList);
			if(productList != null){
				Listino listino = parseComifarListino(productList);
				SqlQueries.insertGetProductList(productList, listino, conn);
				conn.commit();
				String actualResponse = "";
				String successResponse = "OK." + queryType;
				if(listino != null && listino.getInfo() != null && listino.getInfo().getOutcome() != null){
					actualResponse = listino.getInfo().getOutcome();
				}

				if(listino != null && listino.getInfo() != null && listino.getInfo().getContinueStatus() != null && 
						listino.getInfo().getContinueStatus().equalsIgnoreCase("N")){
					thereAreMoreRecords = false;
				}
				if ( !successResponse.equalsIgnoreCase(actualResponse)) throw new IllegalStateException("inammissibile, codice esito "+actualResponse+" NON ammesso in questo punto");

				try {
					org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
					if(listino.getEncodedData() != null){
						byte[] decoded = base64.decode(listino.getEncodedData());
						InputStream zippedInputStream = new ByteArrayInputStream(decoded);
						GZIPInputStream gZIPInputStream = new GZIPInputStream(zippedInputStream);
						StringBuilder textBuilder = new StringBuilder();
						try (Reader reader = new BufferedReader(new InputStreamReader
								(gZIPInputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
							int c = 0;
							while ((c = reader.read()) != -1) {
								textBuilder.append((char) c);
							}
						}
						if(textBuilder != null){
							int recordCount = 0;

							String xmlString = "<comifar>" + textBuilder.toString() + "</comifar>";
							JAXBContext jaxbContext = JAXBContext.newInstance(ProductData.class);
							Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
							ProductData productData = (ProductData) jaxbUnmarshaller.unmarshal(
									new ByteArrayInputStream(xmlString.getBytes()));	
							if(productData != null && productData.getArt() != null && !productData.getArt().isEmpty()){
								recordCount = productData.getArt().size();
								productData
								.getArt()
								.stream()
								.forEach(art -> {
									try {
										table.insertRecord(art);
									} catch (Exception e) {
										e.printStackTrace();
									}
								});
							}
							table.insertRecordsCompleted();
							if (recordCount != table.getRecordCount()) {
								String msg = "record attesi da inserire; inseriti: "+table.getRecordCount();
								logger.error(msg);
								logger.error(" rollback ...");
								SqlUtilities.rollbackWithNoException(conn);
								logger.error(" rollback completata");
								throw new IllegalStateException(msg);
							}
							if(productData != null && productData.getArt() != null && !productData.getArt().isEmpty()){
								table.insertPriceRecordsPrepare();
								table.insertPriceRecords(productData.getArt());
								table.insertPriceRecordsCompleted();
							}

							logger.info(" Comifar commit ...");
							SqlUtilities.commitWithNoException(conn);
							logger.info(" Comifar commit completata");
						}
						gZIPInputStream.close();
						zippedInputStream.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} while (thereAreMoreRecords);

		return "";
	}

	@Override
	protected List<ProductAvailibilityBean> doCollectData_getAvailability(List<String> productCodes) throws Exception {
		
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		List<ProductAvailibilityBean> productAvailibilityBeans = new ArrayList<>();
		String serviceID = ComifarSoapWSMethFormatter.formatGetAvailibility();
		logger.info("recupero dati "+serviceID+", invocazione WebService ...");

		for(String productCode : productCodes) {
			String availibility = ws.getAvailibility();
			logger.info("Disponibilit\u00E0 Resposne : {}" + availibility);
			if(availibility != null){
				Disponibilita disponibilita = parseComifarDisponibilita(availibility);
				String actualResponse = "";
				if(disponibilita != null && disponibilita.getOutcome() != null){
					actualResponse = disponibilita.getOutcome().getOutcome();
				}
				if (!"OK".equalsIgnoreCase(actualResponse)) throw new IllegalStateException("inammissibile, codice esito "+actualResponse+" NON ammesso in questo punto");
				ProductAvailibilityBean productAvailibilityBean = new ProductAvailibilityBean();
				productAvailibilityBean.setProductCode(productCode);
				if(disponibilita.getAvail() != null && disponibilita.getAvail().getAvailable() != null 
						&& disponibilita.getAvail().getAvailable().equalsIgnoreCase("S")) {
					productAvailibilityBean.setAvailibility(Boolean.TRUE);
				}else {
					productAvailibilityBean.setAvailibility(Boolean.FALSE);
				}
				productAvailibilityBeans.add(productAvailibilityBean);
			}
		}
		
		return productAvailibilityBeans;
	}
	
	@Override
	protected void doCollectData_getDDTList(Date dataInzio, Date dataFine) throws Exception {
	}

	@Override
	protected List<ProcessedOrderBean> do_OrderProducts(List<ProductOrderRequestBean> productOrderRequests) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		String serviceID = ComifarSoapWSMethFormatter.formatOrderProduct();
		logger.info("recupero dati "+serviceID+", invocazione WebService ...");

		List<ProcessedOrderBean> processedOrders = new ArrayList<>();
		if(productOrderRequests != null && !productOrderRequests.isEmpty()) {
			
			List<OrderItem> items = productOrderRequests
			.stream()
			.filter(p -> p.getProductCode() != null && !p.getProductCode().isEmpty() 
			&& p.getQuantity() != null && p.getQuantity() > 0)
			.map(p -> new OrderItem(p.getProductCode(), p.getQuantity()))
			.collect(Collectors.toList());
			if(items.size() > 0) {
				MyXML myxml = new MyXML();
				Order order = new Order();
				Head head = new Head();
				head.setCliente(getClient());
				head.setNumord("miotest00001");
				order.setHead(head);
				
				OrderBody body = new OrderBody();
				body.setItems(items);
				order.setBody(body);
				
				myxml.setOrder(order);
				
				String orderXML = prepareOrderXML(myxml);

				String orderResponse = ws.orderProducts(orderXML);
				
				OrderResponse response = parseComifarOrdine(orderResponse);
				
				if(response != null && response.getHead() != null && response.getHead().getEsito() != null &&
						response.getHead().getEsito().getOutcome() != null && "OK".equalsIgnoreCase(response.getHead().getEsito().getOutcome())) {
					
					OrderResponseBody orderResponseBody = response.getBody();
					if(orderResponseBody != null) {
						if(orderResponseBody.getOrderMissing() != null && orderResponseBody.getOrderMissing().getItems() != null
								&& orderResponseBody.getOrderMissing().getItems().size() > 0) {
							processedOrders.addAll(orderResponseBody.getOrderMissing().getItems()
							.stream()
							.map(item -> 
							new ProcessedOrderBean(item.getProductCode(), item.getQuantity(),
									Boolean.TRUE, item.getOutcome(), item.getOutcomeDescription()))
							.collect(Collectors.toList()));
							
						}
						
						if(orderResponseBody.getProcessedOrder() != null && orderResponseBody.getProcessedOrder().getItems() != null
								&& orderResponseBody.getProcessedOrder().getItems().size() > 0) {
							processedOrders.addAll(orderResponseBody.getProcessedOrder().getItems()
							.stream()
							.map(item -> 
							new ProcessedOrderBean(item.getProductCode(), item.getQuantity(), Boolean.FALSE))
							.collect(Collectors.toList()));
						}
					}
				}
				
			}
		}

		return processedOrders;
	}
	
	private static String prepareOrderXML(MyXML myXML) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(MyXML.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		StringWriter sw = new StringWriter();
		marshaller.marshal(myXML, sw);
		return sw.toString();
	}

	@Override
	protected void do_prepare_specificSetup() throws Exception {
		logger.info("creazione proxy Web Service ...");
		String client = config.getServiceLogin();
		if (client != null && client.trim().equals("")) client = null;
		setClient(client);
		String primeLevelPassword = config.getServicePass();
		String secondLevelPassword = config.getServicePassSecondLevel();
		if (primeLevelPassword != null) {
			if (primeLevelPassword.trim().equals("")) { 
				primeLevelPassword = null;
			}
			else {
				if (Boolean.TRUE.equals(config.isServicePassEncr())) {
					logger.info("decrittazione primo password del WebService ...");
					primeLevelPassword = Decryptor.decrypt(primeLevelPassword);
				}
			}
		}
		if (secondLevelPassword != null) {
			if (secondLevelPassword.trim().equals("")) { 
				secondLevelPassword = null;
			}
			else {
				if (Boolean.TRUE.equals(config.isServicePassEncr())) {
					logger.info("decrittazione secondo password del WebService ...");
					secondLevelPassword = Decryptor.decrypt(secondLevelPassword);
				}
			}
		}
		setPrimeLevelPassword(primeLevelPassword);
		setSecondLevelPassword(secondLevelPassword);
		
		String queryType = null;
		if ("A".equals(sessionQueryType)) {
			queryType = "AGG";
		}else {
			queryType = "RESET";
		}
		
		ws = new ComifarSoapWS(getClient(), "", "01/08/2021", "21/08/2021", getPrimeLevelPassword(), getSecondLevelPassword(), getProductCodes() != null ? getProductCodes().get(0) : null, queryType);
		ws.setWsUrl(config.getServiceQueryUrl());
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPrimeLevelPassword() {
		return primeLevelPassword;
	}

	public void setPrimeLevelPassword(String primeLevelPassword) {
		this.primeLevelPassword = primeLevelPassword;
	}

	public String getSecondLevelPassword() {
		return secondLevelPassword;
	}

	public void setSecondLevelPassword(String secondLevelPassword) {
		this.secondLevelPassword = secondLevelPassword;
	}
}

