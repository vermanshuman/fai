package fai.imp.farmaclick.ws;

import java.util.List;

import org.apache.log4j.Logger;

import fai.common.util.ExceptionsTool;
import fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.DownloadListinoInputBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.DownloadListinoOutputBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001FCKDownloadListinoSoapBindingStub;
import fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineInputBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineOutputBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001FCKOrdineSoapBindingStub;

class FarmaclickWS_2010_001 extends AbstractFarmaclickWS {
  
  
  static Logger logger = Logger.getLogger(FarmaclickWS_2010_001.class);

  public DownloadListinoOutputBean callDownloadListino(String IDSessione, String codiceFornitore, String tipoRichiesta, String formatoOutput) {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001 binding = null;
    DownloadListinoOutputBean outBean = null;
    try {
      //
      // --- binding ---
      //
      fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001ServiceLocator locator = new fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001ServiceLocator();
      locator.setFarmaclick2010001FCKDownloadListinoEndpointAddress(endpointAddress);
      logger.info(LOG_PREFIX + "endpoint address: "+locator.getFarmaclick2010001FCKDownloadListinoAddress());
      binding = (fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001)locator.getFarmaclick2010001FCKDownloadListino();
      //
      // --- call ---
      //
      DownloadListinoInputBean inBean = new DownloadListinoInputBean(IDSessione, codiceFornitore, formatoOutput, tipoRichiesta);
      logRequestAsXml(LOG_PREFIX, inBean);
      outBean = binding.FCKDownloadListino(inBean);
      //
      // --- gestione risposta ---
      //
      logEsitoServizio(LOG_PREFIX, outBean);
      logResponseAsXml(LOG_PREFIX, outBean);
    }
    catch (javax.xml.rpc.ServiceException jre) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre));
        if(jre.getLinkedCause()!=null) {
          logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause()));
          logger.error(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause());
        }
        throw new IllegalStateException(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre), jre);
    }
    catch (Throwable th) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", th));
    }
    return outBean;
  }
  

  public OrdineOutputBean callOrdine(OrdineInputBean inBean) 
  {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001FCKOrdineSoapBindingStub binding = null;
    OrdineOutputBean outBean = null;
    try {
      //
      // --- binding ---
      //
      fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001ServiceLocator locator = new fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001ServiceLocator();
      
      locator.setFarmaclick2010001FCKOrdineEndpointAddress(endpointAddress);
      logger.info(LOG_PREFIX + "endpoint address: "+locator.getFarmaclick2010001FCKOrdineAddress());
      binding = (fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001FCKOrdineSoapBindingStub)locator.getFarmaclick2010001FCKOrdine();
      //
      // --- call ---
      //
      logRequestAsXml(LOG_PREFIX, inBean);
      outBean = binding.FCKOrdine(inBean);
      //
      // --- gestione risposta ---
      //
      logEsitoServizio(LOG_PREFIX, outBean);
      logResponseAsXml(LOG_PREFIX, outBean);
    }
    catch (javax.xml.rpc.ServiceException jre) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre));
        if(jre.getLinkedCause()!=null) {
          logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause()));
          logger.error(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause());
        }
        throw new IllegalStateException(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre), jre);
    }
    catch (Throwable th) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", th));
    }
    return outBean;
  }  


}
