package fai.orainvmgr.service;

import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import fai.orainvmgr.jsonbean.GetInventoryQuantitiesReq;
import fai.orainvmgr.jsonbean.GetInventoryQuantitiesRes;
import fai.orainvmgr.jsonbean.Link;


public class OraInvMgrService {

  static Logger logger = Logger.getLogger(OraInvMgrService.class);
  
  // private static final String METHOD_GET_INVENTORY_QUANTITIES = "/fscmRestApi/resources/version/availableQuantityDetails";
  private static final String METHOD_GET_INVENTORY_QUANTITIES = "/fscmRestApi/resources/11.13.18.05/availableQuantityDetails";
  
  private OraInvMgrHttpConnection anmhttp = null;
  private String lastSentJSON = null;
  
  private int lastHttpResponseCode = 0;
  
  
  private OraInvMgrService() {
  }
  
  
  



  public OraInvMgrService(String username, String password, String serviceUrl) {
    super();
    anmhttp = new OraInvMgrHttpConnection(username, password, serviceUrl);
  }





  
  
  public String getLastSentJSON() {
    return lastSentJSON;
  }

  
  
  public GetInventoryQuantitiesRes getInventoryQuantities(String organizationCode, String itemNumber) throws Exception {
    lastSentJSON = null;
    //
    GetInventoryQuantitiesReq req = new GetInventoryQuantitiesReq();
    req.setOrganizationCode(organizationCode);
    req.setItemNumber(itemNumber);
    //
    //
    Gson gson = new Gson();
    lastSentJSON = gson.toJson(req);
    //
    
    boolean success = anmhttp.sendData(METHOD_GET_INVENTORY_QUANTITIES, lastSentJSON);
    //
    GetInventoryQuantitiesRes response = null;
    lastHttpResponseCode = anmhttp.getResponseCode();
    if (success) {
      String lastReceivedJSON = anmhttp.getResponseAsText();
      gson = new Gson();       
      response = gson.fromJson(lastReceivedJSON, GetInventoryQuantitiesRes.class);      
    }
    else {
      String errorDescr = "HTTP POST failed. Error code = " + lastHttpResponseCode;
      logger.trace(errorDescr);
      throw new RuntimeException(errorDescr + " (url = " + anmhttp.getTargetUrl() + ")");        
    }
    //
    return response;
  }
  
  public static void main(String[] args) throws Exception {
    
    // ANMService ps = new ANMService("http://www.google.com/", "api-key", "prebooking", "it");
    // ps.sendIssuing(Arrays.asList(new Integer [] { 1234 }), "ticket-type", 10.99, "pippo@unexisting.null", "pippo", "de pippis");
    
    
  }
  
  
  
  

}
