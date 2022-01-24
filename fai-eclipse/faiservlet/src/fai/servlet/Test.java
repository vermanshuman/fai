package fai.servlet;

import java.net.*;

import com.sun.org.apache.xpath.internal.operations.Bool;

import fai.models.FaiFarmaco;
import it.swdes.http.*;
import it.swdes.models.*;

public class Test {

  
  public static void main(String[] args) {
    try {
      AbstractModelResponse response = null;
      AbstractModelResult result = null;
      AbstractModelRequest amr = new AbstractModelRequest();
      
      Requestor requestor = new Requestor();
      requestor.getHttpConnection().setUrl(new java.net.URL("http://localhost:8075/faiservlet"));
      requestor.getHttpConnection().setTimeout(2000000000);

      
      FaiFarmaco f = new FaiFarmaco();
      //f.setOid(new Long(20515));
     f.setCodMinsan("11");
    //  f.setCodEan("22");
    //  f.setDescrProd("a");
    //  f.setDescrTipoProd("b");
    //  f.setDescrEstesoTipoProd("c");
    //  f.setAnagrafica("d");
    //  f.setIsDeleted(Boolean.FALSE);
 
   //   amr.setName("insert");        
      amr.setName("search");    
 //     amr.setName("hardDelete");

      FaiFarmaco f_new = new FaiFarmaco();
      //f_new.setOid(new Long(20515));
      f_new.setCodMinsan("11");
    //  f_new.setCodEan("22");
      f_new.setDescrProd("e");
      f_new.setDescrTipoProd("f");
      f_new.setDescrEstesoTipoProd("g");
      f_new.setAnagrafica("h");
      f_new.setIsDeleted(Boolean.TRUE);
       
     //amr.setName("update");     
      
      //amr.setName("search");    
     amr.addElement(f);
     //amr.setNewAbstractModel(f_new);
     //amr.setOldAbstractModel(f);   
     System.out.println(amr.toJson());
     
      response = requestor.sendSyncRequest(amr);
      
     
      result = response.getResult();
      
      System.out.println(result.getCode());
      System.out.println(response.toJson());
      

    }
    catch (Throwable e) {
      e.printStackTrace();
    }
  }

}
