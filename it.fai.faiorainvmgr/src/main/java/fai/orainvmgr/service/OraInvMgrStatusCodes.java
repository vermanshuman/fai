package fai.orainvmgr.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

class OraInvMgrStatusCodes {
  
  private static final List<Object[]> codesDetails = new ArrayList<Object[]>();
  private static final Hashtable<Integer, Object[]> codesDetailsByCode= new Hashtable<Integer, Object[]>();
  static {
    codesDetails.add(new Object [] { 200, false, "OK", "Success! A successful GET or POST method returns a 200 status code." });
    codesDetails.add(new Object [] { 201, false, "Created", "Your request is successful and a new resource has been created. Response includes a Location header containing the canonical URI for the newly created resource. You receive a 201 status code from: a) A synchronous resource creation, b) An asynchronous resource creation that completed before the response was returned" });
    codesDetails.add(new Object [] { 202, false, "Accepted", "The server has accepted your request for processing, but hasn't yet completed processing it. Your request might be accepted or rejected during processing. / This code is useful in asynchronous resource creation or updates, such as a batch-oriented process that runs only once per day. It indicates that the server has accepted your request, so you don't need to wait or be connected until the process completes. / The server delivers a response that contains a Location header of a job resource. Your client should poll this header to determine the job's completion state and status." });
    codesDetails.add(new Object [] { 400, true, "Bad Request", "Your request couldn't be processed because it contains missing or invalid information, such as a validation error on an input field, a missing required value, and so forth." });
    codesDetails.add(new Object [] { 401, true, "Unauthorized", "Your request isn't authorized. The authentication credentials included with this request are missing or invalid." });
    codesDetails.add(new Object [] { 403, true, "Forbidden", "You don't have authorization to perform this request. You may not have the necessary roles and privileges to use this REST resource. For more information, see Job Roles and Privileges." });
    codesDetails.add(new Object [] { 404, true, "Not Found", "Your request includes a resource URI that doesn't exist." });
    codesDetails.add(new Object [] { 405, true, "Method Not Allowed", "HTTP action specified in the request (DELETE, GET, POST, PUT) isn't supported for this request URI." });
    codesDetails.add(new Object [] { 406, true, "Not Acceptable", "Although the server understands and processes your request, your client can't understand the server response because it's unsupported. A client's request header indicates what data or media types are acceptable. For example, the client's Accept header requests that XML be returned, but the server can return only JSON." });
    codesDetails.add(new Object [] { 415, true, "Not Acceptable", "Indicates that the server refuses to accept the client request because the payload format is unsupported. In your client, the ContentType header isn't correct. For example, the client attempts to send the request in XML, but the server can accept only JSON." });
    codesDetails.add(new Object [] { 500, true, "Internal Server Error", "The server encountered something unexpected that prevented it from completing the request." });
    codesDetails.add(new Object [] { 501, true, "Not Implemented", "Indicates that the server doesn't support the method used in the request, and therefore it can't process the request." });
    codesDetails.add(new Object [] { 503, true, "Service Unavailable", "Indicates that the server is unable to complete the request due to a server overload. This condition is usually temporary." });
    codesDetails.add(new Object [] { 504, true, "Gateway Timeout", "The server acts as the gateway and doesn't receive a timely response from the upstream server to complete the request." });
    for (Object[] det : codesDetails) {
      codesDetailsByCode.put((Integer)det[0], det);
    }
  }
  
  private int code;
  
  public OraInvMgrStatusCodes() {
    super();
  }
  
  
  public OraInvMgrStatusCodes(int code) {
    super();
    this.code = code;
  }



  public OraInvMgrStatusCodes setCode(int code) {
    this.code = code;
    return this;
  }
  
  
  
  public int getCode() {
    return code;
  }


  private Object [] getDetails(int code) {
    Object [] o = codesDetailsByCode.get(code);
    if (o == null) {
      o = new Object [] { code, true, "unknow status code", "the status code "+code+" is not documented at https://docs.oracle.com/en/cloud/saas/supply-chain-management/21b/fasrp/Status_Codes.html"};
    }
    return o;
  }
  
  public boolean isError() {
    Object [] o = getDetails(code);
    return Boolean.TRUE.equals((Boolean)o[1]);
  }
  
  public String getDescription() {
    Object [] o = getDetails(code);
    return o[2].toString();
  }
  
  public String getDetails() {
    Object [] o = getDetails(code);
    return o[3].toString();
  }
  
  
  public static void main(String[] args) {

    int [] codeValue = new int [] { 200, 201, 400, 1400 };
    OraInvMgrStatusCodes code = new OraInvMgrStatusCodes();
    for (int i = 0; i < codeValue.length; i++) {
      System.out.println("---- "+codeValue[i]+" ----");
      code.setCode(codeValue[i]);
      System.out.println(code.isError() ? "error" : "success") ;
      System.out.println(code.getDescription()) ;
      System.out.println(code.getDetails());
    }
  }
  

}
